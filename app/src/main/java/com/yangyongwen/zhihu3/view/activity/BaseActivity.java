package com.yangyongwen.zhihu3.view.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.yangyongwen.zhihu3.Navigation.Navigator;

import javax.inject.Inject;

/**
 * Created by yangyongwen on 16/5/5.
 */
public abstract class BaseActivity extends AppCompatActivity {

    @Inject
    protected Navigator navigator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupActivityComponent();
    }

    protected abstract void setupActivityComponent();

}
