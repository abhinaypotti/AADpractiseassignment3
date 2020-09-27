package com.example.food_ordering_db;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    EditText uname,pw;
    Button b;
    SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        uname = findViewById(R.id.username);
        pw = findViewById(R.id.password);
        b = findViewById(R.id.login);
        b.setOnClickListener(this);
        db = openOrCreateDatabase("foodorder", Context.MODE_PRIVATE,null);

    }

    @Override
    public void onClick(View view) {
        if(uname.getText().toString().isEmpty() || pw.getText().toString().isEmpty()){
            showMessage("Error","Please Enter all Values");
        }
        else{

            Cursor c = db.rawQuery("SELECT username,password,mobile FROM users WHERE username='"+uname.getText().toString()+"';",null);

            if (c.moveToFirst()) {
                // Displaying record if found 
                String u  = c.getString(0);
                String p = c.getString(1);
                String mobile = c.getString(2);
                if(p.matches(pw.getText().toString())){
                    Intent i = new Intent(this,MainActivity2.class);
                    i.putExtra("name",u);
                    i.putExtra("mobile",mobile);
                    startActivity(i);
                }
            } else {
                showMessage("Error", "Invalid Rollno");
                clearText();
            }


        }
    }

    private void clearText() {
        uname.setText("");
        pw.setText("");
        uname.requestFocus();
    }

    private void showMessage(String error, String please_enter_all_values) {
        AlertDialog.Builder a = new AlertDialog.Builder(this);
        a.setTitle(error);
        a.setCancelable(true);
        a.setMessage(please_enter_all_values);
        a.show();
    }
}