package wang.wangxinarhat.materialdesignsamples.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import wang.wangxinarhat.materialdesignsamples.R;
import wang.wangxinarhat.materialdesignsamples.bean.DemoBean;
import wang.wangxinarhat.materialdesignsamples.interfaces.RecyclerOnItemClickListener;
import wang.wangxinarhat.materialdesignsamples.ui.activities.CollapsingToolbarLayoutDemoActivity;
import wang.wangxinarhat.materialdesignsamples.ui.adapter.RecyclerDemoAdapter;
import wang.wangxinarhat.materialdesignsamples.ui.adapter.HeaderAdapter;
import wang.wangxinarhat.materialdesignsamples.utils.FragmentUtils;

/**
 * Created by wang on 2016/3/3.
 */
public class RecyclerViewItemClickDemoFragment extends BaseFragment implements RecyclerOnItemClickListener.OnItemClickListener {


    @Bind(R.id.fragment_recycler_demo_swipe)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @Bind(R.id.fragment_recycler_demo_recycler)
    RecyclerView mRecyclerView;

    private ArrayList<DemoBean> mList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View layout = inflater.inflate(R.layout.fragment_recyclerview_itemclick_demo, container, false);

        ButterKnife.bind(this,layout);
        initRecyclerView();

        return layout;
    }

    private void initRecyclerView() {

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(linearLayoutManager);


        RecyclerDemoAdapter adapter = new RecyclerDemoAdapter(initData(), mContext);


        HeaderAdapter mAdapter = new HeaderAdapter(adapter);

        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addOnItemTouchListener(new RecyclerOnItemClickListener(mContext, mRecyclerView, this));


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

        FragmentUtils.replaceFragment(fm,DetailFragment.class,false);

        startActivity(CollapsingToolbarLayoutDemoActivity.getStartIntent(position, mList.get(position)).setClass(mContext, CollapsingToolbarLayoutDemoActivity.class));
    }

    @Override
    public void onItemLongClick(View view, int position) {

    }

    @Override
    public boolean onBackPressed() {

        fm.popBackStack();
        return true;
    }
}
