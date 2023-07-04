package com.example.shopping4.home.fragment;


import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.fastjson.JSON;
import com.example.shopping4.R;
import com.example.shopping4.WelcomeActivity;
import com.example.shopping4.base.BaseFragment;
import com.example.shopping4.home.FeedbackActivity;
import com.example.shopping4.home.adapter.HomeFragmentAdapter;
import com.example.shopping4.home.bean.ResultBeanData;
import com.example.shopping4.utils.Constants;
import com.zhy.http.okhttp.callback.StringCallback;
import com.zhy.http.okhttp.OkHttpUtils;

import java.util.List;

import okhttp3.Call;


/**
 * created by: xy
 * on: 2023/6/3
 * description:主页Fragment
 */
public class HomeFragment extends BaseFragment {
    private RecyclerView rvHome;
    private TextView tv_search_home;
    private TextView tv_message_home;
    //返回的数据
    private ResultBeanData.ResultBean result;
    private HomeFragmentAdapter adapter;



    @Override
    public View initView() {
        //BaseFragment中的mContext
        View view = View.inflate(mContext, R.layout.fragment_home,null);
        tv_search_home = (TextView) view.findViewById(R.id.tv_search_home);
        tv_message_home = (TextView) view.findViewById(R.id.tv_message_home);
        rvHome = (RecyclerView) view.findViewById(R.id.rv_home);
        //设置点击事件
        initListener();
        return view;
    }

    @Override
    public void initData(){
        super.initData();
        //请求连网数据
        getDataFromNet();

    }
    private void getDataFromNet(){
        String url = Constants.HOME_URL;
        OkHttpUtils
                .get()
                .url(url)
                .build()
                .execute(new StringCallback() {
                    /**
                     * 当请求失败的时候回调
                     * @param call
                     * @param e
                     * @param id
                     */
                    @Override
                    public void onError(Call call, Exception e, int i) {
                        Toast.makeText(mContext, "信息查询失败", Toast.LENGTH_SHORT).show();
                        Log.e(getTag(), "onError: 失败",e );
                    }
                    /**
                     * 当联网成功的时候回调
                     * @param s 请求成功的数据
                     * @param i
                     */
                    @Override
                    public void onResponse(String s, int i) {

                        Toast.makeText(mContext, "信息查询成功", Toast.LENGTH_SHORT).show();
//                        Log.i(getTag(), "信息查询成功: "+s.toString());
                        //解析数据
                        processData(s);
                    }
                });
    }
    private void initListener() {

        //搜素的监听
        tv_search_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "搜索", Toast.LENGTH_SHORT).show();
            }
        });

        //消息的监听
        tv_message_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "进入意见反馈", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(mContext, FeedbackActivity.class));
            }
        });

    }
    private void processData(String json){
        //把json数据解析成ResultBeanData的形式
        ResultBeanData resultBeanData = JSON.parseObject(json, ResultBeanData.class);
        result = resultBeanData.getResult();
        if (result!=null){
            //有数据，则设置适配器
            adapter = new HomeFragmentAdapter(mContext,result);
            rvHome.setAdapter(adapter);
            //设置布局管理器
            //布局管理器将rvhome设置成网格布局，设置美行显示一列
            rvHome.setLayoutManager(new GridLayoutManager(mContext,1));
        }
        //尝试得到result中其中一个HotInfoBean数据进行显示
        String s = result.getHot_info().get(0).getName();
        Log.i(getTag(), "解析成功: "+s.toString());
    }
}
