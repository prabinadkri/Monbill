package com.example.monbill;

import static java.sql.Types.NULL;

import android.app.Dialog;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.Date;

public class Home extends Fragment {
    private FloatingActionButton fab;
    private Dialog add_month;
    private Button button;
    private TextView textview;
    private DBhandler db;
    private EditText purpose;
    private EditText amount;
    private ListView lists;
    ArrayList<String> purposes;
    ArrayList<Integer> amounts;
    ArrayList<String> dates;
    ArrayList<String> months;
    String month;
    RecAdapter recAdapter;
    RecyclerView r;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //month="1st month";
        // Inflate the layout for this fragment
    db = new DBhandler(this.getContext());

        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        fab= (FloatingActionButton) rootView.findViewById(R.id.floatingActionButton2);
        button=(Button) rootView.findViewById(R.id.button);
        textview=(TextView) rootView.findViewById(R.id.textView);
        purpose= (EditText) rootView.findViewById(R.id.editTextTextPersonName2);
        amount= (EditText) rootView.findViewById(R.id.editTextNumberDecimal);
        //lists=(ListView) rootView.findViewById(R.id.lists);
        r=(RecyclerView) rootView.findViewById(R.id.recycle);

        fetchData();
        try{
            month = db.getMonth();
        }catch(Exception e)
        {
            month="1st month";
        }
        textview.setText(month);
        //ArrayAdapter ad=new ArrayAdapter(getContext(),android.R.layout.simple_list_item_1,purposes);
        //lists.setAdapter(ad);
        recAdapter=new RecAdapter(getContext(),purposes,amounts,dates);
        r.setAdapter(recAdapter);
        r.setLayoutManager(new LinearLayoutManager(getContext()));

                button.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        String pur=purpose.getText().toString();
                        String am=amount.getText().toString();
                        int price=0;
                        try {
                            price=Integer.parseInt(am);
                        }catch (NumberFormatException ex)
                        {
                            amount.setError("Required");
                        }

                        if(TextUtils.isEmpty(purpose.getText()) )
                        {
                            purpose.setError("Required");


                        }else {

                            db.addData(pur,price,month);
                            Toast.makeText(getActivity(), "Added", Toast.LENGTH_SHORT).show();
                            fetchData();
                            recAdapter=new RecAdapter(getContext(),purposes,amounts,dates);
                            r.setAdapter(recAdapter);
                            r.setLayoutManager(new LinearLayoutManager(getContext()));
//        // Inflate the layout for this fragment
                            //ArrayAdapter ad=new ArrayAdapter(getContext(),android.R.layout.simple_list_item_1,purposes);
                            //lists.setAdapter(ad);
                            //textview.setText("hello");

                        }
                    }
                }
        );
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(getContext(), "Month Added", Toast.LENGTH_SHORT).show();
                add_month=new Dialog(getContext());
                add_month.setContentView(R.layout.add_month);
                View rootView = inflater.inflate(R.layout.fragment_home, container, false);
                Button btn = add_month.findViewById(R.id.button2);
                EditText moname=add_month.findViewById(R.id.moname);

                btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        if(TextUtils.isEmpty(moname.getText())){
                            moname.setError("Required");
                        }else {
                            String s = moname.getText().toString();
                            db.addData("__mon", NULL, s);
                            fetchData();
                            month = db.getMonth();
                            textview.setText(month);
                            Toast.makeText(getContext(), "Month Added", Toast.LENGTH_SHORT).show();


                            add_month.dismiss();
                        }

                    }
                });
                add_month.show();
            }
        });


        return rootView;
    }
    void fetchData()
    {
        db = new DBhandler(this.getContext());
        purposes=new ArrayList<>();
        amounts=new ArrayList<>();
        dates = new ArrayList<>();
        months= new ArrayList<>();
        Cursor cr=db.recData();
        if(cr.getCount()==0)
        {
            Toast.makeText(getActivity(), "No data", Toast.LENGTH_SHORT).show();
        }else{
            while (cr.moveToNext())
            {

                    purposes.add(cr.getString(1));
                    amounts.add(cr.getInt(2));
                    dates.add(cr.getString(3));

                try {
                    months.add(cr.getString(4));
                }catch (Exception e){
                    Toast.makeText(getContext(), "Failed", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

}