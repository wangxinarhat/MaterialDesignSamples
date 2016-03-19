package wang.wangxinarhat.materialdesignsamples.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by wang on 2016/3/16.
 */
public class ScreenStatusReceiver extends BroadcastReceiver {
    public static boolean wasScreenOn = true;

    @Override
    public void onReceive(final Context context, final Intent intent) {
        if (intent.getAction().equals(Intent.ACTION_SCREEN_OFF)) {
            wasScreenOn = false;
            Log.e(ScreenStatusReceiver.class.getSimpleName(), "屏幕关闭");
        } else if (intent.getAction().equals(Intent.ACTION_SCREEN_ON)) {
            wasScreenOn = true;
            Log.e(ScreenStatusReceiver.class.getSimpleName(), "屏幕点亮");
        }
    }
}
