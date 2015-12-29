package view.recycleview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

import view.pulltorefresh.Pullable;

/**
 * Created by supengchao on 2015/11/11.
 */
public class RefreshRecycleView extends RecyclerView implements Pullable {
//    private int lastChildPosition;
//    private int firstChildPosition;
//    private int allChildPosition;

    public RefreshRecycleView(Context context) {
        super(context);
//        initData();
    }

    public RefreshRecycleView(Context context, AttributeSet attrs) {
        super(context, attrs);
//        initData();
    }


//    private void initData() {
//        lastChildPosition = ((MyStaggeredGridLayoutManager) getLayoutManager()).getLastChildPosition();
//        firstChildPosition = ((MyStaggeredGridLayoutManager) getLayoutManager()).getFirstChildPosition();
//        allChildPosition = ((MyStaggeredGridLayoutManager) getLayoutManager()).getAllChildCount();
//    }

    public RefreshRecycleView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public boolean canPullDown() {

        return true;

    }

    @Override
    public boolean canPullUp() {
        return true;
    }
}
