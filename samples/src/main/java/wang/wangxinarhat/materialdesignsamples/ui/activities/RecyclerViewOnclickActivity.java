package wang.wangxinarhat.materialdesignsamples.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import wang.wangxinarhat.materialdesignsamples.R;
import wang.wangxinarhat.materialdesignsamples.bean.DemoBean;
import wang.wangxinarhat.materialdesignsamples.global.BaseApplication;
import wang.wangxinarhat.materialdesignsamples.interfaces.OnRecyclerViewItemClickListener;
import wang.wangxinarhat.materialdesignsamples.interfaces.OnRecyclerViewItemLongClickListener;
import wang.wangxinarhat.materialdesignsamples.ui.adapter.RecyclerItemAdapter;
import wang.wangxinarhat.materialdesignsamples.utils.SomeUtils;

/**
 * Created by wang on 2016/9/9.
 */
public class RecyclerViewOnclickActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener, OnRecyclerViewItemLongClickListener, OnRecyclerViewItemClickListener {
    @Bind(R.id.toolbar)
    Toolbar mToolbar;
    @Bind(R.id.activity_recycler_demo_recycler)
    RecyclerView mRecycler;
    @Bind(R.id.activity_recycler_demo_swipe)
    SwipeRefreshLayout mSwipe;
    private RecyclerItemAdapter mAdapter;

    public static Intent getStartIntent() {
        return new Intent(BaseApplication.getApplication(), RecyclerViewOnclickActivity.class);
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_recycler_demo);

        ButterKnife.bind(this);
        SomeUtils.initSwipeRefreshLayout(mSwipe);
        initRecyclerView();

    }


    private void initRecyclerView() {

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mRecycler.setHasFixedSize(true);
        mRecycler.setLayoutManager(linearLayoutManager);
        mAdapter = new RecyclerItemAdapter();
        mRecycler.setAdapter(mAdapter);


        mAdapter.updateData(initData(), false);

        mAdapter.setOnItemClickListener(this);
        mAdapter.setOnItemLongClickListener(this);

        mSwipe.setOnRefreshListener(this);

    }


    private List<DemoBean> mList;

    private List<DemoBean> initData() {
        if (null == mList) {
            mList = new ArrayList<>();
        } else {
            mList.clear();
        }

        mList.add(new DemoBean(getString(R.string.news_one_title), getString(R.string.news_one_desc), R.mipmap.news_one));
        mList.add(new DemoBean(getString(R.string.news_two_title), getString(R.string.news_two_desc), R.mipmap.news_two));
        mList.add(new DemoBean(getString(R.string.news_three_title), getString(R.string.news_three_desc), R.mipmap.news_three));
        mList.add(new DemoBean(getString(R.string.news_four_title), getString(R.string.news_four_desc), R.mipmap.news_four));

        return mList;
    }

    @Override
    public void onRefresh() {
        mAdapter.updateData(initData(), true);
    }

    @Override
    public boolean onItemLongClick(View itemView, int position) {

        SomeUtils.shortSnackbar(mSwipe, itemView.getClass().getSimpleName() + " onItemLongClick  position   " + position);
        return true;
    }

    @Override
    public void onItemClick(View itemView, int position) {
        SomeUtils.shortSnackbar(mSwipe, itemView.getClass().getSimpleName() + "  onItemClick   position   " + position);

    }
}
