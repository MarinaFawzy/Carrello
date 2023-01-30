package com.example.convertapp.Showorders;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.convertapp.R;
import com.example.convertapp.database.Carrelloshop;

import java.util.ArrayList;

public class orderadapter extends BaseAdapter {

    private Context context;
    public ArrayList<ordermodel> ordermodels;
    Carrelloshop carrelloshop;
    public orderadapter(Context context, ArrayList<ordermodel> ordermodels) {
        this.context = context;
        this.ordermodels = ordermodels;
        carrelloshop=new Carrelloshop(context);

    }

    @Override
    public int getCount() {
        return ordermodels.size();
    }

    @Override
    public Object getItem(int position) {
        return ordermodels.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        convertView = LayoutInflater.from(context).inflate(R.layout.orderitem,parent,false);

       TextView txtName = (TextView) convertView.findViewById(R.id.username);
        TextView txtorderid = (TextView) convertView.findViewById(R.id.orderid);
        TextView txtdate = (TextView) convertView.findViewById(R.id.orderdate);
        TextView txtproducts = (TextView) convertView.findViewById(R.id.productorder);
        ordermodel ordermodel = ordermodels.get(position);

        txtName.setText(ordermodel.getUsername());
        txtorderid.setText(" "+ordermodel.getId());
        txtdate.setText(ordermodel.getDate());
        txtproducts.setText(ordermodel.getProducts());
        if(position%2==1){

            convertView.setBackgroundResource( R.drawable.productshape2);
        }
        else {
            convertView.setBackgroundResource(R.drawable.productshape);
        }




        return convertView;
    }
}
