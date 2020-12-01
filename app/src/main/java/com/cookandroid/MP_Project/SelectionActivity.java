package com.cookandroid.MP_Project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SelectionActivity extends AppCompatActivity implements View.OnClickListener{

    TextView text_image_name,text_name, text_size;
    CheckBox check_lease,check_view,A1,A2,A3,B1,B2,B3;
    Button btn_ok;
    ImageView image;
    String SIZE, JUNWALMA;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);

        text_image_name = (TextView) findViewById(R.id.text_image_name);
        text_name = (TextView) findViewById(R.id.text_name);
        text_size = (TextView) findViewById(R.id.text_size);
        check_lease = (CheckBox) findViewById(R.id.check_lease);
        check_lease.setOnClickListener(this);
        check_view = (CheckBox) findViewById(R.id.check_view);
        check_view.setOnClickListener(this);
        btn_ok = (Button) findViewById(R.id.btn_ok);
        btn_ok.setOnClickListener(this);
        image = (ImageView) findViewById(R.id.image);
        A1 = (CheckBox) findViewById(R.id.btn_A1);
        A1.setOnClickListener(this);
        A2 = (CheckBox) findViewById(R.id.btn_A2);
        A2.setOnClickListener(this);
        A3 = (CheckBox) findViewById(R.id.btn_A3);
        A3.setOnClickListener(this);
        B1 = (CheckBox) findViewById(R.id.btn_B1);
        B1.setOnClickListener(this);
        B2 = (CheckBox) findViewById(R.id.btn_B2);
        B2.setOnClickListener(this);
        B3 = (CheckBox) findViewById(R.id.btn_B3);
        B3.setOnClickListener(this);

        Intent intent = getIntent();
        String type = intent.getStringExtra("type");

        if (type.equals("스타힐스")) {
            image.setImageResource(R.drawable.starhills);
            text_image_name.setText("조감도");
            text_name.setText("서희 스타 힐스");
            A1.setText("24평");
            A2.setText("30평");
            A3.setText("34평");
        } else if (type.equals("센텀")) {
            image.setImageResource(R.drawable.centom);
            text_image_name.setText("조감도");
            text_name.setText("센텀시티");
            A1.setText("25평");
            A2.setText("29평");
            A3.setText("33평");
        } else {
            Toast.makeText(getApplicationContext(), "잘못된 접근입니다.", Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    @Override
    public void onClick(View v) {

        Intent intent = getIntent();
        String type = intent.getStringExtra("type");

        switch (v.getId()){
            case R.id.check_view:
            {
                text_image_name.setText("조감도");
                if(type.equals("스타힐스"))
                {
                    image.setImageResource(R.drawable.starhills);
                }
                image.setImageResource(R.drawable.centom);
                A1.setChecked(false);
                A2.setChecked(false);
                A3.setChecked(false);
                break;
            }

            case R.id.btn_A1:
            {
                text_image_name.setText("평면도");
                check_view.setChecked(false);
                A2.setChecked(false);
                A3.setChecked(false);
                if(type.equals("스타힐스"))
                {
                    text_name.setText("24평형");
                    image.setImageResource(R.drawable.star_24);
                    SIZE="24";
                }
                else
                {
                    text_name.setText("25평형");
                    image.setImageResource(R.drawable.centom_25);
                    SIZE="25";
                }
                break;
            }
            case R.id.btn_A2:
            {
                text_image_name.setText("평면도");
                check_view.setChecked(false);
                A1.setChecked(false);
                A3.setChecked(false);
                if(type.equals("스타힐스"))
                {
                    text_name.setText("30평형");
                    image.setImageResource(R.drawable.star_30);
                    SIZE="30";
                }
                else
                {
                    text_name.setText("29평형");
                    image.setImageResource(R.drawable.centom_29);
                    SIZE="29";
                }
                break;
            }
            case R.id.btn_A3:
            {
                text_image_name.setText("평면도");
                check_view.setChecked(false);
                A1.setChecked(false);
                A2.setChecked(false);
                if(type.equals("스타힐스"))
                {
                    text_name.setText("34평형");
                    image.setImageResource(R.drawable.star_34);
                    SIZE="34";
                }
                else
                {
                    text_name.setText("33평형");
                    image.setImageResource(R.drawable.centom_33);
                    SIZE="33";
                }
                break;
            }
            case R.id.check_lease:
            {
                if(check_lease.isChecked())
                {
                    B1.setVisibility(View.VISIBLE);
                    B2.setVisibility(View.VISIBLE);
                    B3.setVisibility(View.VISIBLE);
                }
                else
                {
                    B1.setVisibility(View.INVISIBLE);
                    B1.setChecked(false);
                    B2.setVisibility(View.INVISIBLE);
                    B2.setChecked(false);
                    B3.setVisibility(View.INVISIBLE);
                    B3.setChecked(false);
                }
                break;
            }
            case R.id.btn_B1:
            {
                B2.setChecked(false);
                B3.setChecked(false);
                JUNWALMA="전세";
                break;
            }
            case R.id.btn_B2:
            {
                B1.setChecked(false);
                B3.setChecked(false);
                JUNWALMA="월세";
                break;
            }
            case R.id.btn_B3:
            {
                B1.setChecked(false);
                B2.setChecked(false);
                JUNWALMA="매매";
                break;
            }
            case R.id.btn_ok:
            {
                Intent next = new Intent(getApplicationContext(),ResultActivity.class);
                next.putExtra("SIZE","");
                next.putExtra("JUNWALMA", "");
                next.putExtra("DIRECTION",type);
                if(A1.isChecked() || A2.isChecked() || A3.isChecked())
                {
                    next.putExtra("SIZE",SIZE);
                    if(check_lease.isChecked())
                    {
                        if(B1.isChecked() || B2.isChecked() || B3.isChecked())
                        {
                            next.putExtra("JUNWALMA", JUNWALMA);
                        }
                    }
                    startActivity(next);
                    break;
                }
                else if(check_lease.isChecked())
                {
                    if(B1.isChecked() || B2.isChecked() || B3.isChecked())
                    {
                        next.putExtra("JUNWALMA",JUNWALMA);
                        startActivity(next);
                        break;
                    }
                }
                Toast.makeText(getApplicationContext(),
                        "평수나 계약형태(전세, 월세, 매매)중 최소 하나를 선택해주세요.",
                        Toast.LENGTH_SHORT).show();
                break;
            }
        }
    }
}
