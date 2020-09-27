package com.example.food_ordering_db;

import android.Manifest;
import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.telephony.SmsManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

import static android.content.Context.*;

/**
 * A simple {@link Fragment} subclass.
 * Use the  factory method to
 * create an instance of this fragment.
 */
public class ThirdFragment extends Fragment {


    TextView tv;
    String mobile;
    Button b1,b2,b3,b4;
    double money;
    //SQLiteDatabase db;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_third, container, false);
        tv = v.findViewById(R.id.bill);
        b1 = v.findViewById(R.id.sms);
        b2 = v.findViewById(R.id.pay);
        b3 = v.findViewById(R.id.contactus);
        b4 = v.findViewById(R.id.locateus);
        //db = SQLiteDatabase.openOrCreateDatabase("foodorder",null);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String msg = "Your Total Bill amount is: "+money;
                Intent i = new Intent(getContext(),ThirdFragment.class);
                PendingIntent p = PendingIntent.getActivity(getContext(),0,i,0);
                SmsManager s = SmsManager.getDefault();
                if(ContextCompat.checkSelfPermission(getContext(), Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions((Activity) getContext(),new String[]{Manifest.permission.SEND_SMS},111);
                }
                s.sendTextMessage(mobile,null,msg,p,null);
                Toast.makeText(getContext(), "Message Sent successfully!", Toast.LENGTH_LONG).show();
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = "http://“www.paymentxyz.com/totalbill="+money;
                Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(i);

            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_CALL);
                i.setData(Uri.parse("tel:"+"1234567890"));
                if(ActivityCompat.checkSelfPermission(getActivity(),Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(getActivity(),new String[]{Manifest.permission.CALL_PHONE},111);
                }
                startActivity(i);

            }
        });
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri location = Uri.parse("geo:0,0?q=bhalle bhalle,+coimbatore");
                Intent mapintent = new Intent(Intent.ACTION_VIEW,location);
                startActivity(mapintent);

            }
        });
        return v;

    }

    public void changeData(double amount) {
        money = amount;
//        Calendar c = Calendar.getInstance();
        //db.execSQL("CREATE TABLE IF NOT EXISTS "+mobile+"orders(amount VARCHAR,orderdate DATE);");
        //db.execSQL("INSERT INTO orders VALUES('"+amount+"','"+c.get(Calendar.YEAR)+"-"+c.get(Calendar.MONTH)+"-"+c.get(Calendar.DAY_OF_MONTH)+"');" );
        tv.setText("Total Amount: "+String.valueOf(amount));
    }

    public void mobile(String phone) {
        mobile = phone;
    }
}