package wang.wangxinarhat.materialdesignsamples.ui.widget;

import android.content.Context;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

import wang.wangxinarhat.materialdesignsamples.ui.adapter.LoadMoreAdapter;


/**
 * 自带加载更多接口的RecyclerView
 * Created by Fesen on 2015/12/3.
 */
public class LoadMoreRecyclerView extends RecyclerView {
    private OnLoadMoreListener mLoadMoreListener;
    private final int VISIBLE_THRESHOLD = 5;
    private int lastVisibleItem, totalItemCount;
    private boolean loading;
    private LoadMoreAdapter mAdapter;
    private boolean mIsEnd;//是否全部加载

    public LoadMoreRecyclerView(Context context) {
        super(context);
    }

    public LoadMoreRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public LoadMoreRecyclerView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        setItemAnimator(new DefaultItemAnimator());
    }

    @Override
    public void setLayoutManager(LayoutManager layout) {
        super.setLayoutManager(layout);
        init();
    }

    @Override
    public void setAdapter(Adapter adapter) {
        super.setAdapter(adapter);
        mAdapter = (LoadMoreAdapter) adapter;
    }

    /**
     * 初始化上拉加载
     */
    private void init() {
        if (getLayoutManager() instanceof LinearLayoutManager) {
            final LinearLayoutManager llm = (LinearLayoutManager) getLayoutManager();
            addOnScrollListener(new OnScrollListener() {
                @Override
                public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                    super.onScrolled(recyclerView, dx, dy);
                    if (mIsEnd) {
                        return;
                    }
                    totalItemCount = llm.getItemCount();
                    lastVisibleItem = llm.findLastVisibleItemPosition();
                    if (!loading && totalItemCount <= (lastVisibleItem + VISIBLE_THRESHOLD)) {
                        if (mLoadMoreListener != null) {
                            mLoadMoreListener.onLoadMore();
                        }
                        loading = true;
                        mAdapter.setIsLoading(loading);
                    }
                }
            });
        }
    }

    /**
     * 加载完成
     */
    public void setLoadComplete() {
        loading = false;
        mAdapter.setIsLoading(loading);
    }

    public void refresh() {
        mIsEnd = false;
        loading = true;
        mAdapter.refresh();
    }

    /**
     * 设置是否有分割线
     *
     * @param have
     */
    public void setHaveDivider(boolean have) {
        if (have) {
            addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL_LIST));
        }
    }

    /**
     * 是否是最后一页
     */
    public void setIsEnd() {
        mIsEnd = true;
        mAdapter.setIsEnd();
    }

    /**
     * 设置加载更多接口
     *
     * @param onLoadMoreListener
     */
    public void setOnLoadMoreListener(OnLoadMoreListener onLoadMoreListener) {
        mLoadMoreListener = onLoadMoreListener;
    }

    /**
     * 加载更多接口
     */
    public static interface OnLoadMoreListener {
        void onLoadMore();
    }

}
