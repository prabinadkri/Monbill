package com.example.monbill;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity2 extends AppCompatActivity {
    private DBhandler db;
    private RecAdapter rc;
    ArrayList<String> purposes;
    ArrayList<Integer> amounts;
    ArrayList<String> dates;
    RecyclerView rv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Intent intent=getIntent();
        String mon=intent.getStringExtra("monbill.MONAME");
        fetchData(mon);
        rv=findViewById(R.id.dailyre);
        rc=new RecAdapter(this,purposes,amounts,dates);
        rv.setAdapter(rc);
        rv.setLayoutManager(new LinearLayoutManager(this));

    }
    void fetchData(String mon)
    {
        db = new DBhandler(this);
        purposes=new ArrayList<>();
        amounts=new ArrayList<>();
        dates = new ArrayList<>();
        //months= new ArrayList<>();
        Cursor cr=db.getOnMon(mon);
        if(cr.getCount()==0)
        {
            Toast.makeText(this, "No data", Toast.LENGTH_SHORT).show();
        }else{
            while (cr.moveToNext())
            {

                purposes.add(cr.getString(1));
                amounts.add(cr.getInt(2));
                dates.add(cr.getString(3));


            }
        }
    }
}