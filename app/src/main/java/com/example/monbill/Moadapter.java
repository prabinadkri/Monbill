package com.example.monbill;

import static java.sql.Types.NULL;

import android.app.Dialog;
import android.app.usage.UsageEvents;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.media.metrics.Event;
import android.os.Bundle;
import android.util.EventLog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Moadapter extends RecyclerView.Adapter<MyViewHolder>{
    public Dialog del_mon;
    private Monthly_bill mb;
    public DBhandler db;
    public Context context;
    public ArrayList month,totalamount,moid;
    private LayoutInflater inflater;
    private View view;
   // ArrayList<String> months ;
    //ArrayList<Integer> tamounts;

    Moadapter(Context context,ArrayList month,ArrayList totalamount){
        this.context= context;
        this.month=month;
        this.totalamount=totalamount;
       // this.moid=moid;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        inflater=LayoutInflater.from(context);
        view=inflater.inflate(R.layout.mon,parent,false);
        db = new DBhandler(view.getContext());



        return new MyViewHolder(view).linkAdapter(this);
    }


    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.mo.setText(String.valueOf(month.get(position)));
        holder.tamount.setText(String.valueOf(totalamount.get(position)));
        //holder.date.setText(String.valueOf(date.get(position)));
    }

    @Override
    public int getItemCount() {
        return month.size();
    }


}
class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
    private Moadapter adapter;
    TextView mo,tamount,date;
    ImageButton delbtn;
    //onNoteListener onnotelistener;
    public MyViewHolder(@NonNull View itemView) {
        super(itemView);

        mo=itemView.findViewById(R.id.mmonth);
        tamount=itemView.findViewById(R.id.totalamount);
        //date=itemView.findViewById(R.id.date);
        delbtn=itemView.findViewById(R.id.imageButton4);
        //this.onnotelistener=onnotelistener;
        delbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adapter.del_mon=new Dialog(view.getContext());
                adapter.del_mon.setContentView(R.layout.del_mon);
                Button delbtn=adapter.del_mon.findViewById(R.id.button3);
                Button cabtn=adapter.del_mon.findViewById(R.id.button4);
                delbtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {


                        adapter.month.remove(getAdapterPosition());
                        //Toast.makeText(view.getContext(), "Deleted", Toast.LENGTH_SHORT).show();
                        adapter.totalamount.remove(getAdapterPosition());
                        adapter.notifyItemRemoved(getAdapterPosition());
                        adapter.db.deletemon(mo.getText().toString());
                        Toast.makeText(view.getContext(), "Deleted", Toast.LENGTH_SHORT).show();


                        adapter.del_mon.dismiss();

                    }
                });

                cabtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {



                        adapter.del_mon.dismiss();

                    }
                });
                adapter.del_mon.show();
                //Toast.makeText(itemView.getContext(), mo.getText().toString(), Toast.LENGTH_SHORT).show();
                //Toast.makeText(view.getContext(), "Deleted", Toast.LENGTH_SHORT).show();


            }
        });
        itemView.setOnClickListener(this);
    }
    public MyViewHolder linkAdapter(Moadapter adapter)
    {
        this.adapter=adapter;
        return this;
    }

    @Override
    public void onClick(View view) {

        Intent intent=new Intent(view.getContext(),MainActivity2.class);
        intent.putExtra("monbill.MONAME",adapter.month.get(getAdapterPosition()).toString());
        view.getContext().startActivity(intent);


    }



}
