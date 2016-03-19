package wang.wangxinarhat.materialdesignsamples.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.BaseExpandableListAdapter;

import com.diegocarloslima.fgelv.lib.FloatingGroupExpandableListView;
import com.diegocarloslima.fgelv.lib.WrapperExpandableListAdapter;

import wang.wangxinarhat.materialdesignsamples.R;
import wang.wangxinarhat.materialdesignsamples.global.BaseApplication;
import wang.wangxinarhat.materialdesignsamples.ui.adapter.MyFloatGroupAdapter;

/**
 * Created by wang on 2016/3/11.
 */
public class FloatingGroupExpandableListViewActivity extends BaseActivity {

    private FloatingGroupExpandableListView myList;

    public static Intent getStartIntent() {
        Intent intent = new Intent();
        intent.setClass(BaseApplication.getApplication(), FloatingGroupExpandableListViewActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_float_group_expandable);

        initView();
    }

    private void initView() {
        myList = (FloatingGroupExpandableListView) findViewById(R.id.my_list);

        BaseExpandableListAdapter myAdapter = new MyFloatGroupAdapter();
        WrapperExpandableListAdapter wrapperAdapter = new WrapperExpandableListAdapter(myAdapter);
        myList.setAdapter(wrapperAdapter);
    }
}
