package io.ideaction.doctoeasy.db.clinic;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmList;
import io.realm.RealmObject;

public class Clinic extends RealmObject {
    @SerializedName("general_info")
    @Expose
    private GeneralInfo generalInfo;
    @SerializedName("locations")
    @Expose
    private RealmList<ClinicLocation> clinicLocations = null;

    public void cascadeDelete() {
        if (generalInfo != null) generalInfo.deleteFromRealm();

        if (clinicLocations != null) {
            for (int i = clinicLocations.size() - 1; i >= 0; i--) {
                if (clinicLocations.get(i) != null) clinicLocations.get(i).cascadeDelete();
            }
        }

        deleteFromRealm();
    }

    public GeneralInfo getGeneralInfo() {
        return generalInfo;
    }

    public void setGeneralInfo(GeneralInfo generalInfo) {
        this.generalInfo = generalInfo;
    }

    public RealmList<ClinicLocation> getClinicLocations() {
        return clinicLocations;
    }

    public void setClinicLocations(RealmList<ClinicLocation> clinicLocations) {
        this.clinicLocations = clinicLocations;
    }
}
