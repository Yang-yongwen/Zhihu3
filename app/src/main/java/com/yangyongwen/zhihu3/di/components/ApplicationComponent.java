package com.yangyongwen.zhihu3.di.components;

import android.content.Context;

import com.google.gson.Gson;
import com.yangyongwen.zhihu3.WelcomeActivity;
import com.yangyongwen.zhihu3.ZhihuApi.ZhihuApi;
import com.yangyongwen.zhihu3.di.modules.ApplicationModule;
import com.yangyongwen.zhihu3.di.modules.NetworkModule;

import java.util.concurrent.Executor;

import javax.inject.Singleton;

import dagger.Component;
import retrofit2.Retrofit;

/**
 * Created by yangyongwen on 16/5/2.
 */


@Singleton
@Component(modules = {ApplicationModule.class, NetworkModule.class})
public interface ApplicationComponent {


    void inject(WelcomeActivity baseActivity);

    Context context();
    Executor threadExecutor();

    ZhihuApi zhihuApi();
    Retrofit retrofit();
    Gson gson();

}
