package com.example.shopping4.community.fragment;

import android.nfc.Tag;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.example.shopping4.R;
import com.example.shopping4.base.BaseFragment;
import androidx.fragment.app.Fragment;

/**
 * created by: xy
 * on: 2023/6/3
 * description:发现Fragment
 */
public class CommunityFragment extends BaseFragment {

    @Override
    public View initView() {
        //BaseFragment中的mContext
        View view = View.inflate(mContext, R.layout.activity_store, null);
        return view;
    }

    @Override
    public void initData(){
        super.initData();

    }
}
