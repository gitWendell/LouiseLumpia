package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserConfirmOrderActivity extends AppCompatActivity {

    ApiService apiService = ApiClient.getClient().create(ApiService.class);

    String user_id= "";
    int id = 0;

    ListView orderList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_confirm_order);

        Bundle bundleFromShoppingActivity = getIntent().getExtras();
        user_id = bundleFromShoppingActivity.getString("user_id");
        id = Integer.parseInt(user_id);

        orderList = findViewById(R.id.lvUserConfirmOrder);
        displayOrderList();
    }

    public void displayOrderList() {
        Call<ResultOrder> confirmOrderList = apiService.getUserCongirmOrder(id);
        confirmOrderList.enqueue(new Callback<ResultOrder>() {
            @Override
            public void onResponse(Call<ResultOrder> call, Response<ResultOrder> response) {
                if (response.body().getResultCode().equals("1")) {
                    ArrayList<Order> confirmOrderList = new ArrayList<>();

                    for (Order order: response.body().getResultItems()) {
                        confirmOrderList.add(order);
                    }

                    UsersConfirmOrderAdapter adapter = new UsersConfirmOrderAdapter(UserConfirmOrderActivity.this, R.layout.user_confirm_order, confirmOrderList);
                    orderList.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<ResultOrder> call, Throwable t) {

            }
        });
    }
}
