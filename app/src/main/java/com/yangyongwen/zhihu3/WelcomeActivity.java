package com.yangyongwen.zhihu3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import com.yangyongwen.zhihu3.ZhihuApi.ZhihuApi;
import com.yangyongwen.zhihu3.datastructure.LatestStories;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WelcomeActivity extends AppCompatActivity {

    @Inject
    ZhihuApi mZhihuApi;

    @BindView(R.id.button)
    Button mButton;

    @OnClick(R.id.button)
    void onClick(Button button){
        Call<LatestStories> stringCall=mZhihuApi.latestStory();
        stringCall.enqueue(new Callback<LatestStories>() {
            @Override
            public void onResponse(Call<LatestStories> call, Response<LatestStories> response) {
                LatestStories latestStories=response.body();
                int i=1;
            }

            @Override
            public void onFailure(Call<LatestStories> call, Throwable t) {
                int i=1;
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        ButterKnife.bind(this);

        ((ZhihuApp)getApplication()).getApplicationComponent().inject(this);
    }

}
