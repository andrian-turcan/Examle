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

public class ForgotPasswordFragment extends Fragment {

    private static final String TAG = "AuthFragment";

    private AuthActivity mActivity;
    private Context mContext;
    private Unbinder mUnbinder;
    private View mView;

    public static ForgotPasswordFragment newInstance() {
        Log.i(TAG, "ForgotPasswordFragment");
        return new ForgotPasswordFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.i(TAG, "onCreate");
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_forgot_password, container, false);
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

    @OnClick({R.id.rl_next, R.id.tv_log_in})
    public void onClickLogIn() {
        mActivity.setFragment(LogInFragment.newInstance());
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
