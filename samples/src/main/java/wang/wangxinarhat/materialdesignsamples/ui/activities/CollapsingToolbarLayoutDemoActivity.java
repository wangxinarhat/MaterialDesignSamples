package wang.wangxinarhat.materialdesignsamples.ui.activities;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.BindDrawable;
import butterknife.ButterKnife;
import wang.wangxinarhat.materialdesignsamples.R;
import wang.wangxinarhat.materialdesignsamples.bean.DemoBean;
import wang.wangxinarhat.materialdesignsamples.global.BaseApplication;
import wang.wangxinarhat.materialdesignsamples.utils.ImageLoader;
import wang.wangxinarhat.materialdesignsamples.utils.MyToast;
import wang.wangxinarhat.materialdesignsamples.utils.ResourcesUtils;

/**
 * Created by wang on 2016/3/1.
 */
public class CollapsingToolbarLayoutDemoActivity extends BaseActivity implements ViewPager.OnPageChangeListener, SwipeRefreshLayout.OnRefreshListener, Toolbar.OnMenuItemClickListener {


    @Bind(R.id.activity_collapsingtoolbarlayout_demo_viewpager)
    ViewPager viewpager;
    @Bind(R.id.activity_collapsingtoolbarlayout_demo_viewpager_indicator)
    LinearLayout indicator;
    @Bind(R.id.activity_collapsingtoolbarlayout_demo_swipe_layout)
    SwipeRefreshLayout mSwipeRefreshLayout;

    @Bind(R.id.activity_collapsingtoolbarlayout_demo_appbar)
    AppBarLayout mAppbar;

    @Bind(R.id.activity_collapsingtoolbarlayout_demo_toolbar)
    Toolbar mToolbar;

    @BindDrawable(R.drawable.scenery1)
    Drawable scenery1;

    @BindDrawable(R.drawable.scenery2)
    Drawable scenery2;
    private ArrayList<ImageView> mIcons;


    public static Intent getStartIntent(int position, DemoBean bean) {

        Intent intent = new Intent();
        intent.putExtra("position", position);
        intent.putExtra("bean", bean);
        intent.setClass(BaseApplication.getApplication(), CollapsingToolbarLayoutDemoActivity.class);

        return intent;
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_collapsingtoolbarlayout_demo);


        ButterKnife.bind(this);
        Intent intent = getIntent();
        int position = intent.getIntExtra("position", -1);
        DemoBean bean = intent.getParcelableExtra("bean");

        initView(bean);

        mAppbar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {

                if (verticalOffset >= 0) {
                    mSwipeRefreshLayout.setEnabled(true);
                } else {
                    mSwipeRefreshLayout.setEnabled(false);
                }
            }
        });


        // App Logo
        mToolbar.setLogo(R.mipmap.ic_launcher);
        // Title
        mToolbar.setTitle("My Title");
        mToolbar.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        // Sub Title
        mToolbar.setSubtitle("Sub title");

        setSupportActionBar(mToolbar);

        // Navigation Icon 要設定在 setSupoortActionBar 才有作用,否則會出現 back button
        mToolbar.setNavigationIcon(R.mipmap.ic_back);

        //menu 点击事件
        mToolbar.setOnMenuItemClickListener(this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_detail_share, menu);
        return true;
    }

    private void initView(DemoBean bean) {


        viewpager.setAdapter(new MyPagerAdapter(prepareImageView()));
        viewpager.setOnPageChangeListener(this);
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

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        setIcons(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

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

    @Override
    public boolean onMenuItemClick(MenuItem item) {


        switch (item.getItemId()) {
            case R.id.menu_detail_share_share:


                MyToast.showShortToast(R.string.share);
                break;

        }
        return true;

    }

    protected class MyPagerAdapter extends PagerAdapter {

        private ArrayList<ImageView> imageViewList;

        public MyPagerAdapter(ArrayList<ImageView> imageViewList) {
            this.imageViewList = imageViewList;
        }

        @Override
        public int getCount() {
            return null == imageViewList ? 0 : imageViewList.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {

            return view == object;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {


            View v = imageViewList.get(position);

            ViewGroup parent = (ViewGroup) v.getParent();
            if (parent != null) {
                parent.removeAllViews();
            }

            container.addView(v);

            return v;
        }
    }

    /**
     * 准备ImageView集合
     */
    private ArrayList<ImageView> prepareImageView() {

        //图片展示
        ArrayList<ImageView> imageViewList = new ArrayList<>();

        ArrayList<Integer> drawableList = new ArrayList<>();
        Integer int1 = ResourcesUtils.getResourceId(this, "scenery1", ResourcesUtils.TYPE_DRAWABLE);
        Integer int2 = ResourcesUtils.getResourceId(this, "scenery2", ResourcesUtils.TYPE_DRAWABLE);
        Integer int3 = ResourcesUtils.getResourceId(this, "scenery3", ResourcesUtils.TYPE_DRAWABLE);
        if (-1 != int1) {
            drawableList.add(int1);
        }
        if (-1 != int2) {
            drawableList.add(int2);
        }
        if (-1 != int3) {
            drawableList.add(int3);
        }


        for (int i = 0; i < drawableList.size(); i++) {
            ImageView imageView = new ImageView(this);
            imageView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            imageView.setAdjustViewBounds(true);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);

            ImageLoader.getInstance().loadImage(drawableList.get(i), imageView);

            imageViewList.add(imageView);

        }


        //指示器
        if (null == mIcons) {

            mIcons = new ArrayList<>();

        }

        for (int i = 0; i < drawableList.size(); i++) {
            if (drawableList.size() > 1) {
                ImageView imageView = new ImageView(BaseApplication.getApplication());
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(20, 20);
                lp.setMargins(0, 0, 20, 0);
                imageView.setLayoutParams(lp);
                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                imageView.setImageResource(R.drawable.ic_auto_scroll_view_pager_unshow);
                mIcons.add(imageView);
                indicator.addView(imageView);
            }

        }
        setIcons(0);


        return imageViewList;
    }

    /**
     * 设置ViewPager的指示器图片
     *
     * @param position
     */
    private void setIcons(int position) {
        if (mIcons != null) {
            for (int i = 0; i < mIcons.size(); i++) {
                if (position == i) {
                    mIcons.get(i).setImageResource(R.drawable.ic_auto_scroll_view_pager_show);
                } else {
                    mIcons.get(i).setImageResource(R.drawable.ic_auto_scroll_view_pager_unshow);
                }
            }
        }
    }


}
