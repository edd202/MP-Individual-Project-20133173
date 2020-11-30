package com.cookandroid.MP_Project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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

