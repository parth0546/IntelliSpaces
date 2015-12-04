package wtech.com.intellispaces;

import android.app.Application;
import android.widget.Toast;

import com.estimote.sdk.Beacon;
import com.estimote.sdk.BeaconManager;
import com.estimote.sdk.Region;

import java.util.List;
import java.util.UUID;

/**
 * Created by Parth on 29-11-2015.
 */
public class MyApplication extends Application {


BeaconManager beaconManager;
    @Override
    public void onCreate(){
        super.onCreate();
        beaconManager=new BeaconManager(getApplicationContext());
        beaconManager.setMonitoringListener(new BeaconManager.MonitoringListener() {
            @Override
            public void onEnteredRegion(Region region, List<Beacon> list) {
                Toast.makeText(MyApplication.this,"you have entered the beacon region",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onExitedRegion(Region region) {

            }
        });
        beaconManager.connect(new BeaconManager.ServiceReadyCallback() {
            @Override
            public void onServiceReady() {
                beaconManager.startMonitoring
                        (new Region("monitered region", UUID.fromString("B9407F30-F5F8-466E-AFF9-25556B57FE6D"), null, null));
            }
        });
    }
}
