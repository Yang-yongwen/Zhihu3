package com.yangyongwen.zhihu3;

import android.app.Application;
import android.content.Context;

import com.yangyongwen.zhihu3.di.components.ApplicationComponent;
import com.yangyongwen.zhihu3.di.components.DaggerApplicationComponent;
import com.yangyongwen.zhihu3.di.modules.ApplicationModule;

/**
 * Created by yangyongwen on 16/5/2.
 */
public class ZhihuApp extends Application {

    private ApplicationComponent applicationComponent;



    @Override public void onCreate() {
        super.onCreate();
        this.initializeInjector();
    }


    private void initializeInjector(){
        applicationComponent= DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }

    public ApplicationComponent getApplicationComponent(){
        return this.applicationComponent;
    }

    public static ZhihuApp get(Context context){
        return (ZhihuApp)context.getApplicationContext();
    }

}
