package wang.wangxinarhat.materialdesignsamples.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import wang.wangxinarhat.materialdesignsamples.R;

/**
 * Created by wang on 2016/3/3.
 */
public class DetailFragment extends BaseFragment {
    public static Fragment newInstance() {
        return new DetailFragment();
    }



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.activity_collapsingtoolbarlayout_demo,container,false);

        ButterKnife.bind(this,layout);
        return layout;
    }
}
