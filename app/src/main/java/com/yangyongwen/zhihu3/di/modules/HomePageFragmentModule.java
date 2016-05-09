package com.yangyongwen.zhihu3.di.modules;

import android.content.Context;

import com.yangyongwen.zhihu3.R;
import com.yangyongwen.zhihu3.ZhihuApp;
import com.yangyongwen.zhihu3.dao.DaoSession;
import com.yangyongwen.zhihu3.dao.StoryDao;
import com.yangyongwen.zhihu3.di.ActivityScope;
import com.yangyongwen.zhihu3.di.FragmentScope;
import com.yangyongwen.zhihu3.model.HomePageModel;
import com.yangyongwen.zhihu3.presenter.HomePagePresenter;
import com.yangyongwen.zhihu3.utils.SharePreferenceUtils;
import com.yangyongwen.zhihu3.view.activity.HomePageActivity;
import com.yangyongwen.zhihu3.view.adapter.HomePageAdapter;
import com.yangyongwen.zhihu3.view.adapter.TopStoryAdapter;
import com.yangyongwen.zhihu3.view.fragment.HomePageFragment;
import com.yangyongwen.zhihu3.zhihuapi.ZhihuApi;

import dagger.Module;
import dagger.Provides;

/**
 * Created by yangyongwen on 16/5/5.
 */

@Module
public class HomePageFragmentModule {

    private HomePageFragment homePageFragment;

    public HomePageFragmentModule(HomePageFragment homePageFragment) {
        this.homePageFragment = homePageFragment;
    }

    @Provides
    @FragmentScope
    HomePageActivity provideHomePageActivity() {
        return (HomePageActivity) homePageFragment.getActivity();
    }

    @Provides
    @FragmentScope
    HomePageFragment provideHomePageFragment(){
        return this.homePageFragment;
    }

    @Provides
    @FragmentScope
    HomePagePresenter provideHomePageActivityPresenter(HomePageModel homePageModel) {
        return new HomePagePresenter(homePageFragment,homePageModel);
    }

    @Provides
    @FragmentScope
    HomePageModel provideHomePageModel(ZhihuApi zhihuApi, DaoSession daoSession,SharePreferenceUtils sharePreferenceUtils){
        return new HomePageModel(zhihuApi,daoSession,sharePreferenceUtils);
    }

    @Provides
    @FragmentScope
    HomePageAdapter provideHomePageAdapter(HomePageFragment homePageFragment,TopStoryAdapter topStoryAdapter){
        return new HomePageAdapter(homePageFragment,topStoryAdapter);
    }

    @Provides
    @FragmentScope
    TopStoryAdapter provideTopStoryAdapter(HomePageFragment homePageFragment){
        return new TopStoryAdapter(homePageFragment.getFragmentManager());
    }


}
