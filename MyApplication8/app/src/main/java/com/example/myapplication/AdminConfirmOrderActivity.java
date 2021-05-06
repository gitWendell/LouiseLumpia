package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdminConfirmOrderActivity extends AppCompatActivity {

    ApiService apiService = ApiClient.getClient().create(ApiService.class);

    ListView orderList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_confirm_order);

        orderList = findViewById(R.id.lvAdminConfirmOrder);
        displayOrderList();
    }

    public void displayOrderList() {
        Call<ResultOrder> confirmOrder = apiService.getAllConfirmOrder();
        confirmOrder.enqueue(new Callback<ResultOrder>() {
            @Override
            public void onResponse(Call<ResultOrder> call, Response<ResultOrder> response) {
                if (response.body().getResultCode().equals("1")) {
                    ArrayList<Order> confirmOrderList = new ArrayList<>();

                    for (Order order: response.body().getResultItems()) {
                        confirmOrderList.add(order);
                    }

                    AdminConfirmOrderAdapter adapter = new AdminConfirmOrderAdapter(AdminConfirmOrderActivity.this, R.layout.admin_confirm_order, confirmOrderList);
                    orderList.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<ResultOrder> call, Throwable t) {

            }
        });
    }

}
