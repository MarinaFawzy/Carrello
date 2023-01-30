package com.example.convertapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;



public class Carrelloshop extends SQLiteOpenHelper {

SQLiteDatabase shop;
    public Carrelloshop(Context context){
        super(context,"carrelloshop",null,8);
    }



    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table users (cusid integer primary key,name text ,username text," +
                "password text,gender text,Birthdate text,job text,phone integer ,email text)");
        db.execSQL("create table category (id integer primary key  autoincrement , name text not null,image blob )");
        db.execSQL("create table product (id integer primary key  autoincrement , name text not null,image blob ," +
                "price integer,quantity integer,cartid integer,foreign key (cartid) references category (id))");
        db.execSQL("create table orders (id integer primary key  autoincrement , address text not null," +
                "orderdate text,cus_id integer,foreign key (cus_id) references users (cusid))");

        db.execSQL("create table order_details (orderid integer not null  , productid integer not null ," +
                " quantity integer,productname text ,PRIMARY KEY (orderid, productid) ,FOREIGN KEY (orderid) REFERENCES orders(id) ," +
                "FOREIGN KEY (productid) REFERENCES product(id) )");

        db.execSQL("create table cart (id integer primary key  autoincrement , nameproduct text not null," +
                "imageprroduct blob ," +
                "priceproduct integer,cus_id integer,foreign key (cus_id) references users (cusid))");

    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists users");
        db.execSQL("drop table if exists category");
        db.execSQL("drop table if exists product");
        db.execSQL("drop table if exists cart");
        db.execSQL("drop table if exists order_details");
        db.execSQL("drop table if exists orders");

        onCreate(db);
    }
    public  void  insertuser(String name, String usern, String pass, String gender,String Birthdate, String job, int phone,String email ){
        ContentValues row =new ContentValues();
        row.put("name",name);
        row.put("username",usern);
        row.put("password",pass);
        row.put("gender",gender);
        row.put("Birthdate", Birthdate);
        row.put("job", job);
        row.put("phone", phone);
        row.put("email", email);

        shop=getWritableDatabase();
      shop.insert("users",null,row);
      shop.close();

    }

    public Cursor userLogin(String username, String pass) {
       SQLiteDatabase database = getReadableDatabase();
        String[] args = {username, pass};

        Cursor cursor = database.rawQuery("select cusid from users where username =? and  password =? ", args);

        if (cursor != null)
            cursor.moveToFirst();

        database.close();
        return cursor;

    }

    public Cursor checkuser(String email) {
        SQLiteDatabase database = getReadableDatabase();


        Cursor cursor = database.rawQuery("select cusid from users  where email LIKE '"+  email +"'" , null);

        if (cursor != null)
            cursor.moveToFirst();

        database.close();
        return cursor;
    }

    public void updatepass(String pass,String email) {
        SQLiteDatabase database = getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("password",pass);


        database.update("users",values,"email=?",new String[]{email});
        database.close();
    }



    public void insertcategory(String name,  byte[] image){
        SQLiteDatabase database = getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("name",name);

        values.put("image",image);
        database.insert("category",null,values);
        database.close();
    }
    public Cursor getcategory(){
        SQLiteDatabase database = getReadableDatabase();
        return database.rawQuery("SELECT * FROM category", null);
    }
    public  void deleteData(int id) {
        SQLiteDatabase database = getWritableDatabase();
        database.delete("category", "id" + "=" + id, null);
        database.close();
    }
    public  void deleteproduct(int id) {
        SQLiteDatabase database = getWritableDatabase();
        database.delete("product", "id" + "=" + id, null);
        database.close();
    }


    public Cursor getData(String sql){
        SQLiteDatabase database = getReadableDatabase();

        return database.rawQuery(sql, null);
    }

    public void updatecategory(String name, byte[] image, int id) {
        SQLiteDatabase database = getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("name",name);

        values.put("image",image);
        database.update("category",values,"id=?",new String[]{String.valueOf(id)});
        database.close();
    }
    public void updateproduct(String name, byte[] image, int id,int price,int quantity,int cartid) {
        SQLiteDatabase database = getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("name",name);

        values.put("image",image);
        values.put("price",price);
        values.put("quantity",quantity);
        values.put("cartid",cartid);



        database.update("product",values,"id=?",new String[]{String.valueOf(id)});
        database.close();
    }

    public Cursor searchcategoryname(String name) {
        SQLiteDatabase database = getReadableDatabase();
        String[] args = {name};

        Cursor cursor = database.rawQuery("select * from category where name LIKE'"+  name +"%'" ,null);

        if (cursor != null)
            cursor.moveToFirst();

        database.close();
        return cursor;

    }
    public Cursor searchproductname(String name) {
        SQLiteDatabase database = getReadableDatabase();
        String[] args = {name};

        Cursor cursor = database.rawQuery("select * from product where name LIKE'"+  name +"%'" ,null);

        if (cursor != null)
            cursor.moveToFirst();

        database.close();
        return cursor;

    }
    public Cursor searchproductname_id(String name,int cid) {
        SQLiteDatabase database = getReadableDatabase();



       Cursor cursor = database.rawQuery("select * from product where cartid like  '" + cid + "' and name like '" + name+ "%'",null);


        if (cursor != null)
            cursor.moveToFirst();

        database.close();
        return cursor;

    }
    public Cursor searchproductid(int id,int cid) {
        SQLiteDatabase database = getReadableDatabase();

        Cursor cursor = database.rawQuery("select * from product where id like  '" + id + "' and cartid like '" + cid+ "%'"  , null);

        if (cursor != null)
            cursor.moveToFirst();

        database.close();
        return cursor;

    }

    public Cursor searchcategory(int id) {
        SQLiteDatabase database = getReadableDatabase();


        Cursor cursor = database.rawQuery("select * from category where id like +'" + id + "%'" , null);

        if (cursor != null)
            cursor.moveToFirst();

        database.close();
        return cursor;

    }


    public void insertproduct(String name,  byte[] image,int price,int quantity,int cartid){
        SQLiteDatabase database = getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("name",name);

        values.put("image",image);
        values.put("price",price);
        values.put("quantity",quantity);
        values.put("cartid",cartid);

        database.insert("product",null,values);
        database.close();
    }
    public Cursor getproducttt(){
        SQLiteDatabase database = getReadableDatabase();
        String[]det={"id","name","image","price","quantity"};
        Cursor c=database.query("product",det,null,null,null,null,null);
        if(c!=null)
            c.moveToFirst();
        database.close();
        return c;

    }
    public Cursor getproductid(int catid){
        SQLiteDatabase database = getReadableDatabase();




        Cursor c = database.rawQuery("SELECT * FROM product WHERE cartid = '"+catid+"'", null);
        if(c!=null)
            c.moveToFirst();
        database.close();
        return c;

    }
    public Cursor getproductbuid(int id){
        SQLiteDatabase database = getReadableDatabase();




        Cursor c = database.rawQuery("SELECT * FROM product WHERE id = '"+id+"'", null);
        if(c!=null)
            c.moveToFirst();
        database.close();
        return c;

    }

    public  void  insrtcart(String namepro, byte[]imgproduct,int priceproduct, int cus_id){
        ContentValues row =new ContentValues();
        row.put("nameproduct",namepro);
        row.put("imageprroduct",imgproduct);
        row.put("priceproduct",priceproduct);

        row.put("cus_id", cus_id);

        shop=getWritableDatabase();
        shop.insert("cart",null,row);
        shop.close();

    }

    public Cursor getcart(int uid){
            SQLiteDatabase database = getReadableDatabase();
            Cursor c = database.rawQuery("SELECT * FROM cart WHERE cus_id = '"+uid+"'", null);
            if(c!=null)
                c.moveToFirst();
            database.close();
            return c;
    }
    public Cursor getcartdata(int uid){
        SQLiteDatabase database = getReadableDatabase();
        Cursor c = database.rawQuery("SELECT * FROM cart WHERE id = '"+uid+"'", null);
        if(c!=null)
            c.moveToFirst();
        database.close();
        return c;
    }

    public Cursor searchcartname_id(String name,int uid) {
        SQLiteDatabase database = getReadableDatabase();

        Cursor cursor = database.rawQuery("select * from cart where cus_id like  '" + uid + "' and nameproduct like '" + name+ "%'",null);
        if (cursor != null)
            cursor.moveToFirst();
        database.close();
        return cursor;

    }
    public Cursor searchcartid(int id,int uid) {

            SQLiteDatabase database = getReadableDatabase();
        Cursor cursor = database.rawQuery("select * from cart where cus_id like  '" + uid + "' and id like '" + id+ "%'",null);

        if (cursor != null)
                cursor.moveToFirst();

            database.close();
            return cursor;
        }
    public  void deletcart(int id) {
        SQLiteDatabase database = getWritableDatabase();
        database.delete("cart", "id" + "=" + id, null);
        database.close();
    }
    public  void deletcartperuser(int uid) {
        SQLiteDatabase database = getWritableDatabase();
        database.delete("cart", "cus_id" + "=" + uid, null);
        database.close();
    }

    public void insertorder(String address, String orderdate, int cus_id) {

        ContentValues values=new ContentValues();
        values.put("address",address);

        values.put("orderdate",orderdate);

        values.put("cus_id",cus_id);
        SQLiteDatabase shop=getWritableDatabase();
        shop.insert("orders",null,values);


        shop.close();
    }

    public Cursor getorder(){
        SQLiteDatabase database = getReadableDatabase();

        Cursor cursor = database.rawQuery("select * from orders  ", null);

        if (cursor != null)
            cursor.moveToFirst();

        database.close();
        return cursor;
    }
    public Cursor getorder_date(String date){
        SQLiteDatabase database = getReadableDatabase();


        Cursor cursor = database.rawQuery("SELECT * FROM orders WHERE orderdate = '"+date+"'", null);

        if (cursor != null)
            cursor.moveToFirst();

        database.close();
        return cursor;
    }

    public Cursor getorderuserid(int uid){
        SQLiteDatabase database = getReadableDatabase();
        Cursor c = database.rawQuery("SELECT * FROM orders WHERE cus_id = '"+uid+"'", null);
        if(c!=null)
            c.moveToFirst();
        database.close();
        return c;

    }
    public Cursor getorderuseridanddate(int uid,String date){
        SQLiteDatabase database = getReadableDatabase();
        Cursor c = database.rawQuery("SELECT * FROM orders WHERE cus_id = '"+uid+"'"+ "and orderdate = '" +date+"'", null);
        if(c!=null)
            c.moveToFirst();
        database.close();
        return c;

    }

    public void insertorderdetailes(int orderid, int productid,String pname, int quantity) {
        ContentValues values=new ContentValues();
        values.put("orderid",orderid);

        values.put("productid",productid);
        values.put("productname",pname);

        values.put("quantity",quantity);
        SQLiteDatabase shop=getWritableDatabase();
        shop.insert("order_details",null,values);


        shop.close();
    }

    public Cursor getorderdetailsid(int id) {
        SQLiteDatabase database = getReadableDatabase();
     Cursor c = database.rawQuery("SELECT * FROM order_details WHERE orderid = '"+id+"'", null);
        if(c!=null)
            c.moveToFirst();
        database.close();
        return c;
    }

public Cursor getuniqevalue() {
    SQLiteDatabase database = getReadableDatabase();


    Cursor c = database.rawQuery("SELECT DISTINCT productid from order_details ",null);
    if(c!=null)
        c.moveToFirst();
    database.close();
    return c;

}
public  int getsumqut(int id){
    SQLiteDatabase database = getReadableDatabase();
    Cursor cur = database.rawQuery("SELECT SUM(quantity) FROM order_details where productid like +'" + id + "%'" , null);
    if(cur.moveToFirst())
    {
        return cur.getInt(0);
    }
 return 0;
}
}
