package com.example.diceandload.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.diceandload.MainActivity;
import com.example.diceandload.R;
import com.facebook.FacebookSdk;
import com.google.android.material.textfield.TextInputLayout;


public class LoginActivity extends AppCompatActivity implements SmartLoginCallbacks {
    private static final String TAG = MainActivity.class.getSimpleName();
    private Button facebookLoginButton, googleLoginButton, customSigninButton, logoutButton;
    private View customSignupButton;
    private TextInputLayout passwordTextInputLayout, emailTextInputLayout;
    private EditText emailEditText, passwordEditText;
    SmartUser currentUser;
    //GoogleApiClient mGoogleApiClient;
    SmartLoginConfig config;
    SmartLogin smartLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        bindViews();
        setListeners();

        config = new SmartLoginConfig(this, this);
        config.setFacebookAppId(getString(R.string.facebook_app_id));
        config.setFacebookPermissions(null);
        config.setGoogleApiClient(null);
    }

    @Override
    protected void onResume() {
        super.onResume();
        currentUser = UserSessionManager.getCurrentUser(this);
        refreshLayout();
    }

    private void refreshLayout() {
        currentUser = UserSessionManager.getCurrentUser(this);
        if (currentUser != null) {
            Log.d(TAG, "Logged in user: " + currentUser.toString());
            facebookLoginButton.setVisibility(View.GONE);
            googleLoginButton.setVisibility(View.GONE);
            customSigninButton.setVisibility(View.GONE);
            customSignupButton.setVisibility(View.GONE);
            emailTextInputLayout.setVisibility(View.GONE);
            passwordTextInputLayout.setVisibility(View.GONE);
            logoutButton.setVisibility(View.VISIBLE);
        } else {
            facebookLoginButton.setVisibility(View.VISIBLE);
            googleLoginButton.setVisibility(View.VISIBLE);
            customSigninButton.setVisibility(View.VISIBLE);
            customSignupButton.setVisibility(View.VISIBLE);
            passwordTextInputLayout.setVisibility(View.VISIBLE);
            emailTextInputLayout.setVisibility(View.VISIBLE);
            logoutButton.setVisibility(View.GONE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (smartLogin != null) {
            smartLogin.onActivityResult(requestCode, resultCode, data, config);
        }
    }

    private void setListeners() {
        facebookLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Perform Facebook login
                FacebookSdk.setApplicationId(getString(R.string.facebook_app_id));
                FacebookSdk.sdkInitialize(LoginActivity.this);
                smartLogin = SmartLoginFactory.build(LoginType.Facebook);
                smartLogin.login(config);
            }
        });

        googleLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Perform Google login
                smartLogin = SmartLoginFactory.build(LoginType.Google);
                smartLogin.login(config);
            }
        });

        customSigninButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Perform custom sign in
                smartLogin = SmartLoginFactory.build(LoginType.CustomLogin);
                smartLogin.login(config);
            }
        });

        customSignupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Perform custom sign up
                Log.d(TAG, "Called signup button");
                smartLogin = SmartLoginFactory.build(LoginType.CustomLogin);
                smartLogin.signup(config);
            }
        });

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentUser != null) {
                    if (currentUser instanceof SmartFacebookUser) {
                        smartLogin = SmartLoginFactory.build(LoginType.Facebook);
                    } else if (currentUser instanceof SmartGoogleUser) {
                        smartLogin = SmartLoginFactory.build(LoginType.Google);
                    } else {
                        smartLogin = SmartLoginFactory.build(LoginType.CustomLogin);
                    }
                    boolean result = smartLogin.logout(LoginActivity.this);
                    if (result) {
                        refreshLayout();
                        Toast.makeText(LoginActivity.this, "User logged out successfully", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    private void bindViews() {
        facebookLoginButton = (Button) findViewById(R.id.facebook_login_button);
        googleLoginButton = (Button) findViewById(R.id.google_login_button);
        customSigninButton = (Button) findViewById(R.id.custom_signin_button);
        customSignupButton = findViewById(R.id.custom_signup_button);
        emailEditText = (EditText) findViewById(R.id.email_edittext);
        passwordTextInputLayout = (TextInputLayout) findViewById(R.id.password_text_input_layout);
        emailTextInputLayout = (TextInputLayout) findViewById(R.id.email_text_input_layout);
        passwordEditText = (EditText) findViewById(R.id.password_edittext);
        logoutButton = (Button) findViewById(R.id.logout_button);
    }

    @Override
    public void onLoginSuccess(SmartUser user) {
        Toast.makeText(this, user.toString(), Toast.LENGTH_SHORT).show();
        refreshLayout();
    }

    @Override
    public void onLoginFailure(SmartLoginException e) {
        Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public SmartUser doCustomLogin() {
        SmartUser user = new SmartUser();
        user.setEmail(emailEditText.getText().toString());
        return user;
    }

    @Override
    public SmartUser doCustomSignup() {
        SmartUser user = new SmartUser();
        user.setEmail(emailEditText.getText().toString());
        return user;
    }
}
