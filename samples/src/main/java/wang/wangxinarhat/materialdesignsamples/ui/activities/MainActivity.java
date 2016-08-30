package wang.wangxinarhat.materialdesignsamples.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import wang.wangxinarhat.materialdesignsamples.R;
import wang.wangxinarhat.materialdesignsamples.interfaces.RecyclerOnItemClickListener;
import wang.wangxinarhat.materialdesignsamples.service.PoService;
import wang.wangxinarhat.materialdesignsamples.ui.adapter.HeaderAdapter;
import wang.wangxinarhat.materialdesignsamples.ui.adapter.MainFragmentAdapter;

public class MainActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener, SwipeRefreshLayout.OnRefreshListener, RecyclerOnItemClickListener.OnItemClickListener {

    @Bind(R.id.content_main_swipe)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @Bind(R.id.content_main_recycler)
    RecyclerView mRecyclerView;
    @Bind(R.id.toolbar)
    Toolbar mToolbar;
    @Bind(R.id.fab)
    FloatingActionButton mFab;
    @Bind(R.id.drawer_layout)
    DrawerLayout mDrawer;
    @Bind(R.id.nav_view)
    NavigationView mNavigationView;

    private ArrayList<String> mList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        startService(new Intent(this, PoService.class));

        setSupportActionBar(mToolbar);

        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mDrawer, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawer.setDrawerListener(toggle);
        toggle.syncState();

        mNavigationView.setNavigationItemSelectedListener(this);


        initRecyclerView();
        initSwipeRefreshLayout();


    }

    private void initSwipeRefreshLayout() {


        int[] colors = getResources().getIntArray(R.array.refresh_color);
        mSwipeRefreshLayout.setColorSchemeColors(colors);

        mSwipeRefreshLayout.setProgressViewOffset(false, 0, (int) TypedValue
                .applyDimension(TypedValue.COMPLEX_UNIT_DIP, 24, getResources()
                        .getDisplayMetrics()));


        mSwipeRefreshLayout.setOnRefreshListener(this);

    }

    private void initRecyclerView() {

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        MainFragmentAdapter adapter = new MainFragmentAdapter(initData(), this);
        HeaderAdapter mAdapter = new HeaderAdapter(adapter);

        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addOnItemTouchListener(new RecyclerOnItemClickListener(this, mRecyclerView, this));


    }


    private ArrayList<String> initData() {
        if (null == mList) {
            mList = new ArrayList<>();
        }

        mList.add("RecyclerView条目点击Demo");
        mList.add("CoordinatorLayout & TabLayout 结合ViewPagerDemo");
        mList.add("仿qq联系人列表Demo");
        mList.add("Toolbar上searchView实现");
        mList.add("筛选Demo");

        mList.add("纸飞机Demo");

        mList.add("PinnedHeaderExpandableListViewDemoActivity    Demo");
        mList.add("FloatingGroupExpandableListView  Demo");
        mList.add("EditText限制输入限制Demo");
        mList.add("监听手机屏幕状态Demo");
        mList.add("Material Design FloatActionButton类似印象笔记");
//        mList.add(getString(R.string.recycler_itemclick_demo));

        return mList;
    }

    @Override
    public void onItemClick(View view, int position) {

        switch (position) {

            case 0:

                startActivity(RecyclerViewDemoActivity.getStartIntent());

                break;
            case 1:
                startActivity(TabLayoutDemoActivity.getStartIntent());
                break;
            case 2:
                startActivity(ExpandableListActivity.getStartIntent());
                break;


            case 3:
                startActivity(ToolbarSearchDemoActivity.getStartIntent());
                break;
            case 4:
                startActivity(FilterDemoActivity.getStartIntent());
                break;

            case 5:
                startActivity(FlyDemoActivity.getStartIntent());
                break;


            case 6:
                startActivity(PinnedHeaderExpandableListViewDemoActivity.getStartIntent());
                break;

            case 7:
                startActivity(FloatingGroupExpandableListViewActivity.getStartIntent());
                break;

            case 8:
                startActivity(EditTextDecimalLimitActivity.getStartIntent());
                break;
            case 9:
                startActivity(ScreenStatusActivity.getStartIntent());
                break;

            case 10:
                startActivity(MaterialFloatActionButtonActivity.getStartIntent());
                break;
        }

    }

    @Override
    public void onItemLongClick(View view, int position) {

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    @Override
    public void onRefresh() {


        mSwipeRefreshLayout.setRefreshing(true);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mSwipeRefreshLayout.setRefreshing(false);
            }
        }, 2000);
    }


    @Override
    public void onBackPressed() {


        if (mDrawer.isDrawerOpen(GravityCompat.START)) {
            mDrawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        mDrawer.closeDrawer(GravityCompat.START);
        return true;
    }


}
