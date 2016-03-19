package wang.wangxinarhat.materialdesignsamples.ui.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import wang.wangxinarhat.materialdesignsamples.R;
import wang.wangxinarhat.materialdesignsamples.global.STATUS;
import wang.wangxinarhat.materialdesignsamples.interfaces.BaseListLoadData;
import wang.wangxinarhat.materialdesignsamples.ui.adapter.LoadMoreAdapter;
import wang.wangxinarhat.materialdesignsamples.ui.widget.BaseLoadingLayout;
import wang.wangxinarhat.materialdesignsamples.ui.widget.LoadMoreRecyclerView;

/**
 * Created by wang on 2016/3/8.
 */
public abstract class BaseRecyclerFragment extends Fragment
        implements BaseLoadingLayout.OnReloadListener, Handler.Callback, LoadMoreRecyclerView.OnLoadMoreListener
        , SwipeRefreshLayout.OnRefreshListener {
    private BaseLoadingLayout mLoadingLayout;
    private LoadMoreRecyclerView mRecyclerView;
    protected SwipeRefreshLayout mRefreshLayout;

    protected LinearLayoutManager mLayoutManager;
    private LoadMoreAdapter mLoadMoreAdapter;
    private BaseListLoadData mData;
    protected Handler mHandler;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mHandler = new Handler(this);
        init();
        getArgs(getArguments());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mLoadingLayout = (BaseLoadingLayout) inflater.inflate(R.layout.layout_base_loading, container, false);
        mRefreshLayout = (SwipeRefreshLayout) LayoutInflater.from(getContext()).inflate(R.layout.fragment_base_recycler, null);
        mRecyclerView = (LoadMoreRecyclerView) mRefreshLayout.findViewById(R.id.fragment_base_recycler_recycler_view);

        mRefreshLayout.setOnRefreshListener(this);
        if (isAdded()) {
            mRefreshLayout.setColorSchemeColors(getResources().getIntArray(R.array.refresh_color));
        }
        mLoadingLayout.setContentView(mRefreshLayout);
        mLoadingLayout.setOnReloadListener(this);

        mData = onBindData();
        mLoadMoreAdapter = onBindAdapter();

        mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mLoadMoreAdapter);
        mRecyclerView.setOnLoadMoreListener(this);

        onReload();
        return mLoadingLayout;
    }

    /**
     * 可以在此方法种初始化一些数据
     */
    public abstract void init();

    /**
     * 获得arguments
     *
     * @param b
     */
    public abstract void getArgs(Bundle b);

    /**
     * 绑定继承自LoadMoreAdapter的adapter
     *
     * @return
     */
    public abstract LoadMoreAdapter onBindAdapter();

    /**
     * 绑定网络请求数据
     *
     * @return
     */
    public abstract BaseListLoadData onBindData();

    /**
     * 点击按钮重新加载
     */
    @Override
    public void onReload() {
        mData.reLoad();
        mRecyclerView.refresh();
    }

    /**
     * 下拉刷新
     */
    @Override
    public void onRefresh() {
        mData.reLoad();
        mRecyclerView.refresh();
    }

    /**
     * 上拉加载
     */
    @Override
    public void onLoadMore() {
        mData.load(false);
    }

    public void setRefreshEnabled(boolean enabled) {
        mRefreshLayout.setEnabled(enabled);
    }

    @Override
    public boolean handleMessage(Message msg) {
        switch (msg.what) {
            case STATUS.LOAD_LOADING:
                mLoadingLayout.setLoadStatus(STATUS.LOAD_LOADING);
                break;
            case STATUS.LOAD_EMPTY:
                mLoadingLayout.setLoadStatus(STATUS.LOAD_EMPTY);
                break;
            case STATUS.LOAD_ERROR:
                mLoadingLayout.setLoadStatus(STATUS.LOAD_ERROR);
                break;
            case STATUS.LOAD_SUCCESS:
                mLoadingLayout.setLoadStatus(STATUS.LOAD_SUCCESS);
                mLoadMoreAdapter.notifyItemInserted(mLoadMoreAdapter.getItemCount() - 1);
                break;
            case STATUS.LOAD_RELOAD_SUCCESS:
                mRecyclerView.refresh();
                mLoadMoreAdapter.notifyDataSetChanged();
                mLoadingLayout.setLoadStatus(STATUS.LOAD_SUCCESS);
                break;
            case STATUS.LOAD_ALL:
                mRecyclerView.setIsEnd();
                mLoadMoreAdapter.notifyDataSetChanged();
                mLoadingLayout.setLoadStatus(STATUS.LOAD_SUCCESS);
                break;
            case STATUS.LOAD_COMPLETE:
                break;
        }
        mRecyclerView.setLoadComplete();
        mRefreshLayout.setRefreshing(false);
        return true;
    }

    public LoadMoreRecyclerView getRecyclerView() {
        return mRecyclerView;
    }
}
