package wang.wangxinarhat.materialdesignsamples.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import wang.wangxinarhat.materialdesignsamples.R;
import wang.wangxinarhat.materialdesignsamples.bean.People;
import wang.wangxinarhat.materialdesignsamples.ui.holder.SearchPeopleHolder;

/**
 * Created by wang on 2016/3/18.
 */
public class SearchPeopleAdapter extends RecyclerView.Adapter<SearchPeopleHolder> {


    private List<People> mList;
    public SearchPeopleAdapter(List<People> list) {
        this.mList = list;
    }

    @Override
    public SearchPeopleHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.holder_search_people,parent,false);
        return new SearchPeopleHolder(view);
    }

    @Override
    public void onBindViewHolder(SearchPeopleHolder holder, int position) {
        holder.bind(mList.get(position));
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public void setFilter(List<People> peoples){
        mList = new ArrayList<>();
        mList.addAll(peoples);
        notifyDataSetChanged();
    }

    public void animateTo(List<People> peoples) {
        applyAndAnimateRemovals(peoples);
        applyAndAnimateAdditions(peoples);
        applyAndAnimateMovedItems(peoples);
    }

    private void applyAndAnimateRemovals(List<People> peoples) {
        for (int i = mList.size() - 1; i >= 0; i--) {
            final People model = mList.get(i);
            if (!peoples.contains(model)) {
                removeItem(i);
            }
        }
    }

    private void applyAndAnimateAdditions(List<People> peoples) {
        for (int i = 0, count = peoples.size(); i < count; i++) {
            final People model = mList.get(i);
            if (!mList.contains(model)) {
                addItem(i, model);
            }
        }
    }

    private void applyAndAnimateMovedItems(List<People> peoples) {
        for (int toPosition = peoples.size() - 1; toPosition >= 0; toPosition--) {
            final People model = peoples.get(toPosition);
            final int fromPosition = mList.indexOf(model);
            if (fromPosition >= 0 && fromPosition != toPosition) {
                moveItem(fromPosition, toPosition);
            }
        }
    }


    public People removeItem(int position) {
        final People model = mList.remove(position);
        notifyItemRemoved(position);
        return model;
    }


    public void addItem(int position, People model) {
        mList.add(position, model);
        notifyItemInserted(position);
    }

    public void moveItem(int fromPosition, int toPosition) {
        final People model = mList.remove(fromPosition);
        mList.add(toPosition, model);
        notifyItemMoved(fromPosition, toPosition);
    }

}
