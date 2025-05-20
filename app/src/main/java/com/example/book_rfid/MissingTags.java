package com.example.book_rfid;

import static com.example.book_rfid.ReadFragment.missingtags;
import static com.example.book_rfid.ReadFragment.scan;

import static jxl.Workbook.createWorkbook;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
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

public class MissingTags extends AppCompatActivity {
    Adapter5 adapter5;
    List<String> list3;
    TextView missedTags;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_missing_tags);
            missedTags=(TextView)findViewById(R.id.export1);
        @SuppressLint({"MissingInflatedId", "LocalSuppress"})
        ListView Totalexceltags=(ListView) findViewById(R.id.LvTags3);
        list3=new ArrayList<>(missingtags);
        Log.d("list", String.valueOf(list3.size()));
        adapter5=new Adapter5(this,list3);
        //Log.d("tags", String.valueOf(ReadFragment.excelTags.size()));

        Totalexceltags.setAdapter(adapter5);

        missedTags.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               exportmissingTags();
            }
        });
    }


    private void exportmissingTags(){
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

            List<String> missingtag=new ArrayList<>(missingtags);


            List<String> missingtags2=new ArrayList<>();



            missingtags2.add("MissingTags");





            for (int b=0;b<missingtags.size();b++){
                String tag=missingtag.get(b);
                String c=tag.trim();
                missingtags2.add(c);
            }

            for (int c=0;c<missingtags2.size();c++){
                String temp=missingtags2.get(c);
                label=new Label(0,c,temp);
                sheet.addCell(label);
            }


            /*Map<String,String> missngtags3=new LinkedHashMap<>();
            missngtags3.put("EPC No","Product Name");
            for (int b=0;b<missingtags2.size();b++){
                String tag=missingtags2.get(b);
                String c=tag.trim();
             String g =c.replace("                "," ");
             Log.d("goo",g);
                String[] tag1=g.split(" ");
                Log.d("klm",tag1[0]);
                missngtags3.put(tag1[0],tag1[1]);
                Log.d("jkl", String.valueOf(missngtags3.get(tag1[0])));
                tag1=null;
            }

            int count=0;
            for(Map.Entry<String,String> mt:missngtags3.entrySet()){
                String temp1=null;

                temp1=mt.getKey();
                label=new Label(0,count,temp1);
                sheet.addCell(label);
                count++;
            }

            int count1=0;
            for(Map.Entry<String,String> mt:missngtags3.entrySet()){
                String temp2=null;

                temp2=mt.getValue();
                label=new Label(1,count1,temp2);
                sheet.addCell(label);
                count1++;
            }*/


            /*for (int i = 0; i <= missngtags3.size() - 1; i++) {
               // HashMap<String, String> temp = new HashMap<String, String>();
              //  temp = mContext.tagList.get(i);
                String temp=null;
                temp=missngtags3.get(i);
               // String tempStr = temp.get("tagEpcTID");
                String tempStr = temp;
                label = new Label(0, i, tempStr);
                Log.d("CheckArray", i + "::" + missingtags2.get(i));


                //Log.d("Mi", String.valueOf(mContext.tagList.size()));
                sheet.addCell(label);

            }*/

         /*   for (int i = 0; i <= missingtags2.size() - 1; i++) {
                // HashMap<String, String> temp = new HashMap<String, String>();
                //  temp = mContext.tagList.get(i);
                String temp=null;
                temp=missingtags2.get(i);
                // String tempStr = temp.get("tagEpcTID");
                String tempStr = temp;
                label = new Label(0, i, tempStr);
                Log.d("CheckArray", i + "::" + missingtags2.get(i));


                //Log.d("Mi", String.valueOf(mContext.tagList.size()));
                sheet.addCell(label);

            }*/

            workbook.write();
            workbook.close();

            Toast.makeText(this, "File has been downloaded in Internal Storage/bookrfid/" + Fnamexls, Toast.LENGTH_SHORT).show();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (RowsExceededException e) {
            e.printStackTrace();
        } catch (WriteException e) {
            e.printStackTrace();
        }
    }
    }
