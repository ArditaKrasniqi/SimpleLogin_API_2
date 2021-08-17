package com.example.simplelogin_api_2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;


public class HomeActivity extends AppCompatActivity{

    private Button showUsersBtn;
    private Button createNewUserBtn;
    private RecyclerView recyclerView;
    private ArrayList<User> arrayList;
    Dialog myDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        showUsersBtn = findViewById(R.id.userListBtn);
        createNewUserBtn = findViewById(R.id.createUserBtn);
        myDialog = new Dialog(this);

        showUsersBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        createNewUserBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView txtclose;
                Button addBtn;
                myDialog.setContentView(R.layout.user_details_pop_up);
                txtclose = (TextView) myDialog.findViewById(R.id.txtclose);
                addBtn = (Button) myDialog.findViewById(R.id.addBtn);
                txtclose.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        myDialog.dismiss();
                    }
                });
                addBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ApiInterface apiInterface = RetrofitClient.getRetrofitInstance().create(ApiInterface.class);
                        Call<User> call = apiInterface.getUserInformation("Ardita","Android Developer");
                        call.enqueue(new Callback<User>() {
                            @Override
                            public void onResponse(Call<User> call, Response<User> response) {
                                Log.e(TAG, "onResponse:"+response.code());
                                Log.e(TAG,"onResponse: name:"+response.body().getName());
                                Log.e(TAG,"onResponse: job:"+response.body().getJob());
                                Log.e(TAG,"onResponse: id:"+response.body().getId());
                                Log.e(TAG,"onResponse: createdAt:"+response.body().getCreatedAt());
                            }

                            @Override
                            public void onFailure(Call<User> call, Throwable t) {
                                Log.e(TAG,"onFailure:"+t.getMessage());
                            }

                        });
                        String message = "Added Successfully";
                        Toast.makeText(HomeActivity.this,message,Toast.LENGTH_LONG).show();
                    }
                });
                myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                myDialog.show();
            }
        });
    }}













