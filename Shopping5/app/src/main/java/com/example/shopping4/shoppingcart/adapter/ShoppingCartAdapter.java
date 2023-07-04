package com.example.shopping4.shoppingcart.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.shopping4.R;
import com.example.shopping4.home.bean.Goodsbean;
import com.example.shopping4.shoppingcart.util.CartStorage;
import com.example.shopping4.shoppingcart.view.AddSubView;
import com.example.shopping4.utils.Constants;

import java.util.List;

/**
 * created by: xy
 * on: 2023/6/19
 * description:购物车适配器
 */
public class ShoppingCartAdapter extends RecyclerView.Adapter<ShoppingCartAdapter.ViewHolder> {

    private final Context mContext;
    private final List<Goodsbean> datas;
    private final TextView tvShopcartTotal;
    private final CheckBox checkboxAll;
    //完成状态下的删除CheckBox



    public ShoppingCartAdapter(Context mContext, List<Goodsbean> goodsBeanList, TextView tvShopcartTotal, CheckBox checkboxAll, CheckBox cbAll) {
        this.mContext = mContext;
        this.datas = goodsBeanList;
        this.tvShopcartTotal = tvShopcartTotal;
        this.checkboxAll = checkboxAll;
        showTotalPrice();
        //设置点击事件
        setListener();
        //校验是否全选
        checkAll();
    }

    private void setListener() {
        setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                //1.根据位置找到对应的Bean对象
                Goodsbean goodsBean = datas.get(position);
                //2.设置取反状态，只要点击checkbox的状态就改变
                goodsBean.setSelected(!goodsBean.isSelected());
                //3.刷新状态
                notifyItemChanged(position);
                //4.校验是否全选
                checkAll();
                //5.重新计算总价格
                showTotalPrice();
            }
        });

        //CheckBox的点击事件
        checkboxAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //1.得到状态
                boolean isCheck = checkboxAll.isChecked();

                //2.根据状态设置全选和非全选
                checkAll_none(isCheck);

                //3.计算总价格
                showTotalPrice();

            }
        });



    }

    /**
     * 设置全选和非全选
     * @param isCheck
     */
    public void checkAll_none(boolean isCheck) {
        if(datas != null && datas.size() >0){
            for (int i=0;i<datas.size();i++){
                Goodsbean goodsBean = datas.get(i);
                goodsBean.setSelected(isCheck);
                notifyItemChanged(i);
            }
        }
    }

    public void checkAll() {
        if(datas != null && datas.size() >0){
            int number = 0;
            for (int i=0;i<datas.size();i++){
                Goodsbean goodsBean = datas.get(i);
                if(!goodsBean.isSelected()){
                    //非全选
                    checkboxAll.setChecked(false);
                }else{
                    //选中的
                    number ++;
                }
            }

            if(number == datas.size()){
                //全选
                checkboxAll.setChecked(true);
            }
        }else{
            checkboxAll.setChecked(false);
        }
    }



    public void showTotalPrice() {
        tvShopcartTotal.setText("合计:" + getTotalPrice());
    }

    /**
     * 计算总价格
     *
     * @return
     */
    public double getTotalPrice() {
        double totalPrice = 0.0;

        if (datas != null && datas.size() > 0) {

            for (int i = 0; i < datas.size(); i++) {
                double currentPrice = 0.0;
                Goodsbean goodsBean = datas.get(i);
                //团购价一律五折
                if(goodsBean.isSelected()){
                        currentPrice = Double.valueOf(goodsBean.getNumber()) * Double.valueOf(goodsBean.getCover_price())*0.5;
                        totalPrice = totalPrice + currentPrice;
                }
            }
        }
        return totalPrice;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = View.inflate(mContext, R.layout.item_shop_cart, null);
        return new ViewHolder(itemView);
    }

    /**
     *购物车中的某条商品
     */
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        //1.根据位置得到对应的Bean对象
        final Goodsbean goodsBean = datas.get(position);
        //2.设置数据
        holder.cb_gov.setChecked(goodsBean.isSelected());
        //上传图片
        Glide.with(mContext).load(Constants.BASE_URL_IMAGE+goodsBean.getFigure()).into(holder.iv_gov);
        holder.tv_desc_gov.setText(goodsBean.getName());
        holder.tv_price_gov.setText("￥" + goodsBean.getCover_price());
        holder.addSubView.setValue(goodsBean.getNumber());
        holder.addSubView.setMinValue(1);
        holder.addSubView.setMaxValue(8);

        //设置商品数量的变化，OnNumberChangeListener是AddSubView里面的，这里属于使用了它的回调方法
        holder.addSubView.setOnNumberChangeListener(new AddSubView.OnNumberChangeListener() {
            @Override
            public void onNumberChange(int value) {
                //1.当前列表内存中
                goodsBean.setNumber(value);
                //2本地要更新
                CartStorage.getInstance().updateData(goodsBean);
                //3.刷新适配器
                notifyItemChanged(position);
                //4.再次计算总价格
                showTotalPrice();
            }
        });

    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    public void deleteData() {
        if(datas != null && datas.size() >0){
            for (int i=0;i<datas.size();i++){
                //删除选中的
                Goodsbean goodsBean = datas.get(i);
                if(goodsBean.isSelected()){
                    //内存-把移除
                    datas.remove(goodsBean);
                    //保持到本地
                    CartStorage.getInstance().deleteData(goodsBean);
                    //刷新
                    notifyItemRemoved(i);
                    i--;
                }
            }
        }
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        private CheckBox cb_gov;
        private ImageView iv_gov;
        private TextView tv_desc_gov;
        private TextView tv_price_gov;
        private AddSubView addSubView;

        public ViewHolder(View itemView) {
            super(itemView);
            cb_gov = (CheckBox) itemView.findViewById(R.id.cb_gov);
            iv_gov = (ImageView) itemView.findViewById(R.id.iv_gov);
            tv_desc_gov = (TextView) itemView.findViewById(R.id.tv_desc_gov);
            tv_price_gov = (TextView) itemView.findViewById(R.id.tv_price_gov);
            addSubView = (AddSubView) itemView.findViewById(R.id.addSubView);
            //设置item的点击事件
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(onItemClickListener != null){
                        //getLayoutPosition()为RecycleView中的方法，获取item的位置
                        onItemClickListener.onItemClick(getLayoutPosition());
                    }
                }
            });
        }
    }

    /**
     * 点击item的监听者
     */
    public interface  OnItemClickListener{
        /**
         * 当点击某条的时候被回调
         * @param position
         */
       public void  onItemClick( int position);
    }
    private OnItemClickListener onItemClickListener;

    /**
     * 设置item的监听
     * @param onItemClickListener
     */
    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}
