package wang.wangxinarhat.materialdesignsamples.ui.activities;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.OvershootInterpolator;

import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;

import butterknife.Bind;
import butterknife.ButterKnife;
import wang.wangxinarhat.materialdesignsamples.R;
import wang.wangxinarhat.materialdesignsamples.global.BaseApplication;
import wang.wangxinarhat.materialdesignsamples.utils.SomeUtils;

/**
 * Created by wang on 2016/8/30.
 */
public class MaterialFloatActionButtonActivity extends BaseActivity implements View.OnClickListener {
    @Bind(R.id.toolbar)
    Toolbar mToolbar;
    @Bind(R.id.recycler)
    RecyclerView mRecycler;
    @Bind(R.id.fab1)
    FloatingActionButton mFab1;
    @Bind(R.id.fab2)
    FloatingActionButton mFab2;
    @Bind(R.id.fab3)
    FloatingActionButton mFab3;
    @Bind(R.id.fam)
    FloatingActionMenu mFam;

    public static Intent getStartIntent() {
        return new Intent(BaseApplication.getApplication(), MaterialFloatActionButtonActivity.class);
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_fab);

        ButterKnife.bind(this);


        mFam.setClosedOnTouchOutside(true);

        createCustomAnimation();
        mFab1.setOnClickListener(this);
        mFab2.setOnClickListener(this);
        mFab3.setOnClickListener(this);
    }


    private void createCustomAnimation() {
        AnimatorSet set = new AnimatorSet();

        ObjectAnimator scaleOutX = ObjectAnimator.ofFloat(mFam.getMenuIconView(), "scaleX", 1.0f, 0.2f);
        ObjectAnimator scaleOutY = ObjectAnimator.ofFloat(mFam.getMenuIconView(), "scaleY", 1.0f, 0.2f);

        ObjectAnimator scaleInX = ObjectAnimator.ofFloat(mFam.getMenuIconView(), "scaleX", 0.2f, 1.0f);
        ObjectAnimator scaleInY = ObjectAnimator.ofFloat(mFam.getMenuIconView(), "scaleY", 0.2f, 1.0f);

        scaleOutX.setDuration(50);
        scaleOutY.setDuration(50);

        scaleInX.setDuration(150);
        scaleInY.setDuration(150);

        scaleInX.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                mFam.getMenuIconView().setImageResource(mFam.isOpened()
                        ? R.mipmap.ic_close : R.mipmap.ic_star);
            }
        });

        set.play(scaleOutX).with(scaleOutY);
        set.play(scaleInX).with(scaleInY).after(scaleOutX);
        set.setInterpolator(new OvershootInterpolator(2));

        mFam.setIconToggleAnimatorSet(set);
    }

    @Override
    public void onClick(View v) {
        if (mFam.isOpened()) {
            String msg = null;
            switch (v.getId()) {
                case R.id.fab1:
                    msg = "fab1";
                    break;
                case R.id.fab2:
                    msg = "fab2";

                    break;
                case R.id.fab3:
                    msg = "fab3";

                    break;
            }
            SomeUtils.shortSnackbar(mRecycler, msg);
        }

        mFam.toggle(true);
    }
}
