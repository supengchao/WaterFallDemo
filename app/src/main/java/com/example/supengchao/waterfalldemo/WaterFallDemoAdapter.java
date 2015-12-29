package com.example.supengchao.waterfalldemo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by supengchao on 2015/11/9.
 */
public class WaterFallDemoAdapter extends RecyclerView.Adapter<BaseViewHolder> {
    private Context mContext;
    private List<ItemBean> mData;
    private List<Integer> mHeights;
    private OnItemClickListener onItemClickListener;

    public WaterFallDemoAdapter(Context mContext, List<ItemBean> mData) {
        this.mContext = mContext;
        this.mData = mData;
        mHeights = new ArrayList<Integer>();
        for (int i = 0; i < mData.size(); i++)
        {
            mHeights.add( (int) (100 + Math.random() * 300));
        }
    }
    public void setData( List<ItemBean> mData){
        this.mData = mData;
        notifyDataSetChanged();
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(
                mContext).inflate(R.layout.water_fall_item_view, parent,
                false);
        BaseViewHolder viewHolder = new BaseViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final BaseViewHolder holder, final int position) {
        if (position < mData.size()) {

            ViewGroup.LayoutParams lp = holder.textView.getLayoutParams();
            lp.height = mHeights.get(position);

            holder.textView.setLayoutParams(lp);
            holder.textView.setText(mData.get(position).name);

            String url = mData.get(position).url;
//            Glide.with(mContext).load(url).into(holder.imageView);

            if(onItemClickListener!=null){
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int pos = holder.getLayoutPosition();
                        onItemClickListener.setOnItemClickListener(holder.itemView, pos);
                    }
                });
                holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View view) {
                        int pos = holder.getLayoutPosition();
                        onItemClickListener.setOnItemLongClickListener(holder.itemView, pos);
                        return false;
                    }
                });
            }
        }
    }
    public void removeData(int position){
        if(position<mData.size()){

            mData.remove(position);
            notifyItemRemoved(position);
        }
    }
    public void addData(int position){
        for(int i=0;i<10;i++){
            ItemBean bean = new ItemBean();
            bean.name="Insert Data"+i;
            mData.add(position,bean);
            mHeights.add( (int) (100 + Math.random() * 300));
            notifyItemInserted(position);
        }


    }

    public void setOnItemClickListening(OnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }
    @Override
    public int getItemCount() {
        return mData.size();
    }

    public interface OnItemClickListener{
        void setOnItemClickListener(View view,int position);
        void setOnItemLongClickListener(View view,int position);
    }
}
