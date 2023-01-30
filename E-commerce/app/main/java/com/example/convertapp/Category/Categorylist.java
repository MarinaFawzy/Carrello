package com.example.convertapp.Category;

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
import android.widget.GridView;
import android.widget.SearchView;
import android.widget.SearchView.OnQueryTextListener;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.convertapp.R;
import com.example.convertapp.database.Carrelloshop;
import com.example.convertapp.loginandregisteration.login;
import com.example.convertapp.prduct.productlist;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.lang.reflect.Field;
import java.util.ArrayList;

public class Categorylist extends AppCompatActivity {
    GridView gridView;
    String voice;
    ArrayList<categorymodel> list;
    CategoryAdapter adapter = null;
    Carrelloshop carrelloshop = new Carrelloshop(this);

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {

            case R.id.add:

                Intent add = new Intent(Categorylist.this, Addcategory.class);
                startActivity(add);
                break;
            case R.id.delete:
                AdapterView.AdapterContextMenuInfo pos = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

                AlertDialog.Builder dialogDelete = new AlertDialog.Builder(Categorylist.this);
                Carrelloshop carrelloshop = new Carrelloshop(this);
                dialogDelete.setTitle("Warning!!");
                dialogDelete.setMessage("Are you sure you want to this delete?");
                dialogDelete.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        try {
                            Cursor c = carrelloshop.getData("SELECT id FROM category");
                            ArrayList<Integer> arrID = new ArrayList<Integer>();
                            while (c.moveToNext()) {
                                arrID.add(c.getInt(0));
                            }
                            int id = arrID.get(pos.position);
                           // Toast.makeText(getApplicationContext(), "" + id, Toast.LENGTH_SHORT).show();

                            carrelloshop.deleteData(id);
                            Toast.makeText(getApplicationContext(), "Delete successfully!", Toast.LENGTH_SHORT).show();
                        } catch (Exception e) {
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
                AdapterView.AdapterContextMenuInfo p = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

                Carrelloshop carrelloshop1 = new Carrelloshop(this);
                Cursor c = carrelloshop1.getData("SELECT id FROM category");
                ArrayList<Integer> arrID = new ArrayList<Integer>();
                while (c.moveToNext()) {
                    arrID.add(c.getInt(0));
                }
                int id = arrID.get(p.position);

                Intent next = new Intent(Categorylist.this, updatescategory.class);
                next.putExtra("id", id);
                startActivity(next);

        }


        return super.onContextItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categorylist);
        gridView = (GridView) findViewById(R.id.grid);
        Intent camera=getIntent();
        int cameraid= camera.getIntExtra("cameraid",0);

        list = new ArrayList<>();
        adapter = new CategoryAdapter(this, list);


        gridView.setAdapter(adapter);
        try {
            Field field = CursorWindow.class.getDeclaredField("sCursorWindowSize");
            field.setAccessible(true);
            field.set(null, 100 * 1024 * 1024); //the 100MB is the new size
        } catch (Exception e) {
            e.printStackTrace();
        }

        Cursor cursor = carrelloshop.getcategory();
        list.clear();
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            byte[] image = cursor.getBlob(2);
            list.add(new categorymodel(name, image, id));
        }
        adapter.notifyDataSetChanged();
        if(login.adminname.equals("admin")&&login.adminpass.equals("admin")) {
    gridView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
        @Override
        public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
            registerForContextMenu(gridView);


            return false;
        }
    });
}
        Carrelloshop carrelloshop = new Carrelloshop(this);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Cursor c = carrelloshop.getData("SELECT id FROM category");
                ArrayList<Integer> arrID = new ArrayList<Integer>();
                while (c.moveToNext()) {
                    arrID.add(c.getInt(0));
                }
                int ID = arrID.get(position);


                Intent i = new Intent(Categorylist.this, productlist.class);
                i.putExtra("id", ID);
                startActivity(i);


            }
        });


    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menus, menu);
        menu.setHeaderTitle("Select option");
        super.onCreateContextMenu(menu, v, menuInfo);
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
               searchView.setOnQueryTextListener(new OnQueryTextListener() {
                   @Override
                   public boolean onQueryTextSubmit(String query) {
                       Cursor c=carrelloshop.searchcategoryname(query);


                       list.clear();
                       while (!c.isAfterLast()) {
                           int id = c.getInt(0);
                           String name = c.getString(1);
                           byte[] image = c.getBlob(2);
                           list.add(new categorymodel(name,  image, id));
                           c.moveToNext();
                       }
                       adapter.notifyDataSetChanged();

                       return false;
                   }

                   @Override
                   public boolean onQueryTextChange(String newText) {
                       Cursor c=carrelloshop.searchcategoryname(newText);


                       list.clear();
                       while (!c.isAfterLast()) {
                           int id = c.getInt(0);
                           String name = c.getString(1);
                           byte[] image = c.getBlob(2);
                           list.add(new categorymodel(name,  image, id));
                           c.moveToNext();
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

            Cursor c=carrelloshop.searchcategoryname(voice);


            list.clear();
            while (!c.isAfterLast()) {
                int id = c.getInt(0);
                String name = c.getString(1);
                byte[] image = c.getBlob(2);
                list.add(new categorymodel(name,  image, id));
                c.moveToNext();
            }
            adapter.notifyDataSetChanged();
        }
        else {
            IntentResult intentResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);

            int cid = 0;
            cid = Integer.parseInt(intentResult.getContents());
            Toast.makeText(getApplicationContext(), " " + cid, Toast.LENGTH_LONG).show();
        Cursor c = carrelloshop.searchcategory(cid);
            list.clear();

            while (!c.isAfterLast()) {
                int id = c.getInt(0);
                String name = c.getString(1);
                byte[] image = c.getBlob(2);
                list.add(new categorymodel(name,  image, id));
                c.moveToNext();
            }
            adapter.notifyDataSetChanged();

            super.onActivityResult(requestCode, resultCode, data);

           }

    }
}



