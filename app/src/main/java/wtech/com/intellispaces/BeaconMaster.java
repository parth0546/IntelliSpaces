package wtech.com.intellispaces;

/**
 * Created by girish on 15-05-2015.
 */
public class BeaconMaster {

    private Integer id;
    private Integer _id;
    private String beaconId;   //e.g. 234872-28782-2878782-39989 proximity id+major+minor
    private String beaconDesc; //e.g. Entrance beacon, Meeting room beacon..etc.
    private String beaconKey; //e.g. BEA_ENTR, BEA_MEET

    private String proximityId;
    private String major;
    private String minor;

    // private String color;
    private String entryMsg;
    private String exitMsg;
    private String event;
    private String latitude;
    private String longitude;
    private String location;


    public BeaconMaster() {

    }

    public BeaconMaster(String beaconId, String beaconDesc, String beaconKey, String proximityId, String major, String minor,String entryMsg, String exitMsg, String event, String latitude, String longitude, String location) {
        this.beaconId = beaconId;
        this.beaconDesc = beaconDesc;
        this.beaconKey = beaconKey;
        this.proximityId = proximityId;
        this.major = major;
        this.minor = minor;
        this.entryMsg = entryMsg;
        this.exitMsg = exitMsg;
        this.event = event;
        this.latitude = latitude;
        this.longitude = longitude;
        this.location = location;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer get_id() {
        return _id;
    }

    public void set_id(Integer _id) {
        this._id = _id;
    }

    public String getBeaconId() {
        return beaconId;
    }

    public void setBeaconId(String beaconId) {
        this.beaconId = beaconId;
    }

    public String getBeaconDesc() {
        return beaconDesc;
    }

    public void setBeaconDesc(String beaconDesc) {
        this.beaconDesc = beaconDesc;
    }

    public String getBeaconKey() {
        return beaconKey;
    }

    public void setBeaconKey(String beaconKey) {
        this.beaconKey = beaconKey;
    }

    public String getProximityId() {
        return proximityId;
    }

    public void setProximityId(String proximityId) {
        this.proximityId = proximityId;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getMinor() {
        return minor;
    }

    public void setMinor(String minor) {
        this.minor = minor;
    }

//   // public String getColor() {
//        return color;
//    }
//
//    public void setColor(String color) {
//        this.color = color;
//    }

    public String getEntryMsg() {
        return entryMsg;
    }

    public void setEntryMsg(String entryMsg) {
        this.entryMsg = entryMsg;
    }

    public String getExitMsg() {
        return exitMsg;
    }

    public void setExitMsg(String exitMsg) {
        this.exitMsg = exitMsg;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public String getEvent() {
        return event;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLongitutde(String longitutde) {
        this.longitude = longitutde;
    }

    public String getLongitutde() {
        return longitude;
    }

    public void setLocation(String Location) {
        this.location = Location;
    }

    public String getLocation() {
        return location;
    }
}
