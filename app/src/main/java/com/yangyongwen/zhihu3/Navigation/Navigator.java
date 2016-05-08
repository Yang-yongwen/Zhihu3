package com.yangyongwen.zhihu3.Navigation;

import android.content.Context;
import android.content.Intent;

import com.yangyongwen.zhihu3.view.activity.HomePageActivity;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by samsung on 2016/5/6.
 */
@Singleton
public class Navigator {



    @Inject
    public Navigator(Context context) {
        //empty
    }

    /**
     * Goes to the Home page
     *
     * @param context A Context needed to open the destiny activity.
     */
    public void navigateHomePage(Context context) {
        if (context != null) {
            Intent intentToLaunch = HomePageActivity.getCallingIntent(context);
            context.startActivity(intentToLaunch);
        }
    }


}