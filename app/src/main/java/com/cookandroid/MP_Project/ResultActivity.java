package com.cookandroid.MP_Project;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ResultActivity extends AppCompatActivity implements View.OnClickListener {

    ImageView image;
    Button btn_main, btn_pre, btn_next;
    TextView text_size, text_direction, text_price, text_lease, text_loc, text_phone, text_option, text_etc,
             db_size, db_direction, db_price, db_lease, db_loc, db_phone, db_option, db_etc;

    int i=0;
    long nowIndex;
    String SIZE, DIRECTION, PRICE, JUNWALMA = "", LOC, PHONE, OPTION, ETC;
    String sort = "size";
    String Where="direction=";

    ArrayAdapter<String> arrayAdapter;
    static ArrayList<String> arrayIndex = new ArrayList<String>();
    static ArrayList<String> arrayData = new ArrayList<String>();
    private DBOpenHelper mDBOpenHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        Intent intent = getIntent();
        String SIZE = intent.getStringExtra("SIZE");
        String JUNWALMA = intent.getStringExtra("JUNWALMA");
        String DIRECTION = intent.getStringExtra("DIRECTION");

        if(DIRECTION.equals("스타힐스"))
        {
            Where=Where.concat("'서희스타힐스'");
            if(SIZE.equals(""))
            {
                if(JUNWALMA.equals("전세"))
                {
                    Where=Where.concat(" and junwalma='전세'");
                }else if(JUNWALMA.equals("월세"))
                {
                    Where=Where.concat(" and junwalma='월세'");
                }else if(JUNWALMA.equals("매매"))
                {
                    Where=Where.concat(" and junwalma='매매'");
                }else
                {

                }
            }
            else if(SIZE.equals("24"))
            {
                Where=Where.concat(" and size='24'");
                if(JUNWALMA.equals("전세"))
                {
                    Where=Where.concat(" and junwalma='전세'");
                }else if(JUNWALMA.equals("월세"))
                {
                    Where=Where.concat(" and junwalma='월세'");
                }else if(JUNWALMA.equals("매매"))
                {
                    Where=Where.concat(" and junwalma='매매'");
                }else
                {

                }
            }
            else if(SIZE.equals("30"))
            {
                Where=Where.concat(" and size='30'");
                if(JUNWALMA.equals("전세"))
                {
                    Where=Where.concat(" and junwalma='전세'");
                }else if(JUNWALMA.equals("월세"))
                {
                    Where=Where.concat(" and junwalma='월세'");
                }else if(JUNWALMA.equals("매매"))
                {
                    Where=Where.concat(" and junwalma='매매'");
                }else
                {

                }
            }
            else
            {
                Where=Where.concat(" and size='34'");
                if(JUNWALMA.equals("전세"))
                {
                    Where=Where.concat(" and junwalma='전세'");
                }else if(JUNWALMA.equals("월세"))
                {
                    Where=Where.concat(" and junwalma='월세'");
                }else if(JUNWALMA.equals("매매"))
                {
                    Where=Where.concat(" and junwalma='매매'");
                }else
                {

                }
            }
        }
        else if(DIRECTION.equals("센텀"))
        {
            Where=Where.concat("'센텀시티'");
            if(SIZE.equals(""))
            {
                if(JUNWALMA.equals("전세"))
                {
                    Where=Where.concat(" and junwalma='전세'");
                }else if(JUNWALMA.equals("월세"))
                {
                    Where=Where.concat(" and junwalma='월세'");
                }else if(JUNWALMA.equals("매매"))
                {
                    Where=Where.concat(" and junwalma='매매'");
                }else
                {

                }
            }
            else if(SIZE.equals("25"))
            {
                Where=Where.concat(" and size='25'");
                if(JUNWALMA.equals("전세"))
                {
                    Where=Where.concat(" and junwalma='전세'");
                }else if(JUNWALMA.equals("월세"))
                {
                    Where=Where.concat(" and junwalma='월세'");
                }else if(JUNWALMA.equals("매매"))
                {
                    Where=Where.concat(" and junwalma='매매'");
                }else
                {

                }
            }
            else if(SIZE.equals("29"))
            {
                Where=Where.concat(" and size='29'");
                if(JUNWALMA.equals("전세"))
                {
                    Where=Where.concat(" and junwalma='전세'");
                }else if(JUNWALMA.equals("월세"))
                {
                    Where=Where.concat(" and junwalma='월세'");
                }else if(JUNWALMA.equals("매매"))
                {
                    Where=Where.concat(" and junwalma='매매'");
                }else
                {

                }
            }
            else
            {
                Where=Where.concat(" and size='33'");
                if(JUNWALMA.equals("전세"))
                {
                    Where=Where.concat(" and junwalma='전세'");
                }else if(JUNWALMA.equals("월세"))
                {
                    Where=Where.concat(" and junwalma='월세'");
                }else if(JUNWALMA.equals("매매"))
                {
                    Where=Where.concat(" and junwalma='매매'");
                }else
                {

                }
            }
        }
        else
        {
            Where=Where.concat("'상가'");
        }


        image = (ImageView) findViewById(R.id.image);

        btn_main = (Button) findViewById(R.id.btn_main);
        btn_main.setOnClickListener(this);
        btn_pre = (Button) findViewById(R.id.btn_pre);
        btn_pre.setOnClickListener(this);
        btn_next = (Button) findViewById(R.id.btn_next);
        btn_next.setOnClickListener(this);

        text_size = (TextView) findViewById(R.id.text_size);
        text_direction = (TextView) findViewById(R.id.text_direction);
        text_price = (TextView) findViewById(R.id.text_price);
        text_lease = (TextView) findViewById(R.id.text_lease);
        text_loc = (TextView) findViewById(R.id.text_loc);
        text_phone = (TextView) findViewById(R.id.text_phone);
        text_option = (TextView) findViewById(R.id.text_option);
        text_etc = (TextView) findViewById(R.id.text_etc);

        db_size = (TextView) findViewById(R.id.db_size);
        db_direction = (TextView) findViewById(R.id.db_direction);
        db_price = (TextView) findViewById(R.id.db_price);
        db_lease = (TextView) findViewById(R.id.db_lease);
        db_loc = (TextView) findViewById(R.id.db_loc);
        db_phone = (TextView) findViewById(R.id.db_phone);
        db_option = (TextView) findViewById(R.id.db_option);
        db_etc = (TextView) findViewById(R.id.db_etc);

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
        try {
            mDBOpenHelper = new DBOpenHelper(this);
            mDBOpenHelper.open();
            mDBOpenHelper.create();
            Cursor iCursor = mDBOpenHelper.selectColumns(Where);
            iCursor.moveToFirst();
            String tempSize = iCursor.getString(iCursor.getColumnIndex("size"));
            db_size.setText(tempSize + "평형");
            String tempDirection = iCursor.getString(iCursor.getColumnIndex("direction"));
            db_direction.setText(tempDirection);
            String tempPrice = iCursor.getString(iCursor.getColumnIndex("price"));
            db_price.setText(tempPrice + " 만원");
            String tempJunWalMa = iCursor.getString(iCursor.getColumnIndex("junwalma"));
            db_lease.setText(tempJunWalMa);
            String tempLoc = iCursor.getString(iCursor.getColumnIndex("loc"));
            db_loc.setText(tempLoc);
            String tempPhone = iCursor.getString(iCursor.getColumnIndex("phone"));
            db_phone.setText(tempPhone);
            String tempOption = iCursor.getString(iCursor.getColumnIndex("option"));
            db_option.setText(tempOption);
            String tempEtc = iCursor.getString(iCursor.getColumnIndex("etc"));
            db_etc.setText(tempEtc);
        } catch (Exception e)
        {
            Toast.makeText(getApplicationContext(),"해당하는 건물이 없습니다",Toast.LENGTH_SHORT).show();
            Intent intent1 = new Intent(getApplicationContext(),MainActivity.class);
            startActivity(intent1);
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case  R.id.btn_main:
            {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
                break;
            }

            case R.id.btn_pre:
            {
                mDBOpenHelper = new DBOpenHelper(this);
                mDBOpenHelper.open();
                mDBOpenHelper.create();
                Cursor iCursor = mDBOpenHelper.selectColumns(Where);

                if(i==-1)
                {
                    i=0;
                    break;
                }
                if(i==0)
                {
                    Toast.makeText(getApplicationContext(),"첫 건물입니다.",Toast.LENGTH_SHORT).show();
                    break;
                }
                i--;

                iCursor.moveToPosition(i);
                String tempSize =       iCursor.getString(iCursor.getColumnIndex("size"));
                db_size.setText(tempSize+"평형");
                String tempDirection =  iCursor.getString(iCursor.getColumnIndex("direction"));
                db_direction.setText(tempDirection);
                String tempPrice =      iCursor.getString(iCursor.getColumnIndex("price"));
                db_price.setText(tempPrice+" 만원");
                String tempJunWalMa =   iCursor.getString(iCursor.getColumnIndex("junwalma"));
                db_lease.setText(tempJunWalMa);
                String tempLoc =        iCursor.getString(iCursor.getColumnIndex("loc"));
                db_loc.setText(tempLoc);
                String tempPhone =      iCursor.getString(iCursor.getColumnIndex("phone"));
                db_phone.setText(tempPhone);
                String tempOption =     iCursor.getString(iCursor.getColumnIndex("option"));
                db_option.setText(tempOption);
                String tempEtc =        iCursor.getString(iCursor.getColumnIndex("etc"));
                db_etc.setText(tempEtc);
                mDBOpenHelper.close();
                if(tempDirection.equals("서희스타힐스"))
                {
                    if(tempSize.equals("24"))
                    {
                        image.setImageResource(R.drawable.star_24);
                    }else if(tempSize.equals("30"))
                    {
                        image.setImageResource(R.drawable.star_30);
                    }else
                    {
                        image.setImageResource(R.drawable.star_34);
                    }
                }else if(tempDirection.equals("센텀시티"))
                {
                    if(tempSize.equals("25"))
                    {
                        image.setImageResource(R.drawable.centom_25);
                    }else if(tempSize.equals("29"))
                    {
                        image.setImageResource(R.drawable.centom_29);
                    }else
                    {
                        image.setImageResource(R.drawable.centom_33);
                    }
                }else
                {
                    image.setImageResource(R.drawable.sang_ga);
                }
                break;
            }

            case R.id.btn_next:
            {

                mDBOpenHelper = new DBOpenHelper(this);
                mDBOpenHelper.open();
                mDBOpenHelper.create();
                Cursor iCursor = mDBOpenHelper.selectColumns(Where);
                if(i==iCursor.getCount()-1)
                {
                    Toast.makeText(getApplicationContext(),"마지막 건물입니다.",Toast.LENGTH_SHORT).show();
                    i=iCursor.getCount()-2;
                    break;
                }

                i++;
                iCursor.moveToPosition(i);
                String tempSize =       iCursor.getString(iCursor.getColumnIndex("size"));
                db_size.setText(tempSize+"평형");
                String tempDirection =  iCursor.getString(iCursor.getColumnIndex("direction"));
                db_direction.setText(tempDirection);
                String tempPrice =      iCursor.getString(iCursor.getColumnIndex("price"));
                db_price.setText(tempPrice+" 만원");
                String tempJunWalMa =   iCursor.getString(iCursor.getColumnIndex("junwalma"));
                db_lease.setText(tempJunWalMa);
                String tempLoc =        iCursor.getString(iCursor.getColumnIndex("loc"));
                db_loc.setText(tempLoc);
                String tempPhone =      iCursor.getString(iCursor.getColumnIndex("phone"));
                db_phone.setText(tempPhone);
                String tempOption =     iCursor.getString(iCursor.getColumnIndex("option"));
                db_option.setText(tempOption);
                String tempEtc =        iCursor.getString(iCursor.getColumnIndex("etc"));
                db_etc.setText(tempEtc);

                if(tempDirection.equals("서희스타힐스"))
                {
                    if(tempSize.equals("24"))
                    {
                        image.setImageResource(R.drawable.star_24);
                    }else if(tempSize.equals("30"))
                    {
                        image.setImageResource(R.drawable.star_30);
                    }else
                    {
                        image.setImageResource(R.drawable.star_34);
                    }
                }else if(tempDirection.equals("센텀시티"))
                {
                    if(tempSize.equals("25"))
                    {
                        image.setImageResource(R.drawable.centom_25);
                    }else if(tempSize.equals("29"))
                    {
                        image.setImageResource(R.drawable.centom_29);
                    }else
                    {
                        image.setImageResource(R.drawable.centom_33);
                    }
                }else
                {
                    image.setImageResource(R.drawable.sang_ga);
                }
                break;

           }

            }
        }
}
