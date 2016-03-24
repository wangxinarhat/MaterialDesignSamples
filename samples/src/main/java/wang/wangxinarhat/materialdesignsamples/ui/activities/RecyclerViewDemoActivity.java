package wang.wangxinarhat.materialdesignsamples.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.View;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import wang.wangxinarhat.materialdesignsamples.R;
import wang.wangxinarhat.materialdesignsamples.bean.DemoBean;
import wang.wangxinarhat.materialdesignsamples.global.BaseApplication;
import wang.wangxinarhat.materialdesignsamples.interfaces.RecyclerOnItemClickListener;
import wang.wangxinarhat.materialdesignsamples.ui.adapter.HeaderAdapter;
import wang.wangxinarhat.materialdesignsamples.ui.adapter.RecyclerDemoAdapter;

/**
 * Created by wang on 2016/3/4.
 */
public class RecyclerViewDemoActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener, RecyclerOnItemClickListener.OnItemClickListener {


    @Bind(R.id.activity_recycler_demo_swipe)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @Bind(R.id.activity_recycler_demo_recycler)
    RecyclerView mRecyclerView;

    private ArrayList<DemoBean> mList;

    public static Intent getStartIntent() {


        return new Intent(BaseApplication.getApplication(), RecyclerViewDemoActivity.class);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_recycler_demo);

        ButterKnife.bind(this);
        initRecyclerView();
        initSwipeRefreshLayout();

    }


    private void initRecyclerView() {

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(linearLayoutManager);


        RecyclerDemoAdapter adapter = new RecyclerDemoAdapter(initData(), this);


        HeaderAdapter mAdapter = new HeaderAdapter(adapter);

        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addOnItemTouchListener(new RecyclerOnItemClickListener(this, mRecyclerView, this));


    }

    private void initSwipeRefreshLayout() {


        int[] colors = getResources().getIntArray(R.array.refresh_color);
        mSwipeRefreshLayout.setColorSchemeColors(colors);

        mSwipeRefreshLayout.setProgressViewOffset(false, 0, (int) TypedValue
                .applyDimension(TypedValue.COMPLEX_UNIT_DIP, 24, getResources()
                        .getDisplayMetrics()));






        mSwipeRefreshLayout.setOnRefreshListener(this);

    }


    private ArrayList<DemoBean> initData() {
        if (null == mList) {
            mList = new ArrayList<>();
        }

        mList.add(new DemoBean(getString(R.string.news_one_title), getString(R.string.news_one_desc), R.mipmap.news_one));
        mList.add(new DemoBean(getString(R.string.news_two_title), getString(R.string.news_two_desc), R.mipmap.news_two));
        mList.add(new DemoBean(getString(R.string.news_three_title), getString(R.string.news_three_desc), R.mipmap.news_three));
        mList.add(new DemoBean(getString(R.string.news_four_title), getString(R.string.news_four_desc), R.mipmap.news_four));

        return mList;
    }

    @Override
    public void onItemClick(View view, int position) {

        startActivity(CollapsingToolbarLayoutDemoActivity.getStartIntent(position, mList.get(position)));
    }

    @Override
    public void onItemLongClick(View view, int position) {

    }


    @Override
    public void onRefresh() {
        mSwipeRefreshLayout.setRefreshing(true);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mSwipeRefreshLayout.setRefreshing(false);
            }
        }, 3000);

    }
}
