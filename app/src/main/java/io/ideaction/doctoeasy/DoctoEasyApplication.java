package io.ideaction.doctoeasy;

import android.app.Application;

import net.grandcentrix.tray.AppPreferences;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.ideaction.doctoeasy.network.DoctoEasyAPI;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class DoctoEasyApplication extends Application {

    private static final String IS_LOGGED = "io.ideaction.doctoeasy.is.logged";
    private static final String SEARCH_SPECIALIST = "io.ideaction.doctoeasy.search.specialist";
    private static final String SEARCH_AREA = "io.ideaction.doctoeasy.search.area";
    private static final String SEARCH_DATE = "io.ideaction.doctoeasy.search.date";

    private static DoctoEasyApplication sDoctorEasyApplication;
    private static Retrofit sRetrofit;
    private static AppPreferences sAppPreferences;

    public static DoctoEasyApplication getInstance() {
        return sDoctorEasyApplication;
    }

    public static DoctoEasyAPI apiInterface() {
        return sRetrofit.create(DoctoEasyAPI.class);
    }

    @Override
    public void onCreate() {
        super.onCreate();

        Realm.init(this);
        RealmConfiguration configuration = new RealmConfiguration.Builder().name("doctoeasy.realm").build();
        Realm.setDefaultConfiguration(configuration);

        sDoctorEasyApplication = this;
        sAppPreferences = new AppPreferences(this);

        sRetrofit = new Retrofit.Builder()
                .baseUrl("http://xxx.xxx.xxx/xxx/")
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        setIsLogged(false);
        setSearchSpecialist("");
        setSearchArea("");
        setSearchDate("");
    }

    public static void setIsLogged(boolean isLogged) {
        sAppPreferences.put(IS_LOGGED, isLogged);
    }

    public static boolean isLogged() {
        return sAppPreferences.getBoolean(IS_LOGGED, false);
    }

    public static void setSearchSpecialist(String specialist) {
        sAppPreferences.put(SEARCH_SPECIALIST, specialist);
    }

    public static String getSearchSpecialist() {
        return sAppPreferences.getString(SEARCH_SPECIALIST, "");
    }

    public static void setSearchArea(String area) {
        sAppPreferences.put(SEARCH_AREA, area);
    }

    public static String getSearchArea() {
        return sAppPreferences.getString(SEARCH_AREA, "");
    }

    public static void setSearchDate(String date) {
        sAppPreferences.put(SEARCH_DATE, date);
    }

    public static String getSearchDate() {
        return sAppPreferences.getString(SEARCH_DATE, "");
    }
}
