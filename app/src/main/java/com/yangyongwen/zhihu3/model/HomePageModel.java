package com.yangyongwen.zhihu3.model;

import android.os.Bundle;

import com.yangyongwen.zhihu3.ZhihuApp;
import com.yangyongwen.zhihu3.dao.DaoSession;
import com.yangyongwen.zhihu3.dao.Image;
import com.yangyongwen.zhihu3.dao.ImageDao;
import com.yangyongwen.zhihu3.dao.Story;
import com.yangyongwen.zhihu3.dao.StoryDao;
import com.yangyongwen.zhihu3.dao.TopStory;
import com.yangyongwen.zhihu3.dao.TopStoryDao;
import com.yangyongwen.zhihu3.datastructure.DailyStory;
import com.yangyongwen.zhihu3.datastructure.LatestStories;
import com.yangyongwen.zhihu3.di.FragmentScope;
import com.yangyongwen.zhihu3.utils.LogUtils;
import com.yangyongwen.zhihu3.zhihuapi.ZhihuApi;

import java.util.List;

import javax.inject.Inject;

import dagger.Module;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by yangyongwen on 16/5/5.
 */

@FragmentScope
public class HomePageModel implements Model {



    //component 沒有inject 該對象，所以這裏是無法注入的，model 也應該在fragment中進行注入（通過fragment的構造函數注入），然後set 到presenter中去 參考clean project


    private StoryDao storyDao;
    private ZhihuApi zhihuApi;
    private ImageDao imageDao;
    private DaoSession daoSession;
    private TopStoryDao topStoryDao;


    private static final String TAG= LogUtils.makeLogTag(HomePageModel.class);

    public static final String DATE="date";


    @Inject
    public HomePageModel(ZhihuApi zhihuApi,DaoSession daoSession){
        this.zhihuApi=zhihuApi;
        this.daoSession=daoSession;
        this.storyDao=daoSession.getStoryDao();
        this.imageDao=daoSession.getImageDao();
        this.topStoryDao=daoSession.getTopStoryDao();
    }


    public Observable<Story[]> queryDailyStories(final String date){

        return Observable.just(date)
                .flatMap(new Func1<String, Observable<Story[]>>() {
                    @Override
                    public Observable<Story[]> call(String date) {
                        List<Story> storyList=storyDao.queryBuilder()
                                .where(StoryDao.Properties.Date.eq(date))
                                .list();
                        Story[] stories=storyList.toArray(new Story[storyList.size()]);
                        List<Image> images;
                        if(stories!=null&&stories.length!=0){
                             images=stories[0].getImages();
                        }
                        return stories!=null&&stories.length!=0
                                ?Observable.just(stories)
                                :Observable.<Story[]>error(new NullPointerException("Stories data not in local Db"));
                    }
                })
                .subscribeOn(Schedulers.io())
                .retryWhen(new Func1<Observable<? extends Throwable>, Observable<?>>() {
                    @Override
                    public Observable<?> call(Observable<? extends Throwable> observable) {
                        return observable.flatMap(new Func1<Throwable, Observable<?>>() {
                            @Override
                            public Observable<?> call(Throwable throwable) {
                                if (throwable instanceof IllegalArgumentException || throwable instanceof NullPointerException) {
                                    return zhihuApi.dailyStory(date)
                                            .doOnNext(new Action1<DailyStory>() {
                                                @Override
                                                public void call(DailyStory dailyStory) {
                                                    Story[] stories=dailyStory.getStories();
                                                    for(Story story:stories){
                                                        insertStory(date,story);
                                                    }
                                                }
                                            });
                                }
                                return Observable.error(throwable);
                            }
                        });
                    }
                })
                .observeOn(AndroidSchedulers.mainThread());

    }


    public Observable<LatestStories> updateLatest(){
        return zhihuApi.latestStory()
                .doOnNext(new Action1<LatestStories>() {
                    @Override
                    public void call(LatestStories latestStories) {
                        Story[] stories=latestStories.getStories();
                        TopStory[] topStories=latestStories.getTopStories();
                        String date=latestStories.getDate();
                        for (Story story:stories){
                            insertStory(date,story);
                        }
                        topStoryDao.deleteAll();
                        for (TopStory topStory:topStories){
                            topStory.setDate(date);
                            topStory.setReaded(false);
                            topStoryDao.insertOrReplace(topStory);
                        }
                    }
                })
                .subscribeOn(Schedulers.io());
    }


    private void insertStory(String date,Story story){
        for(Image image:story.getImages()){
            image.setId(story.getId());
            imageDao.insertOrReplace(image);
        }
        story.setDate(date);
        story.setReaded(false);
        storyDao.insertOrReplace(story);
    }






}
