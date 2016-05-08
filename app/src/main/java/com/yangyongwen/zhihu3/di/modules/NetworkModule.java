package com.yangyongwen.zhihu3.di.modules;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.yangyongwen.zhihu3.dao.Image;
import com.yangyongwen.zhihu3.zhihuapi.ZhihuApi;

import java.lang.reflect.Type;

import javax.inject.Inject;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
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
    Retrofit provideRetrofit(){

        GsonBuilder builder=new GsonBuilder();

        builder.registerTypeAdapter(Image.class, new JsonDeserializer<Image>() {
            @Override
            public Image deserialize(JsonElement arg0, Type arg1,
                                     JsonDeserializationContext arg2) throws JsonParseException{
                String url=arg0.getAsString();
                Image image=new Image();
                image.setImage_url(url);
                return image;
            }
        });


        Gson gson=builder.create(); // 自定义Gson,Retrofit解析的时候用到



        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(ZhihuApi.BASE_URL)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        return retrofit;
    }

    @Provides
    @Singleton
    Gson provideGson(){
        GsonBuilder builder=new GsonBuilder();

        builder.registerTypeAdapter(Image.class, new JsonDeserializer<Image>() {
            @Override
            public Image deserialize(JsonElement arg0, Type arg1,
                                     JsonDeserializationContext arg2) throws JsonParseException{
                String url=arg0.getAsString();
                Image image=new Image();
                image.setImage_url(url);
                return image;
            }
        });


        Gson gson=builder.create(); // 自定义Gson,Retrofit解析的时候用到

        return gson;
    }


}
