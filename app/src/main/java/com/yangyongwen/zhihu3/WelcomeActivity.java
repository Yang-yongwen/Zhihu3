package com.yangyongwen.zhihu3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.yangyongwen.zhihu3.view.activity.BaseActivity;
import com.yangyongwen.zhihu3.zhihuapi.ZhihuApi;
import com.yangyongwen.zhihu3.datastructure.LatestStories;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class WelcomeActivity extends BaseActivity {

    @Inject
    ZhihuApi mZhihuApi;

    @BindView(R.id.button)
    Button mButton;

    @OnClick(R.id.button)
    void onClick(Button button){
//        Observer<LatestStories> observer=new Observer<LatestStories>() {
//            @Override
//            public void onCompleted() {
//
//            }
//
//            @Override
//            public void onError(Throwable e) {
//
//            }
//
//            @Override
//            public void onNext(LatestStories latestStories) {
//                LatestStories latestStories1=latestStories;
//                int i;
//            }
//        };
//
//        Toast.makeText(this,"..",Toast.LENGTH_LONG).show();
//
//        mZhihuApi.latestStory()
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(observer);

        this.navigator.navigateHomePage(this);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        ButterKnife.bind(this);
        this.navigator.navigateHomePage(this);
        finish();

    }

    protected void setupActivityComponent(){
        ((ZhihuApp)getApplication()).getApplicationComponent().inject(this);
    }


}
