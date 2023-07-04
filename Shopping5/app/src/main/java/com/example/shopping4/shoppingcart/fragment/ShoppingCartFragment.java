package com.example.shopping4.shoppingcart.fragment;

import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.text.TextUtils;
import android.util.Log;

import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;

import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shopping4.R;
import com.example.shopping4.base.BaseFragment;
import com.example.shopping4.home.bean.Goodsbean;
import com.example.shopping4.shoppingcart.adapter.ShoppingCartAdapter;
import com.example.shopping4.shoppingcart.util.CartStorage;

import java.util.List;


/**
 * created by: xy
 * on: 2023/6/3
 * description:购物车Fragment
 */
public class ShoppingCartFragment extends BaseFragment implements View.OnClickListener{
    private TextView tvShopcartEdit;
    private RecyclerView recyclerview;
    private LinearLayout llCheckAll;
    private CheckBox checkboxAll;
    private TextView tvShopcartTotal;
    private Button btnCheckOut;
    private LinearLayout llDelete;
    private CheckBox cbAll;
    private Button btnDelete;
    private Button btnCollection;
    private ImageView ivEmpty;
    private TextView tvEmptyCartTobuy;
    private ShoppingCartAdapter shoppingCartAdapter;

    //编辑状态
    private static final int ACTION_EDIT = 1;
    //完成状态
    private static final int ACTION_COMPLETE = 2;





    @Override
    public View initView() {
        //BaseFragment中的mContext
        View view = View.inflate(mContext, R.layout.fragment_shopping_cart,null);
        tvShopcartEdit = (TextView)view.findViewById( R.id.tv_shopcart_edit );
        recyclerview = (RecyclerView)view.findViewById( R.id.recyclerview );
        llCheckAll = (LinearLayout)view.findViewById( R.id.ll_check_all );
        checkboxAll = (CheckBox)view.findViewById( R.id.checkbox_all );
        tvShopcartTotal = (TextView)view.findViewById( R.id.tv_shopcart_total );
        btnCheckOut = (Button)view.findViewById( R.id.btn_check_out );
        llDelete = (LinearLayout)view.findViewById( R.id.ll_delete );
        cbAll = (CheckBox)view.findViewById( R.id.cb_all );
        btnDelete = (Button)view.findViewById( R.id.btn_delete );
        btnCollection = (Button)view.findViewById( R.id.btn_collection );



        btnCheckOut.setOnClickListener( this );
        btnDelete.setOnClickListener( this );
        btnCollection.setOnClickListener( this );
        return view;
    }
    @Override
    public void onClick(View v) {
        if ( v == btnCheckOut ) {
            //结算，删除选中
            shoppingCartAdapter.deleteData();
            Toast.makeText(mContext, "成功结算", Toast.LENGTH_SHORT).show();
        } else if ( v == btnDelete ) {
            //在购物车中删除选中的部分
            shoppingCartAdapter.deleteData();
            //校验状态
            shoppingCartAdapter.checkAll();
        } else if ( v == btnCollection ) {
            // Handle clicks for btnCollection
        }
    }


    @Override
    public void initData(){
        super.initData();
        showData();
    }
    private void showData(){
        List<Goodsbean> goodsBeanList = CartStorage.getInstance().getAllData();
        if(goodsBeanList!=null&&goodsBeanList.size()>0){
            //设置适配器
            shoppingCartAdapter = new ShoppingCartAdapter(mContext,goodsBeanList,tvShopcartTotal,checkboxAll,cbAll);
            recyclerview.setAdapter(shoppingCartAdapter);
            //设置布局管理器
            recyclerview.setLayoutManager(new LinearLayoutManager(mContext,LinearLayoutManager.VERTICAL,false));
        }
        for(int i=0;i<goodsBeanList.size();i++){
            Log.e(getTag(),goodsBeanList.get(i).toString());
        }
    }
}
