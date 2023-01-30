package com.example.convertapp.shoppingcart;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.convertapp.R;
import com.example.convertapp.database.Carrelloshop;

import java.util.ArrayList;

public class cartadapter extends BaseAdapter {


    private Context context;
    public ArrayList<cartmodel> cartmodels;
    ImageView imageView;
    Carrelloshop carrelloshop;
    TextView txtName,txtprice;
    public  static double cost=0;
    public  static double Totalcost=0;

    public cartadapter(Context context, ArrayList<cartmodel> cartmodels) {
        this.context = context;
        this.cartmodels = cartmodels;
        carrelloshop=new Carrelloshop(context);

    }

    @Override
    public int getCount() {
        return cartmodels.size();
    }
    @Override
    public Object getItem(int position) {
        return cartmodels.get(position);
    }
    @Override
    public long getItemId(int position) {
        return position;
    }
    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        view = LayoutInflater.from(context).inflate(R.layout.cartitem,viewGroup,false);
        txtName = (TextView) view.findViewById(R.id.name);
        txtprice = (TextView) view.findViewById(R.id.price);
        imageView = (ImageView) view.findViewById(R.id.imageView);
        ImageView add=view.findViewById(R.id.add);
        ImageView remove=view.findViewById(R.id.remove);
        ImageView cancel=view.findViewById(R.id.cancel);
        cartmodel cartmodel = cartmodels.get(position);
        TextView counts=view.findViewById(R.id.count);
        TextView tcoust=view.findViewById(R.id.cost);
        txtName.setText(cartmodel.getName());
        txtprice.setText(String.valueOf(cartmodel.getPrice()));
        tcoust.setText(String.valueOf(cartmodel.getPrice()));
        cartmodel.setQnt(1);

        if(position%2==1){

            view.setBackgroundResource( R.drawable.productshape2);
        }
        else {
            view.setBackgroundResource(R.drawable.productshape);
        }

add.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
Cursor cursor1=carrelloshop.searchproductname(cartmodels.get(position).getName());
int pid=cursor1.getInt(0);
int qut=cursor1.getInt(4);
      String  count= counts.getText().toString();
       int c=Integer.parseInt(count);
       if(c<qut)
       { c++;}
       else {
           Toast.makeText(context.getApplicationContext(),"The item not available more than now!",Toast.LENGTH_LONG).show();

       }

       counts.setText(String.valueOf(c));
        int id = cartmodel.getId();
        cartmodel.setQnt(c);
        Cursor cursor =carrelloshop.getcartdata(id);
        int price=cursor.getInt(3);
cost=c*price;
tcoust.setText(String.valueOf(cost));
    }
});
remove.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        String  count= counts.getText().toString();
        int c=Integer.parseInt(count);
        if(c>1)
        { c--;}
        counts.setText(String.valueOf(c));
        cartmodel.setQnt(c);
        int id = cartmodel.getId();
        Cursor cursor =carrelloshop.getcartdata(id);
        int price=cursor.getInt(3);
        cost=c*price;
        tcoust.setText(String.valueOf(cost));

    }
});
cancel.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        int id = cartmodel.getId();

        carrelloshop.deletcart(id);
        Toast.makeText(context, "Delete item successfully.", Toast.LENGTH_SHORT).show();
    }
});
        byte[] foodImage = cartmodel.getImage();
        Bitmap bitmap = BitmapFactory.decodeByteArray(foodImage, 0, foodImage.length);
        imageView.setImageBitmap(bitmap);
        return view;
    }
}
