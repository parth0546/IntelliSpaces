package wtech.com.intellispaces;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class IntelliSpaces extends AppCompatActivity {
    Button immediate, near, far, button;
    public static boolean n = false, i = false, f = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_near_beacons);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        immediate = (Button) findViewById(R.id.immediate);
        near = (Button) findViewById(R.id.near);
        far = (Button) findViewById(R.id.far);
        button = (Button) findViewById(R.id.bs);

        i = false;
        n = false;
        f = false;
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

//                Thread thread = new Thread(new Runnable() {
//                    @Override
//                    public void run() {
//
//                        try {
//                            JSONArray js = MainActivity.JSONfunctions.getJSONfromURL("http://52.24.71.247/intelliservices/api/1.0/beacon");
//                            JSONObject c = js.getJSONObject(0);
//                            String id = c.getString("_id");
//                            String beaconId = c.getString("beaconId");
//                            String major = c.getString("major");
//                            String minor = c.getString("minor");
//                            String latitude = c.getString("latitude");
//                            String longitude = c.getString("longitude");
//                            String location = c.getString("location");
//                            Log.i("loc", location);
//                            BeaconMaster beacon = new BeaconMaster();
//                            //  int id1=Integer.parseInt(id);
//                            beacon.set_id(Integer.parseInt(beaconId));
//                            beacon.setBeaconDesc("sss");
//                            beacon.setBeaconId(id);
//                            beacon.setBeaconKey("dd");
//                            beacon.setEntryMsg("Region Entered");
//                            beacon.setEvent("Product Found");
//                            beacon.setExitMsg("Region Exited");
//                            beacon.setLatitude(latitude);
//                            beacon.setLongitutde(longitude);
//                            beacon.setLocation(location);
//                            beacon.setMajor(major);
//                            beacon.setMinor(minor);
//                            DatabaseHandler db = new DatabaseHandler(IntelliSpaces.this);
//                            db.addBeacons(beacon);
//                            Log.i("id", c.toString());
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
//
//
//                    }
//                });
               // thread.start();
                Intent i = new Intent(IntelliSpaces.this, BeaconFromServer.class);
                startActivity(i);
            }
        });
        immediate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(IntelliSpaces.this, MainActivity.class);
                startActivity(intent);
                i = true;
                n = false;
                f = false;
            }
        });
        near.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(IntelliSpaces.this, MainActivity.class);
                startActivity(intent);
                n = true;
                i = false;
                f = false;
            }
        });
        far.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(IntelliSpaces.this, MainActivity.class);
                startActivity(intent);
                f = true;
                i = false;
                n = false;
            }
        });
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

}
