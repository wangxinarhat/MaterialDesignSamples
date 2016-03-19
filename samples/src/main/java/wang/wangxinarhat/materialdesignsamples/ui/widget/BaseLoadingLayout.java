package wang.wangxinarhat.materialdesignsamples.ui.widget;

/**
 * Created by wang on 2016/3/8.
 */

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewStub;
import android.widget.FrameLayout;

import wang.wangxinarhat.materialdesignsamples.R;
import wang.wangxinarhat.materialdesignsamples.global.STATUS;

/**
 * 加载状态layout<br/>
 * 以下方法必须执行
 * {@link #setContentView(View contentView)},
 * {@link #setOnReloadListener(OnReloadListener listener)},
 * {@link #setLoadStatus(int status)}<br/>
 * Created by Fesen on 2015/12/26.
 */
public class BaseLoadingLayout extends FrameLayout implements View.OnClickListener {
    private ViewStub mStubEmpty;
    private ViewStub mStubError;

    private View mViewProgress;
    private View mViewEmpty;
    private View mViewError;
    private View mViewContent;
    private OnReloadListener mListener;

    public BaseLoadingLayout(Context context) {
        super(context);
        init(context);
    }

    public BaseLoadingLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public BaseLoadingLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);

    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
    }

    private void init(Context context) {
        View view = inflate(context, R.layout.base_loading_layout, this);
        mViewProgress = view.findViewById(R.id.base_loading_stub_progress);
        mStubEmpty = (ViewStub) view.findViewById(R.id.base_loading_stub_empty);
        mStubError = (ViewStub) view.findViewById(R.id.base_loading_stub_error);
        setLoadStatus(STATUS.LOAD_LOADING);
    }

    /**
     * 设置加载状态
     */
    public void setLoadStatus(int status) {
        showProgress(STATUS.LOAD_LOADING == status);
        showEmpty(STATUS.LOAD_EMPTY == status);
        showError(STATUS.LOAD_ERROR == status);
        showContent(STATUS.LOAD_SUCCESS == status);
    }

    /**
     * 是否显示进度条
     *
     * @param show
     */
    private void showProgress(boolean show) {
        mViewProgress.setVisibility(show ? VISIBLE : GONE);
    }

    /**
     * 是否显示错误界面
     *
     * @param show
     */
    private void showError(boolean show) {
        if (!show && mViewError == null) {
            return;
        }

        if (mViewError == null) {
            mViewError = mStubError.inflate();
            mViewError.findViewById(R.id.base_loading_layout_button_reload).setOnClickListener(this);
        }
        mViewError.setVisibility(show ? VISIBLE : GONE);
    }

    /**
     * 是否显示内容为空页面
     *
     * @param show
     */
    private void showEmpty(boolean show) {
        if (!show && mViewEmpty == null) {
            return;
        }

        if (mViewEmpty == null) {
            mViewEmpty = mStubEmpty.inflate();
        }

        mViewEmpty.setVisibility(show ? VISIBLE : GONE);
    }

    /**
     * 是否显示内容页
     *
     * @param show
     */
    private void showContent(boolean show) {
        if (!show && mViewContent == null) {
            return;
        }
        if (mViewContent != null) {
            mViewContent.setVisibility(show ? VISIBLE : GONE);
        }
    }

    /**
     * 设置内容view
     */
    public void setContentView(View contentView) {
        mViewContent = contentView;
        addView(mViewContent);
        mViewContent.setVisibility(GONE);
    }

    /**
     * 重新加载接口
     *
     * @param listener
     */
    public void setOnReloadListener(OnReloadListener listener) {
        mListener = listener;
    }

    @Override
    public void onClick(View v) {
        if (mListener != null) {
            setLoadStatus(STATUS.LOAD_LOADING);
            mListener.onReload();
        }
    }

    /**
     * 第一次加载或者重新加载
     */
    public interface OnReloadListener {
        void onReload();
    }
}
