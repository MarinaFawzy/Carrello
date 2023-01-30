package com.example.convertapp.userorder;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.convertapp.R;
import com.example.convertapp.database.Carrelloshop;

import java.util.ArrayList;

public class userorderadapter extends BaseAdapter {


    private Context context;
    public ArrayList<order> orders;
    ImageView imageView;
    Carrelloshop carrelloshop;
    TextView txtName,txtprice;
    public  static double cost=0;
    public  static double Totalcost=0;

    public userorderadapter(Context context, ArrayList<order> order) {
        this.context = context;
        this.orders = order;
        carrelloshop=new Carrelloshop(context);

    }

    @Override
    public int getCount() {
        return orders.size();
    }
    @Override
    public Object getItem(int position) {
        return orders.get(position);
    }
    @Override
    public long getItemId(int position) {
        return position;
    }
    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        view = LayoutInflater.from(context).inflate(R.layout.userorderitem,viewGroup,false);
        txtName = (TextView) view.findViewById(R.id.name);
        txtprice = (TextView) view.findViewById(R.id.price);
        imageView = (ImageView) view.findViewById(R.id.imageView);
      TextView  txtqut = (TextView) view.findViewById(R.id.edtqnt);
      order order = orders.get(position);

        txtName.setText(order.getName());
        txtprice.setText(String.valueOf(order.getPrice()));
        txtqut.setText(String.valueOf(order.getQnt()));

        if(position%2==1){

            view.setBackgroundResource( R.drawable.productshape2);
        }
        else {
            view.setBackgroundResource(R.drawable.productshape);
        }
        byte[] foodImage = order.getImage();
        Bitmap bitmap = BitmapFactory.decodeByteArray(foodImage, 0, foodImage.length);
        imageView.setImageBitmap(bitmap);
        return view;
    }




}
