package com.yangyongwen.zhihu3.di.components;

import com.yangyongwen.zhihu3.di.ActivityScope;
import com.yangyongwen.zhihu3.di.modules.HomePageActivityModule;
import com.yangyongwen.zhihu3.view.activity.HomePageActivity;

import dagger.Subcomponent;

/**
 * Created by yangyongwen on 16/5/5.
 */


@ActivityScope
@Subcomponent(modules = HomePageActivityModule.class)
public interface HomePageActivityComponent {
    HomePageActivity inject(HomePageActivity homePageActivity);
}


