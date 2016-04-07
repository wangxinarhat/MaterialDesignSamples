package wang.wangxinarhat.materialdesignsamples.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.race604.flyrefresh.FlyRefreshLayout;

import butterknife.Bind;
import butterknife.ButterKnife;
import wang.wangxinarhat.materialdesignsamples.R;
import wang.wangxinarhat.materialdesignsamples.global.BaseApplication;

/**
 * Created by wang on 2016/4/7.
 */
public class FlyDemoActivity extends BaseActivity {


    @Bind(R.id.recycler)
    RecyclerView recycler;
    @Bind(R.id.fly_layout)
    FlyRefreshLayout flyLayout;
    @Bind(R.id.toolbar)
    Toolbar toolbar;

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
    }

}
