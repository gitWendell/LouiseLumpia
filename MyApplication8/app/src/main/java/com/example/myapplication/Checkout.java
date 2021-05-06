package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Checkout extends AppCompatActivity {
    ApiService apiService = ApiClient.getClient().create(ApiService.class);

    @BindView(R.id.txtPerson)
    EditText txtPerson;

    @BindView(R.id.txtNumber)
    EditText txtNumber;

    @BindView(R.id.txtLocation)
    EditText txtLocation;

    @BindView(R.id.txtNote)
    EditText txtNote;

    ListView incompleteOrders;
    ArrayAdapter<String> orderAdapter;

    String user_id = "";
    int id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);
        ButterKnife.bind(this);

        Bundle bundleFromShoppingActivity = getIntent().getExtras();
        user_id = bundleFromShoppingActivity.getString("user_id");
        incompleteOrders = findViewById(R.id.checkoutOrder);
        id = Integer.parseInt(user_id);

        displayIncompleteOrder();
    }

    public void placeOrder(View view) {
        if (isFieldsSet()) {
            int number = Integer.parseInt(txtNumber.getText().toString());

            Delivery delivery = new Delivery();
            delivery.setContact_person(txtPerson.getText().toString());
            delivery.setContact_number(number);
            delivery.setNote(txtNote.getText().toString());
            delivery.setLocation(txtLocation.getText().toString());

            Call<ResultDelivery> createDelivery = apiService.createDelivery(id,
                    delivery.getContact_person(), delivery.getContact_number(),
                    delivery.getLocation(), delivery.getNote());

            createDelivery.enqueue(new Callback<ResultDelivery>() {
                @Override
                public void onResponse(Call<ResultDelivery> call, Response<ResultDelivery> response) {
                    if (response.body().getResultCode().equals("1")) {
                        Toast.makeText(Checkout.this, "Thank You!", Toast.LENGTH_SHORT).show();

                        Intent shoppingPage = new Intent(Checkout.this, Shopping.class);
                        shoppingPage.putExtra("user_id", user_id);
                        startActivity(shoppingPage);
                    }
                }

                @Override
                public void onFailure(Call<ResultDelivery> call, Throwable t) {

                }
            });
        } else {
            Toast.makeText(this, "Whoops", Toast.LENGTH_SHORT).show();
        }
    }
//    Check if all fields
    public boolean isFieldsSet() {
        if (txtLocation.getText().toString().equals("") || txtNote.getText().toString().equals("")
                || txtNumber.getText().toString().equals("") || txtPerson.getText().toString().equals("")) {
            return false;
        }
        return  true;
    }

    //    Display Incomplete Order
    public void displayIncompleteOrder() {
        Call<ResultOrder> getIncompleteOrder = apiService.getIncompleteOrder(id);
        getIncompleteOrder.enqueue(new Callback<ResultOrder>() {
            @Override
            public void onResponse(Call<ResultOrder> call, Response<ResultOrder> response) {
                if (response.body().getResultCode().equals("1")) {

                    ArrayList<String> orderList = new ArrayList<>();

                    for (Order order: response.body().getResultItems()) {
                        StringBuilder str = new StringBuilder();

                        str.append("Id:"+order.getId()+ ",\n");
                        str.append("Lumpia: "+order.getName()+ ",\n");
                        str.append("Size: "+order.getSize()+ ",\n");
                        str.append("Qty: "+order.getQty()+ "\n");
                        orderList.add(str.toString());

                    }

                    orderAdapter = new ArrayAdapter<>(getApplicationContext(), R.layout.support_simple_spinner_dropdown_item, orderList);
                    incompleteOrders.setAdapter(orderAdapter);
                }
            }

            @Override
            public void onFailure(Call<ResultOrder> call, Throwable t) {

            }
        });
    }
}
