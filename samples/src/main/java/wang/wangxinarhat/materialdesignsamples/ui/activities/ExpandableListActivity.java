package wang.wangxinarhat.materialdesignsamples.ui.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.Toast;

import java.util.ArrayList;

import wang.wangxinarhat.materialdesignsamples.R;
import wang.wangxinarhat.materialdesignsamples.bean.ExpanbleGroup;
import wang.wangxinarhat.materialdesignsamples.bean.expanbleDemoBean;
import wang.wangxinarhat.materialdesignsamples.global.BaseApplication;
import wang.wangxinarhat.materialdesignsamples.ui.adapter.MyBaseExpandableListAdapter;

/**
 * Created by wang on 2016/3/10.
 */
public class ExpandableListActivity extends BaseActivity {


    private ArrayList<ExpanbleGroup> gData = null;
    private ArrayList<ArrayList<expanbleDemoBean>> iData = null;
    private ArrayList<expanbleDemoBean> lData = null;
    private Context mContext;
    private ExpandableListView exlist_lol;
    private MyBaseExpandableListAdapter myAdapter = null;

    public static Intent getStartIntent() {
        Intent intent = new Intent();
        intent.setClass(BaseApplication.getApplication(), ExpandableListActivity.class);
        return intent;
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expandablelist);
        mContext = ExpandableListActivity.this;
        exlist_lol = (ExpandableListView) findViewById(R.id.exlist_lol);


        //数据准备
        gData = new ArrayList<ExpanbleGroup>();
        iData = new ArrayList<ArrayList<expanbleDemoBean>>();
        gData.add(new ExpanbleGroup("AD"));
        gData.add(new ExpanbleGroup("AP"));
        gData.add(new ExpanbleGroup("TANK"));

        lData = new ArrayList<expanbleDemoBean>();

        //AD组
        lData.add(new expanbleDemoBean(R.mipmap.default_avatar,"剑圣"));
        lData.add(new expanbleDemoBean(R.mipmap.ic_default,"德莱文"));
        lData.add(new expanbleDemoBean(R.mipmap.ic_operate_chat,"男枪"));
        lData.add(new expanbleDemoBean(R.mipmap.ic_geography_mark_green,"韦鲁斯"));
        iData.add(lData);
        //AP组
        lData = new ArrayList<expanbleDemoBean>();
        lData.add(new expanbleDemoBean(R.mipmap.ic_operate_call, "提莫"));
        lData.add(new expanbleDemoBean(R.mipmap.ic_operate_call, "安妮"));
        lData.add(new expanbleDemoBean(R.mipmap.ic_operate_call, "天使"));
        lData.add(new expanbleDemoBean(R.mipmap.ic_operate_call, "泽拉斯"));
        lData.add(new expanbleDemoBean(R.mipmap.ic_operate_call, "狐狸"));
        iData.add(lData);
        //TANK组
        lData = new ArrayList<expanbleDemoBean>();
        lData.add(new expanbleDemoBean(R.mipmap.ic_operate_chat, "诺手"));
        lData.add(new expanbleDemoBean(R.mipmap.ic_operate_chat, "德邦"));
        lData.add(new expanbleDemoBean(R.mipmap.ic_operate_chat, "奥拉夫"));
        lData.add(new expanbleDemoBean(R.mipmap.ic_operate_chat, "龙女"));
        lData.add(new expanbleDemoBean(R.mipmap.ic_operate_chat, "狗熊"));
        iData.add(lData);

        myAdapter = new MyBaseExpandableListAdapter(gData,iData,mContext);
        exlist_lol.setAdapter(myAdapter);

        //为列表设置点击事件
        exlist_lol.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                Toast.makeText(mContext, "你点击了：" + iData.get(groupPosition).get(childPosition).getName(), Toast.LENGTH_SHORT).show();
                return true;
            }
        });


    }
}
