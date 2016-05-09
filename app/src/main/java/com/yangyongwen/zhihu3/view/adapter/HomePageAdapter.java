package com.yangyongwen.zhihu3.view.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import com.yangyongwen.zhihu3.utils.DateUtils;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
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
import butterknife.OnClick;

/**
 * Created by yangyongwen on 16/5/8.
 */
public class HomePageAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{


    public static final int TOPSTORY_ITEM=0;
    public static final int  DATE_ITEM=1;
    public static final int STORY_ITEM=2;

    private int topStoryViewPagerPos;


    SwipeRefreshLayout swipeRefreshLayout;




    HomePageFragment homePageFragment;
    LayoutInflater layoutInflater;

    TopStoryAdapter topStoryAdapter;

    Context context;

    String oldestDate;

    private ArrayList<Story> stories;
    private ArrayList<TopStory> topStories;


    private final static String TAG= LogUtils.makeLogTag(HomePageAdapter.class);



    @Inject
    public HomePageAdapter(HomePageFragment homePageFragment,TopStoryAdapter topStoryAdapter){
        this.topStoryAdapter=topStoryAdapter;
        this.homePageFragment=homePageFragment;
        this.layoutInflater=homePageFragment.getLayoutInflater(null);
        this.context=homePageFragment.getContext();
        stories=new ArrayList<>();
        topStories=new ArrayList<>();

    }

    public void setSwipeRefreshLayout(SwipeRefreshLayout swipeRefreshLayout){
        this. swipeRefreshLayout=swipeRefreshLayout;
    }


    public void setTopStories(ArrayList<TopStory> topStories){
        this.topStories.clear();
        this.topStories.addAll(topStories);
        notifyDataSetChanged();
    }

    public void addDailyStories(ArrayList<Story> dailyStories){
        String date=dailyStories.get(0).getDate();
        oldestDate=date;
        Story story=new Story();
        story.setDate(date);
        story.setTitle("date: "+date);
        stories.add(story);
        stories.addAll(dailyStories);
        notifyDataSetChanged();
    }


    
    public void clearAll(){
        stories.clear();
        topStories.clear();
        oldestDate="";
        notifyDataSetChanged();
    }

    public String getOldestDate(){
        return oldestDate;
    }
    public String getToday(){
        return topStories.get(0).getDate();
    }

    public String getDateByPosition(int pos){
        String s=null;
        if(pos==0){
            s=topStories.get(0).getDate();
        }else{
            s=stories.get(pos-1).getDate();
        }
        return s;
    }

    public  class TopStoryViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.topstory_viewpager)ViewPager viewPager;

        @BindView(R.id.topstory_indicator) CircleIndicator circleIndicator;
        GestureDetector tapGestureDetector;
        public TopStoryViewHolder(View v){
            super(v);
            ButterKnife.bind(this,v);
        }

    }

    public  class StoryViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.story_item_title) TextView storyTitle;
        @BindView(R.id.story_item_icon) ImageView storyIcon;
        @BindView(R.id.story_multipic) ImageView storyMultiPic;
        public StoryViewHolder(View v) {
            super(v);
            ButterKnife.bind(this,v);
        }

    }

    public static class DateViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.story_date) TextView dateTitle;
        public DateViewHolder(View v){
            super(v);
            ButterKnife.bind(this,v);
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
        switch (getItemViewType(position)){
            case TOPSTORY_ITEM:
                bindTopStoryViewHolder(holder,position);
                break;
            case STORY_ITEM:
                bindStoryViewHolder(holder,position);
                break;
            case DATE_ITEM:
                bindDateViewHolder(holder,position);
                break;
            default:
                break;
        }
    }



    private void bindTopStoryViewHolder(RecyclerView.ViewHolder holder,int position){
        TopStoryViewHolder topStoryViewHolder=(TopStoryViewHolder)holder;

        topStoryAdapter.setTopStories(topStories);
        topStoryViewHolder.viewPager.setAdapter(topStoryAdapter);
        topStoryViewHolder.viewPager.setCurrentItem(topStoryViewPagerPos);


        if(topStories.size()!=0){
            topStoryViewHolder.circleIndicator.setViewPager(topStoryViewHolder.viewPager);
            topStoryViewHolder.viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                }

                @Override
                public void onPageSelected(int position) {
                    topStoryViewPagerPos=position;
                }

                @Override
                public void onPageScrollStateChanged(int state) {
                    if (state == ViewPager.SCROLL_STATE_DRAGGING) {
                        swipeRefreshLayout.setEnabled(false);
                    } else if (state == ViewPager.SCROLL_STATE_IDLE) {
                        swipeRefreshLayout.setEnabled(true);
                    }
                }
            });
            topStoryViewHolder.circleIndicator.setVisibility(View.VISIBLE);
            topStoryViewHolder.circleIndicator.showIndicator();
        }

    }

    private void bindStoryViewHolder(RecyclerView.ViewHolder holder,int position){
        StoryViewHolder vh=(StoryViewHolder)holder;

        Story story=stories.get(position-1);
        vh.storyTitle.setText(story.getTitle());
        Picasso.with(context).load(story.getImages().get(0).getImage_url()).into(vh.storyIcon);
        if(story.getMultipic()!=null&&story.getMultipic()){
            vh.storyMultiPic.setVisibility(View.VISIBLE);
        }else{
            vh.storyMultiPic.setVisibility(View.GONE);
        }
    }

    private void bindDateViewHolder(RecyclerView.ViewHolder holder,int position){
        DateViewHolder vh=(DateViewHolder)holder;
        String date=stories.get(position-1).getTitle().split(":")[1].substring(1);
        String dateTitle;
        String today=topStories.get(0).getDate();
        if(date.equals(today)){
            dateTitle=new String("今日热闻");
        }else{
            String s1=date.substring(4,6);
            String s2=date.substring(6,8);
            dateTitle=s1+"月"+s2+"日 "+ DateUtils.convertDay(date);
        }
        vh.dateTitle.setText(dateTitle);
    }



    @Override
    public void onViewRecycled(RecyclerView.ViewHolder vh){
        if(vh instanceof TopStoryViewHolder){
            topStoryViewPagerPos=((TopStoryViewHolder) vh).viewPager.getCurrentItem();

        }
        super.onViewRecycled(vh);
    }



    @Override
    public int getItemCount(){
        return stories.size()+1;
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
        return stories.get(position-1).getTitle().contains("date")?true:false;
    }




}
