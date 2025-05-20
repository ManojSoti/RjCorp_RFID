package com.example.book_rfid;

import static com.example.book_rfid.ReadFragment.excelTags;
import static com.example.book_rfid.ReadFragment.scan;


import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;
import com.example.book_rfid.ReadFragment;
import com.example.book_rfid.Adapter;
public class FoundTags extends AppCompatActivity {

    Adapter4 adapter4;
    List<String> list2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_found_tags);

        @SuppressLint({"MissingInflatedId", "LocalSuppress"})
        ListView Totalexceltags=(ListView) findViewById(R.id.LvTags2);
        list2=new ArrayList<>(scan);
        Log.d("list", String.valueOf(list2.size()));
        adapter4=new Adapter4(this,list2);
        Log.d("tags", String.valueOf(ReadFragment.excelTags.size()));

        Totalexceltags.setAdapter(adapter4);
    }
}