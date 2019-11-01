package com.example.DeCoCar;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DatabaseExe {
    SQLiteDatabase database;
    DatabaseHelper  dbHelper;
    int car_id = 2038;

    public DatabaseExe(DatabaseHelper database) {
        this.dbHelper = database;
    }
    public int setting(){
        create();
        drop();
        create();
        return select(1);
    }
    public void create (){
        String name = "cluster";
        database = dbHelper.getWritableDatabase();
        if (database == null) {
            return;
        }
        database.execSQL("create table if not exists " + name + "("
                + " car_id , "
                + " status text )");
        System.out.println("createDB");
    }
    public void insert(int id, String stat){
        if (database == null) {
            return;
        }
        String query ="insert into cluster"
                + "( car_id , status ) values "
                + "('" + id + " ' , ' " +  stat+ "' ) ";
        database.execSQL(query);
        System.out.println("insertDB");
    }
    public int select(int cmd){
        int id=0;
        Cursor cursor = database.rawQuery("select car_id , status from cluster", null);
        int recordCount = cursor.getCount();
        if(recordCount<=0){ // 0면 없으니까 insert 하자
            insert(car_id,"1234567");
            id = select(1);
        }
        else{ // 있으면 갖고 오자
            cursor.moveToNext();
            id = cursor.getInt(0);
        }
        System.out.println(id+"");
        cursor.close();
        return id;
    }
    public void drop(){
        String q = "delete from cluster";
        database.execSQL(q);
        System.out.println("dropDB");
    }
}
