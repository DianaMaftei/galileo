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
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.constraintlayout.widget.ConstraintLayout;

public class BeaconsActivity extends Activity {

    private static final int LOCATION_PERMISION = 10;
    protected static final String TAG = "RangingActivity";
    BeaconLocation beaconLocation;
    TextView distanceValue;
    ConstraintLayout layout;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.beacons);
        distanceValue = new TextView(this);
        distanceValue = findViewById(R.id.distValue);
        layout = findViewById(R.id.layout1);
        if (this.checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

//            Snackbar snackbar = Snackbar.make(layout, "Allow application to use your location", Snackbar.LENGTH_SHORT);
//            snackbar.show();
            Toast.makeText(this, "Allow application to use your location", Toast.LENGTH_LONG).show();
            requestPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, LOCATION_PERMISION);


        } else {
            beaconLocation =  new BeaconLocation(this);

            beaconLocation.startService();
            beaconLocation.setBeaconDistanceListener(new BeaconLocation.BeaconDistanceListener() {
                @Override
                public void onDataSend(double distance) {
                    distanceValue.setText(Double.toString(distance));
                }
            });


        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case LOCATION_PERMISION:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    beaconLocation =  new BeaconLocation(this);
                    beaconLocation.startService();
                    beaconLocation.setBeaconDistanceListener(new BeaconLocation.BeaconDistanceListener() {
                        @Override
                        public void onDataSend(double distance) {
                            distanceValue.setText(Double.toString(distance));
                        }
                    });


                } else {
                    Toast.makeText(this, "Location denied", Toast.LENGTH_LONG).show();
                    requestPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, LOCATION_PERMISION);
                }
                break;

            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }

    }
}