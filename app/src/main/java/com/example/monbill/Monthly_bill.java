package com.example.monbill;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;


public class Monthly_bill extends Fragment {
    ListView listview;
    DBhandler db;
    RecyclerView r;
    Moadapter mo;
    LayoutInflater inflater;
    ViewGroup container;
    Bundle savedInstanceState;
    ArrayList<String> months;
    ArrayList<Integer> tamounts;
    ImageButton btn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.inflater = inflater;
        this.container = container;
        this.savedInstanceState = savedInstanceState;
        View rootView = inflater.inflate(R.layout.fragment_monthly_bill, container, false);
        r = (RecyclerView) rootView.findViewById(R.id.morecycle);
        fetchData();
        mo = new Moadapter(getContext(), months, tamounts);
        r.setAdapter(mo);

        r.setLayoutManager(new LinearLayoutManager(getContext()));

        //Toast.makeText(getContext(), "mobill", Toast.LENGTH_SHORT).show();
        //listview=(ListView) rootView.findViewById(R.id.listview);
//        // Inflate the layout for this fragment
        // ArrayAdapter ad=new ArrayAdapter(this.getContext(),android.R.layout.simple_list_item_1,purposes);
        //listview.setAdapter(ad);
        return rootView;
    }
   // public void refresh(){

        //Toast.makeText(this.getContext(), "refreshed", Toast.LENGTH_SHORT).show();
//

  // }

  public void fetchData() {
        //this.onCreateView()
        db = new DBhandler(this.getContext());

        months = new ArrayList<>();
        tamounts = new ArrayList<>();

        Cursor cr = db.monData();

        if (cr.getCount() == 0) {
            Toast.makeText(getActivity(), "No data", Toast.LENGTH_SHORT).show();
        } else {
            while (cr.moveToNext()) {

                months.add(cr.getString(0));


                tamounts.add(totalam(cr.getString(0)));


            }
        }
    }

    public int totalam(String month) {
        int total = 0;

        db = new DBhandler(this.getContext());

        Cursor cr = db.getOnMon(month);

        if (cr.getCount() == 0) {
            //Toast.makeText(getActivity(), "No data", Toast.LENGTH_SHORT).show();
        } else {
            while (cr.moveToNext()) {
                total = total + cr.getInt(2);

            }
        }
        return total;
    }


}