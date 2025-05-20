package com.example.book_rfid;

import static com.example.book_rfid.ReadFragment.excelTags;
import static com.example.book_rfid.ReadFragment.missingtags;
import static com.example.book_rfid.ReadFragment.scan;
import static com.example.book_rfid.ReadFragment.unknown;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.media.AudioManager;
import android.media.SoundPool;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends BaseActivity {

    ImageView button;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        button=findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clear();
                Intent intent=new Intent(getApplicationContext(),Read.class);
                startActivity(intent);
            }
        });


    }

  public  void clear(){
        excelTags.clear();
      missingtags.clear();
      unknown.clear();
      scan.clear();
  }
}