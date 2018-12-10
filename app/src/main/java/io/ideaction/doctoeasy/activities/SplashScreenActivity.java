package io.ideaction.doctoeasy.activities;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;
import io.realm.exceptions.RealmMigrationNeededException;
import io.ideaction.doctoeasy.DoctoEasyApplication;
import io.ideaction.doctoeasy.R;
import io.ideaction.doctoeasy.db.Area;
import io.ideaction.doctoeasy.db.DiagnosticMethod;
import io.ideaction.doctoeasy.db.Illness;
import io.ideaction.doctoeasy.db.specialist.Specialist;
import io.ideaction.doctoeasy.db.TreatmentProcedure;
import io.ideaction.doctoeasy.db.clinic.Clinic;
import io.ideaction.doctoeasy.network.DoctoEasyAPI;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SplashScreenActivity extends AppCompatActivity {

    private static final String TAG = "SplashScreenActivity";

    private Realm mRealm;
    private int count;
    private AlertDialog mAlertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        Log.i(TAG, "onCreate");

        RealmConfiguration configuration = new RealmConfiguration.Builder().name("doctoeasy.realm").build();
        try {
            Log.i(TAG, "get Instance");
            mRealm = Realm.getInstance(configuration);
        } catch (RealmMigrationNeededException r) {
            Log.i(TAG, "delete and get Instance");
            Realm.deleteRealm(configuration);
            mRealm = Realm.getInstance(configuration);
        }

        new Handler().postDelayed(
                () -> startActivity(AuthActivity.getIntentToStart(SplashScreenActivity.this)),
                2000
        );

//        getSpecialists();
//        getAreas();
//        getClinics();
//        getDiagnosticMethods();
//        getIllness();
//        getTreatmentProcedures();
    }

    private void getSpecialists() {
        Log.i(TAG, "deleteSpecialists");
        mRealm.executeTransactionAsync(
                r -> {
                    RealmResults<Specialist> specialists = r.where(Specialist.class).findAll();

                    if (specialists != null)
                        for (int i = specialists.size() - 1; i >= 0; i--)
                            if (specialists.get(i) != null)
                                specialists.get(i).cascadeDelete();
                },
                () -> {
                    Log.i(TAG, "getSpecialists");
                    Call<List<Specialist>> call = DoctoEasyApplication.apiInterface().getSpecialists(DoctoEasyAPI.GET_SPECIALIST);

                    call.enqueue(new Callback<List<Specialist>>() {
                        @Override
                        public void onResponse(Call<List<Specialist>> call, Response<List<Specialist>> response) {
                            if (response.isSuccessful()) {
                                if (response.body() != null) {
                                    mRealm.executeTransactionAsync(
                                            r -> r.copyToRealm(response.body()),
                                            () -> downloadDataFinish(),
                                            e -> {
                                                showErrorDialog();
                                                Log.i(TAG, "getSpecialists -> " + e.getMessage(), e);
                                            }
                                    );
                                } else {
                                    showErrorDialog();
                                    Log.i(TAG, "response body is null");
                                }
                            } else {
                                showErrorDialog();
                                Log.i(TAG, "response is not successful: " + response.message());
                            }
                        }

                        @Override
                        public void onFailure(Call<List<Specialist>> call, Throwable t) {
                            showErrorDialog();
                            Log.i(TAG, "call getSpecialists -> " + t.getMessage(), t);
                        }
                    });
                },
                e -> {
                    showErrorDialog();
                    Log.i(TAG, "deleteSpecialists -> " + e.getMessage(), e);
                }
        );
    }

    private void getClinics() {
        Log.i(TAG, "deleteClinics");
        mRealm.executeTransactionAsync(
                r -> {
                    RealmResults<Clinic> clinics = r.where(Clinic.class).findAll();

                    if (clinics != null)
                        for (int i = clinics.size() - 1; i >= 0; i--)
                            if (clinics.get(i) != null)
                                clinics.get(i).cascadeDelete();
                },
                () -> {
                    Log.i(TAG, "getClinics");
                    Call<List<Clinic>> call = DoctoEasyApplication.apiInterface().getClinics(DoctoEasyAPI.GET_CLINIC);

                    call.enqueue(new Callback<List<Clinic>>() {
                        @Override
                        public void onResponse(Call<List<Clinic>> call, Response<List<Clinic>> response) {
                            if (response.isSuccessful()) {
                                if (response.body() != null) {
                                    mRealm.executeTransactionAsync(
                                            r -> r.copyToRealm(response.body()),
                                            () -> downloadDataFinish(),
                                            e -> {
                                                showErrorDialog();
                                                Log.i(TAG, "getClinics -> " + e.getMessage(), e);
                                            }
                                    );
                                } else {
                                    showErrorDialog();
                                    Log.i(TAG, "response body is null");
                                }
                            } else {
                                showErrorDialog();
                                Log.i(TAG, "response is not successful");
                            }
                        }

                        @Override
                        public void onFailure(Call<List<Clinic>> call, Throwable t) {
                            showErrorDialog();
                            Log.i(TAG, "call getClinics -> " + t.getMessage(), t);
                        }
                    });
                },
                e -> {
                    showErrorDialog();
                    Log.i(TAG, "deleteClinics -> " + e.getMessage(), e);
                }
        );
    }

    private void getAreas() {
        Log.i(TAG, "deleteAreas");
        mRealm.executeTransactionAsync(
                r -> {
                    RealmResults<Area> areas = r.where(Area.class).findAll();

                    if (areas != null)
                        areas.deleteAllFromRealm();
                },
                () -> {
                    Log.i(TAG, "getAreas");
                    Call<List<Area>> call = DoctoEasyApplication.apiInterface().getAreas(DoctoEasyAPI.GET_AREA);

                    call.enqueue(new Callback<List<Area>>() {
                        @Override
                        public void onResponse(Call<List<Area>> call, Response<List<Area>> response) {
                            if (response.isSuccessful()) {
                                if (response.body() != null) {
                                    mRealm.executeTransactionAsync(
                                            r -> r.copyToRealm(response.body()),
                                            () -> downloadDataFinish(),
                                            e -> {
                                                showErrorDialog();
                                                Log.i(TAG, "getAreas -> " + e.getMessage(), e);
                                            }
                                    );
                                } else {
                                    showErrorDialog();
                                    Log.i(TAG, "response body is null");
                                }
                            } else {
                                showErrorDialog();
                                Log.i(TAG, "response is not successful");
                            }
                        }

                        @Override
                        public void onFailure(Call<List<Area>> call, Throwable t) {
                            showErrorDialog();
                            Log.i(TAG, "call getAreas -> " + t.getMessage(), t);
                        }
                    });
                },
                e -> {
                    showErrorDialog();
                    Log.i(TAG, "deleteAreas -> " + e.getMessage(), e);
                }
        );
    }

    private void getDiagnosticMethods() {
        Log.i(TAG, "deleteDiagnosticMethods");
        mRealm.executeTransactionAsync(
                r -> {
                    RealmResults<DiagnosticMethod> diagnosticMethods = r.where(DiagnosticMethod.class).findAll();

                    if (diagnosticMethods != null)
                        diagnosticMethods.deleteAllFromRealm();
                },
                () -> {
                    Log.i(TAG, "getDiagnosticMethods");
                    Call<List<DiagnosticMethod>> call = DoctoEasyApplication.apiInterface().getDiagnosticMethods(DoctoEasyAPI.GET_DIAGNOSTIC_METHOD);

                    call.enqueue(new Callback<List<DiagnosticMethod>>() {
                        @Override
                        public void onResponse(Call<List<DiagnosticMethod>> call, Response<List<DiagnosticMethod>> response) {
                            if (response.isSuccessful()) {
                                if (response.body() != null) {
                                    mRealm.executeTransactionAsync(
                                            r -> r.copyToRealm(response.body()),
                                            () -> downloadDataFinish(),
                                            e -> {
                                                showErrorDialog();
                                                Log.i(TAG, "getDiagnosticMethods -> " + e.getMessage(), e);
                                            }
                                    );
                                } else {
                                    showErrorDialog();
                                    Log.i(TAG, "response body is null");
                                }
                            } else {
                                showErrorDialog();
                                Log.i(TAG, "response is not successful");
                            }
                        }

                        @Override
                        public void onFailure(Call<List<DiagnosticMethod>> call, Throwable t) {
                            showErrorDialog();
                            Log.i(TAG, "call getDiagnosticMethods -> " + t.getMessage(), t);
                        }
                    });
                },
                e -> {
                    showErrorDialog();
                    Log.i(TAG, "deleteDiagnosticMethods -> " + e.getMessage(), e);
                }
        );
    }

    private void getIllness() {
        Log.i(TAG, "deleteIllness");
        mRealm.executeTransactionAsync(
                r -> {
                    RealmResults<Illness> illnesses = r.where(Illness.class).findAll();

                    if (illnesses != null)
                        illnesses.deleteAllFromRealm();
                },
                () -> {
                    Log.i(TAG, "getIllness");
                    Call<List<Illness>> call = DoctoEasyApplication.apiInterface().getIllness(DoctoEasyAPI.GET_ILLNESS);

                    call.enqueue(new Callback<List<Illness>>() {
                        @Override
                        public void onResponse(Call<List<Illness>> call, Response<List<Illness>> response) {
                            if (response.isSuccessful()) {
                                if (response.body() != null) {
                                    mRealm.executeTransactionAsync(
                                            r -> r.copyToRealm(response.body()),
                                            () -> downloadDataFinish(),
                                            e -> {
                                                showErrorDialog();
                                                Log.i(TAG, "getIllness -> " + e.getMessage(), e);
                                            }
                                    );
                                } else {
                                    showErrorDialog();
                                    Log.i(TAG, "response body is null");
                                }
                            } else {
                                showErrorDialog();
                                Log.i(TAG, "response is not successful");
                            }
                        }

                        @Override
                        public void onFailure(Call<List<Illness>> call, Throwable t) {
                            showErrorDialog();
                            Log.i(TAG, "call getIllness -> " + t.getMessage(), t);
                        }
                    });
                },
                e -> {
                    showErrorDialog();
                    Log.i(TAG, "deleteIllness -> " + e.getMessage(), e);
                }
        );
    }

    private void getTreatmentProcedures() {
        Log.i(TAG, "deleteTreatmentProcedures");
        mRealm.executeTransactionAsync(
                r -> {
                    RealmResults<TreatmentProcedure> treatmentProcedures = r.where(TreatmentProcedure.class).findAll();

                    if (treatmentProcedures != null)
                        treatmentProcedures.deleteAllFromRealm();
                },
                () -> {
                    Log.i(TAG, "getTreatmentProcedures");
                    Call<List<TreatmentProcedure>> call = DoctoEasyApplication.apiInterface().getTreatmentProcedures(DoctoEasyAPI.GET_TREATMENT_PROCEDURE);

                    call.enqueue(new Callback<List<TreatmentProcedure>>() {
                        @Override
                        public void onResponse(Call<List<TreatmentProcedure>> call, Response<List<TreatmentProcedure>> response) {
                            if (response.isSuccessful()) {
                                if (response.body() != null) {
                                    mRealm.executeTransactionAsync(
                                            r -> r.copyToRealm(response.body()),
                                            () -> downloadDataFinish(),
                                            e -> {
                                                showErrorDialog();
                                                Log.i(TAG, "getClinics -> " + e.getMessage(), e);
                                            }
                                    );
                                } else {
                                    showErrorDialog();
                                    Log.i(TAG, "response body is null");
                                }
                            } else {
                                showErrorDialog();
                                Log.i(TAG, "response is not successful");
                            }
                        }

                        @Override
                        public void onFailure(Call<List<TreatmentProcedure>> call, Throwable t) {
                            showErrorDialog();
                            Log.i(TAG, "call getClinics -> " + t.getMessage(), t);
                        }
                    });
                },
                e -> {
                    showErrorDialog();
                    Log.i(TAG, "deleteTreatmentProcedures -> " + e.getMessage(), e);
                }
        );
    }

    private void downloadDataFinish() {
        if (++count == 6) startActivity(AuthActivity.getIntentToStart(SplashScreenActivity.this));
    }
    
    private void showErrorDialog() {
        if (mAlertDialog != null) {
            if (!mAlertDialog.isShowing()) {
                mAlertDialog.show();
            }
        } else {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);

            builder.setTitle("Unexpected error");
            builder.setMessage("An unexpected error occurred. Please restart application.");
            builder.setPositiveButton("Ok", (dialog, which) -> recreate());
            builder.setNegativeButton("Exit", (dialog, which) -> {
                finishAffinity();
                int pid = android.os.Process.myPid();
                android.os.Process.killProcess(pid);
            });
            mAlertDialog = builder.create();
            mAlertDialog.show();
        }
    }
}
