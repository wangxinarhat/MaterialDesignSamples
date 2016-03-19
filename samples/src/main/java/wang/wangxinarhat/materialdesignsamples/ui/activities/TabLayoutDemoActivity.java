package wang.wangxinarhat.materialdesignsamples.ui.activities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import wang.wangxinarhat.materialdesignsamples.R;
import wang.wangxinarhat.materialdesignsamples.global.BaseApplication;
import wang.wangxinarhat.materialdesignsamples.ui.adapter.ViewPagerTabAdapter;
import wang.wangxinarhat.materialdesignsamples.ui.fragment.BaseFragment;
import wang.wangxinarhat.materialdesignsamples.ui.fragment.GameDoublesRankFragment;
import wang.wangxinarhat.materialdesignsamples.ui.fragment.GameOverFragment;
import wang.wangxinarhat.materialdesignsamples.ui.fragment.GameRecentFragment;
import wang.wangxinarhat.materialdesignsamples.ui.fragment.GameSingleRankFragment;
import wang.wangxinarhat.materialdesignsamples.utils.MyToast;

/**
 * Created by wang on 2016/3/8.
 */
public class TabLayoutDemoActivity extends BaseActivity implements BaseFragment.BackHandlerInterface, Toolbar.OnMenuItemClickListener {


    //    @Bind(R.id.activity_game_list_swipe)
//    SwipeRefreshLayout mSwipeRefreshLayout;
    @Bind(R.id.activity_game_list_coordinator)
    CoordinatorLayout mCoordinator;
    @Bind(R.id.activity_game_list_appbar)
    AppBarLayout mAppBar;
    @Bind(R.id.activity_game_list_tablayout)
    TabLayout mTabLayout;
    @Bind(R.id.activity_game_list_toolbar)
    Toolbar mToolbar;
    @Bind(R.id.activity_game_list_viewpager)
    ViewPager mViewPager;


    @Bind(R.id.activity_game_list_toolbar_title)
    TextView mToolbarTitle;


    private ArrayList<Fragment> fragments;

    public static Intent getStartIntent() {
        return new Intent(BaseApplication.getApplication(), TabLayoutDemoActivity.class);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_tablayout_demo);
        ButterKnife.bind(this);

        initData();
    }

    private void initData() {
//        initSwipeRefreshLayout();

        initToolBar();

        setUpViewPager(mViewPager);
        mTabLayout.setupWithViewPager(mViewPager);//给TabLayout设置关联ViewPager，如果设置了ViewPager，那么ViewPagerAdapter中的getPageTitle()方法返回的就是Tab上的标题
    }

    private void initToolBar() {
        mToolbar.setTitleTextColor(Color.WHITE);//设置ToolBar的titl颜色
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);


        mToolbarTitle.setText("比赛");

// 否則會出現 back button
        mToolbar.setNavigationIcon(R.mipmap.ic_back);

        mToolbar.setOnMenuItemClickListener(this);


    }

    private void setUpViewPager(ViewPager viewPager) {
        fragments = new ArrayList<>();

        fragments.add(GameRecentFragment.newInstance());
        fragments.add(GameOverFragment.newInstance());
        fragments.add(GameSingleRankFragment.newInstance());
        fragments.add(GameDoublesRankFragment.newInstance());

        ArrayList<String> titles = new ArrayList<>();
        titles.add("近期");
        titles.add("已结束");
        titles.add("单打排名");
        titles.add("双打排名");

        ViewPagerTabAdapter adapter = ViewPagerTabAdapter.newInstance(getSupportFragmentManager()
                , fragments, titles);
        viewPager.setAdapter(adapter);
    }

//    private void initSwipeRefreshLayout() {
//
//        int[] colors = getResources().getIntArray(R.array.refresh_color);
//        mSwipeRefreshLayout.setColorSchemeColors(colors);
//
//        mSwipeRefreshLayout.setProgressViewOffset(false, 0, (int) TypedValue
//                .applyDimension(TypedValue.COMPLEX_UNIT_DIP, 24, getResources()
//                        .getDisplayMetrics()));
//
//
//        mSwipeRefreshLayout.setOnRefreshListener(this);
//
//    }

//    @Override
//    public void onRefresh() {
//        mSwipeRefreshLayout.setRefreshing(true);
//
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                mSwipeRefreshLayout.setRefreshing(false);
//            }
//        }, 3000);
//
//    }
//

    @Override
    public void setSelectedFragment(BaseFragment backHandledFragment) {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_detail_share, menu);
        return true;
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_detail_share_share:


                MyToast.showShortToast(R.string.share);
                break;

        }
        return true;
    }


}
