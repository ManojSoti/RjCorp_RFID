package com.example.book_rfid;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class bookinfo {
    private ArrayList<HashMap<String, String>> tagList = null;
    private long time;
    private int count=0;
    private int tagNumber=0;
    private int selectIndex=-1;
    private List<String> tempDatas;
    private String selectItem;
   // String name;
    public bookinfo() {
    }




    public ArrayList<HashMap<String, String>> getTagList() {
        return tagList;
    }

    public void setTagList(ArrayList<HashMap<String, String>> tagList) {
        this.tagList = tagList;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getTagNumber() {
        return tagNumber;
    }

    public void setTagNumber(int tagNumber) {
        this.tagNumber = tagNumber;
    }

    public int getSelectIndex() {
        return selectIndex;
    }

    public void setSelectIndex(int selectIndex) {
        this.selectIndex = selectIndex;
    }

    public List<String> getTempDatas() {
        return tempDatas;
    }

    public void setTempDatas(List<String> tempDatas) {
        this.tempDatas = tempDatas;
    }

    public String getSelectItem() {
        return selectItem;
    }

    public void setSelectItem(String selectItem) {
        this.selectItem = selectItem;
    }




}
