package io.ideaction.doctoeasy.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.text.Spannable;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.ideaction.doctoeasy.DoctoEasyApplication;
import io.ideaction.doctoeasy.R;
import io.ideaction.doctoeasy.activities.AuthActivity;

public class SearchFragment extends Fragment {

    private static final String TAG = "SearchFragment";

    @BindView(R.id.toolbar_phone)
    ImageView mIVPhone;
    @BindView(R.id.tv_hello)
    TextView mTVHello;
    @BindView(R.id.tv_find)
    TextView mTVFind;
    @BindView(R.id.tv_specialist_title)
    TextView mTVSpecialistTitle;
    @BindView(R.id.tv_specialist)
    TextView mTVSpecialist;
    @BindView(R.id.tv_area_title)
    TextView mTVAreaTitle;
    @BindView(R.id.tv_area)
    TextView mTVArea;
    @BindView(R.id.tv_date_title)
    TextView mTVDateTitle;
    @BindView(R.id.tv_date)
    TextView mTVDate;

    private AuthActivity mActivity;
    private Context mContext;
    private Unbinder mUnbinder;
    private View mView;
    private Realm mRealm;

    public static SearchFragment newInstance() {
        Log.i(TAG, "newInstance");
        return new SearchFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "onCreate");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_search, container, false);

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

        String find = mContext.getResources().getString(R.string.find);
        String findNext = mContext.getResources().getString(R.string.medical_services_now);
        mTVFind.setText(find + " " + findNext, TextView.BufferType.SPANNABLE);
        Spannable spannable = (Spannable) mTVFind.getText();
        spannable.setSpan(new ForegroundColorSpan(0xffffa000), 0, find.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        if (!DoctoEasyApplication.getSearchSpecialist().isEmpty()) {
            mTVSpecialist.setText(DoctoEasyApplication.getSearchSpecialist());
        }

        if (!DoctoEasyApplication.getSearchArea().isEmpty()) {
            mTVArea.setText(DoctoEasyApplication.getSearchArea());
        }

        if (!DoctoEasyApplication.getSearchDate().isEmpty()) {
            mTVDate.setText(DoctoEasyApplication.getSearchDate());
        }
    }

    @OnClick(R.id.tv_specialist)
    public void onClickSpecialist() {
        mActivity.setFragment(SearchSpecialistFragment.newInstance());
    }

    @OnClick(R.id.tv_area)
    public void onClickArea() {
        mActivity.setFragment(SearchAreaFragment.newInstance());
    }

    @OnClick(R.id.tv_date)
    public void onClickDate() {
        mActivity.setFragment(SearchDateFragment.newInstance());
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
