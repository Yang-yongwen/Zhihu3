package com.yangyongwen.zhihu3.zhihuapi;


import com.yangyongwen.zhihu3.datastructure.DailyStory;
import com.yangyongwen.zhihu3.datastructure.LatestStories;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by yangyongwen on 16/5/2.
 */
public interface ZhihuApi {

    String BASE_URL="http://news-at.zhihu.com/api/4/";

    @GET("news/latest")
    Observable<LatestStories> latestStory();



    @GET("news/before/{id}")
    Observable<DailyStory> dailyStory(@Path("id")String id);




}
