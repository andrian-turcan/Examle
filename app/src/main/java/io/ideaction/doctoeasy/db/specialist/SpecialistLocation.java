package io.ideaction.doctoeasy.db.specialist;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmList;
import io.realm.RealmObject;

public class SpecialistLocation extends RealmObject {
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
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("schedule")
    @Expose
    private RealmList<SpecialistSchedule> specialistSchedule = null;

    public void cascadeDelete() {
        if (specialistSchedule != null) specialistSchedule.deleteAllFromRealm();
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public RealmList<SpecialistSchedule> getSpecialistSchedule() {
        return specialistSchedule;
    }

    public void setSpecialistSchedule(RealmList<SpecialistSchedule> specialistSchedule) {
        this.specialistSchedule = specialistSchedule;
    }
}
