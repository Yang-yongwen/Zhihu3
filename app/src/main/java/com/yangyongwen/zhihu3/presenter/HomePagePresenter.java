package com.yangyongwen.zhihu3.presenter;

import android.os.Bundle;

import com.yangyongwen.zhihu3.dao.Story;
import com.yangyongwen.zhihu3.datastructure.LatestStories;
import com.yangyongwen.zhihu3.di.ActivityScope;
import com.yangyongwen.zhihu3.di.FragmentScope;
import com.yangyongwen.zhihu3.model.HomePageModel;
import com.yangyongwen.zhihu3.view.UserActionEnum;
import com.yangyongwen.zhihu3.view.activity.HomePageActivity;
import com.yangyongwen.zhihu3.view.fragment.HomePageFragment;

import javax.inject.Inject;

import rx.Observable;
import rx.Observer;

/**
 * Created by yangyongwen on 16/5/5.
 */
@FragmentScope
public class HomePagePresenter implements Presenter{

    HomePageFragment homePageFragment;
    HomePageModel homePageModel;

    @Override
    public void onUserAction(UserActionEnum userActionEnum, Bundle args){
        Story story;
        Observable<LatestStories> observable=homePageModel.updateLatest();
        
        Observer<LatestStories> observer=new Observer<LatestStories>() {
            @Override
            public void onCompleted() {
                int i;
                i=0;
                i++;
            }

            @Override
            public void onError(Throwable e) {
                int i;
                i=0;
                i++;

            }

            @Override
            public void onNext(LatestStories stories) {
                LatestStories s1=stories;
            }
        };

        observable.subscribe(observer);

        int i=0;
        i++;

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
