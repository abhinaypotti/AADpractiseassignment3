package com.example.food_ordering_db;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.HashMap;

/**
 * A simple {@link Fragment} subclass.
 * Use the  factory method to
 * create an instance of this fragment.
 */
public class CountFragment extends Fragment implements View.OnClickListener {

    String[] item = {"Panner 65","Roti","Cashewnut curry","Dosa","Idly","Chapati","Upma","Biryani","Chicken Lollipop","Chicken 65"};
    String[] price = {"11","22","33","44","55","66","77","88","99","100"};
    View v;
    LinearLayout ll;
    Button b;
    double amount;
    Communicator2 com;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v =inflater.inflate(R.layout.fragment_count, container, false);
        ll = v.findViewById(R.id.linearlayout);
        b = v.findViewById(R.id.tot);
        b.setOnClickListener(this);

        return v;
    }

    public void changeData(HashMap<Integer, Integer> hm) {
        amount = 0;
        for (int i : hm.keySet()){
            LinearLayout ll2 = new LinearLayout(getContext());
            TextView t = new TextView(getContext());
            t.setText(item[i]+"         "+"$"+price[i]);
            ll2.addView(t);
            amount = amount + Integer.valueOf(price[i]);
            ll.addView(ll2);
        }
        if(com == null){
            com = (Communicator2) getActivity();
        }
        com.respond(amount);
    }

    @Override
    public void onClick(View view) {
        if(com == null){
            com = (Communicator2) getActivity();
        }
        com.respond(amount);
    }
}