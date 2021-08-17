package com.example.simplelogin_api_2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;

public class RegisterActivity extends AppCompatActivity {
    private Button registerBtn;
    private EditText email;
    private EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        registerBtn = findViewById(R.id.RegisterBtn);
        email = findViewById(R.id.email_input);
        password = findViewById(R.id.pass);


        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkDataEntered();

            }
        });

    }

    private void registerbtnClicked(){
        ApiInterface apiInterface = RetrofitClient.getRetrofitInstance().create(ApiInterface.class);
        Call<RegisterResponse> call = apiInterface.getRegisterInformation("eve.holt@reqres.in","pistol");
        call.enqueue(new Callback<RegisterResponse>() {
            @Override
            public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                Log.e(TAG, "onResponse: "+response.code());
                Log.e(TAG,"onResponse:"+response.body().getId());
                Log.e(TAG, "onResponse: "+response.body().getToken());
            }

            @Override
            public void onFailure(Call<RegisterResponse> call, Throwable t) {
                Log.e(TAG, "onFailure: "+t.getMessage());

            }
        });
        String message= "Successful...";
        Toast.makeText(getApplicationContext(),message,Toast.LENGTH_SHORT).show();
        startActivity(new Intent(RegisterActivity.this,MainActivity.class));
    }
    public void checkDataEntered(){
       if(isEmpty(email)){
            Toast t = Toast.makeText(this, "You must enter email to register!", Toast.LENGTH_SHORT);
            t.show();
        }
        else if(isEmpty(password)){
            Toast t = Toast.makeText(this, "You must enter password to register!", Toast.LENGTH_SHORT);
            t.show();
        }
        else{
            registerbtnClicked();
        }
    }

    boolean isEmpty(EditText text) {
        CharSequence str = text.getText().toString();
        return TextUtils.isEmpty(str);
    }
}