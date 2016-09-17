package waitingpiri.waitingpiri.location;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import waitingpiri.waitingpiri.MainActivity;
import waitingpiri.waitingpiri.SplashActivity;

/**
 * Created by sergio on 17/09/16.
 */
public class LocationStartup extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Intent activity = new Intent(context, SplashActivity.class);
        activity.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(activity);
    }
}
