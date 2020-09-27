package com.example.food_ordering_db;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import java.util.Calendar;
import java.util.HashMap;

public class SecondActivity extends AppCompatActivity implements Communicator2{
    String phone;
    SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Bundle b = getIntent().getExtras();
        phone = b.getString("phonenumber");
        HashMap<Integer,Integer> hm = new HashMap<>();
        hm = (HashMap<Integer, Integer>) b.getSerializable("map");
        FragmentManager fm = getSupportFragmentManager();
        CountFragment f2 = (CountFragment) fm.findFragmentById(R.id.cf);
        f2.changeData(hm);
    }

    @Override
    public void respond(double amount) {
        FragmentManager fm2 = getSupportFragmentManager();
        ThirdFragment t = (ThirdFragment) fm2.findFragmentById(R.id.tf);
        Calendar c = Calendar.getInstance();
        db = openOrCreateDatabase("foodorder", Context.MODE_PRIVATE,null);
        db.execSQL("CREATE TABLE IF NOT EXISTS orders"+phone+"(amount VARCHAR,orderdate DATE);");
        db.execSQL("INSERT INTO orders"+phone+" VALUES('"+amount+"','"+c.get(Calendar.YEAR)+"-"+c.get(Calendar.MONTH)+"-"+c.get(Calendar.DAY_OF_MONTH)+"');" );
        t.changeData(amount);
        t.mobile(phone);
    }
}