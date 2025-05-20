package com.example.book_rfid;

import static com.example.book_rfid.ReadFragment.scan;
import static com.example.book_rfid.ReadFragment.unknown;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class UnknownTags extends AppCompatActivity {
    Adapter2 adapter5;
    List<String> list4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unknown_tags);

        @SuppressLint({"MissingInflatedId", "LocalSuppress"})
        ListView Totalexceltags=(ListView) findViewById(R.id.LvTags4);
        list4=new ArrayList<>(unknown);
        Log.d("list", String.valueOf(list4.size()));
        adapter5=new Adapter2(this,list4);


        Totalexceltags.setAdapter(adapter5);
    }
}