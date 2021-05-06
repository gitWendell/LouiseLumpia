package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class AdminConfirmOrderAdapter extends ArrayAdapter<Order> {

    private static final String TAG = "UsersOrderAdapter";

    private Context mContext;
    int mResource;

    public AdminConfirmOrderAdapter(Context context, int resource, List<Order> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        int id = getItem(position).getId();
        String name = getItem(position).getName();
        String size = getItem(position).getSize();
        int qty = getItem(position).getQty();

        String QTY = ""+qty;
        String ID = ""+id;

        Order order = new Order();
        order.setId(id);
        order.setName(name);
        order.setSize(size);
        order.setQty(qty);

        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource, parent, false);

        TextView tvId = (TextView) convertView.findViewById(R.id.adminId);
        TextView tvName = (TextView) convertView.findViewById(R.id.adminLumpia);
        TextView tvSize = (TextView) convertView.findViewById(R.id.adminSize);
        TextView tvQty = (TextView) convertView.findViewById(R.id.adminQty);

        tvId.setText(ID);
        tvName.setText(name);
        tvSize.setText(size);
        tvQty.setText(QTY);

        return  convertView;
    }
}
