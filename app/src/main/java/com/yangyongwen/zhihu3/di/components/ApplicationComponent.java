package com.yangyongwen.zhihu3.di.components;

import com.yangyongwen.zhihu3.WelcomeActivity;
import com.yangyongwen.zhihu3.di.modules.DaoDbModule;
import com.yangyongwen.zhihu3.di.modules.HomePageFragmentModule;
import com.yangyongwen.zhihu3.di.modules.ApplicationModule;
import com.yangyongwen.zhihu3.di.modules.NetworkModule;
import com.yangyongwen.zhihu3.view.activity.HomePageActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by yangyongwen on 16/5/2.
 */


@Singleton
@Component(modules = {ApplicationModule.class, NetworkModule.class, DaoDbModule.class})
public interface ApplicationComponent {


    void inject(WelcomeActivity baseActivity);
    void inject(HomePageActivity homePageActivity);

    HomePageFragmentComponent plus(HomePageFragmentModule homePageFragmentModule);

}
