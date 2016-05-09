package com.yangyongwen.zhihu3.presenter;

import android.os.Bundle;

import com.yangyongwen.zhihu3.dao.Story;
import com.yangyongwen.zhihu3.dao.TopStory;
import com.yangyongwen.zhihu3.datastructure.LatestStories;
import com.yangyongwen.zhihu3.di.ActivityScope;
import com.yangyongwen.zhihu3.di.FragmentScope;
import com.yangyongwen.zhihu3.model.HomePageModel;
import com.yangyongwen.zhihu3.utils.DateUtils;
import com.yangyongwen.zhihu3.utils.SharePreferenceUtils;
import com.yangyongwen.zhihu3.view.UserActionEnum;
import com.yangyongwen.zhihu3.view.activity.HomePageActivity;
import com.yangyongwen.zhihu3.view.fragment.HomePageFragment;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.Observer;
import rx.functions.Action1;
import rx.functions.Func3;

/**
 * Created by yangyongwen on 16/5/5.
 */
@FragmentScope
public class HomePagePresenter implements Presenter{

    HomePageFragment homePageFragment;
    HomePageModel homePageModel;

    @Inject
    SharePreferenceUtils sharePreferenceUtils;

    @Override
    public void onUserAction(UserActionEnum userActionEnum, Bundle args){
        int action=userActionEnum.getId();

        if(action== HomePageFragment.HomePageActionEnum.LOADDAILY.getId()){
            String date=args.getString("date");
            loadDailyStories(date);
        }else if(action== HomePageFragment.HomePageActionEnum.INIT.getId()){
            initHomePage();
        }else if(action== HomePageFragment.HomePageActionEnum.UPDATELATEST.getId()){
            updateLatest();
        }
    }

    private void initHomePage(){
        String today=homePageModel.getToday();
        String yesterday= DateUtils.yesterday(today);

        Observer<Object> observer=new Observer<Object>() {
            @Override
            public void onCompleted() {
                homePageFragment.stopLoading();
            }
            @Override
            public void onError(Throwable e) {
                homePageFragment.stopLoading();
            }

            @Override
            public void onNext(Object o) {}
        };


        Observable.zip(homePageModel.queryTopStories(),
                homePageModel.queryDailyStories(today),
                homePageModel.queryDailyStories(yesterday),
                new Func3<TopStory[], Story[], Story[], Object>() {
                    @Override
                    public Object call(TopStory[] topStories, Story[] today,Story[] yesterday) {
                        homePageFragment.setTopStories(topStories);
                        homePageFragment.addDailyStories(today);
                        homePageFragment.addDailyStories(yesterday);
                        return null;
                    }
                })
                .subscribe(observer);


    }


    private void loadDailyStories(String date){
        Observer<Story[]> observer=new Observer<Story[]>() {
            @Override
            public void onCompleted() {
                homePageFragment.stopLoading();
            }
            @Override
            public void onError(Throwable e) {
                homePageFragment.stopLoading();
            }

            @Override
            public void onNext(Story[] stories) {
                homePageFragment.addDailyStories(stories);
            }
        };
        homePageModel.queryDailyStories(date)
                .subscribe(observer);
    }


    private void updateLatest(){
        Observer<LatestStories> observer=new Observer<LatestStories>() {
            @Override
            public void onCompleted() {
                homePageFragment.stopLoading();
            }
            @Override
            public void onError(Throwable e) {
                homePageFragment.stopLoading();
            }

            @Override
            public void onNext(LatestStories stories) {
                homePageFragment.homePageAdapter.clearAll();
                homePageFragment.setTopStories(stories.getTopStories());
                homePageFragment.addDailyStories(stories.getStories());
            }
        };
        homePageModel.updateLatest()
                .subscribe(observer);
    }



    @Override
    public void onResume(){

    }

    @Override
    public void onPause(){

    }

    @Override
    public void onDestroy(){

    }

    @Inject
    public HomePagePresenter(HomePageFragment homePageFragment,HomePageModel homePageModel){
        this.homePageFragment=homePageFragment;
        this.homePageModel=homePageModel;
    }


}
