package com.example.convertapp.shoppingcart;

import android.content.Intent;
import android.database.Cursor;
import android.database.CursorWindow;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.convertapp.R;
import com.example.convertapp.database.Carrelloshop;
import com.example.convertapp.loginandregisteration.login;
import com.example.convertapp.userorder.userorders;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.lang.reflect.Field;
import java.util.ArrayList;

public class cartlist extends AppCompatActivity {
    ListView listviews;
    ArrayList<cartmodel> list;
    cartadapter adapter = null;
    String voice = null;
    int []productid=null;
    int[]quantity=null;
    Carrelloshop carrelloshop = new Carrelloshop(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cartlist);

        Button goorder=findViewById(R.id.order);
        Button showorder=findViewById(R.id.showorder);


        listviews = findViewById(R.id.cartlist);
        list = new ArrayList<>();
        adapter = new cartadapter(this, list);
        listviews.setAdapter(adapter);
        try {
            Field field = CursorWindow.class.getDeclaredField("sCursorWindowSize");
            field.setAccessible(true);
            field.set(null, 100 * 1024 * 1024); //the 100MB is the new size
        } catch (Exception e) {
            e.printStackTrace();
        }
        list.clear();
        Cursor cursor;


        cursor = carrelloshop.getcart(login.uid);


        while (!cursor.isAfterLast()) {
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            byte[] image = cursor.getBlob(2);
            int price = cursor.getInt(3);
            int uids = cursor.getInt(4);

            list.add(new cartmodel(name, image, id, price));
            cursor.moveToNext();
        }

productid = new int[list.size()];
 quantity=new int[list.size()];
        adapter.notifyDataSetChanged();
        goorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int sum=0,i;
                for(i=0;i< list.size();i++) {
                    sum = sum + (list.get(i).getPrice() * list.get(i).getQnt());
                    Cursor cursor1=carrelloshop.searchproductname(list.get(i).getName());

                    productid[i]=cursor1.getInt(0);
                    quantity[i]=list.get(i).getQnt();
                }
                Intent next=new Intent(cartlist.this, order.class);
                next.putExtra("sum",sum);
                next.putExtra("listsize",list.size());

                next.putExtra("productid",productid);
                next.putExtra("quantity",quantity);

                startActivity(next);



            }
        });
showorder.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        int sum=0,i;
        for(i=0;i< list.size();i++) {
            sum = sum + (list.get(i).getPrice() * list.get(i).getQnt());
            Cursor cursor1=carrelloshop.searchproductname(list.get(i).getName());


            quantity[i]=list.get(i).getQnt();
        }
        Intent next=new Intent(cartlist.this, userorders.class);
        next.putExtra("sum",sum);
        next.putExtra("quantity",quantity);

        startActivity(next);

    }
});
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.toolbar, menu);

        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        SearchView searchView = (SearchView) item.getActionView();

        switch (item.getItemId()) {
            case R.id.search:

                searchView.setQueryHint("Search");
                searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                    @Override
                    public boolean onQueryTextSubmit(String query) {
                        Toast.makeText(getApplicationContext()," "+login.uid,Toast.LENGTH_LONG).show();
                        Cursor cursor = carrelloshop.searchcartname_id(query, login.uid);
                        list.clear();

                        while (!cursor.isAfterLast()) {
                            int id = cursor.getInt(0);
                            String name = cursor.getString(1);
                            byte[] image = cursor.getBlob(2);
                            int price = cursor.getInt(3);
                            int uids = cursor.getInt(4);

                            list.add(new cartmodel(name, image, id, price));
                            cursor.moveToNext();
                        }
                        adapter.notifyDataSetChanged();
                        return false;
                    }

                    @Override
                    public boolean onQueryTextChange(String newText) {
                        Cursor cursor = carrelloshop.searchcartname_id(newText, login.uid);
                        Toast.makeText(getApplicationContext()," "+login.uid,Toast.LENGTH_LONG).show();

                        list.clear();

                        while (!cursor.isAfterLast()) {
                            int id = cursor.getInt(0);
                            String name = cursor.getString(1);
                            byte[] image = cursor.getBlob(2);
                            int price = cursor.getInt(3);
                            int uids = cursor.getInt(4);

                            list.add(new cartmodel(name, image, id, price));
                            cursor.moveToNext();
                        }
                        adapter.notifyDataSetChanged();
                        return false;
                    }
                });

                break;
            case R.id.voice:
                Intent neIntent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                startActivityForResult(neIntent, 100);
                break;
            case R.id.camera:
                IntentIntegrator intentIntegrator = new IntentIntegrator(this);
                intentIntegrator.setPrompt("Scan a barcode or QR Code");
                intentIntegrator.setOrientationLocked(true);
                intentIntegrator.initiateScan();


                break;

        }
        return super.onOptionsItemSelected(item);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 100 && resultCode == RESULT_OK) {
            ArrayList<String> text = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            voice = text.get(0);
Toast.makeText(getApplicationContext()," "+login.uid,Toast.LENGTH_LONG).show();
            Cursor cursor = carrelloshop.searchcartname_id(voice, login.uid);
            list.clear();

            while (!cursor.isAfterLast()) {
                int id = cursor.getInt(0);
                String name = cursor.getString(1);
                byte[] image = cursor.getBlob(2);
                int price = cursor.getInt(3);
                int uids = cursor.getInt(4);
                list.add(new cartmodel(name, image, id, price));
                cursor.moveToNext();
            }
            adapter.notifyDataSetChanged();
        }
        else {
            IntentResult intentResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
            int cid = 0;
            cid = Integer.parseInt(intentResult.getContents().toString());
            Toast.makeText(getApplicationContext(), " " + cid, Toast.LENGTH_LONG).show();
            Cursor cursor = carrelloshop.searchcartid(cid, login.uid);
            list.clear();
Toast.makeText(getApplicationContext()," "+login.uid,Toast.LENGTH_LONG).show();
            while (!cursor.isAfterLast()) {
                int id = cursor.getInt(0);
                String name = cursor.getString(1);
                byte[] image = cursor.getBlob(2);
                int price = cursor.getInt(3);
                int uids = cursor.getInt(4);
                list.add(new cartmodel(name, image, id, price));
                cursor.moveToNext();
            }
            adapter.notifyDataSetChanged();


            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}


