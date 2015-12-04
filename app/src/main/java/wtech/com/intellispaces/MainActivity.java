package wtech.com.intellispaces;

import android.annotation.TargetApi;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.le.BluetoothLeAdvertiser;
import android.bluetooth.le.BluetoothLeScanner;
import android.bluetooth.le.ScanSettings;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.estimote.sdk.Beacon;
import com.estimote.sdk.BeaconManager;
import com.estimote.sdk.Region;
import com.estimote.sdk.Utils;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import AsyncTask.PostBeacon;

@TargetApi(Build.VERSION_CODES.LOLLIPOP)
public class MainActivity extends AppCompatActivity {
    BeaconManager beaconManager;
    Region region;
    int txpower;
    BluetoothLeScanner mBluetoothLeScanner;
    BluetoothAdapter mBluetoothAdapter;
    BluetoothLeAdvertiser mBluetoothLeAdvertiser;
    ScanSettings mScanSettings;
    int p;
    private List<Beacon> BeaconList;
    int rssi;
    BeaconAdapter adapter;
    ListView listView;
    boolean n = false, im = false, f = false;
    double sum = 0.0;
    double avg = 0.0;
    double distance = 0.0;
    int k = 0;
    boolean one = false;
    public AsyncTask<Object, Object, ArrayList<BeaconMaster>> getBeacon;
    public AsyncTask<Object, Object, Object> postBeacon;
  public BeaconMaster beacon;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        mBluetoothLeScanner = mBluetoothAdapter.getBluetoothLeScanner();
        mBluetoothLeAdvertiser = mBluetoothAdapter.getBluetoothLeAdvertiser();
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Scanning....");
        one = false;
        BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (mBluetoothAdapter == null) {
            // Device does not support Bluetooth
            Toast.makeText(MainActivity.this, "Device does not support Bluetooth", Toast.LENGTH_SHORT).show();
        } else {
            if (!mBluetoothAdapter.isEnabled()) {
                // Bluetooth is not enable :)
                Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                startActivityForResult(enableBtIntent, 1);
                Toast.makeText(getApplicationContext(), "Enabling Bluetooth!!", Toast.LENGTH_LONG).show();
            }
        }
        listView = (ListView) findViewById(R.id.list);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(MainActivity.this, Info.class);
                intent.putExtra("beacons", adapter.getItem(i));
                startActivity(intent);
            }
        });
        //Adap=new BeaconAdapter(MainActivity.this);
        // listView.setAdapter(adapter);

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                //code to do the HTTP request
                // JSONParser jsonParser = new JSONParser();
                // JSONObject jsonObject = jsonParser.getJSONFromUrl("http://52.24.71.247/intelliservices/api/1.0/beacon");
                //JSONArray jsonArray=jsonParser.getJSONFromUrl("http://52.24.71.247/intelliservices/api/1.0/beacon");
                try {
                    JSONArray js = JSONfunctions.getJSONfromURL("http://52.24.71.247/intelliservices/api/1.0/beacon");

                    for(int i=0;i<js.length();i++) {
                        JSONObject c = js.getJSONObject(i);
                        String id = c.getString("_id");
                        String beaconId = c.getString("beaconId");
                        String major = c.getString("major");
                        String minor = c.getString("minor");
                        String latitude = c.getString("latitude");
                        String longitude = c.getString("longitude");
                        String location = c.getString("location");
                        Log.i("loc", location);
                        BeaconMaster beacon = new BeaconMaster();
                        //  int id1=Integer.parseInt(id);
                        beacon.set_id(Integer.parseInt(beaconId));
                        beacon.setBeaconDesc("sss");
                        beacon.setBeaconId(id);
                        beacon.setBeaconKey("dd");
                        beacon.setEntryMsg("Region Entered");
                        beacon.setEvent("Product Found");
                        beacon.setExitMsg("Region Exited");
                        beacon.setLatitude(latitude);
                        beacon.setLongitutde(longitude);
                        beacon.setLocation(location);
                        beacon.setMajor(major);
                        beacon.setMinor(minor);
                        DatabaseHandler db = new DatabaseHandler(MainActivity.this);
                        db.addBeacons(beacon);
                        Log.i("id", c.toString());
                   }
                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }


            }
        });
        thread.start();
        DatabaseHandler db=new DatabaseHandler(MainActivity.this);
        List<BeaconMaster> list=db.getAllBeacons();
        //beaconMaster=new BeaconMaster();
        beacon=list.get(0);
        beacon.setBeaconDesc("sss");
        beacon.setBeaconId("97988");
        beacon.setBeaconKey("dd");
        beacon.setEntryMsg("Region Entered");
        beacon.setEvent("Product Found");
        beacon.setExitMsg("Region Exited");
        beacon.setLatitude("877");
        beacon.setLongitutde("7979");
        beacon.setLocation("8790");
        beacon.setMajor("987909");
        beacon.setMinor("909090");
        postBeacon=new PostBeacon(){



        }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,beacon);
//        DatabaseHandler db = new DatabaseHandler(this);
//        List<BeaconMaster> list = db.getAllBeacons();
//        BeaconMaster beaconMaster = list.get(0);
//        String major = beaconMaster.getMajor();
//        Toast.makeText(this, major, Toast.LENGTH_SHORT).show();
//        getBeacon = new GetBeacon() {
//            @Override
//            public void onPostExecute(ArrayList<BeaconMaster> response) {
//                super.onPostExecute(response);
//                Log.i("data", String.valueOf(response));
//
//
//            }
//        }.execute();
        final DatabaseHandler db1 = new DatabaseHandler(this);
        beaconManager = new BeaconManager(this);
        region = new Region("monitered region", UUID.fromString("B9407F30-F5F8-466E-AFF9-25556B57FE6D"), null, null);
        beaconManager.setRangingListener(new BeaconManager.RangingListener() {
            @Override
            public void onBeaconsDiscovered(Region region, List<Beacon> list) {
                List<Beacon> list1 = new ArrayList<Beacon>();
                List<Beacon> list2 = new ArrayList<Beacon>();
                List<Beacon> list3 = new ArrayList<Beacon>();
                IntelliSpaces nb = new IntelliSpaces();

                double[] avg = new double[list.size()];
                if (!one) {
                    for (int j = 0; j < list.size(); j++) {

                        for (int i = 0; i < 9; i++) {
                            distance = Utils.computeAccuracy(list.get(j));
                            sum = distance + sum;

                        }
                        avg[j] = sum / list.size();
                        sum = 0;
                    }
//                    Toast.makeText(MainActivity.this, String.valueOf(avg[0]) + " " + String.valueOf(avg[1]) + "  " + String.valueOf(avg[2]) + "  " + String.valueOf(avg[3]) + "  " + String.valueOf(avg[4]), Toast.LENGTH_SHORT).show();
                }
                one = true;
                for (int i = 0; i < list.size(); i++) {
                    Beacon beacon = list.get(i);
                    double dist = Utils.computeAccuracy(beacon);


                    if (dist < 4.00) {
                        list1.add(beacon);

                    } else if (dist > 4.00 && dist < 10.00) {
                        list2.add(beacon);

                    } else {
                        list3.add(beacon);

                    }
                }

                if (IntelliSpaces.i) {
                    adapter = new BeaconAdapter(MainActivity.this, list1);
                    toolbar.setTitle("Immediate Beacons");

                }
                if (IntelliSpaces.n) {
                    adapter = new BeaconAdapter(MainActivity.this, list2);
                    toolbar.setTitle("Near Beacons");

                }
                if (IntelliSpaces.f) {
                    adapter = new BeaconAdapter(MainActivity.this, list3);
                    toolbar.setTitle("Far Beacons");
                }

                listView.setAdapter(adapter);
            }


        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onResume() {
        super.onResume();
        //SystemRequirementsChecker.checkWithDefaultDialogs(this);

        beaconManager.connect(new BeaconManager.ServiceReadyCallback() {
            @Override
            public void onServiceReady() {
                beaconManager.startRanging(region);
            }
        });
    }

    @Override
    public void onStop() {
        super.onStop();
        beaconManager.stopRanging(region);
    }

    @Override
    protected void onPause() {
        beaconManager.stopRanging(region);

        super.onPause();
    }

    public static class JSONfunctions {
        public static JSONArray getJSONfromURL(String url) throws IOException, JSONException {

            InputStream is = null;
            String result = "";
            JSONArray jArray = null;

            HttpClient httpclient = new DefaultHttpClient();
            HttpGet httpget = new HttpGet(url);
            HttpResponse response = httpclient.execute(httpget);
            HttpEntity entity = response.getEntity();
            is = entity.getContent();

            BufferedReader reader = new BufferedReader(new InputStreamReader(is, "iso-8859-1"), 8);
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
            is.close();
            result = sb.toString();

            jArray = new JSONArray(result);
            return jArray;
        }
    }


}

