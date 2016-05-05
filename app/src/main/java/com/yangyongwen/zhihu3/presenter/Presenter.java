package com.yangyongwen.zhihu3.presenter;

import android.os.Bundle;

import com.yangyongwen.zhihu3.view.UserActionEnum;

/**
 * Created by yangyongwen on 16/5/5.
 */
public interface Presenter {
    void onUserAction(UserActionEnum userActionEnum, Bundle args);

    void onResume();
    void onPause();
    void onDestroy();

}
