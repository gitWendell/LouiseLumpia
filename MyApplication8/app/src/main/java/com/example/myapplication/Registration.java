package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Registration extends AppCompatActivity {

    ApiService apiService = ApiClient.getClient().create(ApiService.class);

    @BindView(R.id.txtUsername)
    EditText txtUsername;

    @BindView(R.id.txtPassword)
    EditText txtPassword;

    @BindView(R.id.txtName)
    EditText txtName;

    Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        ButterKnife.bind(this);
    }

    public void create(View view) {
        Users users = new Users();

        users.setName(txtName.getText().toString());
        users.setUsername(txtUsername.getText().toString());
        users.setPassword(txtPassword.getText().toString());

        Call<Result> createUser = apiService.createUser(users.getName(), users.getUsername(), users.getPassword());
        createUser.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(Registration.this, "Success", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(Registration.this, "Not success", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {

            }
        });
    }

    public void login(View view) {
        Intent backToLogin = new Intent(this, MainActivity.class);
        startActivity(backToLogin);
    }
}
