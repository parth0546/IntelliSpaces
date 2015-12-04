package wtech.com.intellispaces;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.estimote.sdk.Beacon;
import com.estimote.sdk.Utils;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Parth on 30-11-2015.
 */
public class BeaconAdapter extends BaseAdapter {
    List<Beacon> beacons;
     TextView macTextView;
     TextView majorTextView;
     TextView minorTextView;
     TextView measuredPowerTextView;
     TextView rssiTextView;
LayoutInflater inflater;
    public BeaconAdapter(Context context,List<Beacon> bcons){
   this.beacons=bcons;
        this.inflater=LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return beacons.size();
    }

    @Override
    public Beacon getItem(int position) {
        return beacons.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView=inflater.inflate(R.layout.beacon_item,null);

        macTextView = (TextView) convertView.findViewWithTag("mac");
        majorTextView = (TextView) convertView.findViewWithTag("major");
        minorTextView = (TextView) convertView.findViewWithTag("minor");
        measuredPowerTextView = (TextView) convertView.findViewWithTag("mpower");
        rssiTextView = (TextView) convertView.findViewWithTag("rssi");
        macTextView.setText(String.format("MAC: %s (%.2fm)", getItem(position).getMacAddress().toStandardString(), Utils.computeAccuracy(getItem(position))));
        majorTextView.setText("Major: " + getItem(position).getMajor());
        minorTextView.setText("Minor: " + getItem(position).getMinor());
        measuredPowerTextView.setText("MPower: " +getItem(position).getMeasuredPower());
        rssiTextView.setText("RSSI: " + getItem(position).getRssi());

        return convertView;
    }
}