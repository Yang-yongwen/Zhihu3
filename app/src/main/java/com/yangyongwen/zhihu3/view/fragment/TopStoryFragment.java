package com.yangyongwen.zhihu3.view.fragment;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.yangyongwen.zhihu3.R;
import com.yangyongwen.zhihu3.view.adapter.TopStoryAdapter;

/**
 * Created by yangyongwen on 16/5/8.
 */
public class TopStoryFragment extends Fragment {

    public static final String ARG_OBJECT = "object";



    @Override
    public View onCreateView(final LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        // The last two arguments ensure LayoutParams are inflated
        // properly.
        View rootView = inflater.inflate(
                R.layout.topstory_viewpager_container, container, false);



        Bundle args = getArguments();

        String image=args.getString(TopStoryAdapter.IMAGE);
        String title=args.getString(TopStoryAdapter.TITLE);


        TextView textView=(TextView)rootView.findViewById(R.id.topstory_title);
        textView.setText(title);

        ImageView imageView=(ImageView)rootView.findViewById(R.id.topstory_icon);
        Picasso.with(getActivity().getApplicationContext()).load(image).into(imageView);



        return rootView;
    }




}