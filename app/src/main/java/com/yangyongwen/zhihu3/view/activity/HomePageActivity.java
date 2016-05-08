package com.yangyongwen.zhihu3.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.yangyongwen.zhihu3.R;
import com.yangyongwen.zhihu3.ZhihuApp;
import com.yangyongwen.zhihu3.di.modules.HomePageFragmentModule;

public class HomePageActivity extends BaseActivity {


    public static Intent getCallingIntent(Context context) {
        return new Intent(context, HomePageActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
    }

    
    @Override
    protected void setupActivityComponent(){
        ZhihuApp.get(this).
                getApplicationComponent().
                inject(this);
    }

}
