package com.example.book_rfid;

import static com.example.book_rfid.History.datee;
import static com.example.book_rfid.History.found_tags;
import static com.example.book_rfid.History.missed_tags;
import static com.example.book_rfid.History.total_tags;
import static com.example.book_rfid.History.unknown_tags;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.LinkedList;
import java.util.List;

public class MyHistoryTagsDBHelper extends SQLiteOpenHelper {
    private Context context;
    private static final String DATABASE_NAME="Tags.db";
    private static final int DATABASE_VERSION=1;
    private static final String TABLE_NAME="my_Tags";
    private static final String  COLUMN_ID="_id";
    private static final String COLUMN_DATE="Date";
    private static final String COLUMN_TOTAL="Total";
    private static final String COLUMN_FOUND="Found";
    private static final String COLUMN_MISSING="Missing";
    private static final String COLUMN_UNKNOWN="Unknown";

List<String> dummy=new LinkedList<>();
    public MyHistoryTagsDBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query=
                "CREATE TABLE " + TABLE_NAME + " (" + COLUMN_ID +  " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        COLUMN_DATE + " TEXT, " +
                        COLUMN_TOTAL + " TEXT, " +
                        COLUMN_FOUND + " TEXT, " +
                        COLUMN_MISSING + " TEXT, " +
                        COLUMN_UNKNOWN + " TEXT); " ;

        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }


    void addBookA(List<String> date, List<String> total, List<String> found, List<String> missing, List<String> unknown) {
        SQLiteDatabase db = this.getWritableDatabase();


        /*db.beginTransaction();
        try {
            ContentValues cv=new ContentValues();
            for (int i = 0; i < date.size(); i++) {
                String da = date.get(i);
                cv.put(COLUMN_DATE, da);
                db.insert(TABLE_NAME, null, cv);
            }
            for (int i = 0; i < total.size(); i++) {
                String tot = total.get(i);
                cv.put(COLUMN_TOTAL, tot);
                db.insert(TABLE_NAME, null, cv);
            }

            for (int j = 0; j < found.size(); j++) {
                String fof = found.get(j);
                cv.put(COLUMN_FOUND, fof);
                db.insert(TABLE_NAME, null, cv);
            }

            for (int p = 0; p < missing.size(); p++) {
                String miss = missing.get(p);
                cv.put(COLUMN_MISSING, miss);
                db.insert(TABLE_NAME, null, cv);
            }

            for (int q = 0; q < unknown.size(); q++) {
                String unk = unknown.get(q);
                cv.put(COLUMN_UNKNOWN, unk);
                db.insert(TABLE_NAME, null, cv);
            }
            db.setTransactionSuccessful();
        }finally {
            db.endTransaction();
            db.close();
        }*/


        int size = Math.max(
                Math.max(date.size(), total.size()),
                Math.max(Math.max(found.size(), missing.size()), unknown.size())
        );
        Log.d("size", String.valueOf(size));
        for (int i = 0; i < size; i++) {
            ContentValues values = new ContentValues();

            if (i < date.size()) {
                values.put(COLUMN_DATE, date.get(i)); // Replace "column_name1" with your column name
            }

            if (i < total.size()) {
                values.put(COLUMN_TOTAL, total.get(i)); // Replace "column_name2" with your column name
            }

            if (i < found.size()) {
                values.put(COLUMN_FOUND, found.get(i)); // Replace "column_name3" with your column name
            }

            if (i < missing.size()) {
                values.put(COLUMN_MISSING, missing.get(i)); // Replace "column_name4" with your column name
            }

            if (i < unknown.size()) {
                values.put(COLUMN_UNKNOWN, unknown.get(i)); // Replace "column_name5" with your column name
            }

            long result = db.insert(TABLE_NAME, null, values);
            if (result == -1) {
                Toast.makeText(context, "Failed!", Toast.LENGTH_SHORT).show();
            } else {
                //Toast.makeText(context, "Success!", Toast.LENGTH_SHORT).show();
            }
        }


    }
    Cursor readAllData(){
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }
    void updateData(String date,List<String> total,List<String> found,List<String> missing,List<String> unknown){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(COLUMN_DATE,date);
        for(int i=0;i< total.size();i++){
            String tot=total.get(i);
            cv.put(COLUMN_TOTAL,tot);
        }

        for (int j=0;j< found.size();j++){
            String fof= found.get(j);
            cv.put(COLUMN_FOUND,fof);
        }

        for (int p=0;p< missing.size();p++){
            String miss= missing.get(p);
            cv.put(COLUMN_MISSING,miss);
        }

        for (int q=0;q< unknown.size();q++){
            String unk= unknown.get(q);
            cv.put(COLUMN_UNKNOWN,unk);
        }


        long result = db.update(TABLE_NAME, cv, "Date=?", new String[]{date});
        if(result == -1){
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Updated Successfully!", Toast.LENGTH_SHORT).show();
        }

    }

    void deleteAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_NAME);
    }

    void storeTagsInExcel(){
        SQLiteDatabase db = this.getWritableDatabase();
        String[] columns = {COLUMN_DATE, COLUMN_TOTAL, COLUMN_FOUND, COLUMN_MISSING, COLUMN_UNKNOWN};
        String table = TABLE_NAME;
        Cursor cursor = db.query(table, columns, null, null, null, null, null);
        datee.clear();
        total_tags.clear();
        found_tags.clear();
        missed_tags.clear();
        unknown_tags.clear();
        while (cursor.moveToNext()) {
            @SuppressLint("Range") String item1 = cursor.getString(cursor.getColumnIndex(COLUMN_DATE)); // Replace "column_name1" with your column name
            @SuppressLint("Range") String item2 = cursor.getString(cursor.getColumnIndex(COLUMN_TOTAL)); // Replace "column_name2" with your column name
            @SuppressLint("Range") String item3 = cursor.getString(cursor.getColumnIndex(COLUMN_FOUND)); // Replace "column_name3" with your column name
            @SuppressLint("Range") String item4 = cursor.getString(cursor.getColumnIndex(COLUMN_MISSING)); // Replace "column_name4" with your column name
            @SuppressLint("Range") String item5 = cursor.getString(cursor.getColumnIndex(COLUMN_UNKNOWN)); // Replace "column_name5" with your column name

            datee.add(item1);
            total_tags.add(item2);
            found_tags.add(item3);
            missed_tags.add(item4);
            unknown_tags.add(item5);
        }
        cursor.close();

    }

}
