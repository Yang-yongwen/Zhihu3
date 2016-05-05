package com.yangyongwen.zhihu3.view;

import android.os.Bundle;

import com.yangyongwen.zhihu3.model.QueryEnum;

/**
 * Created by yangyongwen on 16/5/5.
 */
public interface UpdatableView <M>{
    void userAction(UserActionEnum userActionEnum, Bundle args);
    void displayData(M model, QueryEnum query, Bundle args);
    void displayErrorMessage(QueryEnum query,Bundle args);
}
