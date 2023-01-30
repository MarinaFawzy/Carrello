package com.example.convertapp.Category;

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

import java.util.ArrayList;

public class CategoryAdapter extends BaseAdapter {
    private Context context;

    private ArrayList<categorymodel> categorymodels;
    ImageView imageView;
    TextView txtName;

    public CategoryAdapter(Context context, ArrayList<categorymodel> categorymodels) {
        this.context = context;

        this.categorymodels = categorymodels;
    }

    @Override
    public int getCount() {
        return categorymodels.size();
    }
    @Override
    public Object getItem(int position) {
        return categorymodels.get(position);
    }
    @Override
    public long getItemId(int position) {
        return position;
    }
    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
       view = LayoutInflater.from(context).inflate(R.layout.categoryitem,viewGroup,false);
        txtName = (TextView) view.findViewById(R.id.categoryname);
        imageView = (ImageView) view.findViewById(R.id.categoryimage);
        categorymodel categorymodel = categorymodels.get(position);
        txtName.setText(categorymodel.getName());
        byte[] foodImage = categorymodel.getImage();
        Bitmap bitmap = BitmapFactory.decodeByteArray(foodImage, 0, foodImage.length);
        imageView.setImageBitmap(bitmap);
        return view;

    }

}
