package io.ideaction.doctoeasy.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import io.ideaction.doctoeasy.R;
import io.ideaction.doctoeasy.activities.AuthActivity;

public class SignUpFragment extends Fragment {

    private static final String TAG = "SignUpFragment";

    private AuthActivity mActivity;
    private Context mContext;
    private Unbinder mUnbinder;
    private View mView;

    public static SignUpFragment newInstance() {
        Log.i(TAG, "newInstance");
        return new SignUpFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "onCreate");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_sign_up, container, false);
        Log.i(TAG, "onCreateView");

        setFragment();

        return mView;
    }

    private void setFragment() {
        Log.i(TAG, "setFragment");
        mActivity = (AuthActivity) getActivity();
        mContext = mActivity;
        mUnbinder = ButterKnife.bind(this, mView);
    }

    @OnClick(R.id.tv_log_in)
    public void onClickLogIn() {
        mActivity.setFragment(LogInFragment.newInstance());
    }

    @OnClick(R.id.btn_patient)
    public void onClickPatient() {
        mActivity.setFragment(SignUpFormFragment.newInstance(SignUpFormFragment.PATIENT));
    }

    @OnClick(R.id.btn_specialist)
    public void onClickSpecialist() {
        mActivity.setFragment(SignUpFormFragment.newInstance(SignUpFormFragment.SPECIALIST));
    }

    @OnClick(R.id.btn_clinic)
    public void onClickClinic() {
        mActivity.setFragment(SignUpFormFragment.newInstance(SignUpFormFragment.CLINIC));
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
    }
}
