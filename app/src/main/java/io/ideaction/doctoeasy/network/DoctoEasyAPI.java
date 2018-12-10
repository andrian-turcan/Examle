package io.ideaction.doctoeasy.network;

import java.util.ArrayList;
import java.util.List;

import io.ideaction.doctoeasy.db.Area;
import io.ideaction.doctoeasy.db.DiagnosticMethod;
import io.ideaction.doctoeasy.db.Illness;
import io.ideaction.doctoeasy.db.specialist.Specialist;
import io.ideaction.doctoeasy.db.TreatmentProcedure;
import io.ideaction.doctoeasy.db.clinic.Clinic;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface DoctoEasyAPI {

    String GET_SPECIALIST = "xxxx";
    String GET_CLINIC = "xxxx";
    String GET_ILLNESS = "xxxx";
    String GET_DIAGNOSTIC_METHOD = "xxxx";
    String GET_TREATMENT_PROCEDURE = "xxxx";
    String GET_AREA = "xxxx";


    @FormUrlEncoded
    @POST("./")
    Call<List<Specialist>> getSpecialists(@Field("xxxx") String task);

    @FormUrlEncoded
    @POST("./")
    Call<List<Clinic>> getClinics(@Field("xxxx") String task);

    @FormUrlEncoded
    @POST("./")
    Call<List<Area>> getAreas(@Field("xxxx") String task);

    @FormUrlEncoded
    @POST("./")
    Call<List<Illness>> getIllness(@Field("xxxx") String task);

    @FormUrlEncoded
    @POST("./")
    Call<List<DiagnosticMethod>> getDiagnosticMethods(@Field("xxxx") String task);

    @FormUrlEncoded
    @POST("./")
    Call<List<TreatmentProcedure>> getTreatmentProcedures(@Field("task") String task);
}
