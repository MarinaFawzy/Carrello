package com.example.convertapp.prduct;
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
import com.example.convertapp.loginandregisteration.login;

import java.util.ArrayList;
public class Productadapter extends BaseAdapter {
    private Context context;
private ArrayList<Productmodel> productsmodel;
    ImageView imageView;
    Carrelloshop carrelloshop;
    TextView txtName,txtprice,txtquantaty;
    public Productadapter(Context context, ArrayList<Productmodel> productmodel) {
        this.context = context;
        this.productsmodel = productmodel;
        carrelloshop=new Carrelloshop(context);

    }

    @Override
    public int getCount() {
        return productsmodel.size();
    }
    @Override
    public Object getItem(int position) {
        return productsmodel.get(position);
    }
    @Override
    public long getItemId(int position) {
        return position;
    }
    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        view = LayoutInflater.from(context).inflate(R.layout.productitem,viewGroup,false);
        txtName = (TextView) view.findViewById(R.id.edtname);
        txtprice = (TextView) view.findViewById(R.id.edtprice);
        txtquantaty = (TextView) view.findViewById(R.id.quttt);
        TextView tqut=view.findViewById(R.id.tquantaty);
        imageView = (ImageView) view.findViewById(R.id.imageView4);
        Productmodel productmodel = productsmodel.get(position);
        txtName.setText(productmodel.getName());
        txtprice.setText(String.valueOf(productmodel.getPrice()));
        tqut.setVisibility(View.INVISIBLE);
    txtquantaty.setText(" "+productmodel.getQuantaty());
        txtquantaty.setVisibility(View.INVISIBLE);
        if(login.adminname.equals("admin")&&login.adminpass.equals("admin")){
            tqut.setVisibility(View.VISIBLE);
        txtquantaty.setVisibility(View.VISIBLE);
        }

        if(position%2==1){

    view.setBackgroundResource( R.drawable.productshape2);
}
else {
    view.setBackgroundResource(R.drawable.productshape);
}
        ImageView  addtocart=view.findViewById(R.id.buy);

        if(login.adminname.equals("admin")&&login.adminpass.equals("admin")){
    addtocart.setVisibility(View.INVISIBLE);
}
        addtocart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = productmodel.getId();
                Cursor c = carrelloshop.getproductbuid(id);
                int Pid = c.getInt(0);
                String name = c.getString(1);
                byte[] image = c.getBlob(2);
                int price = c.getInt(3);

                int Userid = login.uid;
                Cursor cursor = carrelloshop.searchcartname_id(name, login.uid);
                if (cursor.getCount() > 0) {
                    Toast.makeText(context.getApplicationContext(), "The item is already in cart. ", Toast.LENGTH_SHORT).show();

                } else {

                    carrelloshop.insrtcart(name, image, price, Userid);
                    Toast.makeText(context.getApplicationContext(), "Add to cart", Toast.LENGTH_SHORT).show();
                }
            }
        });
        byte[] foodImage = productmodel.getImage();
        Bitmap bitmap = BitmapFactory.decodeByteArray(foodImage, 0, foodImage.length);
        imageView.setImageBitmap(bitmap);
        return view;

    }

}
