package wang.wangxinarhat.materialdesignsamples.ui.fragment;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;

import wang.wangxinarhat.materialdesignsamples.R;
import wang.wangxinarhat.materialdesignsamples.http.GameRecentData;
import wang.wangxinarhat.materialdesignsamples.interfaces.BaseListLoadData;
import wang.wangxinarhat.materialdesignsamples.ui.adapter.GameRecentAdapter;
import wang.wangxinarhat.materialdesignsamples.ui.adapter.LoadMoreAdapter;

/**
 * Created by wang on 2016/3/8.
 */
public class GameRecentFragment extends BaseRecyclerFragment {

    private BaseListLoadData mData;

    public static GameRecentFragment newInstance() {
        GameRecentFragment fragment = new GameRecentFragment();

        return fragment;
    }


    @Override
    public void init() {

        AppBarLayout appbar = (AppBarLayout) getActivity().findViewById(R.id.activity_game_list_appbar);
        appbar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (verticalOffset >= 0 && mLayoutManager.findFirstVisibleItemPosition() == 0) {
                    mRefreshLayout.setEnabled(true);
                } else {
                    mRefreshLayout.setEnabled(false);
                }
            }
        });


    }

    @Override
    public void getArgs(Bundle b) {

    }

    @Override
    public LoadMoreAdapter onBindAdapter() {
        return new GameRecentAdapter(mData.getData());
    }

    @Override
    public BaseListLoadData onBindData() {
        mData = new GameRecentData(mHandler);
        return mData;
    }
}
