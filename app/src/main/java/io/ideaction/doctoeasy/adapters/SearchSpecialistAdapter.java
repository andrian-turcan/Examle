package io.ideaction.doctoeasy.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.ideaction.doctoeasy.R;
import io.ideaction.doctoeasy.db.Area;
import io.ideaction.doctoeasy.db.DiagnosticMethod;
import io.ideaction.doctoeasy.db.Illness;
import io.ideaction.doctoeasy.db.TreatmentProcedure;
import io.ideaction.doctoeasy.db.clinic.Clinic;
import io.ideaction.doctoeasy.db.specialist.Specialist;

public class SearchSpecialistAdapter extends RecyclerView.Adapter<SearchSpecialistAdapter.ViewHolder> {

    private Context mContext;
    private SearchSpecialistListener mSearchSpecialistListener;
    private ArrayList<Specialist> mSpecialists;
    private ArrayList<Clinic> mClinics;
    private ArrayList<DiagnosticMethod> mDiagnosticMethods;
    private ArrayList<Illness> mIllness;
    private ArrayList<TreatmentProcedure> mTreatmentProcedures;

    public SearchSpecialistAdapter(Context context, SearchSpecialistListener searchSpecialistListener, List<Specialist> specialists, List<Clinic> clinics, List<DiagnosticMethod> diagnosticMethods, List<Illness> illness, List<TreatmentProcedure> treatmentProcedures) {
        mContext = context;
        mSearchSpecialistListener = searchSpecialistListener;
        mSpecialists = (ArrayList<Specialist>) specialists;
        mClinics = (ArrayList<Clinic>) clinics;
        mDiagnosticMethods = (ArrayList<DiagnosticMethod>) diagnosticMethods;
        mIllness = (ArrayList<Illness>) illness;
        mTreatmentProcedures = (ArrayList<TreatmentProcedure>) treatmentProcedures;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.search_specialist_item, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        if (i < mSpecialists.size()) {
            viewHolder.mTV.setText(mSpecialists.get(i).getPersonalInfo().getName());

        } else if (i < mSpecialists.size() + mClinics.size()) {
            viewHolder.mTV.setText(mClinics.get(i - mSpecialists.size()).getGeneralInfo().getTitle());

        } else if (i < mSpecialists.size() + mClinics.size() + mDiagnosticMethods.size()) {
            viewHolder.mTV.setText(mDiagnosticMethods.get(i - mSpecialists.size() - mClinics.size()).getTitle());

        } else if (i < mSpecialists.size() + mClinics.size() + mDiagnosticMethods.size() + mIllness.size()) {
            viewHolder.mTV.setText(mIllness.get(i - mSpecialists.size() - mClinics.size() - mDiagnosticMethods.size()).getTitle());

        } else if (i < mSpecialists.size() + mClinics.size() + mDiagnosticMethods.size() + mIllness.size() + mTreatmentProcedures.size()) {
            viewHolder.mTV.setText(mTreatmentProcedures.get(i - mSpecialists.size() - mClinics.size() - mDiagnosticMethods.size() - mIllness.size()).getTitle());
        }
    }

    @Override
    public int getItemCount() {
        return mSpecialists.size() + mClinics.size() + mDiagnosticMethods.size() + mIllness.size() + mTreatmentProcedures.size();
    }

    public void setData(List<Specialist> specialists, List<Clinic> clinics, List<DiagnosticMethod> diagnosticMethods, List<Illness> illness, List<TreatmentProcedure> treatmentProcedures) {
        mSpecialists = (ArrayList<Specialist>) specialists;
        mClinics = (ArrayList<Clinic>) clinics;
        mDiagnosticMethods = (ArrayList<DiagnosticMethod>) diagnosticMethods;
        mIllness = (ArrayList<Illness>) illness;
        mTreatmentProcedures = (ArrayList<TreatmentProcedure>) treatmentProcedures;
        notifyDataSetChanged();
    }

    public void clearData() {
        mSpecialists.clear();
        mClinics.clear();
        mDiagnosticMethods.clear();
        mIllness.clear();
        mTreatmentProcedures.clear();
        notifyDataSetChanged();
    }

    public interface SearchSpecialistListener {
        void onClickItem(int position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.rl)
        RelativeLayout mRL;
        @BindView(R.id.tv)
        TextView mTV;
        @BindView(R.id.iv)
        ImageView mIV;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
        }
    }
}
