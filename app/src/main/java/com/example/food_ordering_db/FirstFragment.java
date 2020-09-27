package com.example.food_ordering_db;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Use the  factory method to
 * create an instance of this fragment.
 */
public class FirstFragment extends Fragment implements AdapterView.OnItemClickListener, View.OnClickListener {



    String[] item = {"Panner 65","Roti","Cashewnut curry","Dosa","Idly","Chapati","Upma","Biryani","Chicken Lollipop","Chicken 65"};
    String[] price = {"11","22","33","44","55","66","77","88","99","100"};
    ListView lv;
    View v;
    Button bc;
    Communicator comm;
    HashMap<Integer,Integer> forcart;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        forcart = new HashMap<Integer,Integer>();
        v = inflater.inflate(R.layout.fragment_first, container, false);
        lv = v.findViewById(R.id.menu);
        List<Map<String,String>> menu = new ArrayList<>();
        for(int i=0;i<item.length;i++){
            HashMap<String,String> m = new HashMap<>();
            m.put("item",item[i]);
            m.put("price","$ "+price[i]);
            menu.add(m);
        }
        bc = v.findViewById(R.id.cart);

        String[] entry = {"item","price"};
        SimpleAdapter adap = new SimpleAdapter(getContext(), menu, R.layout.menulistview,entry,new int[]{R.id.itemname,R.id.itemprice});
        lv.setAdapter(adap);
        lv.setOnItemClickListener(this);
        bc.setOnClickListener(this);
        return v;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        forcart.put(i,i);

    }


    @Override
    public void onClick(View view) {
        if(comm == null){
            comm = (Communicator)getActivity();

        }
        comm.respond(forcart);
    }
}