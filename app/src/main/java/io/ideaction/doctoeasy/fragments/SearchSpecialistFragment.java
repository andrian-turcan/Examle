package io.ideaction.doctoeasy.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import io.realm.Case;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;
import io.ideaction.doctoeasy.DoctoEasyApplication;
import io.ideaction.doctoeasy.R;
import io.ideaction.doctoeasy.activities.AuthActivity;
import io.ideaction.doctoeasy.adapters.SearchSpecialistAdapter;
import io.ideaction.doctoeasy.db.Area;
import io.ideaction.doctoeasy.db.DiagnosticMethod;
import io.ideaction.doctoeasy.db.Illness;
import io.ideaction.doctoeasy.db.TreatmentProcedure;
import io.ideaction.doctoeasy.db.clinic.Clinic;
import io.ideaction.doctoeasy.db.specialist.Specialist;

public class SearchSpecialistFragment extends Fragment implements SearchSpecialistAdapter.SearchSpecialistListener {

    private static final String TAG = "LogInFragment";

    @BindView(R.id.et_search)
    EditText mETSearch;
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;

    private AuthActivity mActivity;
    private Context mContext;
    private Unbinder mUnbinder;
    private View mView;
    private Realm mRealm;

    private SearchSpecialistAdapter mSearchSpecialistAdapter;
    private List<Specialist> mSpecialists;
    private List<Clinic> mClinics;
    private List<DiagnosticMethod> mDiagnosticMethods;
    private List<Illness> mIllness;
    private List<TreatmentProcedure> mTreatmentProcedures;

    public static SearchSpecialistFragment newInstance() {
        Log.i(TAG, "newInstance");
        return new SearchSpecialistFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "onCreate");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_search_specialist, container, false);
        Log.i(TAG, "onCreateView");

        setFragment();
        setRecyclerView();

        return mView;
    }

    private void setFragment() {
        Log.i(TAG, "setFragment");
        mActivity = (AuthActivity) getActivity();
        mContext = mActivity;
        mUnbinder = ButterKnife.bind(this, mView);

        RealmConfiguration configuration = new RealmConfiguration.Builder().name("doctoeasy.realm").build();
        mRealm = Realm.getInstance(configuration);

        mETSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                Log.i(TAG, "afterTextChanged");
                if (String.valueOf(mETSearch.getText()).length() > 1) {
                    Log.i(TAG, "afterTextChanged > 1");
                    updateRecyclerView();
                } else {
                    Log.i(TAG, "afterTextChanged < 2");
                    mSearchSpecialistAdapter.clearData();
//                    mSearchSpecialistAdapter.setData(mSpecialists, mClinics, mDiagnosticMethods, mIllness, mTreatmentProcedures);
                }
            }
        });
    }

    private void setRecyclerView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(layoutManager);

        mSpecialists = new ArrayList<>();
        mClinics = new ArrayList<>();
        mDiagnosticMethods = new ArrayList<>();
        mIllness = new ArrayList<>();
        mTreatmentProcedures = new ArrayList<>();

        mSearchSpecialistAdapter = new SearchSpecialistAdapter(mContext, SearchSpecialistFragment.this, mSpecialists, mClinics, mDiagnosticMethods, mIllness, mTreatmentProcedures);
        mRecyclerView.setAdapter(mSearchSpecialistAdapter);
    }

    private void updateRecyclerView() {
        final String searchText = String.valueOf(mETSearch.getText());

        mRealm.executeTransactionAsync(
                r -> {
                    RealmResults<Specialist> specialists = r
                            .where(Specialist.class)
                            .contains("personalInfo.name", searchText, Case.INSENSITIVE)
                            .findAll();
                    if (specialists != null) {
                        mSpecialists = r.copyFromRealm(specialists);
                    }
                },
                () -> {
                    mRealm.executeTransactionAsync(
                            r -> {
                                RealmResults<Clinic> clinics = r
                                        .where(Clinic.class)
                                        .contains("generalInfo.title", searchText, Case.INSENSITIVE)
                                        .findAll();
                                if (clinics != null) {
                                    mClinics = r.copyFromRealm(clinics);
                                }
                            },
                            () -> {
                                mRealm.executeTransactionAsync(
                                        r -> {
                                            RealmResults<DiagnosticMethod> diagnosticMethods = r
                                                    .where(DiagnosticMethod.class)
                                                    .contains("title", searchText, Case.INSENSITIVE)
                                                    .findAll();
                                            if (diagnosticMethods != null) {
                                                mDiagnosticMethods = r.copyFromRealm(diagnosticMethods);
                                            }
                                        },
                                        () -> {
                                            mRealm.executeTransactionAsync(
                                                    r -> {
                                                        RealmResults<Illness> illnesses = r
                                                                .where(Illness.class)
                                                                .contains("title", searchText, Case.INSENSITIVE)
                                                                .findAll();
                                                        if (illnesses != null) {
                                                            mIllness = r.copyFromRealm(illnesses);
                                                        }
                                                    },
                                                    () -> {
                                                        mRealm.executeTransactionAsync(
                                                                r -> {
                                                                    RealmResults<TreatmentProcedure> treatmentProcedures = r
                                                                            .where(TreatmentProcedure.class)
                                                                            .contains("title", searchText, Case.INSENSITIVE)
                                                                            .findAll();
                                                                    if (treatmentProcedures != null) {
                                                                        mTreatmentProcedures = r.copyFromRealm(treatmentProcedures);
                                                                    }
                                                                },
                                                                () -> mSearchSpecialistAdapter.setData(mSpecialists, mClinics, mDiagnosticMethods, mIllness, mTreatmentProcedures),
                                                                e -> {
                                                                    showErrorDialog();
                                                                    Log.i(TAG, "updateRecyclerView treatmentProcedures -> " + e.getMessage(), e);
                                                                }
                                                        );
                                                    },
                                                    e -> {
                                                        showErrorDialog();
                                                        Log.i(TAG, "updateRecyclerView illness -> " + e.getMessage(), e);
                                                    }
                                            );
                                        },
                                        e -> {
                                            showErrorDialog();
                                            Log.i(TAG, "updateRecyclerView diagnosticMethod -> " + e.getMessage(), e);
                                        }
                                );
                            },
                            e -> {
                                showErrorDialog();
                                Log.i(TAG, "updateRecyclerView clinic -> " + e.getMessage(), e);
                            }
                    );
                },
                e -> {
                    showErrorDialog();
                    Log.i(TAG, "updateRecyclerView specialist -> " + e.getMessage(), e);
                }
        );
    }

    private void showErrorDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);

        builder.setTitle("Unexpected error");
        builder.setMessage("An unexpected error occurred. Please restart application.");
        builder.setPositiveButton("Ok", (dialog, which) -> dialog.dismiss());
        builder.setNegativeButton("Exit", (dialog, which) -> dialog.dismiss());
        builder.show();
    }

    @Override
    public void onClickItem(int position) {

    }

    @OnClick(R.id.btn_done)
    public void onClickDone() {
        mActivity.hideKeyboard();
        DoctoEasyApplication.setSearchSpecialist(String.valueOf(mETSearch.getText()));
        mActivity.setFragment(SearchFragment.newInstance());
    }

    @OnClick(R.id.iv_back)
    public void onClickBack() {
        mActivity.hideKeyboard();
        mActivity.setFragment(SearchFragment.newInstance());
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        if (mUnbinder != null) {
            mUnbinder.unbind();
            mUnbinder = null;
        }

        if (mRealm != null) {
            mRealm.close();
            mRealm = null;
        }
    }
}
