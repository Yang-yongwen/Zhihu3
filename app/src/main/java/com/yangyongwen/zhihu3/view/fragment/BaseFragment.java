package com.yangyongwen.zhihu3.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.yangyongwen.zhihu3.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Subscription;

/**
 * Created by samsung on 2016/5/6.
 */
public abstract class BaseFragment extends Fragment {


    @BindView(R.id.toolbar_actionbar)
    Toolbar toolbar;

    private ArrayList<Subscription> subscriptions=new ArrayList<>();

    protected void showToastMessage(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }


    public void onAttach(Context context) {
        super.onAttach(context);
        setupFragmentComponent();
    }

    @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                       Bundle savedInstanceState) {
        return inflater.inflate(getFragmentLayout(), container, false);
    }


    protected abstract void setupFragmentComponent();



    @Override public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        injectViews(view);
        setHasOptionsMenu(true);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
    }




    /**
     * Every fragment has to inflate a layout in the onCreateView method. We have added this method to
     * avoid duplicate all the inflate code in every fragment. You only have to return the layout to
     * inflate in this method when extends BaseFragment.
     */
    protected abstract int getFragmentLayout();



    protected void injectViews(View view){
        ButterKnife.bind(this,view);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        clearSubscription();
    }


    protected void addSubscription(Subscription subscription){
        subscriptions.add(subscription);
    }

    protected void clearSubscription(){
        for(Subscription subscription:subscriptions){
            if(subscription!=null&&!subscription.isUnsubscribed()){
                subscription.unsubscribe();
            }
        }
        subscriptions.clear();
    }



}
