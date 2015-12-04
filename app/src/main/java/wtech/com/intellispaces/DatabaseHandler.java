package wtech.com.intellispaces;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


import com.estimote.sdk.Beacon;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by girish on 02-04-2015.
 */
public class DatabaseHandler extends SQLiteOpenHelper {

    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "wtsSmart";

    // Employee table name
    private static final String TABLE_EMP = "employee_m";

    // Employee Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_EMP_ID = "emp_id";
    private static final String KEY_FIRST_NAME = "emp_first_name";
    private static final String KEY_LAST_NAME = "emp_last_name";
    private static final String KEY_DOB = "emp_dob";
    private static final String KEY_DEVICE_ID = "emp_device_id";
    private static final String KEY_ROLE = "role";
    private static final String KEY_DOF_JOIN = "date_join";

    // Employee_log table
    private static final String TABLE_LOG = "employee_log";

    // Employee Table Columns names
    private static final String KEY_IN_TIME = "emp_in_time";
    private static final String KEY_OUT_TIME = "emp_out_time";
    private static final String KEY_DAY = "emp_day";
    private static final String KEY_FIRST_IN = "emp_first_in";
    private static final String KEY_LAST_OUT = "emp_last_out";
    private static final String KEY_LOC = "emp_location";

    private static final String KEY_FIRST_IN_NAME = "first_in_name";
    private static final String KEY_FIRST_IN_LAST = "first_in_last_name";

    private static final String KEY_lAST_IN_NAME = "last_in_name";
    private static final String KEY_LAST_IN_LAST = "last_in_last_name";

    //Common column
    private static final String KEY_EMAIL = "emp_email";

    //beacon table
    private static final String TABLE_BEACON = "beacon";
    private static final String KEY_BEACON_ID = "beaconId";
    private static final String KEY_MAJOR = "major";
    private static final String KEY_MINOR = "minor";
    private static final String KEY_PROXIMITY = "proximityId";
    private static final String KEY_BEACON_DESC = "beaconDesc";
    private static final String KEY_BEACON_KEY = "beaconKey";
    private static final String KEY_BEACON_ENTRY_MSG = "entryMsg";
    private static final String KEY_BEACON_EXIT_MSG = "exitMsg";
    private static final String KEY_COLOR = "color";
    private static final String KEY_LATITUDE = "latitude";
    private static final String KEY_LONGITUDE = "longitude";
    private static final String KEY_LOCATION = "location";

    //region log table
    private static final String TABLE_REGION = "region_log";
    private static final String KEY_DATE = "entry_date";
    private static final String KEY_REGION_IN = "region_in_flag";
    private static final String KEY_NEAREST_BEACON = "near_beacon";
    private static final String KEY_NEAREST_BE_DISTANCE = "distance";

    //notification log table
    private static final String TABLE_INFORMATION = "notification";
    private static final String KEY_INFO = "infoKey";
    private static final String KEY_INFO_DESC = "infoDesc";
    private static final String KEY_INFO_ROLE = "role";
    private static final String KEY_INFO_IS_NEW = "isNew";
    private static final String KEY_INFO_USER_ID = "userId";
    private static final String KEY_UUID = "uuid";


    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
//        dropTables(db);
        String CREATE_EMP_TABLE = "CREATE TABLE " + TABLE_EMP + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_EMP_ID + " TEXT," + KEY_DEVICE_ID + " TEXT," + KEY_FIRST_NAME + " TEXT,"
                + KEY_LAST_NAME + " TEXT," + KEY_DOB + " TEXT," + KEY_EMAIL + " TEXT," + KEY_ROLE + "TEXT," + KEY_DOF_JOIN + " TEXT" + ")";
        db.execSQL(CREATE_EMP_TABLE);
        Log.i("DatabaseHandler", "Employee Table Created......");
        String CREATE_LOG_TABLE = "CREATE TABLE " + TABLE_LOG + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_EMAIL + " TEXT," + KEY_IN_TIME + " TEXT,"
                + KEY_OUT_TIME + " TEXT," + KEY_DAY + " TEXT, " + KEY_FIRST_IN + " TEXT, " + KEY_LAST_OUT + " TEXT, "
                + KEY_FIRST_IN_NAME + " TEXT, " + KEY_FIRST_IN_LAST + " TEXT, " + KEY_LAST_NAME + " TEXT, " + KEY_LAST_IN_LAST + " TEXT, " + KEY_LOC + " TEXT" + ")";
        db.execSQL(CREATE_LOG_TABLE);
        Log.i("DatabaseHandler", "Log Table Created......");
        String CREATE_BEACON_TABLE = "CREATE TABLE " + TABLE_BEACON + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_BEACON_DESC + " TEXT," + KEY_BEACON_KEY + " TEXT,"
                + KEY_BEACON_ID + " TEXT," + KEY_COLOR + " TEXT, " + KEY_BEACON_ENTRY_MSG + " TEXT, " + KEY_BEACON_EXIT_MSG + " TEXT, " + KEY_MAJOR + " TEXT, " + KEY_MINOR + " TEXT, " + KEY_PROXIMITY + " TEXT, " + KEY_LATITUDE + " TEXT, " + KEY_LONGITUDE + " TEXT, " + KEY_LOCATION + " TEXT" + ")";
        db.execSQL(CREATE_BEACON_TABLE);
        Log.i("DatabaseHandler", "Beacon Table Created......" + CREATE_BEACON_TABLE);
        String CREATE_TABLE_REGION = "CREATE TABLE " + TABLE_REGION + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_REGION_IN + " TEXT," + KEY_NEAREST_BEACON + " TEXT,"
                + KEY_NEAREST_BE_DISTANCE + " TEXT," + KEY_DATE + " DATE" + ")";
        db.execSQL(CREATE_TABLE_REGION);
        Log.i("DatabaseHandler", "Region Table Created......");
        String CREATE_INFORMATION = "CREATE TABLE " + TABLE_INFORMATION + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_INFO + " TEXT," + KEY_INFO_DESC + " TEXT,"
                + KEY_INFO_IS_NEW + " TEXT," + KEY_INFO_ROLE + " TEXT," + KEY_INFO_USER_ID + " TEXT" + ")";
        db.execSQL(CREATE_INFORMATION);
        Log.i("DatabaseHandler", "Information Table Created......");

    }

    private void dropTables(SQLiteDatabase db) {
        db.execSQL("drop table " + TABLE_INFORMATION);
        db.execSQL("drop table " + TABLE_BEACON);
        db.execSQL("drop table " + TABLE_EMP);
        db.execSQL("drop table " + TABLE_LOG);
        db.execSQL("drop table " + TABLE_REGION);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//    onCreate(db);
    }

    public void addBeacons(BeaconMaster bea) {
        Log.i("DatabaseHandler", "----Add Beacon Start-----");
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_BEACON_KEY, bea.getBeaconKey());
        values.put(KEY_BEACON_DESC, bea.getBeaconDesc());
        values.put(KEY_MAJOR, bea.getMajor());
        values.put(KEY_MINOR, bea.getMinor());
        values.put(KEY_BEACON_ID, bea.getBeaconId());
        values.put(KEY_PROXIMITY, bea.getProximityId());
        values.put(KEY_BEACON_ENTRY_MSG, bea.getEntryMsg());
        values.put(KEY_BEACON_EXIT_MSG, bea.getExitMsg());
        values.put(KEY_LATITUDE, bea.getLatitude());
        values.put(KEY_LONGITUDE, bea.getLongitutde());
        values.put(KEY_LOCATION, bea.getLocation());
        // Inserting Row
        db.insert(TABLE_BEACON, null, values);
        db.close(); // Closing database connection
        Log.i("DatabaseHandler", "------Add Beacon End----" + bea.getBeaconId());
    }

    public List<BeaconMaster> getAllBeacons() {
        Log.i("DatabaseHandler", "Get all beacon record----------");
        List<BeaconMaster> attList = new ArrayList<BeaconMaster>();
        // Select All Query
        String selectQuery = "SELECT " + KEY_BEACON_KEY + "," + KEY_BEACON_DESC + "," + KEY_BEACON_ENTRY_MSG + "," + KEY_BEACON_EXIT_MSG + "," + KEY_BEACON_ID + "," + KEY_MAJOR + "," + KEY_MINOR + "," + KEY_LATITUDE + "," + KEY_LONGITUDE + "," + KEY_LOCATION + " FROM " + TABLE_BEACON;


        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                BeaconMaster bea = new BeaconMaster();
                bea.setBeaconKey(cursor.getString(0));
                bea.setBeaconDesc(cursor.getString(1));
//                bea.setColor(cursor.getString(1));
                bea.setBeaconId(cursor.getString(4));
                bea.setEntryMsg(cursor.getString(2));
                bea.setExitMsg(cursor.getString(3));
                bea.setMajor(cursor.getString(5));
                bea.setMinor(cursor.getString(6));
                bea.setLatitude(cursor.getString(7));
                bea.setLongitutde(cursor.getString(8));
                bea.setLocation(cursor.getString(9));
                // Adding contact to list
                attList.add(bea);
            } while (cursor.moveToNext());
        }

        // return contact list
        return attList;
    }

}

