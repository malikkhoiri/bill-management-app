package com.code5.kb5;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import com.google.android.material.textfield.TextInputEditText;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.code5.kb5.engine.AppController;
import com.code5.kb5.engine.interfaces.RetrofitCallback;
import com.code5.kb5.engine.session.LoginSession;
import com.code5.kb5.model.login.LoginData;
import com.code5.kb5.model.login.LoginParam;
import com.code5.kb5.model.login.LoginResponse;
import com.code5.kb5.utils.DrawableClickListener;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    @BindView(R.id.btn_login) Button btnLogin;
    @BindView(R.id.edt_username) TextInputEditText edtUsername;
    @BindView(R.id.edt_password) TextInputEditText edtPassword;
    @BindView(R.id.pb_login) ProgressBar pbLogin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        final LoginSession loginSession = new LoginSession(getApplicationContext());

        if (loginSession.isLogin(LoginSession.IS_LOGIN)){
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            finish();
        }

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setProgressBar();
                final String username = edtUsername.getText().toString().trim();
                String password = edtPassword.getText().toString().trim();
                if(username.isEmpty() || password.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Username/password kosong", Toast.LENGTH_SHORT).show();
                    unsetProgressBar();
                }else {
                    setProgressBar();
                    AppController app = new AppController();
                    app.login(new LoginParam(username, password), new RetrofitCallback() {
                        @Override
                        public void onResponse(Response<?> response) {
                            if(response.isSuccessful()){
                                LoginResponse loginResponse = (LoginResponse) response.body();
                                if (loginResponse != null && loginResponse.getStatus() == 200) {
                                    LoginData loginData = loginResponse.getData();
                                    loginSession.setIsLogin(LoginSession.IS_LOGIN, true);
                                    loginSession.setLoginSession(LoginSession.NAME, loginData.getName());
                                    loginSession.setLoginSession(LoginSession.EMAIL, loginData.getEmail());
                                    loginSession.setLoginSession(LoginSession.ACCESS_TOKEN, loginData.getToken());

                                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                                    finish();
                                } else{
                                    Toast.makeText(getApplicationContext(), "Login gagal", Toast.LENGTH_SHORT).show();
                                    unsetProgressBar();
                                }
                            } else{
                                Toast.makeText(getApplicationContext(), "Login gagal", Toast.LENGTH_SHORT).show();
                                unsetProgressBar();
                            }
                        }

                        @Override
                        public void onFailure(String result) {
                            Toast.makeText(getApplicationContext(), "Login gagal", Toast.LENGTH_SHORT).show();
                            unsetProgressBar();
                        }
                    });
                }
            }
        });

        edtPassword.setOnTouchListener(new DrawableClickListener.RightDrawableClickListener(edtPassword) {
            @Override
            public boolean onDrawableClick() {
                if (edtPassword.getInputType() == 129){
                    edtPassword.setInputType(InputType.TYPE_CLASS_TEXT);
                    return true;
                }
                else{
                    edtPassword.setInputType(129);
                    return false;
                }
            }
        });
    }

    private void setProgressBar(){
        pbLogin.setVisibility(View.VISIBLE);
        btnLogin.setEnabled(false);
        edtUsername.setEnabled(false);
        edtPassword.setEnabled(false);
    }

    private void unsetProgressBar(){
        pbLogin.setVisibility(View.INVISIBLE);
        btnLogin.setEnabled(true);
        edtUsername.setEnabled(true);
        edtPassword.setEnabled(true);
    }
}
