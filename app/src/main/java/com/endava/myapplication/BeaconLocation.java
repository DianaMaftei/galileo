package com.endava.myapplication;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;

import androidx.localbroadcastmanager.content.LocalBroadcastManager;

public class BeaconLocation {

    Context context;
    Intent background;

    public BeaconLocation(Context context) {
        this.context = context;
    }

    public interface BeaconDistanceListener {
        void onDataSend(double distance);

    }

    private BeaconDistanceListener listenerBeaconDistance;

    public void setBeaconDistanceListener(BeaconDistanceListener listener) {
        this.listenerBeaconDistance = listener;
    }

    public void startService() {

        LocalBroadcastManager.getInstance(context).registerReceiver(new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {

                Bundle extra = intent.getBundleExtra("DISTANCE");
                double distanceToBeacon = extra.getDouble("distance_val");

                listenerBeaconDistance.onDataSend(distanceToBeacon);

            }
        }, new IntentFilter("DISTANCE_DATA"));

        background = new Intent(context, BeaconLocationService.class);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            context.startForegroundService(background);
        } else {
            context.startService(background);
        }

    }


}
