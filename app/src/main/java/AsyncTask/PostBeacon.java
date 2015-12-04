package AsyncTask;

import android.os.AsyncTask;
import android.util.Log;

import com.estimote.sdk.repackaged.gson_v2_3_1.com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import wtech.com.intellispaces.BeaconMaster;

/**
 * Created by Parth on 02-12-2015.
 */
public class PostBeacon extends AsyncTask<Object,Object,Object> {
    @Override
    protected BeaconMaster doInBackground(Object... objects) {
        try {
            BeaconMaster b=((BeaconMaster) objects[0]);
            ServiceHandler sh = new ServiceHandler();
            Gson gson = new Gson();
            JSONObject o = new JSONObject();
            o.put("_id", "1234");
            o.put("createdDate","111");
            o.put("beaconId","6666");
            o.put("major","43656");
            o.put("minor","4767");
            o.put("latitude","556665");
            o.put("latitude","73737");
            o.put("location","Mumbai");
            String response = sh.makeServiceCall("http://52.24.71.247/intelliservices/api/1.0/beacon", ServiceHandler.POST,gson.toJson(b));
//            Log.i("res", response);
//            return gson.fromJson(response, BeaconMaster.class);


        } catch (JSONException e) {
            e.printStackTrace();
        }
return  null;
    }

}
