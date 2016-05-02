package com.yangyongwen.zhihu3.di.modules;

import android.content.Context;

import com.yangyongwen.zhihu3.ZhihuApp;
import com.yangyongwen.zhihu3.executor.JobExecutor;

import java.util.concurrent.Executor;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by yangyongwen on 16/5/2.
 */

@Module
public class ApplicationModule {

    private final ZhihuApp application;

    public ApplicationModule(ZhihuApp app){
        this.application=app;
    }

    @Provides
    @Singleton
    Context provideApplicationContext() {
        return this.application;
    }

    @Provides
    @Singleton
    Executor provideThreadExecutor(JobExecutor jobExecutor) {
        return jobExecutor;
    }




}
