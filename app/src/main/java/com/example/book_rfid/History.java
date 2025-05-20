package com.example.book_rfid;

import static com.example.book_rfid.ReadFragment.excelTags;
import static com.example.book_rfid.ReadFragment.history_found;
import static com.example.book_rfid.ReadFragment.history_missing;
import static com.example.book_rfid.ReadFragment.history_total;
import static com.example.book_rfid.ReadFragment.history_unknown;
import static com.example.book_rfid.ReadFragment.missingtags;
import static com.example.book_rfid.ReadFragment.scan;
import static com.example.book_rfid.ReadFragment.unknown;

import static jxl.Workbook.createWorkbook;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.os.Environment;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

import jxl.WorkbookSettings;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class History extends AppCompatActivity {

    Adapter3 adapter6;
    /*List<String> list5=new LinkedList<>();
    List<String> list6=new LinkedList<>();
    private SimpleDateFormat mDateFormat = new SimpleDateFormat("dd-MM");*/
    MyHistoryDBHelper mhdb;
    MyHistoryTagsDBHelper mhtd;
    List<String> date,total,found,missed,unknown;
   static List<String> datee,total_tags,found_tags,missed_tags,unknown_tags;
    ImageView empty_imageview;
    TextView no_data;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        /*String datee=mDateFormat.format(date);
        Log.d("mith", "onCreate:"+mDateFormat.format(date));

        list6.add(""+mDateFormat.format(date));
        Log.d("mith","size:"+list6);
        Log.d("mith","sizee"+list6.size());*/
        empty_imageview = findViewById(R.id.empty_imageview);
        no_data = findViewById(R.id.no_data);
        ListView Totalexceltag=(ListView) findViewById(R.id.LvTags6);




      //  Totalexceltag.setAdapter(adapter6);

        mhdb = new MyHistoryDBHelper(History.this);
        date = new LinkedList<>();
        total = new LinkedList<>();
        found = new LinkedList<>();
        missed = new LinkedList<>();
        unknown=new LinkedList<>();

        mhtd=new MyHistoryTagsDBHelper(History.this);
        datee=new LinkedList<>();
        total_tags=new LinkedList<>();
        found_tags=new LinkedList<>();
        missed_tags=new LinkedList<>();
        unknown_tags=new LinkedList<>();

        storeDataInArrays();
        mhtd.storeTagsInExcel();
        adapter6=new Adapter3(this,date,total,found,missed,unknown);
        Totalexceltag.setAdapter(adapter6);

        Log.d("date size", String.valueOf(datee.size()));
        Log.d("total size", String.valueOf(total_tags.size()));
        Log.d("found size", String.valueOf(found_tags.size()));
        Log.d("missing size", String.valueOf(missed_tags.size()));
        Log.d("unknown size", String.valueOf(unknown_tags.size()));
    }

   /* private void storeTagsInExcel() {
        *//*Cursor cursor = mhtd.readAllData();
        if(cursor.getCount() == 0){
            Toast.makeText(this, "No data to write!", Toast.LENGTH_SHORT).show();
        }else{
            while (cursor.moveToNext()){
                datee.add(cursor.getString(1));
                total_tags.add(cursor.getString(2));
                found_tags.add(cursor.getString(3));
                missed_tags.add(cursor.getString(4));
                unknown_tags.add(cursor.getString(5));
            }
        }*//*

    }*/

    void storeDataInArrays(){
        Cursor cursor = mhdb.readAllData();
        if(cursor.getCount() == 0){
            empty_imageview.setVisibility(View.VISIBLE);
            no_data.setVisibility(View.VISIBLE);
        }else{
            while (cursor.moveToNext()){
                date.add(cursor.getString(1));
                total.add(cursor.getString(2));
                found.add(cursor.getString(3));
                missed.add(cursor.getString(4));
                unknown.add(cursor.getString(5));
            }
            empty_imageview.setVisibility(View.GONE);
            no_data.setVisibility(View.GONE);
        }
    }


}