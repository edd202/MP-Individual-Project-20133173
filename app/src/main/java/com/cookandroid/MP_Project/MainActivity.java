package com.cookandroid.MP_Project;

import androidx.appcompat.app.AppCompatActivity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private static final String TAG = "Main";


    EditText edit_size;
    EditText edit_direction;
    EditText edit_price;
    EditText edit_loc;
    EditText edit_phone;
    EditText edit_option;
    EditText edit_etc;

    CheckBox check_junse;
    CheckBox check_walse;
    CheckBox check_mama;

    CheckBox check_size;
    CheckBox check_price;

    CheckBox check_starhills;
    CheckBox check_centom;
    CheckBox check_sang_ga;

    Button btn_update;
    Button btn_insert;
    Button btn_select;
    Button btn_delete;

    long nowIndex;
    String SIZE;
    String DIRECTION;
    String PRICE;
    String JUNWALMA = "";
    String LOC;
    String PHONE;
    String OPTION;
    String ETC;

    String sort = "size";//평수 시세

    ArrayAdapter<String> arrayAdapter;

    static ArrayList<String> arrayIndex = new ArrayList<String>();
    static ArrayList<String> arrayData = new ArrayList<String>();
    private DBOpenHelper mDBOpenHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_insert = (Button) findViewById(R.id.btn_insert);
        btn_insert.setOnClickListener(this);
        btn_select = (Button) findViewById(R.id.btn_select);
        btn_select.setOnClickListener(this);
        btn_update = (Button) findViewById(R.id.btn_update);
        btn_update.setOnClickListener(this);
        btn_delete = (Button) findViewById(R.id.btn_delete);
        btn_delete.setOnClickListener(this);

        edit_size = (EditText) findViewById(R.id.edit_size);
        edit_price = (EditText) findViewById(R.id.edit_price);
        edit_loc = (EditText) findViewById(R.id.edit_Loc);
        edit_phone = (EditText) findViewById(R.id.edit_phone);
        edit_option = (EditText) findViewById(R.id.edit_option);
        edit_etc = (EditText) findViewById(R.id.edit_etc);

        check_junse = (CheckBox) findViewById(R.id.check_junse);
        check_junse.setOnClickListener(this);
        check_walse = (CheckBox) findViewById(R.id.check_walse);
        check_walse.setOnClickListener(this);
        check_mama = (CheckBox) findViewById(R.id.check_mama);
        check_mama.setOnClickListener(this);

        check_size = (CheckBox) findViewById(R.id.check_size);
        check_size.setOnClickListener(this);
        check_price = (CheckBox) findViewById(R.id.check_price);
        check_price.setOnClickListener(this);

        check_starhills = (CheckBox) findViewById(R.id.check_starhills);
        check_starhills.setOnClickListener(this);
        check_centom = (CheckBox) findViewById(R.id.check_centom);
        check_centom.setOnClickListener(this);
        check_sang_ga = (CheckBox) findViewById(R.id.check_sang_ga);
        check_sang_ga.setOnClickListener(this);

        arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1);
        ListView listView = (ListView) findViewById(R.id.db_list_view);
        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(onClickListener);

        mDBOpenHelper = new DBOpenHelper(this);
        mDBOpenHelper.open();
        mDBOpenHelper.create();

        check_size.setChecked(true);
        showDatabase(sort);

        btn_insert.setEnabled(true);
        btn_update.setEnabled(false);
        btn_delete.setEnabled(false);

        check_starhills.setChecked(true);
        check_junse.setChecked(true);
    }

    public void setInsertMode(){
        edit_size.setText("");
        edit_price.setText("");
        edit_loc.setText("");
        edit_phone.setText("");
        edit_option.setText("");
        edit_etc.setText("");

        check_size.setChecked(false);
        check_price.setChecked(false);
        btn_insert.setEnabled(true);
        btn_update.setEnabled(false);
        btn_delete.setEnabled(false);
    }

    private AdapterView.OnItemClickListener onClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Log.e("On Click", "position = " + position);
            nowIndex = Long.parseLong(arrayIndex.get(position));
            Log.e("On Click", "nowIndex = " + nowIndex);
            Log.e("On Click", "Data: " + arrayData.get(position));
            String[] tempData = arrayData.get(position).split("\\s+");
            Log.e("On Click", "Split Result = " + tempData);

            edit_size.setText(tempData[2].trim());

            if(tempData[5].trim().equals("서희스타힐스")){
                check_starhills.setChecked(true);
                DIRECTION="서희스타힐스";
            }else if(tempData[5].trim().equals("센텀시티")){
                check_centom.setChecked(true);
                DIRECTION="센텀시티";
            }else if(tempData[5].trim().equals("상가")){
                check_sang_ga.setChecked(true);
                DIRECTION="상가";
            }

            edit_price.setText(tempData[8].trim());

            if(tempData[11].trim().equals("전세")){
                check_junse.setChecked(true);
                JUNWALMA = "전세";
            } else if (tempData[11].trim().equals("월세")){
                check_walse.setChecked(true);
                JUNWALMA = "월세";
                } else if(tempData[11].trim().equals("매매")){
                check_mama.setChecked(true);
                JUNWALMA = "매매";
                }

            edit_loc.setText(tempData[14].trim());
            edit_phone.setText(tempData[17].trim());
            edit_option.setText(tempData[20].trim());
            edit_etc.setText(tempData[23].trim());

            btn_insert.setEnabled(false);
            btn_update.setEnabled(true);
            btn_delete.setEnabled(true);
        }
    };

    public void showDatabase(String sort){
        Cursor iCursor = mDBOpenHelper.sortColumn(sort);
        Log.d("showDataBase", "DB Size: " + iCursor.getCount());
        arrayData.clear();
        arrayIndex.clear();
        while(iCursor.moveToNext()){
            String tempIndex = iCursor.getString(iCursor.getColumnIndex("_id"));

            String tempSize = "평수 : "+iCursor.getString(iCursor.getColumnIndex("size"))+" ";

            String tempDirection = "\n"+"종류 : "+iCursor.getString(iCursor.getColumnIndex("direction"))+" ";

            String tempPrice = "\n"+"시세 : "+iCursor.getString(iCursor.getColumnIndex("price"))+" ";

            String tempJunWalMa = "\n"+"판매형태 : "+iCursor.getString(iCursor.getColumnIndex("junwalma"))+" ";

            String tempLoc = "\n"+"위치 : "+iCursor.getString(iCursor.getColumnIndex("loc"))+" ";

            String tempPhone = "\n"+"전화번호 : "+iCursor.getString(iCursor.getColumnIndex("phone"))+" ";

            String tempOption = "\n"+"옵션 : "+iCursor.getString(iCursor.getColumnIndex("option"))+" ";

            String tempEtc = "\n"+"기타 : "+iCursor.getString(iCursor.getColumnIndex("etc"));

            String Result = tempSize + tempDirection + tempPrice + tempJunWalMa + tempLoc + tempPhone + tempOption + tempEtc;
            arrayData.add(Result);
            arrayIndex.add(tempIndex);
        }
        arrayAdapter.clear();
        arrayAdapter.addAll(arrayData);
        arrayAdapter.notifyDataSetChanged();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case  R.id.btn_insert:
            {
                SIZE = edit_size.getText().toString().trim();
                PRICE = edit_price.getText().toString().trim();
                LOC = edit_loc.getText().toString().trim();
                PHONE = edit_phone.getText().toString().trim();
                OPTION = edit_option.getText().toString().trim();
                ETC = edit_etc.getText().toString().trim();


                if(SIZE.equals("") || PRICE.equals("") || LOC.equals("") || PHONE.equals(""))
                {
                    Toast.makeText(getApplicationContext(),"빈칸을 모두 채워주세요",Toast.LENGTH_SHORT).show();
                    break;
                }
                if(edit_option.getText().toString().trim().equals("")){
                    OPTION="없음";
                } else {
                    OPTION = edit_option.getText().toString().trim();
                }
                if(edit_etc.getText().toString().trim().equals("")){
                    ETC="없음";
                } else {
                    ETC = edit_etc.getText().toString().trim();
                }
                mDBOpenHelper.open();
                mDBOpenHelper.insertColumn(SIZE, DIRECTION, PRICE, JUNWALMA, LOC, PHONE, OPTION, ETC);
                showDatabase(sort);
                setInsertMode();
                edit_size.requestFocus();
                edit_size.setCursorVisible(true);
                mDBOpenHelper.close();
                break;
            }

            case R.id.btn_update:
            {
                SIZE = edit_size.getText().toString().trim();
                PRICE = edit_price.getText().toString().trim();
                LOC = edit_loc.getText().toString().trim();
                PHONE = edit_phone.getText().toString().trim();
                OPTION = edit_option.getText().toString().trim();
                ETC = edit_etc.getText().toString().trim();
                if(SIZE.equals("") || PRICE.equals("") || LOC.equals("") || PHONE.equals("") )
                {
                    Toast.makeText(getApplicationContext(),"빈칸을 모두 채워주세요",Toast.LENGTH_SHORT).show();
                    break;
                }
                mDBOpenHelper.open();
                mDBOpenHelper.updateColumn(nowIndex,SIZE, DIRECTION, PRICE, JUNWALMA, LOC, PHONE, OPTION, ETC);
                showDatabase(sort);
                setInsertMode();
                edit_size.requestFocus();
                edit_size.setCursorVisible(true);
                mDBOpenHelper.close();
                break;
            }

            case R.id.btn_select:
            {
                showDatabase(sort);
                break;
            }

            case R.id.btn_delete:
            {
                mDBOpenHelper.open();
                mDBOpenHelper.deleteColumn(nowIndex);
                showDatabase(sort);
                setInsertMode();
                edit_size.requestFocus();
                edit_size.setCursorVisible(true);
                mDBOpenHelper.close();
                break;
            }

            case R.id.check_starhills:
            {
                check_centom.setChecked(false);
                check_sang_ga.setChecked(false);
                DIRECTION="서희스타힐스";
                break;
            }

            case R.id.check_centom:
            {
                check_starhills.setChecked(false);
                check_sang_ga.setChecked(false);
                DIRECTION="센텀시티";
                break;
            }

            case R.id.check_sang_ga:
            {
                check_starhills.setChecked(false);
                check_centom.setChecked(false);
                DIRECTION="상가";
                break;
            }

            case R.id.check_junse:
            {
                check_walse.setChecked(false);
                check_mama.setChecked(false);
                JUNWALMA="전세";
                break;
            }

            case R.id.check_walse:
            {
                check_junse.setChecked(false);
                check_mama.setChecked(false);
                JUNWALMA="월세";
                break;
            }

            case R.id.check_mama:
            {
                check_junse.setChecked(false);
                check_walse.setChecked(false);
                JUNWALMA="매매";
                break;
            }

            case R.id.check_size:
            {
                check_price.setChecked(false);
                sort="size";
                break;
            }

            case R.id.check_price:
            {
                check_size.setChecked(false);
                sort="price";
                break;
            }
        }
    }
}