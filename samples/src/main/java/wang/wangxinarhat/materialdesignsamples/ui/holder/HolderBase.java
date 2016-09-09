package wang.wangxinarhat.materialdesignsamples.ui.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import wang.wangxinarhat.materialdesignsamples.interfaces.OnHolderClickListener;
import wang.wangxinarhat.materialdesignsamples.interfaces.OnHolderLongClickListener;

/**
 * Created by wang on 2016/7/22.
 */
public abstract class HolderBase<T> extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

    protected OnHolderClickListener mOnHolderClickListener;
    protected OnHolderLongClickListener mOnHolderLongClickListener;

    public HolderBase(View itemView) {
        super(itemView);
    }

    public HolderBase(View itemView, OnHolderClickListener onHolderClickListener) {
        super(itemView);
        itemView.setOnClickListener(this);
        this.mOnHolderClickListener = onHolderClickListener;
    }


    public HolderBase(View itemView, OnHolderLongClickListener onHolderLongClickListener) {
        super(itemView);
        itemView.setOnLongClickListener(this);
        this.mOnHolderLongClickListener = onHolderLongClickListener;
    }


    public HolderBase(View itemView, OnHolderClickListener onHolderClickListener, OnHolderLongClickListener onHolderLongClickListener) {
        super(itemView);
        itemView.setOnClickListener(this);
        itemView.setOnLongClickListener(this);
        this.mOnHolderClickListener = onHolderClickListener;
        this.mOnHolderLongClickListener = onHolderLongClickListener;
    }


    protected abstract void bindData(T t);

    @Override
    public void onClick(View v) {
        if (null != mOnHolderClickListener) {
            mOnHolderClickListener.onHolderClick(v, getLayoutPosition());
        }
    }


    @Override
    public boolean onLongClick(View v) {
        if (null != mOnHolderLongClickListener) {
            return mOnHolderLongClickListener.onHolderLongClick(v, getLayoutPosition());
        } else {
            return false;
        }
    }
}
