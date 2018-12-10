package io.ideaction.doctoeasy.fragments;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.TextView;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.ideaction.doctoeasy.R;
import io.ideaction.doctoeasy.activities.AuthActivity;

public class SignUpFormFragment extends Fragment {

    private static final String TAG = "SignUpFormFragment";
    static final int PATIENT = 0;
    static final int SPECIALIST = 1;
    static final int CLINIC = 2;
    private static final String USER_TYPE = "io.ideaction.doctoeasy.fragments.userType";

    private AuthActivity mActivity;
    private Context mContext;
    private Unbinder mUnbinder;
    private View mView;
    private Realm mRealm;

    @BindView(R.id.tv_form_type)
    TextView mTVFormType;
    @BindView(R.id.tv_gender)
    TextView mTVGender;
    @BindView(R.id.tv_date_of_birth)
    TextView mTVDateOfBirth;

    private int mUserType;

    public static SignUpFormFragment newInstance(int userType) {
        Log.i(TAG, "newInstance");
        SignUpFormFragment fragment = new SignUpFormFragment();
        Bundle args = new Bundle();
        args.putInt(USER_TYPE, userType);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.i(TAG, "onCreate");
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mUserType = getArguments().getInt(USER_TYPE);
        } else {
            mUserType = 99;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_sign_up_form, container, false);
        Log.i(TAG, "onCreateView");

        setFragment();

        return mView;
    }

    private void setFragment() {
        Log.i(TAG, "setFragment");
        mActivity = (AuthActivity) getActivity();
        mContext = mActivity;
        mUnbinder = ButterKnife.bind(this, mView);

        RealmConfiguration configuration = new RealmConfiguration.Builder().name("doctoeasy.realm").build();
        mRealm = Realm.getInstance(configuration);

        switch (mUserType) {
            case PATIENT:
                mTVFormType.setText(R.string.patient);
                break;
            case SPECIALIST:
                mTVFormType.setText(R.string.healthcare_specialist);
                break;
            case CLINIC:
                mTVFormType.setText(R.string.clinic);
                break;
            default:
                mActivity.onBackPressed();
                break;
        }
    }

    @OnClick(R.id.tv_log_in)
    public void onClickLogIn() {
        mActivity.setFragment(LogInFragment.newInstance());
    }

    @OnClick(R.id.tv_gender)
    public void onClickGender() {
        PopupMenu popupMenu = new PopupMenu(mContext, mTVGender);
        popupMenu.getMenu().add("Male");
        popupMenu.getMenu().add("Female");

        popupMenu.setOnMenuItemClickListener(item -> {
            mTVGender.setText(item.getTitle());
            return true;
        });

        popupMenu.show();
    }

    @OnClick(R.id.tv_date_of_birth)
    public void onClickDateOfBirth() {
        Calendar calendar = Calendar.getInstance();
        final int y = calendar.get(Calendar.YEAR);
        final int m = calendar.get(Calendar.MONTH);
        final int d = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(
                mContext,
                (view, year, month, dayOfMonth) -> {
                    month += 1;
                    String sMonth = month < 10 ? "0" + month : String.valueOf(month);
                    String sDayOfMonth = dayOfMonth < 10 ? "0" + dayOfMonth : String.valueOf(dayOfMonth);

                    mTVDateOfBirth.setText(year + "/" + sMonth + "/" + sDayOfMonth);
                }, y, m, d);

        datePickerDialog.show();
    }

    @OnClick(R.id.btn_sign_up)
    public void onClickSignUp() {
        mActivity.setFragment(AuthFragment.newInstance());
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
