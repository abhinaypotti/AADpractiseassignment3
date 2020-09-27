package com.example.food_ordering_db;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import java.util.Calendar;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener, DatePickerDialog.OnDateSetListener {

    EditText uname,pw,dob,phn;
    Button db,register;
    SQLiteDatabase dbase;
    private int mYear, mMonth, mDay;
    int year;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        uname = findViewById(R.id.username);
        pw = findViewById(R.id.password);
        dob = findViewById(R.id.dateofbirth);
        phn = findViewById(R.id.mobile);
        db = findViewById(R.id.dobbutton);
        register = findViewById(R.id.register);
        register.setOnClickListener(this);
        db.setOnClickListener(this);
        dbase = openOrCreateDatabase("foodorder", Context.MODE_PRIVATE,null);
        dbase.execSQL("CREATE TABLE IF NOT EXISTS users(username VARCHAR,password VARCHAR,mobile VARCHAR,dateofbirth DATE);");

    }

    @Override
    public void onClick(View view) {
        if(R.id.dobbutton == view.getId()){
            Calendar c = Calendar.getInstance();
            mYear = c.get(Calendar.YEAR);
            mMonth = c.get(Calendar.MONTH);
            mDay = c.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog dpd = new DatePickerDialog(this,this,mYear,mMonth,mDay);
            dpd.show();
        }
        if(R.id.register == view.getId()){
            if(uname.getText().toString().isEmpty() || pw.getText().toString().isEmpty() || dob.getText().toString().isEmpty() || phn.getText().toString().isEmpty()){
                AlertDialog.Builder a = new AlertDialog.Builder(this);
                a.setTitle("Failed to Register");
                a.setCancelable(true);
                a.setMessage("Please enter all the values to register");
                a.show();
            }
            else{
                String password = pw.getText().toString();
                int age = Math.abs(mYear-year);
                String regex = "^(?=.*[0-9])"
                        + "(?=.*[a-z])(?=.*[A-Z])"
                        + "(?=.*[@#$%^&+=])"
                        + "(?=\\S+$).{8,20}$";
                if(age >=18 && password.matches(regex) ) {
                    dbase.execSQL("INSERT INTO users VALUES('" + uname.getText().toString() + "','" + pw.getText().toString() + "','" + phn.getText().toString() + "','" + dob.getText().toString() + "');");
                    AlertDialog.Builder a = new AlertDialog.Builder(this);
                    a.setTitle("Success");
                    a.setCancelable(true);
                    a.setMessage("Registered Successfully");
                    a.show();
                    Intent i = new Intent(this, LoginActivity.class);
                    startActivity(i);
                }
                else{
                    AlertDialog.Builder a = new AlertDialog.Builder(this);
                    a.setTitle("Failed to Register");
                    a.setCancelable(true);
                    a.setMessage("Please enter a valid suitable password or your age is not sufficient");
                    a.show();
                    pw.requestFocus();

                }
            }
        }
    }

    @Override
    public void onDateSet(DatePicker datePicker, int i, int m, int i2) {
        year = i;
        dob.setText(i + "-" + (m+1) + "-" + i2);

    }
}