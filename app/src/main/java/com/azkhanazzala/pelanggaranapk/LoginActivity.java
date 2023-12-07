package com.azkhanazzala.pelanggaranapk;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.azkhanazzala.pelanggaranapk.response.GetLogin;
import com.azkhanazzala.pelanggaranapk.rest.ApiClient;
import com.azkhanazzala.pelanggaranapk.rest.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    EditText Email, Password;

    Button LogIn;
    ApiInterface mApiInterface;
    String PasswordHolder, EmailHolder;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        sharedPreferences = getSharedPreferences("MyPreferences", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        Email = (EditText) findViewById(R.id.email);
        Password = (EditText) findViewById(R.id.password);
        LogIn = (Button) findViewById(R.id.loginButton);

        mApiInterface = ApiClient.getClient().create(ApiInterface.class);


        LogIn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if (CheckEditTextIsEmptyOrNot()) {
                    handleLoginUSer();
                } else {

                    // Toast.makeText(UserLoginActivity.this, "Please fill all form fields.", Toast.LENGTH_LONG).show();

                    // Get the custom layout view.
                    View toastView = getLayoutInflater().inflate(R.layout.activity_toast_custorm_view, null);

                    // Initiate the Toast instance.
                    Toast toast = new Toast(getApplicationContext());
                    // Set custom view in toast.
                    toast.setView(toastView);
                    toast.setDuration(Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.CENTER, 0,0);
                    toast.show();
                }
            }

        });


    }



    public Boolean CheckEditTextIsEmptyOrNot() {

        EmailHolder = Email.getText().toString();
        PasswordHolder = Password.getText().toString();

        return !TextUtils.isEmpty(EmailHolder) && !TextUtils.isEmpty(PasswordHolder);

    }

    private void handleLoginUSer(){
        Email = findViewById(R.id.email);
        Password = findViewById(R.id.password);

        String email = Email.getText().toString();
        String password = Password.getText().toString();


        try {
            Call<GetLogin> emailUser = mApiInterface.login("login_user",email, password);
            emailUser.enqueue(new Callback<GetLogin>() {

                @Override
                public void onResponse(Call<GetLogin> call, Response<GetLogin> response) {
                    if (response.isSuccessful()) {
                       if (response.body().getStatus()) {
                           handleSaveIdUser(response.body().getData().getIdUser(), true);
                           Intent i = new Intent(LoginActivity.this,MainActivity.class);
                           startActivity(i);
                       }
                       else Log.e("Error login ", response.body().getMessage());

                    } else {
                        Log.e(response.message() + " " + response.code(), "Response body is null or request not successful.");
                    }
                }
                @Override
                public void onFailure(Call<GetLogin> call, Throwable t) {
                    Log.e("Retrofit Post Login", t.toString());
                }
            });
        }catch (Exception e){
            Log.e("Error", e.toString());
        }
    }

    private void handleSaveIdUser(String idUser, boolean isLogin) {
        editor.putString("idUser", idUser);
        editor.putBoolean("isLogin", isLogin);
        editor.apply();
    }


}

