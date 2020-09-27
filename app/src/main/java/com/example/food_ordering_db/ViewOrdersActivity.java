package com.example.food_ordering_db;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class ViewOrdersActivity extends AppCompatActivity implements View.OnClickListener {

    SQLiteDatabase db;
    Button b1,b2;
    Double total;
    String mobile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_orders);
        Bundle b = getIntent().getExtras();
        b1 = findViewById(R.id.sortamount);
        b2 = findViewById(R.id.sortdate);
        mobile = b.getString("mobile");
        total = Double.valueOf(0);
        Log.d("View",mobile+"");
        db = openOrCreateDatabase("foodorder", Context.MODE_PRIVATE,null);
       try{
            Cursor c = db.rawQuery("SELECT * FROM orders"+mobile+";",null);
//            if (c.getCount() == 0) {
//                showMessage("Error", "No records found");
//                return;
//            }
            Log.d("View","SELECT * FROM orders"+mobile);
            StringBuffer buffer = new StringBuffer();
            while (c.moveToNext())
            {
                buffer.append("Order Amount: " + c.getString(0) + "\n");
                buffer.append("Date: " + c.getString(1) + "\n\n");
                total = total + Double.valueOf(c.getString(0));
            }
            buffer.append("\n\n\n"+"Total Amount: "+total+"\n\n");
            showMessage("Orders Details", buffer.toString());

       }
      catch(Exception e){
            showMessage("Error", "No records found");
       }

       b1.setOnClickListener(this);
       b2.setOnClickListener(this);
    }
    public void showMessage(String title, String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }

    @Override
    public void onClick(View view) {
        if(R.id.sortamount == view.getId()){
            try{
                total = Double.valueOf(0);
                Cursor c = db.rawQuery("SELECT * FROM orders"+mobile+" ORDER BY amount;",null);
//            if (c.getCount() == 0) {
//                showMessage("Error", "No records found");
//                return;
//            }
                Log.d("View","SELECT * FROM orders"+mobile);
                StringBuffer buffer = new StringBuffer();
                while (c.moveToNext())
                {
                    buffer.append("Order Amount: " + c.getString(0) + "\n");
                    buffer.append("Date: " + c.getString(1) + "\n\n");
                    total = total + Double.valueOf(c.getString(0));
                }
                buffer.append("\n\n\n"+"Total Amount: "+total+"\n\n");
                showMessage("Orders Details", buffer.toString());

            }
            catch(Exception e){
                showMessage("Error", "No records found");
            }

        }
        if(R.id.sortdate == view.getId()){
            try{
                total = Double.valueOf(0);
                Cursor c = db.rawQuery("SELECT * FROM orders"+mobile+" ORDER BY orderdate;",null);
//            if (c.getCount() == 0) {
//                showMessage("Error", "No records found");
//                return;
//            }
                Log.d("View","SELECT * FROM orders"+mobile);
                StringBuffer buffer = new StringBuffer();
                while (c.moveToNext())
                {
                    buffer.append("Order Amount: " + c.getString(0) + "\n");
                    buffer.append("Date: " + c.getString(1) + "\n\n");
                    total = total + Double.valueOf(c.getString(0));
                }
                buffer.append("\n\n\n"+"Total Amount: "+total+"\n\n");
                showMessage("Orders Details", buffer.toString());

            }
            catch(Exception e){
                showMessage("Error", "No records found");
            }
        }


    }
}