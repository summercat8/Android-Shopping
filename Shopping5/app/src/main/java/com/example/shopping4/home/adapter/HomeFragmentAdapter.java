package com.example.shopping4.home.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shopping4.R;
import com.example.shopping4.app.GoodsInfoActivity;
import com.example.shopping4.home.bean.Goodsbean;
import com.example.shopping4.home.bean.ResultBeanData;

import java.util.List;

/**
 * created by: xy
 * on: 2023/6/4
 * description:
 */
public class HomeFragmentAdapter extends RecyclerView.Adapter {
    //广告条幅类型
    public static final int BANNER = 0;
    private int currentType = BANNER;
    //频道类型
    public static final int CHANNEL = 1;

    //推荐类型
    public static final int RECOMMEND = 2;
    private static final String GOODS_BEAN = "goodsBean";
    //LayoutInflater是用来找layout下xml布局文件,并且实例化
    private LayoutInflater mLayoutInflater;
    private Context mContext;
    private ResultBeanData.ResultBean result;
    /**
     * 相当于getView 创建ViewHolder部分代码
     * 创建ViewHolder
     *
     * @param parent
     * @param viewType 当前的类型
     * @return
     */
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(viewType==BANNER){
            return new BannerViewHolder(mContext,mLayoutInflater.inflate(R.layout.banner_viewpager,null));
        }else if(viewType == CHANNEL){
            return new ChannelViewHolder(mContext,mLayoutInflater.inflate(R.layout.channel_item,null));
        }else if(viewType == RECOMMEND){
            return new RecommendViewHolder(mContext,mLayoutInflater.inflate(R.layout.recommend_item,null));
        }
        return null;
    }

    /**
     * 相当于getview中的绑定数据模块
     *
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == CHANNEL) {
            ChannelViewHolder channelViewHolder = (ChannelViewHolder)holder;
            channelViewHolder.setData(result.getChannel_info());
        } else if (getItemViewType(position) == RECOMMEND) {
            RecommendViewHolder recommendViewHolder = (RecommendViewHolder)holder;
            recommendViewHolder.setData(result.getRecommend_info());
        }

    }
    class RecommendViewHolder extends RecyclerView.ViewHolder{
        private final Context mContext;
        private TextView tv_more_recommend;
        private GridView gv_recommend;
        private RecommendGridViewAdapter adapter;
        public RecommendViewHolder(final Context mContext,View itemview){
            super(itemview);
            this.mContext = mContext;
            tv_more_recommend =(TextView) itemview.findViewById(R.id.tv_more_recommend);
            //网格布局
            gv_recommend = (GridView) itemview.findViewById(R.id.gv_recommend);
        }
        public void setData(List<ResultBeanData.ResultBean.RecommendInfoBean> recommend_info){
            //得到了数据
            //设置GraidView适配器
            adapter = new RecommendGridViewAdapter(mContext,recommend_info);
            gv_recommend.setAdapter(adapter);

            gv_recommend.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Toast.makeText(mContext, "position:"+i, Toast.LENGTH_SHORT).show();
//                    每日推荐商品信息类
                    Goodsbean goodsbean = new Goodsbean();
                    ResultBeanData.ResultBean.RecommendInfoBean recommendInfoBean = recommend_info.get(i);
                    goodsbean.setCover_price(recommendInfoBean.getCover_price());
                    goodsbean.setFigure(recommendInfoBean.getFigure());
                    goodsbean.setName(recommendInfoBean.getName());
                    goodsbean.setProduct_id(recommendInfoBean.getProduct_id());
                    startGoodsInfoActivity(goodsbean);
                }
            });
        }
    }
    class BannerViewHolder extends RecyclerView.ViewHolder{
        private Context mContext;
        private View itemview;
        public BannerViewHolder(Context mContent, View itemview){
            super(itemview);
            this.mContext = mContent;
        }
    }
    class ChannelViewHolder extends RecyclerView.ViewHolder{
        private Context mContext;
        private GridView gv_channel;
        private ChannelAdapter adapter;
        public ChannelViewHolder(Context mContext,View itemView){
            super(itemView);
            this.mContext = mContext;
            gv_channel = itemView.findViewById(R.id.gv_channel);
            //设置item点击事件
            gv_channel.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Toast.makeText(mContext, "position:"+i, Toast.LENGTH_SHORT).show();
                }
            });
        }


        public void setData(List<ResultBeanData.ResultBean.ChannelInfoBean> channel_info){
            //得到数据
            //设置GraidView适配器
            adapter = new ChannelAdapter(mContext,channel_info);
            gv_channel.setAdapter(adapter);
        }
    }
    /**
     * 启动商品详情信息列表页面
     */
    private void startGoodsInfoActivity(Goodsbean goodsbean){
        Intent intent = new Intent(mContext, GoodsInfoActivity.class);
        intent.putExtra(GOODS_BEAN,goodsbean);
        mContext.startActivity(intent);
    }
    /**
     * 得到类型
     * @param position position to query
     * @return
     */
    @Override
    public int getItemViewType(int position) {
        switch (position) {
            case BANNER:
                currentType = BANNER;
                break;
            case CHANNEL:
                currentType = CHANNEL;
                break;
            case RECOMMEND:
                currentType = RECOMMEND;
                break;
        }
        return currentType;
    }

    /**
     * 总共有多少个item
     *
     * @return
     */
    @Override
    public int getItemCount() {
        //开发过程中从1-->3
        return 3;
    }
    public HomeFragmentAdapter(Context context, ResultBeanData.ResultBean resultBean){
        this.mContext = context;
        this.result = resultBean;
        //初始化布局文件
        mLayoutInflater= LayoutInflater.from(context);
    }
}
