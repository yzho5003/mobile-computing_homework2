package comp5216.sydney.edu.au.homework2;

/**
 * Created by jason on 10/10/16.
 */

import android.app.Application;

import com.facebook.FacebookSdk;

public class EntryPoint extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        FacebookSdk.sdkInitialize(getApplicationContext());
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }
}
