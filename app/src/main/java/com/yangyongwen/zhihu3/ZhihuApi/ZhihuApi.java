package com.yangyongwen.zhihu3.ZhihuApi;

import com.yangyongwen.zhihu3.datastructure.LatestStories;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by yangyongwen on 16/5/2.
 */
public interface ZhihuApi {

    String BASE_URL="http://news-at.zhihu.com/api/4/";

    @GET("news/latest")
    Call<LatestStories> latestStory();


}
