package com.example.supengchao.waterfalldemo;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by supengchao on 2015/11/9.
 */
public class BaseViewHolder extends RecyclerView.ViewHolder {
    public ImageView imageView;
    public TextView textView;
    public BaseViewHolder(View itemView) {
        super(itemView);
        initView(itemView);
    }

    private void initView(View itemView) {
        imageView=(ImageView)itemView.findViewById(R.id.iv);
        textView=(TextView)itemView.findViewById(R.id.tv);
    }

}
