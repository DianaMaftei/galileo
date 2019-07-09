package com.endava.myapplication;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.util.ArrayMap;
import android.widget.TextView;

import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.endava.myapplication.triangulation.Beacon;
import com.endava.myapplication.triangulation.CartesianBeacon;
import com.endava.myapplication.triangulation.CartesianPosition;
import com.endava.myapplication.triangulation.CartesianPositionCalculator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BeaconLocation {

    Context context;
    Intent background;

    public BeaconLocation(Context context) {
        this.context = context;
    }

    public interface BeaconDistanceListener {
        void onDataSend(CartesianPosition beaconIds);

    }

    private BeaconDistanceListener listenerBeaconDistance;

    public void setBeaconDistanceListener(BeaconDistanceListener listener) {
        this.listenerBeaconDistance = listener;
    }

    public void startService() {

        LocalBroadcastManager.getInstance(context).registerReceiver(new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {

                HashMap<String, Double> beacons = (HashMap<String, Double>) intent.getSerializableExtra("INFO");

                Map<Beacon<CartesianPosition>, Double> map = new HashMap<>();
                if (beacons.size() != 0) {
                    for (String beaconId : beacons.keySet()) {
                        if (beaconId.startsWith("42")) {
                            CartesianPosition cartesianPosition1 = new CartesianPosition(0, 0);
                            Beacon<CartesianPosition> cartesianBeacon1 = new CartesianBeacon(cartesianPosition1);
                            map.put(cartesianBeacon1, beacons.get(beaconId));
                        }
                        if (beaconId.startsWith("0x02")) {
                            CartesianPosition cartesianPosition1 = new CartesianPosition(1, 1);
                            Beacon<CartesianPosition> cartesianBeacon1 = new CartesianBeacon(cartesianPosition1);
                            map.put(cartesianBeacon1, beacons.get(beaconId));
                        }
                        if (beaconId.startsWith("0x00")) {
                            CartesianPosition cartesianPosition1 = new CartesianPosition(0, 1);
                            Beacon<CartesianPosition> cartesianBeacon1 = new CartesianBeacon(cartesianPosition1);
                            map.put(cartesianBeacon1, beacons.get(beaconId));
                        }
                    }
                }
                if(map.size() > 2) {
                    CartesianPosition cartesianPosition = new CartesianPositionCalculator(5).getPosition(map);
                    listenerBeaconDistance.onDataSend(cartesianPosition);
                }


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
