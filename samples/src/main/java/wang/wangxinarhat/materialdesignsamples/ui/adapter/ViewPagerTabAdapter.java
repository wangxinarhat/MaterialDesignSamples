package wang.wangxinarhat.materialdesignsamples.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by wang on 2016/3/8.
 */
public class ViewPagerTabAdapter extends FragmentPagerAdapter {
    private ArrayList<Fragment> mItems;
    private ArrayList<String> mTitles;

    public static ViewPagerTabAdapter newInstance(FragmentManager fm, ArrayList<Fragment> items, ArrayList<String> titles) {
        return new ViewPagerTabAdapter(fm, items, titles);
    }

    private ViewPagerTabAdapter(FragmentManager fm, ArrayList<Fragment> items, ArrayList<String> titles) {
        super(fm);
        mItems = items;
        mTitles = titles;
    }

    @Override
    public Fragment getItem(int position) {
        return mItems != null ? mItems.get(position) : null;
    }

    @Override
    public int getCount() {
        return mItems != null ? mItems.size() : 0;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
//        super.destroyItem(container, position, object);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles != null && !mTitles.isEmpty() ? mTitles.get(position) : "";
    }

}
