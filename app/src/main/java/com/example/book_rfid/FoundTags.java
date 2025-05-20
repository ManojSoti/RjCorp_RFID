package com.example.book_rfid;

import static com.example.book_rfid.ReadFragment.scan;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FoundTags extends AppCompatActivity {

    Adapter4 adapter4;
    List<ProductStatus> productStatusList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_found_tags);

        @SuppressLint("MissingInflatedId")
        ListView listView = findViewById(R.id.LvTags2);

        productStatusList = new ArrayList<>();
        HashMap<String, ProductStatus> productMap = new HashMap<>();

        for (String tag : scan) {
            String baseProduct;
            boolean isLeft = tag.endsWith("-L");
            boolean isRight = tag.endsWith("-R");

            if (isLeft || isRight) {
                baseProduct = tag.substring(0, tag.length() - 2); // Remove "-L" or "-R"
            } else {
                baseProduct = tag; // BOX
            }

            ProductStatus status = productMap.get(baseProduct);
            if (status == null) {
                status = new ProductStatus(baseProduct, false, false, false);
            }

            if (isLeft) {
                status.isLeftFound = true;
            } else if (isRight) {
                status.isRightFound = true;
            } else {
                status.isBoxFound = true;
            }

            productMap.put(baseProduct, status);
        }

        // Only add fully found sets
        for (ProductStatus status : productMap.values()) {
            if (status.isBoxFound && status.isLeftFound && status.isRightFound) {
                productStatusList.add(status);
            }
        }

        Log.d("ProductStatus", "Fully matched products: " + productStatusList.size());

        adapter4 = new Adapter4(this, productStatusList);
        listView.setAdapter(adapter4);
    }
}
