package com.example.monbill;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

public class DBhandler extends SQLiteOpenHelper{

    public DBhandler(Context context) {
        super(context, "dbname.db" , null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE trial ( sno INTEGER PRIMARY KEY AUTOINCREMENT ,  purpose TEXT,  amount INTEGER ,  date DATE DEFAULT CURRENT_DATE,month TEXT);");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS trial");
        onCreate(sqLiteDatabase);
    }
    public Cursor getData() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "SELECT * FROM trial ORDER BY sno DESC", null );
        return res;
    }
    public void addData(String purpose,int amount,String month)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv =new ContentValues();

        cv.put("purpose",purpose);
        cv.put("amount",amount);
        cv.put("month",month);
        db.insert("trial",null,cv);

    }
    public Cursor recData() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "SELECT * FROM trial WHERE purpose!='__mon' ORDER BY sno DESC LIMIT 5", null );
        return res;
    }
    public Cursor monData() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "SELECT DISTINCT month FROM trial ORDER BY sno DESC", null );
        return res;
    }
    public String getMonth()
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "SELECT * FROM trial ORDER BY sno DESC LIMIT 1", null );
        res.moveToNext();
        return res.getString(4);
    }
    public Cursor getOnMon(String monthw)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "SELECT * FROM trial WHERE month LIKE '" + monthw + "' AND purpose!='__mon' ORDER BY sno DESC", null );
        return res;
    }
    public void deletemon(String monthw)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("trial","month=?",new String[]{monthw});
    }
}
