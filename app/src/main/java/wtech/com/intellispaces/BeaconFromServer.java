package wtech.com.intellispaces;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import java.util.List;

public class BeaconFromServer extends AppCompatActivity {
TextView id,major,minor,latitude,longitude,location,entry,exit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beacon_from_server);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    id=(TextView)findViewById(R.id.id);
        major=(TextView)findViewById(R.id.major);
        minor=(TextView)findViewById(R.id.minor);
        latitude=(TextView)findViewById(R.id.latitude);
        location=(TextView)findViewById(R.id.location);
        longitude=(TextView)findViewById(R.id.longitude);
        entry=(TextView)findViewById(R.id.entry);
        exit=(TextView)findViewById(R.id.exit);

        DatabaseHandler db=new DatabaseHandler(this);
      List<BeaconMaster> beacon= db.getAllBeacons();
        BeaconMaster beaconMaster=beacon.get(0);
        id.setText(beaconMaster.getBeaconId());
        major.setText(beaconMaster.getMajor());
        minor.setText(beaconMaster.getMinor());
        latitude.setText(beaconMaster.getLatitude());
        longitude.setText(beaconMaster.getLongitutde());
        location.setText(beaconMaster.getLocation());
        entry.setText(beaconMaster.getEntryMsg());
        exit.setText(beaconMaster.getExitMsg());
    }

}
