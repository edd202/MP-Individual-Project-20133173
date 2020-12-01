package com.cookandroid.MP_Project;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DBOpenHelper {

    private static final String DATABASE_NAME = "manager_SQLite.db";
    private static final int DATABASE_VERSION = 1;
    public static SQLiteDatabase mDB;
    private DatabaseHelper mDBHelper;
    private Context mCtx;

    private class DatabaseHelper extends SQLiteOpenHelper{

        public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase db){
            db.execSQL(DataBases.CreateDB._CREATE0);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS "+DataBases.CreateDB._TABLENAME0);
            onCreate(db);
        }
    }

    public DBOpenHelper(Context context){
        this.mCtx = context;
    }

    public DBOpenHelper open() throws SQLException {
        mDBHelper = new DatabaseHelper(mCtx, DATABASE_NAME, null, DATABASE_VERSION);
        mDB = mDBHelper.getWritableDatabase();
        return this;
    }

    public void create(){
        mDBHelper.onCreate(mDB);
    }

    public void close(){
        mDB.close();
    }

    // Insert DB
    public long insertColumn(String size, String direction, String price , String junwalma, String loc, String phone, String option, String etc){
        ContentValues values = new ContentValues();
        values.put(DataBases.CreateDB.SIZE, size);
        values.put(DataBases.CreateDB.DIRECTION, direction);
        values.put(DataBases.CreateDB.PRICE, price);
        values.put(DataBases.CreateDB.JUNWALMA, junwalma);
        values.put(DataBases.CreateDB.LOC, loc);
        values.put(DataBases.CreateDB.PHONE, phone);
        values.put(DataBases.CreateDB.OPTION, option);
        values.put(DataBases.CreateDB.ETC, etc);

        return mDB.insert(DataBases.CreateDB._TABLENAME0, null, values);
    }

    // Update DB
    public boolean updateColumn(long id, String size, String direction, String price , String junwalma, String loc, String phone, String option, String etc){
        ContentValues values = new ContentValues();
        values.put(DataBases.CreateDB.SIZE, size);
        values.put(DataBases.CreateDB.DIRECTION, direction);
        values.put(DataBases.CreateDB.PRICE, price);
        values.put(DataBases.CreateDB.JUNWALMA, junwalma);
        values.put(DataBases.CreateDB.LOC, loc);
        values.put(DataBases.CreateDB.PHONE, phone);
        values.put(DataBases.CreateDB.OPTION, option);
        values.put(DataBases.CreateDB.ETC, etc);
        return mDB.update(DataBases.CreateDB._TABLENAME0, values, "_id=" + id, null) > 0;
    }

    // Delete All
    public void deleteAllColumns() {
        mDB.delete(DataBases.CreateDB._TABLENAME0, null, null);
    }

    // Delete DB
    public boolean deleteColumn(long id){
        return mDB.delete(DataBases.CreateDB._TABLENAME0, "_id="+id, null) > 0;
    }
    // Select DB
    public Cursor selectColumns(String where){
        return mDB.query(DataBases.CreateDB._TABLENAME0, null, where, null, null, null, null);
    }

    // sort by column
    public Cursor sortColumn(String sort){
        Cursor c = mDB.rawQuery( "SELECT * FROM usertable ORDER BY " + sort + ";", null);
        return c;
    }
}