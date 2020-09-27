package com.example.food_ordering_db;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button b1,b2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b1 = findViewById(R.id.register);
        b2 = findViewById(R.id.login);
        b2.setOnClickListener(this);
        b1.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.register){
            Intent i = new Intent(this, RegisterActivity.class);
            startActivity(i);
        }
        else{
            Intent i = new Intent(this, LoginActivity.class);
            startActivity(i);
        }
    }
}