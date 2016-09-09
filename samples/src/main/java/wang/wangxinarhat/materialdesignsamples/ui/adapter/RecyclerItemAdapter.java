package wang.wangxinarhat.materialdesignsamples.ui.adapter;

import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import wang.wangxinarhat.materialdesignsamples.R;
import wang.wangxinarhat.materialdesignsamples.bean.DemoBean;
import wang.wangxinarhat.materialdesignsamples.interfaces.OnHolderClickListener;
import wang.wangxinarhat.materialdesignsamples.interfaces.OnHolderLongClickListener;
import wang.wangxinarhat.materialdesignsamples.interfaces.OnRecyclerViewItemClickListener;
import wang.wangxinarhat.materialdesignsamples.interfaces.OnRecyclerViewItemLongClickListener;
import wang.wangxinarhat.materialdesignsamples.ui.holder.HolderBase;
import wang.wangxinarhat.materialdesignsamples.utils.GlideUtils;

/**
 * Created by wang on 2016/9/9.
 */
public class RecyclerItemAdapter extends RecyclerView.Adapter<HolderBase> implements OnHolderClickListener,OnHolderLongClickListener{

    private List<DemoBean> mBeans;
    private OnRecyclerViewItemClickListener mOnItemClickListener;
    private OnRecyclerViewItemLongClickListener mOnItemLongClickListener;


    public void updateData(List<DemoBean> demoBeans, boolean isRefresh) {
        if (null == mBeans) {
            mBeans = new ArrayList<>();
        }

        if (isRefresh) {
            mBeans.clear();
        }

        mBeans.addAll(demoBeans);
        notifyDataSetChanged();
    }

    @Override
    public HolderBase onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cardview, parent, false);

        return new ViewHolder(view,this,this);
    }

    @Override
    public void onBindViewHolder(HolderBase holder, int position) {

        if (holder instanceof ViewHolder) {
            ((ViewHolder) holder).bindData(mBeans.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return mBeans.size();
    }

    public void setOnItemClickListener(@Nullable OnRecyclerViewItemClickListener listener) {
        mOnItemClickListener = listener;
    }

    public void setOnItemLongClickListener(@Nullable OnRecyclerViewItemLongClickListener listener) {
        mOnItemLongClickListener = listener;
    }


    @Override
    public void onHolderClick(View itemView, int position) {

        if (null != mOnItemClickListener) {
            mOnItemClickListener.onItemClick(itemView, position);
        }
    }


    @Override
    public boolean onHolderLongClick(View itemView, int position) {
        if (null != mOnItemLongClickListener) {
            return mOnItemLongClickListener.onItemLongClick(itemView, position);
        } else {
            return false;
        }
    }

    public static class ViewHolder extends HolderBase<DemoBean> {


        @Bind(R.id.photo)
        ImageView mPhoto;
        @Bind(R.id.card_view)
        CardView mCardView;

        public ViewHolder(View itemView,OnHolderClickListener onHolderClickListener,OnHolderLongClickListener onHolderLongClickListener) {
            super(itemView ,onHolderClickListener,onHolderLongClickListener);
            ButterKnife.bind(this, itemView);
        }

        @Override
        protected void bindData(DemoBean demoBean) {


            GlideUtils.loadImage(demoBean.getPhotoId(), mPhoto);
        }


    }
}
