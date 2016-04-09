package wang.wangxinarhat.materialdesignsamples.ui.activities;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AccelerateInterpolator;

import com.race604.flyrefresh.FlyRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import wang.wangxinarhat.materialdesignsamples.R;
import wang.wangxinarhat.materialdesignsamples.bean.People;
import wang.wangxinarhat.materialdesignsamples.global.BaseApplication;
import wang.wangxinarhat.materialdesignsamples.ui.adapter.FlyRefreshAdapter;

/**
 * Created by wang on 2016/4/7.
 */
public class FlyDemoActivity extends BaseActivity implements FlyRefreshLayout.OnPullRefreshListener {


    @Bind(R.id.recycler)
    RecyclerView recycler;
    @Bind(R.id.fly_layout)
    FlyRefreshLayout flyLayout;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    private LinearLayoutManager mLayoutManager;
    private FlyRefreshAdapter mAdapter;

    private List<People> mPeopleList;
    private Handler mHandler = new Handler();

    public static Intent getStartIntent() {
        Intent intent = new Intent();
        intent.setClass(BaseApplication.getApplication(), FlyDemoActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fly_demo);
        ButterKnife.bind(this);

        initView();
    }

    private void initView() {


        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);



        flyLayout.setOnPullRefreshListener(this);
        View actionButton = flyLayout.getHeaderActionButton();
        if (actionButton != null) {
            actionButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    flyLayout.startRefresh();
                }
            });
        }
//        recycler.setItemAnimator(new FlyItemAnimator());


        mLayoutManager = new LinearLayoutManager(this);
        recycler.setLayoutManager(mLayoutManager);
        mAdapter = new FlyRefreshAdapter();
        mPeopleList = new ArrayList<>();

        String[] peopleName = {"Kaka", "Modric", "Rooney", "Ibla", "Bale", "死神", "Maurice Moss", "Roy Trenneman",};
        String[] peopleDes = {"The best player", "莫德里奇是最好的后腰", "鲁尼踢得不好", "伊贝拉是谁？", "贝尔跑得真快", "Aaron", "Oh, four, I mean five, I mean fire!", "哈哈"};

        for (int i = 0; i < peopleName.length; i++) {
            mPeopleList.add(new People(peopleName[i], peopleDes[i]));
        }

        mAdapter.setData(mPeopleList);
        recycler.setAdapter(mAdapter);



    }

    @Override
    public void onRefresh(FlyRefreshLayout view) {
        View child = recycler.getChildAt(0);
        if (child != null) {
            bounceAnimateView(child.findViewById(R.id.icon));
        }

        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                flyLayout.onRefreshFinish();
            }
        }, 2000);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_pinned_section, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }




    private void bounceAnimateView(View view) {
        if (view == null) {
            return;
        }

        Animator swing = ObjectAnimator.ofFloat(view, "rotationX", 0, 30, -20, 0);
        swing.setDuration(400);
        swing.setInterpolator(new AccelerateInterpolator());
        swing.start();
    }


    @Override
    public void onRefreshAnimationEnd(FlyRefreshLayout view) {
        addItem();
    }

    private void addItem() {
        mPeopleList.add(0,new People("haha","this is new person"));

        mAdapter.notifyItemInserted(0);
        mLayoutManager.scrollToPosition(0);

    }
}
