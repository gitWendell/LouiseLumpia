package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UsersOrderAdapter extends ArrayAdapter<UsersOrderInformation> {
    ApiService apiService = ApiClient.getClient().create(ApiService.class);

    private static final String TAG = "UsersOrderAdapter";

    private ArrayList<UsersOrderInformation> orderArrayList;
    private Context mContext;
    int mResource;

    public UsersOrderAdapter(Context context, int resource, ArrayList<UsersOrderInformation> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
        this.orderArrayList = objects;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        String user_name = getItem(position).getUser_name();
        String order_name = getItem(position).getOrder_name();
        String contact_person = getItem(position).getContact_person();
        String location = getItem(position).getLocation();
        String note = getItem(position).getNote();
        String size = getItem(position).getSize();
        int qty = getItem(position).getQty();
        int number = getItem(position).getContact_number();

        String num = ""+number;
        String QTY = ""+qty;

        UsersOrderInformation usersOrder = new UsersOrderInformation();

        usersOrder.setUser_name(user_name);
        usersOrder.setOrder_name(order_name);
        usersOrder.setContact_person(contact_person);
        usersOrder.setLocation(location);
        usersOrder.setNote(note);
        usersOrder.setSize(size);
        usersOrder.setQty(qty);
        usersOrder.setContact_number(number);

        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource, parent, false);

        TextView txtAOrderName = (TextView) convertView.findViewById(R.id.txtAOrderName);
        TextView txtAUserName = (TextView) convertView.findViewById(R.id.txtAUserName);
        TextView txtADeliveryPerson = (TextView) convertView.findViewById(R.id.txtADeliveryPerson);
        TextView txtADeliveryNumber = (TextView) convertView.findViewById(R.id.txtADeliveryNumber);
        TextView txtADeliveryLocation = (TextView) convertView.findViewById(R.id.txtADeliveryLocation);
        TextView txtADeliveryNote = (TextView) convertView.findViewById(R.id.txtADeliveryNote);
        TextView txtAOrderQty = (TextView) convertView.findViewById(R.id.txtAOrderQty);
        TextView txtAOrderSize = (TextView) convertView.findViewById(R.id.txtAOrderSize);
        Button btnConfirm = (Button) convertView.findViewById(R.id.btnConfirm);

        btnConfirm.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Call<ResultOrder> confirmOrder = apiService.updateOrder(getItem(position).getOrder_id());
                confirmOrder.enqueue(new Callback<ResultOrder>() {
                    @Override
                    public void onResponse(Call<ResultOrder> call, Response<ResultOrder> response) {
                        if(response.body().getResultCode().equals("1")) {
                            orderArrayList.remove(position);

                            Toast.makeText(mContext, "Confirm", Toast.LENGTH_SHORT).show();
                            notifyDataSetChanged();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResultOrder> call, Throwable t) {

                    }
                });
            }
        });
        txtADeliveryNote.setText("Note: "+note);
        txtADeliveryNumber.setText("Contact Number: "+num);
        txtADeliveryPerson.setText("Contact Person: "+contact_person);
        txtADeliveryLocation.setText("Location: "+location);
        txtAOrderName.setText("Lumpia: "+order_name);
        txtAOrderQty.setText("Qty: "+QTY);
        txtAOrderSize.setText("Size: "+size);
        txtAUserName.setText("Customer Name: "+user_name);

        return  convertView;
    }

    public void remove(int position) {

    }
}
