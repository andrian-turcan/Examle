package io.ideaction.doctoeasy.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.ideaction.doctoeasy.R;
import io.ideaction.doctoeasy.activities.AuthActivity;

public class SearchDateFragment extends Fragment {

    private static final String TAG = "LogInFragment";

    private AuthActivity mActivity;
    private Context mContext;
    private Unbinder mUnbinder;
    private View mView;
    private Realm mRealm;

    public static SearchDateFragment newInstance() {
        Log.i(TAG, "newInstance");
        return new SearchDateFragment();
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "onCreate");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_search_date, container, false);
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
    }

    @OnClick(R.id.btn_done)
    public void onClickDone() {
        mActivity.hideKeyboard();
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
