package comp5216.sydney.edu.au.homework2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

public class FacebookLogin extends AppCompatActivity {

    LoginButton loginButton;
    CallbackManager callbackManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        FacebookSdk.sdkInitialize(getApplicationContext());

        loginButton = (LoginButton) findViewById(R.id.login_button);
        loginButton.setReadPermissions("email","user_friends","public_profile");
        callbackManager = CallbackManager.Factory.create();
        // Other app specific specialization

        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Log.d("Facebook","login success");
            }

            @Override
            public void onCancel() {
                Log.d("Facebook","login cancelled");
            }

            @Override
            public void onError(FacebookException error) {
                Log.d("Facebook","login error");
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
        Intent intent = new Intent(this, MainActivity_grid.class);

        if(intent != null){
            startActivity(intent);
        }
    }
}
