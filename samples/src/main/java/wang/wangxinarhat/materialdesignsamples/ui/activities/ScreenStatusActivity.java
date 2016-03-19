package wang.wangxinarhat.materialdesignsamples.ui.activities;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;

import wang.wangxinarhat.materialdesignsamples.global.BaseApplication;
import wang.wangxinarhat.materialdesignsamples.receiver.ScreenStatusReceiver;

/**
 * Created by wang on 2016/3/16.
 */
public class ScreenStatusActivity extends BaseActivity {
    private BroadcastReceiver mReceiver = null;

    public static Intent getStartIntent() {
        Intent intent = new Intent();
        intent.setClass(BaseApplication.getApplication(), ScreenStatusActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final IntentFilter filter = new IntentFilter(Intent.ACTION_SCREEN_ON);
        filter.addAction(Intent.ACTION_SCREEN_OFF);

        mReceiver = new ScreenStatusReceiver();
        registerReceiver(mReceiver, filter);
    }

    @Override
    protected void onPause() {

        if (ScreenStatusReceiver.wasScreenOn) {

            Log.e(ScreenStatusActivity.class.getSimpleName(), "屏幕点亮");
        } else {
            Log.e(ScreenStatusActivity.class.getSimpleName(), "屏幕关闭");
        }
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (ScreenStatusReceiver.wasScreenOn) {
            Log.e(ScreenStatusActivity.class.getSimpleName(), "屏幕点亮");
        } else {
            Log.e(ScreenStatusActivity.class.getSimpleName(), "屏幕关闭");
        }
    }

    @Override
    protected void onDestroy() {
        if (mReceiver != null) {
            unregisterReceiver(mReceiver);
            mReceiver = null;
        }
        super.onDestroy();
    }

}
