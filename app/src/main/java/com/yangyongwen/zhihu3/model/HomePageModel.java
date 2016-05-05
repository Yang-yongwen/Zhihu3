package com.yangyongwen.zhihu3.model;

import android.os.Bundle;

import dagger.Module;
import rx.Observable;

/**
 * Created by yangyongwen on 16/5/5.
 */
public class HomePageModel implements Model {
    public <T> Observable<T> query(T x, QueryEnum queryEnum, Bundle args){
        return null;
    }
    public void update(UpdateEnum updateEnum,Object data,Bundle extras){

    }

}
