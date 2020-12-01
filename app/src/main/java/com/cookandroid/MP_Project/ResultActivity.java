package com.cookandroid.MP_Project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ResultActivity extends AppCompatActivity implements View.OnClickListener {

    ImageView image;
    Button btn_main, btn_pre, btn_next;
    TextView text_size, text_direction, text_price, text_lease, text_loc, text_phone, text_option, text_etc,
             db_size, db_direction, db_price, db_lease, db_loce, db_phone, db_option, db_etc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        Intent intent = getIntent();
        String SIZE = intent.getStringExtra("SIZE");
        String JUNWALMA = intent.getStringExtra("JUNWALMA");
        String DIRECTION = intent.getStringExtra("DIRECTION");

        image = (ImageView) findViewById(R.id.image);

        System.out.println("사이즈 : " + SIZE);
        System.out.println("판매형태 : " + JUNWALMA);
        System.out.println("건물종류 : " + DIRECTION);

        if(DIRECTION.equals("스타힐스"))
        {
            if(SIZE.equals("24"))
            {
                image.setImageResource(R.drawable.star_24);
            }
            else if(SIZE.equals("30"))
            {
                image.setImageResource(R.drawable.star_30);
            }
            else {
                image.setImageResource(R.drawable.star_34);
            }
        }
        else if(DIRECTION.equals("센텀"))
        {
            if(SIZE.equals("25"))
            {
                image.setImageResource(R.drawable.centom_25);
            }
            else if(SIZE.equals("29"))
            {
                image.setImageResource(R.drawable.centom_29);
            }
            else {
                image.setImageResource(R.drawable.centom_33);
            }
        }
        else
        {
            image.setImageResource(R.drawable.sang_ga);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

        }
        }
}
