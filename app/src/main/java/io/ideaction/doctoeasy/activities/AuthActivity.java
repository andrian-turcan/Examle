package io.ideaction.doctoeasy.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.ideaction.doctoeasy.R;
import io.ideaction.doctoeasy.fragments.AppointmentFragment;
import io.ideaction.doctoeasy.fragments.AuthFragment;
import io.ideaction.doctoeasy.fragments.ForgotPasswordFragment;
import io.ideaction.doctoeasy.fragments.HealthCheckFragment;
import io.ideaction.doctoeasy.fragments.InboxFragment;
import io.ideaction.doctoeasy.fragments.LogInFragment;
import io.ideaction.doctoeasy.fragments.SearchAreaFragment;
import io.ideaction.doctoeasy.fragments.SearchDateFragment;
import io.ideaction.doctoeasy.fragments.SearchFragment;
import io.ideaction.doctoeasy.fragments.SearchSpecialistFragment;
import io.ideaction.doctoeasy.fragments.SignUpFormFragment;
import io.ideaction.doctoeasy.fragments.SignUpFragment;

public class AuthActivity extends AppCompatActivity {

    private static final String TAG = "AuthActivity";

    @BindView(R.id.bottom_navigation_view)
    BottomNavigationView mBottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);
        Log.i(TAG, "onCreate");

        setActivity();
    }

    private void setActivity() {
        Log.i(TAG, "initActivity");
        ButterKnife.bind(this);

        mBottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.nav_search:
                    setFragment(SearchFragment.newInstance());
                    break;
                case R.id.nav_appointment:
                    setFragment(AppointmentFragment.newInstance());
                    break;
                case R.id.nav_inbox:
                    setFragment(InboxFragment.newInstance());
                    break;
                case R.id.nav_health_check:
                    setFragment(HealthCheckFragment.newInstance());
                    break;
                case R.id.nav_login:
                    setFragment(AuthFragment.newInstance());
                    break;
            }

            return true;
        });

        mBottomNavigationView.setSelectedItemId(R.id.nav_login);
    }

    public static Intent getIntentToStart(Context context) {
        Log.i(TAG, "getIntentToStart");
        return new Intent(context, AuthActivity.class);
    }

    public void setFragment(Fragment fragment) {
        Log.i(TAG, "setFragment");
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.frame_layout, fragment)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE)
                .addToBackStack(null)
                .commit();
    }

    public void hideKeyboard() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
        View view = getCurrentFocus();
        if (view == null) {
            view = new View(this);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    @Override
    public void onBackPressed() {
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.frame_layout);

        if (fragment instanceof ForgotPasswordFragment) {
            setFragment(LogInFragment.newInstance());
        } else if (fragment instanceof LogInFragment) {
            setFragment(AuthFragment.newInstance());
        } else if (fragment instanceof SignUpFormFragment) {
            setFragment(SignUpFragment.newInstance());
        } else if (fragment instanceof SignUpFragment) {
            setFragment(AuthFragment.newInstance());
        } else if (fragment instanceof SearchDateFragment
                || fragment instanceof SearchAreaFragment
                || fragment instanceof SearchSpecialistFragment){
            setFragment(SearchFragment.newInstance());
        } else {
            finishAffinity();
            int pid = android.os.Process.myPid();
            android.os.Process.killProcess(pid);
        }
    }
}
