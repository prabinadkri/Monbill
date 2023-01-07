package com.example.monbill;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Date;

public class RecAdapter extends RecyclerView.Adapter<RecAdapter.MyViewHolder>{
   private Context context;
   private ArrayList purpose,amount,date;
    RecAdapter(Context context,ArrayList purpose,ArrayList amount,ArrayList date){
        this.context= context;
        this.purpose=purpose;
        this.amount=amount;
        this.date=date;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.reclist,parent,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecAdapter.MyViewHolder holder, int position) {
    holder.pur.setText(String.valueOf(purpose.get(position)));
        holder.amount.setText(String.valueOf(amount.get(position)));
        holder.date.setText(String.valueOf(date.get(position)));
    }

    @Override
    public int getItemCount() {
        return purpose.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView pur,amount,date;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            pur=itemView.findViewById(R.id.pur);
            amount=itemView.findViewById(R.id.amount);
            date=itemView.findViewById(R.id.date);
        }
    }
}
