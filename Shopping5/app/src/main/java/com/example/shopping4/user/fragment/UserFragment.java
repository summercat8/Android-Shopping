package com.example.shopping4.user.fragment;

import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.example.shopping4.R;
import com.example.shopping4.base.BaseFragment;


/**
 * created by: xy
 * on: 2023/6/3
 * description:用户中心Fragment
 */
public class UserFragment extends BaseFragment {

    @Override
    public View initView() {
        /*textView = new TextView(mContext);
        textView.setGravity(Gravity.CENTER);
        textView.setTextSize(25);*/
        View view = View.inflate(mContext, R.layout.activity_profile, null);

        return view;
    }

    @Override
    public void initData(){
        super.initData();
//        textView.setText("个人中心内容");
    }
}
