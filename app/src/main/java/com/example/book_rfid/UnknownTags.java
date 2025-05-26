package com.example.book_rfid;

import static com.example.book_rfid.ReadFragment.scan;
import static com.example.book_rfid.ReadFragment.epcToTitleMap;
import static com.example.book_rfid.ReadFragment.unknown;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class UnknownTags extends AppCompatActivity {

    Adapter2 adapter2;
    List<ProductStatus> unknownProductList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unknown_tags); // Make sure this layout exists

        @SuppressLint("MissingInflatedId")
        ListView listView = findViewById(R.id.LvTags4); // Update the ID if needed

        unknownProductList = new ArrayList<>();
        HashMap<String, ProductStatus> productMap = new HashMap<>();

        for (String tag : unknown) {
            String baseEpc;
            boolean isLeft = tag.endsWith("-L");
            boolean isRight = tag.endsWith("-R");

            if (isLeft || isRight) {
                baseEpc = tag.substring(0, tag.length() - 2);
            } else {
                baseEpc = tag;
            }

            String productTitle = epcToTitleMap.get(baseEpc);
            if (productTitle == null) {
                Log.d("UnknownTags", "⚠️ No product title for EPC: " + baseEpc);
                continue;
            }

            ProductStatus status = productMap.get(baseEpc);
            if (status == null) {
                status = new ProductStatus(productTitle, false, false, false);
            }

            if (isLeft) {
                status.isLeftFound = true;
            } else if (isRight) {
                status.isRightFound = true;
            } else {
                status.isBoxFound = true;
            }

            productMap.put(baseEpc, status);
        }

        // ❌ Only add incomplete ones (any part is missing)
        for (ProductStatus status : productMap.values()) {
            if (!(status.isBoxFound && status.isLeftFound && status.isRightFound)) {
                unknownProductList.add(status);
            }
        }

        Log.d("UnknownTags", "❌ Incomplete products: " + unknownProductList.size());

        adapter2 = new Adapter2(this, unknownProductList);
        listView.setAdapter(adapter2);

//        if (ReadFragment.mFoundTags != null) {
//            ReadFragment.mFoundTags.setText(unknownProductList.size());
//        }
    }
}
