//package com.endava.myapplication;
//
//import android.app.Activity;
//import android.os.Bundle;
//import android.os.RemoteException;
//import android.util.Log;
//
//import org.altbeacon.beacon.Beacon;
//import org.altbeacon.beacon.BeaconConsumer;
//import org.altbeacon.beacon.BeaconManager;
//import org.altbeacon.beacon.BeaconParser;
//import org.altbeacon.beacon.Identifier;
//import org.altbeacon.beacon.MonitorNotifier;
//import org.altbeacon.beacon.RangeNotifier;
//import org.altbeacon.beacon.Region;
//
//import java.util.Collection;
//
//public class Beacons extends Activity implements BeaconConsumer {
//
//    public static final String TAG = "BeaconsEverywhere";
//    private BeaconManager beaconManager;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_beacons);
//
//        beaconManager = BeaconManager.getInstanceForApplication(this);
//
//        beaconManager.getBeaconParsers().add(new BeaconParser()
//                .setBeaconLayout("m:2-3=0215,i:4-19,i:20-21,i:22-23,p:24-24,d:25-25"));
//
//        beaconManager.bind(this);
//
//    }
//
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        beaconManager.unbind(this);
//    }
//
//    @Override
//    public void onBeaconServiceConnect() {
//        final Region region = new Region("beacons", null, null, null);
//        beaconManager.setMonitorNotifier(new MonitorNotifier() {
//            @Override
//            public void didEnterRegion(Region region) {
//                try {
//                    Log.d(TAG, "didEnterRegion");
//                    beaconManager.startRangingBeaconsInRegion(region);
//                } catch (RemoteException e) {
//                    e.printStackTrace();
//                }
//            }
//
//            @Override
//            public void didExitRegion(Region region) {
//                try {
//                    Log.d(TAG, "didExitRegion");
//                    beaconManager.stopRangingBeaconsInRegion(region);
//                } catch (RemoteException e) {
//                    e.printStackTrace();
//                }
//            }
//
//            @Override
//            public void didDetermineStateForRegion(int i, Region region) {
//
//            }
//        });
//        beaconManager.setRangeNotifier(new RangeNotifier() {
//            @Override
//            public void didRangeBeaconsInRegion(Collection<Beacon> collection, Region region) {
//                for (Beacon oneBeacon : collection) {
//                    Log.d(TAG, "distance: " + oneBeacon.getDistance() + " id:" + oneBeacon.getId1() + "/" + oneBeacon.getId2() + "/" + oneBeacon.getId3());
//                }
//            }
//        });
//
//        try {
//            beaconManager.startMonitoringBeaconsInRegion(region);
//        } catch (RemoteException e) {
//            e.printStackTrace();
//        }
//
//    }
//
//}
//
package com.endava.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.os.RemoteException;
import android.util.Log;

import org.altbeacon.beacon.Beacon;
import org.altbeacon.beacon.BeaconConsumer;
import org.altbeacon.beacon.BeaconManager;
import org.altbeacon.beacon.BeaconParser;
import org.altbeacon.beacon.Identifier;
import org.altbeacon.beacon.RangeNotifier;
import org.altbeacon.beacon.Region;

import java.util.Collection;

public class Beacons extends Activity implements BeaconConsumer {
    protected static final String TAG = "BeaconsActivity";
    private BeaconManager beaconManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beacons);

        beaconManager = BeaconManager.getInstanceForApplication(this);
        beaconManager.getBeaconParsers().add(new BeaconParser().setBeaconLayout("m:0-3=4c000215,i:4-19,i:20-21,i:22-23,p:24-24"));
        beaconManager.getBeaconParsers().add(new BeaconParser().setBeaconLayout("m:2-3=0215,i:4-19,i:20-21,i:22-23,p:24-24"));
        beaconManager.getBeaconParsers().add(new BeaconParser().setBeaconLayout("x,s:0-1=feaa,m:2-2=20,d:3-3,d:4-5,d:6-7,d:8-11,d:12-15"));
        beaconManager.getBeaconParsers().add(new BeaconParser().setBeaconLayout("s:0-1=feaa,m:2-2=00,p:3-3:-41,i:4-13,i:14-19"));
        beaconManager.getBeaconParsers().add(new BeaconParser().setBeaconLayout("s:0-1=feaa,m:2-2=10,p:3-3:-41,i:4-20v"));
        beaconManager.bind(this);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        beaconManager.unbind(this);
    }
    @Override
    public void onBeaconServiceConnect() {
        beaconManager.setRangeNotifier(new RangeNotifier() {
            @Override
            public void didRangeBeaconsInRegion(Collection<Beacon> beacons, Region region) {
                Log.i(TAG, "didRangeBeaconsInRegion");

                if (beacons.size() > 0) {
                    Log.d(TAG, "The first beacon I see is about " + beacons.iterator().next().getDistance() + " meters away.");
                }
            }
        });
//Identifier.parse("426c7565-4368-6172-6d42-6561636f6e73")
        try {
            beaconManager.startRangingBeaconsInRegion(new Region("myRangingUniqueId",
                    null , null, null));
        } catch (RemoteException e) {    }
    }
}