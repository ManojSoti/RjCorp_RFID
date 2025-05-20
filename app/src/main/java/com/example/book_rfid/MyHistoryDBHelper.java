package com.example.book_rfid;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.rscja.team.qcom.deviceapi.S;

public class MyHistoryDBHelper extends SQLiteOpenHelper {
    private Context context;
    private static final String DATABASE_NAME="BookLibrary.db";
    private static final int DATABASE_VERSION=1;
    private static final String TABLE_NAME="my_library";
    private static final String  COLUMN_ID="_id";
    private static final String COLUMN_DATE="Date";
    private static final String COLUMN_TOTAL="total_count";
    private static final String COLUMN_FOUND="found_count";
    private static final String COLUMN_MISSING="missing_count";
    private static final String COLUMN_UNKNOWN="unknown_count";


    public MyHistoryDBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query=
                "CREATE TABLE " + TABLE_NAME + " (" + COLUMN_ID +  " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_DATE + " TEXT, " +
                        COLUMN_TOTAL + " INTEGER, " +
                        COLUMN_FOUND + " INTEGER, " +
                        COLUMN_MISSING + " INTEGER, " +
                        COLUMN_UNKNOWN + " INTEGER); " ;

        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }


    void addBook(String date,int total,int found,int missing,int unknown){
        SQLiteDatabase db=this.getWritableDatabase();

        String query = " SELECT "+ COLUMN_DATE + " FROM " + TABLE_NAME + " WHERE " + COLUMN_DATE + " = ?";
        Cursor cursor = db.rawQuery(query, new String[]{date});

        if (cursor != null && cursor.getCount() > 0) {
            // Value already exists in the database, do not add it
            updateData(date, total, found, missing, unknown);

        }else{
            ContentValues cv=new ContentValues();
            cv.put(COLUMN_DATE,date);
            cv.put(COLUMN_TOTAL,total);
            cv.put(COLUMN_FOUND,found);
            cv.put(COLUMN_MISSING,missing);
            cv.put(COLUMN_UNKNOWN,unknown);

            long result= db.insert(TABLE_NAME,null,cv);
            if(result==-1){
                Toast.makeText(context, "Failed!", Toast.LENGTH_SHORT).show();
            }else{
              //  Toast.makeText(context,"Success!",Toast.LENGTH_SHORT).show();
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
    void updateData(String date,int total,int found,int missing,int unknown){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(COLUMN_DATE,date);
        cv.put(COLUMN_TOTAL,total);
        cv.put(COLUMN_FOUND,found);
        cv.put(COLUMN_MISSING,missing);
        cv.put(COLUMN_UNKNOWN,unknown);

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
}
