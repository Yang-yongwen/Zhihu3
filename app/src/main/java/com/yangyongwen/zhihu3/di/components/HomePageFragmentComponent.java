package com.yangyongwen.zhihu3.di.components;

import com.yangyongwen.zhihu3.di.ActivityScope;
import com.yangyongwen.zhihu3.di.FragmentScope;
import com.yangyongwen.zhihu3.di.modules.HomePageFragmentModule;
import com.yangyongwen.zhihu3.view.activity.HomePageActivity;
import com.yangyongwen.zhihu3.view.fragment.HomePageFragment;

import dagger.Subcomponent;

/**
 * Created by yangyongwen on 16/5/5.
 */


@FragmentScope
@Subcomponent(modules = HomePageFragmentModule.class)
public interface HomePageFragmentComponent {
    HomePageFragment inject(HomePageFragment homePageFragment);
}


