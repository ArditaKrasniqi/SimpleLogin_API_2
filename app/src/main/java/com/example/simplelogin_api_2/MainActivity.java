package com.example.simplelogin_api_2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;

public class MainActivity extends AppCompatActivity {

    private Button loginBtn;
    private Button registerBtn;
    private EditText password;
    private EditText username_input;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username_input = findViewById(R.id.username_input);
        password = findViewById(R.id.pass);
        registerBtn = findViewById(R.id.RegisterBtn);
        loginBtn = findViewById(R.id.LoginBtn);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkDataEntered();
            }
        });
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnSignUpClicked();
            }
        });
    }
    private void btnSignUpClicked(){
        startActivity(new Intent(MainActivity.this,RegisterActivity.class));
    }

    private  void loginBtnClicked(){
        ApiInterface apiInterface = RetrofitClient.getRetrofitInstance().create(ApiInterface.class);
        Call<LoginResponse> call = apiInterface.getLoginInformation("eve.holt@reqres.in","cityslicka");
        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                Log.e(TAG, "onResponse: "+response.code());
                Log.e(TAG, "onResponse: "+response.body().getToken());
            }
            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Log.e(TAG, "onFailure: "+t.getMessage());

            }
        });
        startActivity(new Intent(MainActivity.this,HomeActivity.class));


    }
    public void checkDataEntered(){
        if(isEmpty(username_input)){
            Toast t = Toast.makeText(this, "You must enter your email to log in!", Toast.LENGTH_SHORT);
            t.show();
        }
        else if(isEmpty(password)){
            Toast t = Toast.makeText(this, "You must enter your password to log in!", Toast.LENGTH_SHORT);
            t.show();
        }
        else{
            loginBtnClicked();
        }
    }

    boolean isEmpty(EditText text) {
        CharSequence str = text.getText().toString();
        return TextUtils.isEmpty(str);
    }

}