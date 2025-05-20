package com.example.book_rfid;

import static com.example.book_rfid.History.datee;
import static com.example.book_rfid.History.found_tags;
import static com.example.book_rfid.History.missed_tags;
import static com.example.book_rfid.History.total_tags;
import static com.example.book_rfid.History.unknown_tags;
import static com.example.book_rfid.ReadFragment.count_found;
import static com.example.book_rfid.ReadFragment.count_missing;
import static com.example.book_rfid.ReadFragment.count_total;
import static com.example.book_rfid.ReadFragment.count_unknown;
import static com.example.book_rfid.ReadFragment.excelTags;
import static com.example.book_rfid.ReadFragment.history_found;
import static com.example.book_rfid.ReadFragment.history_missing;
import static com.example.book_rfid.ReadFragment.history_total;
import static com.example.book_rfid.ReadFragment.history_unknown;
import static com.example.book_rfid.ReadFragment.missingtags;
import static com.example.book_rfid.ReadFragment.scan;
import static com.example.book_rfid.ReadFragment.unknown;

import static jxl.Workbook.createWorkbook;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import jxl.WorkbookSettings;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class Adapter3 extends BaseAdapter {
    private LayoutInflater mInflater;
    private List<String> mlist1,mlist2,mlist3,mlist4,mlist5;
    private Context mcontext;


    public Adapter3(Context context,List<String> list,List<String>list2,List<String>list3,List<String>list4,List<String>list5) {
        this.mcontext=context;
        this.mInflater = LayoutInflater.from(context);
        this.mlist1=list;
        this.mlist2=list2;
        this.mlist3=list3;
        this.mlist4=list4;
        this.mlist5=list5;
    }

    @Override
    public int getCount() {
        return mlist1.size();
    }

    @Override
    public Object getItem(int position) {
        return mlist1.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    static class ViewHolder{
        protected TextView date;
       protected TextView mTotalTags;
         protected  TextView  mFoundTags;
         protected TextView mMissingTags;
         protected TextView mUnknownTags;
         protected ImageView excel;
    }
    @SuppressLint("InflateParams")
    @Override
    public View getView(int position, View view, ViewGroup parent) {
        Adapter3.ViewHolder viewHolder=null;
        if (view==null){
            view=mInflater.inflate(R.layout.table,null,true);
            viewHolder = new Adapter3.ViewHolder();
            viewHolder.date=(TextView)view.findViewById(R.id.date);
            viewHolder.mTotalTags=(TextView)view.findViewById(R.id.total_count);
           viewHolder.mFoundTags=(TextView)view.findViewById(R.id.found_count);
           viewHolder. mMissingTags=(TextView)view.findViewById(R.id.missing_count);
           viewHolder.mUnknownTags=(TextView)view.findViewById(R.id.unknown_count);
            viewHolder.excel=(ImageView)view.findViewById(R.id.excelimg);

            view.setTag(viewHolder);
            view.setTag(R.id.date,viewHolder.date);
            view.setTag(R.id.total_count, viewHolder.mTotalTags);
            view.setTag(R.id.found_count,viewHolder.mFoundTags);
            view.setTag(R.id.missing_count,viewHolder.mMissingTags);
            view.setTag(R.id.unknown_count,viewHolder.mUnknownTags);
            view.setTag(R.id.excelimg,viewHolder.excel);
        }else {
            viewHolder = (Adapter3.ViewHolder) view.getTag();
        }
        try {
            viewHolder.date.setTag(position);
            viewHolder.date.setText(mlist1.get(position));
            viewHolder.mTotalTags.setTag(position);
            viewHolder.mTotalTags.setText(mlist2.get(position));
            viewHolder.mFoundTags.setTag(position);
            viewHolder.mFoundTags.setText(mlist3.get(position));
            viewHolder.mMissingTags.setTag(position);
            viewHolder.mMissingTags.setText(mlist4.get(position));
            viewHolder.mUnknownTags.setTag(position);
            viewHolder.mUnknownTags.setText(mlist5.get(position));
            viewHolder.excel.setTag(position);

            viewHolder.excel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    export2();
                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }
        // viewHolder.tvTagCount.setText(mlist.get(position));
        //viewHolder.tvTagRssi.setText(mlist.get(position));

        return view;
    }

    public void export2() {

        Date todaysdate = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = format.format(todaysdate);


        String Fnamexls = date + ".xls";

        File sdCard = Environment.getExternalStorageDirectory();

        File directory = new File(sdCard.getAbsolutePath() + "/bookrfid");
        directory.mkdirs();

        File file = new File(directory, Fnamexls);

        WorkbookSettings wbSettings = new WorkbookSettings();
        //Workbook wb;
        wbSettings.setLocale(new Locale("en", "EN"));

        WritableWorkbook workbook;
        try {
            int a = 1;
            Label label, label1 = null;

            workbook = createWorkbook(file, wbSettings);
            //wb= WorkbookFactory.create(file, String.valueOf(wbSettings));

            WritableSheet sheet =  workbook.createSheet("First_Sheet",0);
            List<String> date_e=new ArrayList<>();
            List<String> totalTags=new ArrayList<>();
            List<String> found=new ArrayList<>();
            List<String> missingtags2=new ArrayList<>();
            List<String> unkown=new ArrayList<>();

            date_e.add("Date");
            totalTags.add("TotalTags");
            found.add("FoundTags");
            missingtags2.add("MissingTags");
            unkown.add("UnkownTags");

            // String c=tag.trim();
            date_e.addAll(datee);

            for (int c=0;c<date_e.size();c++){
                String temp=date_e.get(c);
                label=new Label(0,c,temp);
                sheet.addCell(label);
            }
            //String c=tag.trim();
            totalTags.addAll(total_tags);

            for (int c=0;c<totalTags.size();c++){
                String temp=totalTags.get(c);
                label=new Label(1,c,temp);
                sheet.addCell(label);
            }

            //String c=tag.trim();
            found.addAll(found_tags);

            for (int c=0;c<found.size();c++){
                String temp=found.get(c);
                label=new Label(2,c,temp);
                sheet.addCell(label);
            }

            //String c=tag.trim();
            missingtags2.addAll(missed_tags);

            for (int c=0;c<missingtags2.size();c++){
                String temp=missingtags2.get(c);
                label=new Label(3,c,temp);
                sheet.addCell(label);
            }

            //String c=tag.trim();
            unkown.addAll(unknown_tags);

            for (int c=0;c<unkown.size();c++){
                String temp=unkown.get(c);
                label=new Label(4,c,temp);
                sheet.addCell(label);
            }


            workbook.write();
            workbook.close();

            Toast.makeText(mcontext, "File has been downloaded in Internal Storage/bookrfid/" + Fnamexls, Toast.LENGTH_SHORT).show();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (RowsExceededException e) {
            e.printStackTrace();
        } catch (WriteException e) {
            e.printStackTrace();
        }
    }
}
