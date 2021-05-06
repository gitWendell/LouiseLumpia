package com.example.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.sax.Element;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Shopping extends AppCompatActivity {
    ArrayList<String> orderList = new ArrayList<>();

    ApiService apiService = ApiClient.getClient().create(ApiService.class);

    @BindView(R.id.txtQty)
    EditText txtQty;

    RadioGroup rgLumpia;
    RadioGroup rgSize;

    RadioButton rbLumpia;
    RadioButton rbSize;

    ListView incompleteOrders;
    ArrayList<String> orderArr;
    ArrayAdapter<String> orderAdapter;

    String user_id = "";
    int qty = 0;
    int id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping);
        ButterKnife.bind(this);

        Bundle bundleFromMainActivity = getIntent().getExtras();
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        user_id = bundleFromMainActivity.getString("user_id");
        incompleteOrders = findViewById(R.id.incompleteOrder);
        id = Integer.parseInt(user_id);

        rgLumpia = (RadioGroup) findViewById(R.id.rgLumpia);
        rgSize = (RadioGroup) findViewById(R.id.rgSize);

        displayIncompleteOrder();
        onClickListItem();
    }

    public void addToList(View view) {
        Order order = new Order();

        if (isFieldsSet()) {
    //        Get Selected Id from Radio Group
            int selectedLumpia = rgLumpia.getCheckedRadioButtonId();
            int selectedSize = rgSize.getCheckedRadioButtonId();

    //        Get Radio button using the ID Above
            rbLumpia = (RadioButton) findViewById(selectedLumpia);
            rbSize = (RadioButton) findViewById(selectedSize);

    //        Set Order Class Variables
            order.setName(rbLumpia.getText().toString());
            order.setQty(qty);
            order.setSize(rbSize.getText().toString());
            order.setUser_id(user_id);

    //        String --> Int
            qty = Integer.parseInt(txtQty.getText().toString());
            id = Integer.parseInt(order.getUser_id());

    //        Operation: Create Order
            Call<Result> createOrder = apiService.createOrder(id, order.getName(), order.getSize(), qty);
            createOrder.enqueue(new Callback<Result>() {
                @Override
                public void onResponse(Call<Result> call, Response<Result> response) {
                    Toast.makeText(Shopping.this, "Order Successfully", Toast.LENGTH_SHORT).show();
                    refresh();
                    displayIncompleteOrder();
                }

                @Override
                public void onFailure(Call<Result> call, Throwable t) {

                }
            });
        } else {
            Toast.makeText(this, "Please Fill Up All", Toast.LENGTH_SHORT).show();
        }
    }

//    Check if all fields are filled up
    public boolean isFieldsSet() {
        if(rgSize.getCheckedRadioButtonId() == -1 || rgLumpia.getCheckedRadioButtonId() == -1 || txtQty.getText().toString() == "") {
            return false;
        }

        return true;
    }

//    Clear the Radio Group and Input Fields
    public void refresh(){
        rgLumpia.clearCheck();
        rgSize.clearCheck();
        txtQty.setText("");
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

//    Delete Order By Clicking on Item List
    public void onClickListItem() {
        incompleteOrders.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String selectedItem = incompleteOrders.getItemAtPosition(i).toString();
                String[] separated = selectedItem.split(":");
                String[] findId = separated[1].split(",");

                final int id = Integer.parseInt(findId[0]);

                AlertDialog.Builder builder = new AlertDialog.Builder(Shopping.this);
                builder.setMessage("Are you sure you want to delete this ?")
                       .setCancelable(false)
                       .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                           @Override
                           public void onClick(DialogInterface dialogInterface, int i) {

                               Call<ResultOrder> delete = apiService.delete(id);
                               delete.enqueue(new Callback<ResultOrder>() {
                                   @Override
                                   public void onResponse(Call<ResultOrder> call, Response<ResultOrder> response) {
                                       if (response.body().getResultCode().equals("1")) {
                                           Toast.makeText(Shopping.this, "Deleted Successfully!", Toast.LENGTH_SHORT).show();
                                           displayIncompleteOrder();
                                       }
                                   }

                                   @Override
                                   public void onFailure(Call<ResultOrder> call, Throwable t) {

                                   }
                               });
                           }
                       })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        });

                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });
    }

    public void checkout(View view){

        Intent checkoutPage = new Intent(this, Checkout.class);
        checkoutPage.putExtra("user_id", user_id);
        startActivity(checkoutPage);
    }

    public void viewMyConfirmOrder(View view) {
        Intent confirmOrderList = new Intent(this, UserConfirmOrderActivity.class);
        confirmOrderList.putExtra("user_id", user_id);
        startActivity(confirmOrderList);
    }

    public void logout(View view) {
        Intent loginPage = new Intent(this, MainActivity.class);
        startActivity(loginPage);
    }
}
