package AsyncTask;

import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;

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
import wtech.com.intellispaces.BeaconMaster;
import wtech.com.intellispaces.MainActivity;

/**
 * Created by Parth on 02-12-2015.
 */
public class GetBeacon extends AsyncTask<Object, Object, ArrayList<BeaconMaster>> {
    @Override
    protected ArrayList<BeaconMaster> doInBackground(Object... objects) {
        try {
            ServiceHandler sh = new ServiceHandler();
            Gson gson = new Gson();
            String response = sh.makeServiceCall("http://52.24.71.247/intelliservices/api/1.0/beacon", ServiceHandler.GET);
            Log.i("res",response);
            JSONObject jsonObjectRoot = null;
            jsonObjectRoot = new JSONObject(response);
            JSONArray jsonArrayData = null;
            jsonArrayData =JSONfunctions.getJSONfromURL("http://52.24.71.247/intelliservices/api/1.0/beacon");
            int jsonArrayDataLength = jsonArrayData.length();
            Log.i("size",String.valueOf(jsonArrayDataLength));
            ArrayList<BeaconMaster> driverJourneryRecords = new ArrayList<BeaconMaster>();
            for (int j = 0; j < jsonArrayDataLength; j++) {
                BeaconMaster beaconMaster = gson.fromJson(jsonArrayData.getString(j), BeaconMaster.class);
                driverJourneryRecords.add(beaconMaster);
            }
            return driverJourneryRecords;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
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

            BufferedReader reader = new BufferedReader(new InputStreamReader(is,"iso-8859-1"),8);
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
            is.close();
            result=sb.toString();

            jArray = new JSONArray(result);
            return jArray;
        }
    }
}
