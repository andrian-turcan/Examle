package io.ideaction.doctoeasy.db.specialist;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmList;
import io.realm.RealmObject;

public class SpecializedInformation extends RealmObject {
    @SerializedName("specialty_id")
    @Expose
    private String specialtyId;
    @SerializedName("languages")
    @Expose
    private RealmList<String> languages = null;
    @SerializedName("rating")
    @Expose
    private String rating;
    @SerializedName("reviews")
    @Expose
    private String reviews;
    @SerializedName("fee_firstConsult")
    @Expose
    private String feeFirstConsult;
    @SerializedName("fee_followUpConsult")
    @Expose
    private String feeFollowUpConsult;

    public void cascadeDelete() {
        if (languages != null) languages.deleteAllFromRealm();
        deleteFromRealm();
    }

    public String getSpecialtyId() {
        return specialtyId;
    }

    public void setSpecialtyId(String specialtyId) {
        this.specialtyId = specialtyId;
    }

    public RealmList<String> getLanguages() {
        return languages;
    }

    public void setLanguages(RealmList<String> languages) {
        this.languages = languages;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getReviews() {
        return reviews;
    }

    public void setReviews(String reviews) {
        this.reviews = reviews;
    }

    public String getFeeFirstConsult() {
        return feeFirstConsult;
    }

    public void setFeeFirstConsult(String feeFirstConsult) {
        this.feeFirstConsult = feeFirstConsult;
    }

    public String getFeeFollowUpConsult() {
        return feeFollowUpConsult;
    }

    public void setFeeFollowUpConsult(String feeFollowUpConsult) {
        this.feeFollowUpConsult = feeFollowUpConsult;
    }
}
