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
//

package com.endava.myapplication;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.RemoteException;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

import org.altbeacon.beacon.Beacon;
import org.altbeacon.beacon.BeaconConsumer;
import org.altbeacon.beacon.BeaconManager;
import org.altbeacon.beacon.BeaconParser;
import org.altbeacon.beacon.MonitorNotifier;
import org.altbeacon.beacon.RangeNotifier;
import org.altbeacon.beacon.Region;

import java.util.Collection;

public class Beacons extends Activity implements BeaconConsumer {
    private static final int PERMISSION_REQUEST_COARSE_LOCATION = 1;

    protected static final String TAG = "BeaconsActivity";
    private BeaconManager beaconManager;
    TextView beaconsConnectedTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        checkPermission();
        setContentView(R.layout.activity_beacons);

        beaconManager = BeaconManager.getInstanceForApplication(this);
        beaconManager.getBeaconParsers().add(new BeaconParser().setBeaconLayout("m:2-3=0215,i:4-19,i:20-21,i:22-23,p:24-24")); // for iBeacons
        Log.d(TAG, "" + beaconManager.checkAvailability());
        beaconManager.bind(this);
        beaconsConnectedTextView = findViewById(R.id.beaconsConnectedTv);
    }

    public void checkPermission(){

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            if (this.checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION)!=PackageManager.PERMISSION_GRANTED){
                AlertDialog.Builder builder=new AlertDialog.Builder(this);
                builder.setTitle("Location Permission");
                builder.setPositiveButton("OK",null);
                builder.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @TargetApi(Build.VERSION_CODES.M)
                    @Override
                    public void onDismiss(DialogInterface dialogInterface) {
                        requestPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},PERMISSION_REQUEST_COARSE_LOCATION);
                    }
                });
                builder.show();
            }
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_REQUEST_COARSE_LOCATION: {

                // If Permission is Granted than its ok
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                }

                // If not Granted then alert the user by the message
                else {
                    final AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setTitle("Functionality limited");
                    builder.setMessage("Since location access has not been granted, this app will not be able to discover beacons when in the background.");
                    builder.setPositiveButton(android.R.string.ok, null);
                    builder.setOnDismissListener(new DialogInterface.OnDismissListener() {
                        @Override
                        public void onDismiss(DialogInterface dialog) {
                            checkPermission();
                        }
                    });
                    builder.show();
                }
                return;
            }
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        beaconManager.unbind(this);
    }

    @Override
    public void onBeaconServiceConnect() {
        beaconManager.addRangeNotifier(new RangeNotifier() {
            @Override
            public void didRangeBeaconsInRegion(Collection<Beacon> beacons, Region region) {
                Log.i(TAG, "beacons connected: " + beacons.size());
                beaconsConnectedTextView.setText("Beacons Connected: " + beacons.size());
                if (beacons.size() > 0) {
                    Log.d(TAG, "The first beacon I see is about " + beacons.iterator().next().getDistance() + " meters away.");
                }
            }
        });
        beaconManager.addMonitorNotifier(new MonitorNotifier() {
            @Override
            public void didEnterRegion(Region region) {
                Log.i(TAG, "didEnterRegion");
            }

            @Override
            public void didExitRegion(Region region) {
                Log.i(TAG, "didExitRegion");

            }

            @Override
            public void didDetermineStateForRegion(int i, Region region) {
                Log.i(TAG, "didDetermineStateForRegion");

            }
        });
//Identifier.parse("426c7565-4368-6172-6d42-6561636f6e73")
        try {
            beaconManager.startRangingBeaconsInRegion(new Region("myRangingUniqueId",
                    null, null, null));
        } catch (RemoteException e) {
        }
    }
}


//import android.app.Activity;
//import android.os.Bundle;
//import android.os.RemoteException;
//import android.util.Log;
//import android.widget.TextView;
//
//import org.altbeacon.beacon.Beacon;
//import org.altbeacon.beacon.BeaconConsumer;
//import org.altbeacon.beacon.BeaconManager;
//import org.altbeacon.beacon.MonitorNotifier;
//import org.altbeacon.beacon.RangeNotifier;
//import org.altbeacon.beacon.Region;
//
//import java.util.Collection;
//
//public class Beacons extends Activity implements BeaconConsumer {
//    protected static final String TAG = "MonitoringActivity";
//    private BeaconManager beaconManager;
//
//    TextView beaconsConnectedTextView;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_beacons);
//        beaconManager = BeaconManager.getInstanceForApplication(this);
//        // To detect proprietary beacons, you must add a line like below corresponding to your beacon
//        // type.  Do a web search for "setBeaconLayout" to get the proper expression.
//        // beaconManager.getBeaconParsers().add(new BeaconParser().
//        //        setBeaconLayout("m:2-3=beac,i:4-19,i:20-21,i:22-23,p:24-24,d:25-25"));
//        beaconManager.bind(this);
//        beaconsConnectedTextView = findViewById(R.id.beaconsConnectedTv);
//    }
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        beaconManager.unbind(this);
//    }
//    @Override
//    public void onBeaconServiceConnect() {
//        beaconManager.removeAllMonitorNotifiers();
//        beaconManager.addRangeNotifier(new RangeNotifier() {
//            @Override
//            public void didRangeBeaconsInRegion(Collection<Beacon> collection, Region region) {
//                Log.i(TAG, "Beacons Connected: " + collection.size());
//                beaconsConnectedTextView.setText("Beacons Connected: " + collection.size());
//                if (collection.size() > 0) {
//                    Log.d(TAG, "The first beacon I see is about " + collection.iterator().next().getDistance() + " meters away.");
//                }
//            }
//        });
////        beaconManager.addMonitorNotifier(new MonitorNotifier() {
////            @Override
////            public void didEnterRegion(Region region) {
////                Log.i(TAG, "I just saw an beacon for the first time!");
////            }
////
////            @Override
////            public void didExitRegion(Region region) {
////                Log.i(TAG, "I no longer see an beacon");
////            }
////
////            @Override
////            public void didDetermineStateForRegion(int state, Region region) {
////                Log.i(TAG, "I have just switched from seeing/not seeing beacons: "+state);
////            }
////        });
//
//
//        try {
//            beaconManager.startMonitoringBeaconsInRegion(new Region("myMonitoringUniqueId", null, null, null));
//        } catch (RemoteException e) {    }
//    }
//
//}