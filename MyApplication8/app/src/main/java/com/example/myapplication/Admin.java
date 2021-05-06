package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Admin extends AppCompatActivity {
    ApiService apiService = ApiClient.getClient().create(ApiService.class);
    ListView orderList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        orderList = findViewById(R.id.lvAdminOrderList);

        displayUsersOrderInformation();
        onItemListClick();
    }

    public void displayUsersOrderInformation() {
        Call<ResultUsersOrderInformation> getUsersOrderInformation = apiService.getUsersOrderInformation();
        getUsersOrderInformation.enqueue(new Callback<ResultUsersOrderInformation>() {
            @Override
            public void onResponse(Call<ResultUsersOrderInformation> call, Response<ResultUsersOrderInformation> response) {
                if (response.body().getResultCode().equals("1")) {
                    ArrayList<UsersOrderInformation> userOrderList = new ArrayList<>();

                    for (UsersOrderInformation order: response.body().getResultItems()) {
                        userOrderList.add(order);
                    }

                    UsersOrderAdapter adapter = new UsersOrderAdapter(Admin.this, R.layout.admin_order_list, userOrderList);
                    orderList.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<ResultUsersOrderInformation> call, Throwable t) {

            }
        });
    }

    public void onItemListClick() {
        orderList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String selectedItem = orderList.getItemAtPosition(i).toString();
                String[] separated = selectedItem.split(":");
            }
        });
    }

    public void logout(View view) {
        Intent loginPage = new Intent(this, MainActivity.class);
        startActivity(loginPage);
    }

    public void viewConfirmOrder(View view) {
        Intent confirmOrder = new Intent(this, AdminConfirmOrderActivity.class);
        startActivity(confirmOrder);
    }
}
