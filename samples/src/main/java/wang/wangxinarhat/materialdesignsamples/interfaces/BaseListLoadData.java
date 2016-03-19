package wang.wangxinarhat.materialdesignsamples.interfaces;

import android.os.Handler;

import java.util.ArrayList;

import wang.wangxinarhat.materialdesignsamples.global.STATUS;


/**
 * 加载列表数据基本类
 * Created by sacowiw on 15/12/28.
 */
public abstract class BaseListLoadData<T> {
    public ArrayList<T> mItems = new ArrayList<>();
    private Handler mHandler;

    protected int mPage = 1;

    public BaseListLoadData(Handler handler) {
        super();
        mHandler = handler;
    }

    public int getDataSize() {
        return mItems != null ? mItems.size() : 0;
    }

    public ArrayList<T> getData() {
        return mItems;
    }

    /**
     * 加载数据,如果isRefresh为false,
     *
     * @param isRefresh
     */
    public abstract void load(boolean isRefresh);

    public void reLoad() {
        mPage = 1;
        load(true);
    }

    protected void sendErrorDifferentMessage(boolean isReLoad) {
        if (isReLoad) {
            sendErrorMessage();
        } else {
            sendLoadCompleteMessage();
        }
    }

    protected void sendSuccessDifferentMessage(boolean isReload) {
        if (isReload) {
            sendReloadSuccessMessage();
        } else {
            sendLoadSuccessMessage();
        }
    }

    protected void sendLoadCompleteMessage() {
        sendMessage(STATUS.LOAD_COMPLETE);
    }

    protected void sendLoadingMessage() {
        sendMessage(STATUS.LOAD_LOADING);
    }

    protected void sendErrorMessage() {
        sendMessage(STATUS.LOAD_ERROR);
    }

    protected void sendEmptyMessage() {
        sendMessage(STATUS.LOAD_EMPTY);
    }

    private void sendReloadSuccessMessage() {
        sendMessage(STATUS.LOAD_RELOAD_SUCCESS);
    }

    private void sendLoadSuccessMessage() {
        sendMessage(STATUS.LOAD_SUCCESS);
    }

    protected void sendLoadAllMessage() {
        sendMessage(STATUS.LOAD_ALL);
    }

    private void sendMessage(int status) {
        if (mHandler != null) {
            mHandler.sendEmptyMessage(status);
        }
    }

}
