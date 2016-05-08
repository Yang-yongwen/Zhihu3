package com.yangyongwen.zhihu3.datastructure;

/**
 * Created by yangyongwen on 16/2/6.
 */
import com.yangyongwen.zhihu3.dao.Story;


public class DailyStory {
    private String date;
    private Story[] stories;

    public String getDate(){
        return date;
    }

    public Story[] getStories(){
        return stories;
    }
}
