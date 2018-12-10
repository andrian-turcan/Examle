package io.ideaction.doctoeasy.db.specialist;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmList;
import io.realm.RealmObject;

public class Specialist extends RealmObject {
    @SerializedName("personal_info")
    @Expose
    private PersonalInfo personalInfo;
    @SerializedName("specialized_information")
    @Expose
    private SpecializedInformation specializedInformation;
    @SerializedName("locations")
    @Expose
    private RealmList<SpecialistLocation> specialistLocations = null;

    public void cascadeDelete() {
        if (personalInfo != null) personalInfo.deleteFromRealm();
        if (specializedInformation != null) specializedInformation.cascadeDelete();

        if (specialistLocations != null) {
            for (int i = specialistLocations.size() - 1; i >= 0; i--) {
                if (specialistLocations.get(i) != null) specialistLocations.get(i).cascadeDelete();
            }
        }

        deleteFromRealm();
    }

    public PersonalInfo getPersonalInfo() {
        return personalInfo;
    }

    public void setPersonalInfo(PersonalInfo personalInfo) {
        this.personalInfo = personalInfo;
    }

    public SpecializedInformation getSpecializedInformation() {
        return specializedInformation;
    }

    public void setSpecializedInformation(SpecializedInformation specializedInformation) {
        this.specializedInformation = specializedInformation;
    }

    public RealmList<SpecialistLocation> getSpecialistLocations() {
        return specialistLocations;
    }

    public void setSpecialistLocations(RealmList<SpecialistLocation> specialistLocations) {
        this.specialistLocations = specialistLocations;
    }
}
