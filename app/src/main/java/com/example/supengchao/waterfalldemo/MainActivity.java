package com.example.supengchao.waterfalldemo;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {

    private RecyclerView mRecyclerView;
    private List<ItemBean> mList;
    private WaterFallDemoAdapter mAdapter;
    private Context context;
    private SwipeRefreshLayout mSwipeRefreshView;
    int lastVisibleItem = 0;
    private StaggeredGridLayoutManager mLayoutManager;
    private int temp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;
        initData();
        mRecyclerView = (RecyclerView)findViewById(R.id.id_recyclerview);
        mSwipeRefreshView = (SwipeRefreshLayout)findViewById(R.id.swipeRefreshView);
         mLayoutManager = new StaggeredGridLayoutManager(4, StaggeredGridLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new WaterFallDemoAdapter(MainActivity.this,mList);
        mRecyclerView.setAdapter(mAdapter);
// 设置item动画
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mSwipeRefreshView.setColorScheme(android.R.color.holo_blue_bright, android.R.color.holo_green_light, android.R.color.holo_orange_light, android.R.color.holo_red_light);

        mSwipeRefreshView.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                lastVisibleItem=0;
                initData();
                mAdapter.setData(mList);
                mSwipeRefreshView.setRefreshing(false);
            }
        });

        initEvent();
    }

    private void initEvent() {
        mAdapter.setOnItemClickListening(new WaterFallDemoAdapter.OnItemClickListener() {
            @Override
            public void setOnItemClickListener(View view, int position) {
                Toast.makeText(context,"click"+position,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void setOnItemLongClickListener(View view, int position) {
                Toast.makeText(context,"long click"+position,Toast.LENGTH_SHORT).show();
                mAdapter.removeData(position);
            }
        });
        mRecyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                int count = mAdapter.getItemCount();

                if(newState == RecyclerView.SCROLL_STATE_IDLE&&lastVisibleItem+1==count){
                    mAdapter.addData(lastVisibleItem+1);
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int[] firstVisibleItemPositions = mLayoutManager.findLastVisibleItemPositions(null);
                for (int firstVisibleItemPosition : firstVisibleItemPositions) {
                    temp = firstVisibleItemPosition;
                    if (lastVisibleItem < temp) {
                        lastVisibleItem = firstVisibleItemPosition;//标记最后一个显示的postion

                    }
                }
//                Toast.makeText(context,"Position"+lastVisibleItem,Toast.LENGTH_SHORT).show();
            }
        });
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


}
