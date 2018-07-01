package com.navneet.finance;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Databasehelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME="people.db";
    public static final String TABLE_NAME="people_table";
    public static final String COL_1="ID";
    public static final String COL_2="NAME";
    public static final String COL_3="MONEY_LENT";
    public static final String COL_4="MONEY_RECIEVED";
    public static final String COL_5="BALANCE";
    public Databasehelper(Context context) {
        super(context,DATABASE_NAME , null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
      db.execSQL("create table "+TABLE_NAME+"(ID INTEGER PRIMARY KEY AUTOINCREMENT ,NAME TEXT,MONEY_LENT INTEGER,MONEY_RECIEVED INTEGER,BALANCE INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXIST"+TABLE_NAME);
        onCreate(db);

    }
    public boolean insertData(String name,String money_lent,String money_recieved){
        SQLiteDatabase db =this.getWritableDatabase();
        ContentValues contentvalues= new ContentValues();
        contentvalues.put(COL_2,name);
        contentvalues.put(COL_3,money_lent);
        contentvalues.put(COL_4,money_recieved);
        int mL=Integer.parseInt(money_lent);
        int mR=Integer.parseInt(money_recieved);
        int bl=mL-mR;
        String balance=Integer.toString(bl);
        contentvalues.put(COL_5,balance);
        long result= db.insert(TABLE_NAME,null,contentvalues);
        if(result==-1){
            return false;
        }
        return true;

    }
    public Cursor getAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res=db.rawQuery("select * from "+TABLE_NAME,null);
        return res;

    }
}
