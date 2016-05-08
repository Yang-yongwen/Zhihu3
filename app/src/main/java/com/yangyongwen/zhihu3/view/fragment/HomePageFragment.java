package com.yangyongwen.zhihu3.view.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yangyongwen.zhihu3.R;
import com.yangyongwen.zhihu3.ZhihuApp;
import com.yangyongwen.zhihu3.di.modules.HomePageFragmentModule;
import com.yangyongwen.zhihu3.model.HomePageModel;
import com.yangyongwen.zhihu3.model.QueryEnum;
import com.yangyongwen.zhihu3.presenter.HomePagePresenter;
import com.yangyongwen.zhihu3.view.UpdatableView;
import com.yangyongwen.zhihu3.view.UserActionEnum;
import com.yangyongwen.zhihu3.view.activity.HomePageActivity;
import com.yangyongwen.zhihu3.view.adapter.HomePageAdapter;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by samsung on 2016/5/6.
 */
public class HomePageFragment extends BaseFragment implements UpdatableView<HomePageModel>{

    @Inject
    HomePagePresenter homePagePresenter;

    @Inject
    HomePageAdapter homePageAdapter;

    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                getActivity(), drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.setDrawerListener(toggle);
        toggle.syncState();

        toolbar.setTitleTextColor(getResources().getColor(R.color.color_white));


    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.home_page_menu,menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return false;
    }




    @Override
    protected int getFragmentLayout(){
        return R.layout.fragment_home_page;
    }



    @Override
    protected  void setupFragmentComponent(){
        ((ZhihuApp) getActivity().
                getApplication()).
                getApplicationComponent().
                plus(new HomePageFragmentModule(this)).
                inject(this);
    }



    @Override public void onResume() {
        super.onResume();
        this.homePagePresenter.onResume();
    }

    @Override public void onPause() {
        super.onPause();
        this.homePagePresenter.onResume();
    }

    @Override public void onDestroyView() {
        super.onDestroyView();
        this.homePagePresenter.onDestroy();
    }



    @Override
    public void userAction(UserActionEnum userActionEnum, Bundle args){

    }
    @Override
    public void displayData(HomePageModel model, QueryEnum query, Bundle args){

    }
    @Override
    public void displayErrorMessage(QueryEnum query,Bundle args){

    }

}
