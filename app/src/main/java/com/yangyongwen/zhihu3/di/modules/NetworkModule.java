package com.yangyongwen.zhihu3.di.modules;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.yangyongwen.zhihu3.ZhihuApi.ZhihuApi;

import javax.inject.Inject;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by yangyongwen on 16/5/2.
 */

@Module
public class NetworkModule {


    @Inject
    public NetworkModule(){

    }


    @Provides
    @Singleton
    ZhihuApi provideZhihuApi(Retrofit retrofit) {
        ZhihuApi zhihuApi=retrofit.create(ZhihuApi.class);
        return zhihuApi;
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(Gson gson){
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(ZhihuApi.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        return retrofit;
    }

    @Provides
    @Singleton
    Gson provideGson(){
        Gson gson=new Gson(); // 自定义Gson,Retrofit解析的时候用到
        return gson;
    }


}
