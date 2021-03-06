package com.garasipelosok.softgainapi;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.garasipelosok.softgainapi.Api.Client;
import com.garasipelosok.softgainapi.Api.Interface;
import com.garasipelosok.softgainapi.Model.Auth.Login;
import com.google.android.material.button.MaterialButton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends Activity {
    Button b1;
    EditText ed1,ed2;
    Interface mApiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        b1 = (Button) findViewById(R.id.button);
        ed1 = (EditText)findViewById(R.id.editText);
        ed2 = (EditText)findViewById(R.id.editText2);

        mApiInterface = Client.getClient().create(Interface.class);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),
                        "Mohon tunggu...",Toast.LENGTH_SHORT).show();

                Call<Login> postLoginExe = mApiInterface.postLogin(ed1.getText().toString(), ed2.getText().toString());
                postLoginExe.enqueue(new Callback<Login>() {
                    @Override
                    public void onResponse(Call<Login> call, Response<Login> response) {
                        if(response.isSuccessful()){
//                            Log.v("log softgain : ", String.valueOf(response.body().getSuccess().getToken()));
                            Toast.makeText(getApplicationContext(),
                                    "Login berhasil",Toast.LENGTH_SHORT).show();

//                            SharedPreferences sgSharedPref = getApplicationContext().getSharedPreferences("sg_shared_pref", getApplicationContext().MODE_PRIVATE);
                            getApplicationContext();
                            SharedPreferences sgSharedPref = getApplicationContext().getSharedPreferences("sg_shared_pref", MODE_PRIVATE);
                            SharedPreferences.Editor editor = sgSharedPref.edit();
                            String token = String.valueOf(response.body().getSuccess().getToken());
                            editor.putString("token", token);
                            editor.apply();

                            startActivity(new Intent(LoginActivity.this, MainActivity.class));
                        }else{
                            Toast.makeText(getApplicationContext() ,"Login gagal",Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Login> call, Throwable t) {
                        /*Log.v("log softgain : ", String.valueOf(t));*/
                        Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
    }
}