package com.example.book_rfid;

import android.util.Log;

import java.util.Comparator;

public class Compare implements Comparator<String> {

    @Override
    public int compare(String o1, String o2) {
        Log.d("o1",o1+"||||"+o2);
        if(o1.equals(o2))
            return 0;
        else
            return -1;

    }
}
