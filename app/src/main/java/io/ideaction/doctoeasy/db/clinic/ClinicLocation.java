package io.ideaction.doctoeasy.db.clinic;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmList;
import io.realm.RealmObject;

public class ClinicLocation extends RealmObject {
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("location_city")
    @Expose
    private String locationCity;
    @SerializedName("location_adress")
    @Expose
    private String locationAdress;
    @SerializedName("location_lat")
    @Expose
    private String locationLat;
    @SerializedName("location_lng")
    @Expose
    private String locationLng;
    @SerializedName("schedule")
    @Expose
    private RealmList<ClinicSchedule> clinicSchedule = null;

    public void cascadeDelete() {
        if (clinicSchedule != null) clinicSchedule.deleteAllFromRealm();
        deleteFromRealm();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLocationCity() {
        return locationCity;
    }

    public void setLocationCity(String locationCity) {
        this.locationCity = locationCity;
    }

    public String getLocationAdress() {
        return locationAdress;
    }

    public void setLocationAdress(String locationAdress) {
        this.locationAdress = locationAdress;
    }

    public String getLocationLat() {
        return locationLat;
    }

    public void setLocationLat(String locationLat) {
        this.locationLat = locationLat;
    }

    public String getLocationLng() {
        return locationLng;
    }

    public void setLocationLng(String locationLng) {
        this.locationLng = locationLng;
    }

    public RealmList<ClinicSchedule> getClinicSchedule() {
        return clinicSchedule;
    }

    public void setClinicSchedule(RealmList<ClinicSchedule> clinicSchedule) {
        this.clinicSchedule = clinicSchedule;
    }
}
