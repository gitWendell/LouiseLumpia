package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    ApiService apiService = ApiClient.getClient().create(ApiService.class);

    @BindView(R.id.txtLoginUser)
    EditText txtUsername;

    @BindView(R.id.txtLoginPass)
    EditText txtPassword;

    Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }


//    Just Intent Section

    public void register(View view) {
        Intent register = new Intent(this, Registration.class);
        startActivity(register);
    }

//    End Intent Section


//    Login Section

    public  void login(View view) {
        Users users = new Users();

        users.setUsername(txtUsername.getText().toString());
        users.setPassword(txtPassword.getText().toString());

        Call<Result> loginUser = apiService.loginUser(users.getUsername(), users.getPassword());

        loginUser.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                if (response.body().getResultCode().equals("1")) {
                    Toast.makeText(MainActivity.this, "Login Successfully", Toast.LENGTH_SHORT).show();

                    if(response.body().getResultItems().get(0).getIsadmin() == 1) {
                        Intent adminPage = new Intent(MainActivity.this, Admin.class);
                        startActivity(adminPage);
                    } else {
                        Intent homePage = new Intent(MainActivity.this, Shopping.class);
                        homePage.putExtra("user_id", String.valueOf(response.body().getResultItems().get(0).getId()));
                        startActivity(homePage);
                    }

                } else {
                    Toast.makeText(MainActivity.this, "2", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {

            }
        });
    }

//    End Login Section
}
