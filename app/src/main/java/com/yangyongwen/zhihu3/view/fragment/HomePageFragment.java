package com.yangyongwen.zhihu3.view.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import com.yangyongwen.zhihu3.dao.Story;
import com.yangyongwen.zhihu3.dao.TopStory;
import com.yangyongwen.zhihu3.di.modules.HomePageFragmentModule;
import com.yangyongwen.zhihu3.model.HomePageModel;
import com.yangyongwen.zhihu3.model.QueryEnum;
import com.yangyongwen.zhihu3.presenter.HomePagePresenter;
import com.yangyongwen.zhihu3.utils.DateUtils;
import com.yangyongwen.zhihu3.utils.SharePreferenceUtils;
import com.yangyongwen.zhihu3.view.UpdatableView;
import com.yangyongwen.zhihu3.view.UserActionEnum;
import com.yangyongwen.zhihu3.view.activity.HomePageActivity;
import com.yangyongwen.zhihu3.view.adapter.HomePageAdapter;

import java.util.ArrayList;

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
    public HomePageAdapter homePageAdapter;

    @Inject
    SharePreferenceUtils sharePreferenceUtils;


    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;

    @BindView(R.id.homepage_content)
    RecyclerView recyclerView;

    @BindView(R.id.swiperefresh)
    SwipeRefreshLayout swipeRefreshLayout;

    LinearLayoutManager linearLayoutManager;
    private Boolean isAddingDailyStories;

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                getActivity(), drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.setDrawerListener(toggle);
        toggle.syncState();

        toolbar.setTitleTextColor(getResources().getColor(R.color.color_white));


        homePageAdapter.setSwipeRefreshLayout(swipeRefreshLayout);


        linearLayoutManager=new LinearLayoutManager(getContext());
        recyclerView.setAdapter(homePageAdapter);
        recyclerView.setLayoutManager(linearLayoutManager);


        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                userAction(HomePageActionEnum.UPDATELATEST,null);
            }
        });

        swipeRefreshLayout.setColorSchemeResources(R.color.color_blue);

        isAddingDailyStories=false;
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }
            @Override
            public void onScrolled(RecyclerView recyclerView,int dx,int dy){
                super.onScrolled(recyclerView,dx,dy);
                if((linearLayoutManager.findLastVisibleItemPosition()==homePageAdapter.getItemCount()-1)&&!isAddingDailyStories){
                    isAddingDailyStories=true;
                    String date= DateUtils.yesterday(homePageAdapter.getOldestDate());
                    Bundle bundle=new Bundle();
                    bundle.putString("date",date);
                    userAction(HomePageActionEnum.LOADDAILY,bundle);
                }

                int pos=linearLayoutManager.findFirstVisibleItemPosition();


                if(pos==0){
                    toolbar.setTitle("首页");
                }else {
                    String today=homePageAdapter.getToday();
                    String date1=homePageAdapter.getDateByPosition(pos);

                    String title=null;

                    if(date1.equals(today)){
                        title=new String("今日热闻");
                    }else{
                        String s1=date1.substring(4,6);
                        String s2=date1.substring(6,8);
                        title=s1+"月"+s2+"日 "+DateUtils.convertDay(date1);
                    }
                    toolbar.setTitle(title);
                }
            }
        });



        userAction(HomePageActionEnum.INIT,null);
        sharePreferenceUtils.putStringValue("11","1111");

    }

    public void stopLoading(){
        swipeRefreshLayout.setRefreshing(false);
        isAddingDailyStories=false;
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.home_page_menu,menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id==R.id.action_message){
        }
        return super.onOptionsItemSelected(item);
    }


    public void addDailyStories(Story[] stories){
        ArrayList<Story> stories1=new ArrayList<>();
        for (Story story:stories){
            stories1.add(story);
        }
        homePageAdapter.addDailyStories(stories1);
    }

    public void setTopStories(TopStory[] stories){
        ArrayList<TopStory> stories1=new ArrayList<>();
        for (TopStory story:stories){
            stories1.add(story);
        }
        homePageAdapter.setTopStories(stories1);
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
        this.homePagePresenter.onPause();
    }

    @Override public void onDestroyView() {
        super.onDestroyView();
        this.homePagePresenter.onDestroy();
    }



    @Override
    public void userAction(UserActionEnum userActionEnum, Bundle args){
        homePagePresenter.onUserAction(userActionEnum,args);
    }
    @Override
    public void displayData(HomePageModel model, QueryEnum query, Bundle args){

    }
    @Override
    public void displayErrorMessage(QueryEnum query,Bundle args){

    }


    public static enum HomePageActionEnum implements UserActionEnum {
        UPDATELATEST(1),
        LOADDAILY(2),
        INIT(3);
        private int id;
        HomePageActionEnum(int id) {
            this.id = id;
        }
        @Override
        public int getId(){
            return id;
        }
    }





}
