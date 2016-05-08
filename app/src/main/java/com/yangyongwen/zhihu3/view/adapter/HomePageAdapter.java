package com.yangyongwen.zhihu3.view.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.yangyongwen.zhihu3.R;
import com.yangyongwen.zhihu3.customview.CircleIndicator;
import com.yangyongwen.zhihu3.dao.Story;
import com.yangyongwen.zhihu3.dao.TopStory;
import com.yangyongwen.zhihu3.utils.LogUtils;
import com.yangyongwen.zhihu3.view.fragment.HomePageFragment;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by yangyongwen on 16/5/8.
 */
public class HomePageAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{


    public static final int TOPSTORY_ITEM=0;
    public static final int  DATE_ITEM=1;
    public static final int STORY_ITEM=2;


    HomePageFragment homePageFragment;
    LayoutInflater layoutInflater;

    private ArrayList<Story> mStories;
    private ArrayList<TopStory> mTopStories;


    private final static String TAG= LogUtils.makeLogTag(HomePageAdapter.class);



    @Inject
    public HomePageAdapter(HomePageFragment homePageFragment){
        this.homePageFragment=homePageFragment;
        this.layoutInflater=homePageFragment.getLayoutInflater(null);
        mStories=new ArrayList<>();
        mTopStories=new ArrayList<>();
    }



    private  class TopStoryViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.topstory_viewpager)ViewPager viewPager;

        @BindView(R.id.topstory_indicator) CircleIndicator circleIndicator;
        GestureDetector tapGestureDetector;
        public TopStoryViewHolder(View v){
            super(v);
            ButterKnife.bind(v);
        }

    }

    public  class StoryViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.story_item_title) TextView storyTitle;
        @BindView(R.id.story_item_icon) ImageView storyIcon;
        public StoryViewHolder(View v) {
            super(v);
            ButterKnife.bind(v);
        }

    }

    public static class DateViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.story_date) TextView dateTitle;
        public DateViewHolder(View v){
            super(v);
            ButterKnife.bind(v);
        }
    }




    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        RecyclerView.ViewHolder vh=null;
        View v=null;
        switch (viewType){
            case STORY_ITEM:
                v= layoutInflater.inflate(R.layout.story_item_cardview,parent,false);
                vh=new StoryViewHolder(v);
                break;
            case TOPSTORY_ITEM:
                v= layoutInflater.inflate(R.layout.topstory_item,parent,false);
                vh=new TopStoryViewHolder(v);
                break;
            case DATE_ITEM:
                v= layoutInflater.inflate(R.layout.story_date_item,parent,false);
                vh=new DateViewHolder(v);
                break;
            default:
                break;
        }
        return vh;
    }



    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder,int position){

    }


    @Override
    public int getItemCount(){
        return 0;
    }

    @Override
    public int getItemViewType(int position){
        if(position==0) {
            return TOPSTORY_ITEM;
        }else if(isStoryDateTitle(position)){
            return DATE_ITEM;
        }else{
            return STORY_ITEM;
        }
    }

    private boolean isStoryDateTitle(int position){
        return mStories.get(position-1).getTitle().contains("date")?true:false;
    }






}
