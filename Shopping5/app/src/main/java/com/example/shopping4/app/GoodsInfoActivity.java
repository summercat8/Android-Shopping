package com.example.shopping4.app;



import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.shopping4.R;
import com.example.shopping4.home.adapter.HomeFragmentAdapter;
import com.example.shopping4.home.bean.Goodsbean;
import com.example.shopping4.shoppingcart.util.CartStorage;
import com.example.shopping4.utils.Constants;

/**
 * 商品详情页面
 */
public class GoodsInfoActivity extends Activity {
    private ImageButton ibGoodInfoBack;
    private ImageButton ibGoodInfoMore;
    private ImageView ivGoodInfoImage;
    private TextView tvGoodInfoName;
    private TextView tvGoodInfoDesc;
    private TextView tvGoodInfoPrice;
    private TextView tvGoodInfoStore;
    private TextView tvGoodInfoStyle;
    private LinearLayout llGoodsRoot;
    private TextView tvGoodInfoCallcenter;
    private TextView tvGoodInfoCollection;
    private TextView tvGoodInfoCart;
    private Button btnGoodInfoAddcart;
//    "更多"中的按钮
    private TextView tv_more_share;
    private TextView tv_more_search;
    private TextView tv_more_home;
    private Button btn_more;

    private Goodsbean goodsbean;

    /**
     * Find the Views in the layout<br />
     * <br />
     * Auto-created on 2023-06-16 08:13:04 by Android Layout Finder
     * (http://www.buzzingandroid.com/tools/android-layout-finder)
     */
    private void findViews() {
//        "更多"中的按钮
        tv_more_share =  (TextView) findViewById(R.id.tv_more_share);
        tv_more_search =  (TextView) findViewById(R.id.tv_more_search);
        tv_more_home =  (TextView) findViewById(R.id.tv_more_home);
        btn_more = (Button) findViewById(R.id.btn_more);

        ibGoodInfoBack = (ImageButton)findViewById( R.id.ib_good_info_back );
        ibGoodInfoMore = (ImageButton)findViewById( R.id.ib_good_info_more );
        ivGoodInfoImage = (ImageView)findViewById( R.id.iv_good_info_image );
        tvGoodInfoName = (TextView)findViewById( R.id.tv_good_info_name );
        tvGoodInfoDesc = (TextView)findViewById( R.id.tv_good_info_desc );
        tvGoodInfoPrice = (TextView)findViewById( R.id.tv_good_info_price );
        tvGoodInfoStore = (TextView)findViewById( R.id.tv_good_info_store );
        tvGoodInfoStyle = (TextView)findViewById( R.id.tv_good_info_style );
        llGoodsRoot = (LinearLayout)findViewById( R.id.ll_goods_root );

//        联系客服、收藏、购物车、加入购物车
        tvGoodInfoCallcenter = (TextView)findViewById( R.id.tv_good_info_callcenter );
        tvGoodInfoCollection = (TextView)findViewById( R.id.tv_good_info_collection );
        tvGoodInfoCart = (TextView)findViewById( R.id.tv_good_info_cart );
        btnGoodInfoAddcart = (Button)findViewById( R.id.btn_good_info_addcart );

        ibGoodInfoBack.setOnClickListener(this::onClick);
        ibGoodInfoMore.setOnClickListener( this::onClick);
        btnGoodInfoAddcart.setOnClickListener( this::onClick );

        tvGoodInfoCallcenter.setOnClickListener( this::onClick );
        tvGoodInfoCollection.setOnClickListener( this::onClick );
        tvGoodInfoCart.setOnClickListener( this::onClick );
        btnGoodInfoAddcart.setOnClickListener( this::onClick );

        tv_more_share.setOnClickListener( this::onClick );
        tv_more_search.setOnClickListener( this::onClick );
        tv_more_home.setOnClickListener( this::onClick );
        btn_more.setOnClickListener( this::onClick );
    }

    /**
     * Handle button click events<br />
     * <br />
     * Auto-created on 2023-06-16 08:13:04 by Android Layout Finder
     * (http://www.buzzingandroid.com/tools/android-layout-finder)
     */
    public void onClick(View v) {
        if ( v == ibGoodInfoBack ) {
            // Handle clicks for ibGoodInfoBack
            finish();
        } else if ( v == ibGoodInfoMore ) {
            // Handle clicks for ibGoodInfoMore
            Toast.makeText(this, "更多", Toast.LENGTH_SHORT).show();
        } else if ( v == btnGoodInfoAddcart ) {
            // Handle clicks for btnGoodInfoAddcart
            Toast.makeText(this, "发起团购成功，已有6人参与拼单", Toast.LENGTH_SHORT).show();
            CartStorage.getInstance().addData(goodsbean);
        } else if (v == tvGoodInfoCallcenter) {
            Toast.makeText(this, "客户中心", Toast.LENGTH_SHORT).show();
        } else if (v == tvGoodInfoCollection) {
            Toast.makeText(this, "收藏", Toast.LENGTH_SHORT).show();
        } else if (v == tvGoodInfoCart) {
            Toast.makeText(this, "跳转到购物车", Toast.LENGTH_SHORT).show();
        } else if (v == tv_more_share) {
            Toast.makeText(this, "分享", Toast.LENGTH_SHORT).show();
        } else if (v == tv_more_search) {
            Toast.makeText(this, "搜索", Toast.LENGTH_SHORT).show();
        } else if (v == tv_more_home) {
            Toast.makeText(this, "主页面", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_info);
//        初始化参数
        findViews();
//        接收数据
         goodsbean =(Goodsbean) getIntent().getSerializableExtra("goodsBean");
         if(goodsbean != null){
             Toast.makeText(this, "goodsBean == "+goodsbean.toString(), Toast.LENGTH_SHORT).show();
             setDataForView(goodsbean);
         }
    }

    /**
     * 设置数据
     * @param goodsbean
     */
    private void setDataForView(Goodsbean goodsbean){
        //设置图片
        Glide.with(this).load(Constants.BASE_URL_IMAGE+goodsbean.getFigure()).into(ivGoodInfoImage);
        //设置文本
        tvGoodInfoName.setText(goodsbean.getName());
        //设置价格
        tvGoodInfoPrice.setText("￥"+goodsbean.getCover_price());


    }

}