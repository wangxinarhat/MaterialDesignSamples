package wang.wangxinarhat.materialdesignsamples.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;

import wang.wangxinarhat.materialdesignsamples.R;
import wang.wangxinarhat.materialdesignsamples.bean.GameListBean;
import wang.wangxinarhat.materialdesignsamples.ui.holder.GameRecentHolder;

/**
 * Created by wang on 2016/3/8.
 */
public class GameRecentAdapter extends LoadMoreAdapter<GameListBean> {
    public GameRecentAdapter(ArrayList<GameListBean> items) {
        super(items);
    }

    @Override
    public void myOnBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        ((GameRecentHolder) holder).setData(mItems.get(position));
    }

    @Override
    public RecyclerView.ViewHolder myOnCreateViewHolder(ViewGroup parent, int viewType) {
        return new GameRecentHolder(LayoutInflater
                .from(parent.getContext()).inflate(R.layout.item_list_recent_game, parent, false));

    }

    @Override
    public int myGetItemViewType(int position) {
        return 0;
    }
}
