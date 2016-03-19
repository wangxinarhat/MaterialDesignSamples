package wang.wangxinarhat.materialdesignsamples.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import wang.wangxinarhat.materialdesignsamples.R;
import wang.wangxinarhat.materialdesignsamples.interfaces.RecyclerOnItemClickListener;
import wang.wangxinarhat.materialdesignsamples.ui.adapter.HeaderAdapter;
import wang.wangxinarhat.materialdesignsamples.ui.adapter.MainFragmentAdapter;
import wang.wangxinarhat.materialdesignsamples.utils.FragmentUtils;

/**
 * Created by wang on 2016/3/3.
 */
public class MainFragment extends BaseFragment implements RecyclerOnItemClickListener.OnItemClickListener, SwipeRefreshLayout.OnRefreshListener {

    @Bind(R.id.fragment_main_swipe)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @Bind(R.id.fragment_main_recycler)
    RecyclerView mRecyclerView;

    private ArrayList<String> mList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View layout = inflater.inflate(R.layout.fragment_main, container, false);

        ButterKnife.bind(this, layout);
        initRecyclerView();
        initSwipeRefreshLayout();

        return layout;
    }

    private void initSwipeRefreshLayout() {


        int[] colors = getResources().getIntArray(R.array.refresh_color);
        mSwipeRefreshLayout.setColorSchemeColors(colors);

        // 第一次进入页面的时候显示加载进度条
        mSwipeRefreshLayout.setProgressViewOffset(false, 0, (int) TypedValue
                .applyDimension(TypedValue.COMPLEX_UNIT_DIP, 24, getResources()
                        .getDisplayMetrics()));


        mSwipeRefreshLayout.setOnRefreshListener(this);

    }

    private void initRecyclerView() {

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        MainFragmentAdapter adapter = new MainFragmentAdapter(initData(), mContext);
        HeaderAdapter mAdapter = new HeaderAdapter(adapter);

        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addOnItemTouchListener(new RecyclerOnItemClickListener(mContext, mRecyclerView, this));


    }


    private ArrayList<String> initData() {
        if (null == mList) {
            mList = new ArrayList<>();
        }

        mList.add("RecyclerView条目点击Demo");
//        mList.add(getString(R.string.recycler_itemclick_demo));

        return mList;
    }

    @Override
    public void onItemClick(View view, int position) {

        switch (position) {

            case 0:
                FragmentUtils.replaceFragment(fm, RecyclerViewItemClickDemoFragment.class, true);
                break;
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
        }

    }

    @Override
    public void onItemLongClick(View view, int position) {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void onRefresh() {
        //TODO pullToRrefresh
    }


}
