package com.example.book_rfid;

import static com.example.book_rfid.ReadFragment.excelTags;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.example.book_rfid.ReadFragment;
import com.example.book_rfid.Adapter;

import java.util.ArrayList;
import java.util.List;

public class TotalTags extends AppCompatActivity {


    Adapter adapter3;
    List<String> list;
   // Context context;
    TotalTags mcontext;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_total_tags);

        ListView   Totalexceltags=(ListView) findViewById(R.id.LvTags);
        list=new ArrayList<>(excelTags);
        Log.d("list", String.valueOf(list.size()));
        adapter3=new Adapter(this,list);
        Log.d("tags", String.valueOf(ReadFragment.excelTags.size()));

        Totalexceltags.setAdapter(adapter3);
    }
}