package com.yangyongwen.zhihu3.view.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.yangyongwen.zhihu3.R;
import com.yangyongwen.zhihu3.ZhihuApp;
import com.yangyongwen.zhihu3.di.modules.HomePageActivityModule;

public class HomePageActivity extends BaseActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
    }

    
    @Override
    protected void setupActivityComponent(){
        ZhihuApp.get(this).
                getApplicationComponent().
                plus(new HomePageActivityModule(this)).
                inject(this);
    }

}
