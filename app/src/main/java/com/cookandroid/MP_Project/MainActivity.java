package com.cookandroid.MP_Project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button btn_star, btn_centom, btn_sang_ga, btn_DB;
    static final int FILE_READ_PERMISSION=1;
    static final int FILE_WRITE_PERMISSION=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int Read_permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE);
        int Write_permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if(ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.READ_EXTERNAL_STORAGE))
        {
            Toast.makeText(getApplicationContext(),"DB 백업과 복구기능을 위하여 권한이 필요합니다.",Toast.LENGTH_SHORT).show();
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE}
                    ,FILE_READ_PERMISSION);
        }
        else
        {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE}
                    ,FILE_READ_PERMISSION);
        }


        btn_star = (Button) findViewById(R.id.btn_star);
        btn_star.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),SelectionActivity.class);
                intent.putExtra("type","star");
                startActivity(intent);
            }
        });

        btn_centom = (Button) findViewById(R.id.btn_centom);
        btn_centom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),SelectionActivity.class);
                intent.putExtra("type","centom");
                startActivity(intent);
            }
        });

        btn_sang_ga = (Button) findViewById(R.id.btn_sang_ga);
        btn_sang_ga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),ResultActivity.class);
                intent.putExtra("type","sang_ga");
                startActivity(intent);
            }
        });

        btn_DB = (Button) findViewById(R.id.btn_DB);
        btn_DB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),DBActivity.class);
                startActivity(intent);
            }
        });
    }
}

