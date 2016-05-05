package com.yangyongwen.zhihu3.di.modules;

import com.yangyongwen.zhihu3.di.ActivityScope;
import com.yangyongwen.zhihu3.model.HomePageModel;
import com.yangyongwen.zhihu3.presenter.HomePagePresenter;
import com.yangyongwen.zhihu3.view.activity.HomePageActivity;

import dagger.Module;
import dagger.Provides;

/**
 * Created by yangyongwen on 16/5/5.
 */

@Module
public class HomePageActivityModule {

    private HomePageActivity homePageActivity;

    public HomePageActivityModule(HomePageActivity homePageActivity) {
        this.homePageActivity = homePageActivity;
    }

    @Provides
    @ActivityScope
    HomePageActivity provideHomePageActivity() {
        return homePageActivity;
    }

    @Provides
    @ActivityScope
    HomePagePresenter provideHomePageActivityPresenter() {
        return new HomePagePresenter();
    }

    @Provides
    @ActivityScope
    HomePageModel provideHomePageModel(){
        return new HomePageModel();
    }

}
