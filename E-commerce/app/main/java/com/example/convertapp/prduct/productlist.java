package com.example.convertapp.prduct;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.CursorWindow;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.convertapp.R;
import com.example.convertapp.database.Carrelloshop;
import com.example.convertapp.loginandregisteration.login;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.lang.reflect.Field;
import java.util.ArrayList;

public class productlist extends AppCompatActivity {
    ListView listviews;
    ArrayList<Productmodel> list;
    Productadapter adapter = null;
    String voice=null;
    int ID=0;
    Carrelloshop carrelloshop = new Carrelloshop(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productlist);

        Intent camera=getIntent();
        int cameraid= camera.getIntExtra("cameraid",0);

        listviews =  findViewById(R.id.productlist);
        list = new ArrayList<>();
        adapter = new Productadapter(this,list);

        if(login.adminname.equals("admin")&&login.adminpass.equals("admin")) {
            listviews.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                    registerForContextMenu(listviews);


                    return false;
                }
            });
        }
        Intent i=getIntent();
       ID=  i.getIntExtra("id",0);


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

        if(ID>0) {
             cursor = carrelloshop.getproductid(ID);
        }
        else { cursor = carrelloshop.getproducttt();}


        while (!cursor.isAfterLast()) {
                int id = cursor.getInt(0);
                String name = cursor.getString(1);
                byte[] image = cursor.getBlob(2);
                int price = cursor.getInt(3);
                int quantity = cursor.getInt(4);

                list.add(new Productmodel(name, image, id, price, quantity));

                cursor.moveToNext();
            }
            adapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.toolbar, menu);

        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        SearchView searchView= (SearchView) item.getActionView();

        switch (item.getItemId()){
            case R.id.search:

                searchView.setQueryHint("Search");
                searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                    @Override
                    public boolean onQueryTextSubmit(String query) {
                        Cursor c;
                        if(ID>0) {
                            c = carrelloshop.searchproductname_id(query,ID);
                        }
                        else { c = carrelloshop.searchproductname(query);}

                        list.clear();

                        while (!c.isAfterLast()) {
                            int id = c.getInt(0);
                            String name = c.getString(1);
                            byte[] image = c.getBlob(2);
                            int price = c.getInt(3);
                            int quanty = c.getInt(4);

                            list.add(new Productmodel(name, image, id, price, quanty));
                            c.moveToNext();
                        }
                        adapter.notifyDataSetChanged();
                        return false;
                    }

                    @Override
                    public boolean onQueryTextChange(String newText) {
                        Cursor cursor;
                        if(ID>0) {
                            cursor = carrelloshop.searchproductname_id(newText,ID);
                        }
                        else { cursor = carrelloshop.searchproductname(newText);}

                        list.clear();

                        while (!cursor.isAfterLast()) {
                            int id = cursor.getInt(0);
                            String name = cursor.getString(1);
                            byte[] image = cursor.getBlob(2);
                            int price = cursor.getInt(3);
                            int quantity = cursor.getInt(4);

                            list.add(new Productmodel(name, image, id, price, quantity));
                            cursor.moveToNext();
                        }
                        adapter.notifyDataSetChanged();

                        return false;
                    }
                });

                break;
            case R.id.voice:
                Intent neIntent=new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                startActivityForResult(neIntent,100);
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

        if(requestCode == 100 && resultCode == RESULT_OK ){
            ArrayList<String>text=data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            voice=text.get(0);

            Cursor cursor;
            if(ID>0) {
                cursor = carrelloshop.searchproductname_id(voice,ID);
            }
            else { cursor = carrelloshop.searchproductname(voice);}

            list.clear();
            while (!cursor.isAfterLast()) {
                int id = cursor.getInt(0);
                String name = cursor.getString(1);
                byte[] image = cursor.getBlob(2);
                int price = cursor.getInt(3);
                int quantity = cursor.getInt(4);

                list.add(new Productmodel(name, image, id, price, quantity));
                cursor.moveToNext();
            }
            adapter.notifyDataSetChanged();
        }
        else {
            IntentResult intentResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);

            int cid = 0;
            cid = Integer.parseInt(intentResult.getContents());

            Cursor cursor;

            if(ID>0) {
                cursor = carrelloshop.searchproductid(cid,ID);
            }
            else { cursor = carrelloshop.getproductbuid(cid);}

            list.clear();

            while (!cursor.isAfterLast()) {
                int id = cursor.getInt(0);
                String name = cursor.getString(1);
                byte[] image = cursor.getBlob(2);
                int price = cursor.getInt(3);
                int quantity = cursor.getInt(4);

                list.add(new Productmodel(name, image, id, price, quantity));
                cursor.moveToNext();
            }
            adapter.notifyDataSetChanged();


        }
        super.onActivityResult(requestCode, resultCode, data);

    }
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menus, menu);
        menu.setHeaderTitle("Select option");
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {

            case R.id.add:

                Intent add = new Intent(productlist.this, Addproduct.class);
                startActivity(add);
                break;
            case R.id.delete:
                AdapterView.AdapterContextMenuInfo pos = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

                AlertDialog.Builder dialogDelete = new AlertDialog.Builder(productlist.this);
                Carrelloshop carrelloshop = new Carrelloshop(this);
                dialogDelete.setTitle("Warning!!");
                dialogDelete.setMessage("Are you sure you want to this delete?");
                dialogDelete.setPositiveButton("OK", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        try {
                            Cursor  cursor=null;
                            if(ID==0) {
                                cursor = carrelloshop.getproducttt();}
                            else {
                                cursor = carrelloshop.getproductid(ID);

                            }
                            ArrayList<Integer> arrID = new ArrayList<Integer>();
                            while (!cursor.isAfterLast()) {

                                arrID.add(cursor.getInt(0));
                                  cursor.moveToNext();

                                }
                                int id = arrID.get(pos.position);
                                carrelloshop.deleteproduct(id);
                                Toast.makeText(getApplicationContext(), "Delete successfully! ", Toast.LENGTH_LONG).show();

                        }
                        catch (Exception e) {
                            Log.e("error", e.getMessage());
                        }

                    }
                });

                dialogDelete.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                dialogDelete.show();

                break;
            case R.id.update:
                AdapterView.AdapterContextMenuInfo position = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
                Carrelloshop carrelloshop1 = new Carrelloshop(this);
                Cursor  cursor=null;
                if(ID==0) {
                    cursor = carrelloshop1.getproducttt();}
                else {
                    cursor = carrelloshop1.getproductid(ID);

                }
                ArrayList<Integer> arrID = new ArrayList<Integer>();
                while (!cursor.isAfterLast()) {

                    arrID.add(cursor.getInt(0));
                    cursor.moveToNext();

                }
                int id = arrID.get(position.position);

                Intent next = new Intent(productlist.this, updateproduct.class);
                next.putExtra("id", id);
                startActivity(next);

        }


        return super.onContextItemSelected(item);
    }


}