package wang.wangxinarhat.materialdesignsamples.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import wang.wangxinarhat.materialdesignsamples.R;

/**
 * Created by Fesen on 2015/12/3.
 */
public abstract class LoadMoreAdapter<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static int VIEW_PROGRESS = -0x101;
    private static int VIEW_END = -0x102;
    private static int VIEW_NORMAL = -0x003;

    protected ArrayList<T> mItems;
    private int mType = VIEW_PROGRESS;
    private RecyclerView.ViewHolder mEndViewHolder;
    private boolean mIsLoading;

    public LoadMoreAdapter(ArrayList<T> items) {
        super();
        mItems = items;
    }

    @Deprecated
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == VIEW_PROGRESS || viewType == VIEW_END) {
            return ProgressViewHolder.newInstance(parent);
        } else {
            return myOnCreateViewHolder(parent, viewType);
        }
    }

    /**
     * 获得该位置下的item
     *
     * @param positon
     */
    public T getItem(int positon) {
        return positon < mItems.size() ? mItems.get(positon) : null;
    }

    /**
     * 删除条目，此动作挺危险的，没什么事就别调用了
     */
    public void deleteItem(int position) {
        if (position < mItems.size()) {
            mItems.remove(position);
            notifyItemRemoved(position);
        }
    }

    @Override
    public int getItemCount() {
        return mItems.size() + 1;
    }

    @Deprecated
    @Override
    public int getItemViewType(int position) {
        if (getItemCount() == 1 || position == getItemCount() - 1) {
            return mType;
        } else {
            return myGetItemViewType(position);
        }
    }


    @Deprecated
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) != VIEW_PROGRESS && getItemViewType(position) != VIEW_END) {
            myOnBindViewHolder(holder, position);
        } else {
            ((ProgressViewHolder) holder).setItemViewVisible(mType == VIEW_END || mIsLoading);
            ((ProgressViewHolder) holder).setIsEnd(mType == VIEW_END);
        }
    }


    public void setIsEnd() {
        mType = VIEW_END;
    }

    public void refresh() {
        mType = VIEW_PROGRESS;
    }

    /**
     * 是否正在加载
     */
    public void setIsLoading(boolean isLoading) {
        mIsLoading = isLoading;
    }

    public abstract void myOnBindViewHolder(RecyclerView.ViewHolder holder, int position);

    public abstract RecyclerView.ViewHolder myOnCreateViewHolder(ViewGroup parent, int viewType);

    public abstract int myGetItemViewType(int position);


    public static class ProgressViewHolder extends RecyclerView.ViewHolder {
        View itemView;
        View loadMore;
        View end;

        public static ProgressViewHolder newInstance(ViewGroup parent) {
            return new ProgressViewHolder(
                    LayoutInflater.from(parent.getContext()).inflate(R.layout.footer_end_and_load_more, parent, false));

        }

        private ProgressViewHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;
            loadMore = itemView.findViewById(R.id.footer_end_and_load_more_load);
            end = itemView.findViewById(R.id.footer_end_and_load_more_end);
        }

        private void setItemViewVisible(boolean visible) {
            itemView.setVisibility(visible ? View.VISIBLE : View.GONE);
        }

        private void setIsEnd(boolean isEnd) {
            loadMore.setVisibility(isEnd ? View.GONE : View.VISIBLE);
            end.setVisibility(isEnd ? View.VISIBLE : View.GONE);
        }
    }
}
