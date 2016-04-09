package wang.wangxinarhat.materialdesignsamples.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import wang.wangxinarhat.materialdesignsamples.R;
import wang.wangxinarhat.materialdesignsamples.bean.People;
import wang.wangxinarhat.materialdesignsamples.ui.holder.SearchPeopleHolder;

/**
 * Created by wang on 2016/4/8.
 */
public class FlyRefreshAdapter extends RecyclerView.Adapter<SearchPeopleHolder> {


    private List<People> mList;

    public void setData(List<People> list) {
        this.mList = list;
        notifyDataSetChanged();
    }

    @Override
    public SearchPeopleHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.holder_search_people, parent, false);

        return new SearchPeopleHolder(view);
    }

    @Override
    public void onBindViewHolder(SearchPeopleHolder holder, int position) {
        holder.bind(mList.get(position));
    }

    @Override
    public int getItemCount() {
        return null == mList ? 0 : mList.size();
    }
}
