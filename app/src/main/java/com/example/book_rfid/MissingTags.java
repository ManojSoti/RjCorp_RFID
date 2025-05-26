package com.example.book_rfid;

import static com.example.book_rfid.ReadFragment.missingtagsobj;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

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

public class MissingTags extends AppCompatActivity {

    Adapter5 adapter5;
    List<ProductStatus> productStatusList;
    TextView missedTags;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_missing_tags);

        missedTags = findViewById(R.id.export1);
        ListView Totalexceltags = findViewById(R.id.LvTags3);

        // Collect products where any of the 3 tags is "Not Found"
        productStatusList = new ArrayList<>();

        for (ProductStatus status : missingtagsobj) {
            boolean isBoxMissing = !status.isBoxFound;
            boolean isLeftMissing = !status.isLeftFound;
            boolean isRightMissing = !status.isRightFound;

            if (isBoxMissing || isLeftMissing || isRightMissing) {
                productStatusList.add(status);
            }
        }

        Log.d("MissingTags", "Total items with any missing tags: " + productStatusList.size());

        adapter5 = new Adapter5(this, productStatusList);
        Totalexceltags.setAdapter(adapter5);

        missedTags.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                exportMissingTags();
            }
        });
    }

    private void exportMissingTags() {
        Date todaysDate = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        String date = format.format(todaysDate);
        String Fnamexls = date + ".xls";

        File sdCard = Environment.getExternalStorageDirectory();
        File directory = new File(sdCard.getAbsolutePath() + "/bookrfid");
        directory.mkdirs();

        File file = new File(directory, Fnamexls);

        WorkbookSettings wbSettings = new WorkbookSettings();
        wbSettings.setLocale(new Locale("en", "EN"));

        try {
            WritableWorkbook workbook = jxl.Workbook.createWorkbook(file, wbSettings);
            WritableSheet sheet = workbook.createSheet("Missing_Tags", 0);

            // Header row
            sheet.addCell(new Label(0, 0, "Product Name"));
            sheet.addCell(new Label(1, 0, "Box"));
            sheet.addCell(new Label(2, 0, "Left"));
            sheet.addCell(new Label(3, 0, "Right"));

            int row = 1;
            for (ProductStatus status : productStatusList) {
                sheet.addCell(new Label(0, row, status.getProductTitle()));
                sheet.addCell(new Label(1, row, status.getBoxEPC()));
                sheet.addCell(new Label(2, row, status.getLeftEPC()));
                sheet.addCell(new Label(3, row, status.getRightEPC()));
                row++;
            }

            workbook.write();
            workbook.close();

            Toast.makeText(this, "File downloaded to Internal Storage/bookrfid/" + Fnamexls, Toast.LENGTH_SHORT).show();

        } catch (IOException | WriteException e) {
            e.printStackTrace();
            Toast.makeText(this, "Export failed: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
}
