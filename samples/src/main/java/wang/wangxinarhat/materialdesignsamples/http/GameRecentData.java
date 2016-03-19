package wang.wangxinarhat.materialdesignsamples.http;

import android.os.Handler;

import java.util.ArrayList;

import wang.wangxinarhat.materialdesignsamples.bean.GameListBean;
import wang.wangxinarhat.materialdesignsamples.interfaces.BaseListLoadData;

/**
 * Created by wang on 2016/3/8.
 */
public class GameRecentData extends BaseListLoadData<GameListBean> {
    public GameRecentData(Handler handler) {
        super(handler);
    }

    @Override
    public void load(boolean isRefresh) {

        ArrayList<GameListBean> list = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            list.add(new GameListBean("title " + i, "2016-07-0" + i, "2", "6", "贝克街 + 22" + i, i + "km", -1));

        }
        mItems.addAll(list);
        sendSuccessDifferentMessage(isRefresh);
    }
}
