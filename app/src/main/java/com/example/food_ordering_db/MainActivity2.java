package com.example.food_ordering_db;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import java.util.HashMap;

public class MainActivity2 extends AppCompatActivity implements Communicator{

    TextView tv;
    FrameLayout fl;
    String phone;
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Bundle b = getIntent().getExtras();
        tv = findViewById(R.id.details);
        btn = findViewById(R.id.viewordersbtn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),ViewOrdersActivity.class);
                i.putExtra("mobile",phone);
                startActivity(i);
            }
        });
        String name = b.getString("name");
        phone = b.getString("mobile");
        tv.setText(name + " " + phone);
        fl = findViewById(R.id.frame);
        FragmentManager fm = getSupportFragmentManager();
        final FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.frame, new FirstFragment());
        ft.commit();
    }

    @Override
    public void respond(HashMap<Integer, Integer> hm) {
        Intent i = new Intent(this,SecondActivity.class);
        i.putExtra("map",hm);
        i.putExtra("phonenumber",phone);
        startActivity(i);
    }
}