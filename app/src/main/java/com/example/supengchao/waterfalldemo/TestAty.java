package com.example.supengchao.waterfalldemo;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import view.pulltorefresh.PullToRefreshLayout;
import view.recycleview.MyStaggeredGridLayoutManager;
import view.recycleview.RefreshRecycleView;

/**
 * Created by supengchao on 2015/11/11.
 */
public class TestAty extends Activity implements PullToRefreshLayout.OnRefreshListener{
    private RefreshRecycleView mRecyclerView;
    private StaggeredGridLayoutManager mLayoutManager;
    private WaterFallDemoAdapter mAdapter;
    private List<ItemBean> mList;
    private PullToRefreshLayout mRefreshView;
    private boolean isFirstIn = true;
    private LinearLayout ll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_layout);
        initData();
        initView();
        initEvent();
    }


    private void initEvent() {
        mRefreshView.setOnRefreshListener(this);
    }

    private void initView() {
        mRecyclerView=(RefreshRecycleView)findViewById(R.id.id_recyclerview);
        mRefreshView=(PullToRefreshLayout)findViewById(R.id.refresh_view);
        mLayoutManager = new MyStaggeredGridLayoutManager(4, StaggeredGridLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new WaterFallDemoAdapter(TestAty.this,mList);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
    }
    private void initData() {
        mList = new ArrayList<ItemBean>();
        mList.clear();
        for(int i=1;i<60;i++){
            ItemBean bean = new ItemBean();
            bean.name="" + i;
            bean.url="http://inthecheesefactory.com/uploads/source/glidepicasso/cover.jpg";
            mList.add(bean);
        }
    }
    private void addData() {
        List list = new ArrayList<ItemBean>();
        list.clear();
        for(int i=1;i<10;i++){
            ItemBean bean = new ItemBean();
            bean.name="" + i;
            bean.url="http://inthecheesefactory.com/uploads/source/glidepicasso/cover.jpg";
            list.add(bean);
        }
        mList.addAll(list);
    }
    @Override
    public void onRefresh(final PullToRefreshLayout pullToRefreshLayout) {
        new Handler()
        {
            @Override
            public void handleMessage(Message msg)
            {
               initData();
                // 千万别忘了告诉控件刷新完毕了哦！
                pullToRefreshLayout.refreshFinish(PullToRefreshLayout.SUCCEED);
            }
        }.sendEmptyMessageDelayed(0, 2000);
    }

    @Override
    public void onLoadMore(final PullToRefreshLayout pullToRefreshLayout) {
        new Handler() {
            @Override
            public void handleMessage(Message msg)
            {
                addData();
                // 千万别忘了告诉控件刷新完毕了哦！
                pullToRefreshLayout.loadmoreFinish(PullToRefreshLayout.SUCCEED);
            }
        }.sendEmptyMessageDelayed(0, 5000);
    }
}
