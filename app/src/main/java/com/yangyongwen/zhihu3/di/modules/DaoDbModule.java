package com.yangyongwen.zhihu3.di.modules;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.yangyongwen.zhihu3.ZhihuApp;
import com.yangyongwen.zhihu3.dao.DaoMaster;
import com.yangyongwen.zhihu3.dao.DaoSession;
import com.yangyongwen.zhihu3.dao.ImageDao;
import com.yangyongwen.zhihu3.dao.StoryDao;
import com.yangyongwen.zhihu3.dao.TopStoryDao;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by samsung on 2016/5/6.
 */

@Module
public class DaoDbModule {

    @Provides
    @Singleton
    DaoMaster provideDaoMaster(Context context){
        DaoMaster.DevOpenHelper helper=new DaoMaster.DevOpenHelper(context,"ZhihuDb",null);
        SQLiteDatabase db=helper.getWritableDatabase();
        DaoMaster daoMaster=new DaoMaster(db);
        return daoMaster;
    }

    @Provides
    @Singleton
    DaoSession provideDaoSession(DaoMaster daoMaster){
        return daoMaster.newSession();
    }

    @Provides
    @Singleton
    StoryDao provideStoryDao(DaoSession daoSession){
        return daoSession.getStoryDao();
    }

    @Provides
    @Singleton
    ImageDao provideImageDao(DaoSession daoSession){
        return daoSession.getImageDao();
    }

    @Provides
    @Singleton
    TopStoryDao provideTopStoryDao(DaoSession daoSession){
        return daoSession.getTopStoryDao();
    }

}
