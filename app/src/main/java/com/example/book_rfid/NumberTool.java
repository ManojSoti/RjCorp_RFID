package com.example.book_rfid;

import java.math.BigDecimal;

public class NumberTool {

    public static double getPointDouble(int point, double val) {
        BigDecimal bd = new BigDecimal(val);
        return bd.setScale(point, BigDecimal.ROUND_HALF_UP).doubleValue();
    }


    public static double getPointDouble(int point, int val) {
        BigDecimal bd = new BigDecimal(val);
        return bd.setScale(point, BigDecimal.ROUND_HALF_UP).doubleValue();
    }


    public static double getPointDouble(int point, long val) {
        BigDecimal bd = new BigDecimal(val);
        return bd.setScale(point, BigDecimal.ROUND_HALF_UP).doubleValue();
    }


    public static double getPointDouble(int point, String val) {
        BigDecimal bd = new BigDecimal(val);
        return bd.setScale(point, BigDecimal.ROUND_HALF_UP).doubleValue();
    }
}
