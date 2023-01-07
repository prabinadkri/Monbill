package com.example.monbill;

import static com.example.monbill.R.id.button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class MainActivity extends AppCompatActivity {
    private TabLayout tabLayout;
    private TabLayout.Tab tab;
    private ViewPager2 viewpager2;
    private VPAdapter pageadapter;
    private Button button;
    private String[] titles ={"Home","Monthly Bill"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tabLayout= findViewById(R.id.tabLayout);
        viewpager2=findViewById(R.id.viewpager2);
    pageadapter=new VPAdapter(this);
    viewpager2.setAdapter(pageadapter);
    new TabLayoutMediator(tabLayout,viewpager2,((tab, position) -> tab.setText(titles[position]))).attach();



    }


//    @Override
//    public void onNoteClick(int position) {
//        Toast.makeText(this, "click", Toast.LENGTH_SHORT).show();
//    }
}