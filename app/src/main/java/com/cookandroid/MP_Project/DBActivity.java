package com.cookandroid.MP_Project;

import android.database.Cursor;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.nio.channels.FileChannel;
import java.util.ArrayList;

public class DBActivity extends AppCompatActivity implements View.OnClickListener {
    EditText edit_size, edit_price, edit_loc, edit_phone, edit_option, edit_etc;
    CheckBox check_size_A1, check_size_A2, check_size_A3,
            check_starhills, check_centom, check_sang_ga,
            check_junse, check_walse, check_mama,
            check_size, check_direction,check_price;
    Button btn_update, btn_insert, btn_select, btn_delete, btn_import, btn_export;

    long nowIndex;
    String SIZE, DIRECTION, PRICE, JUNWALMA = "", LOC, PHONE, OPTION, ETC;
    String sort = "size";//평수 시세

    ArrayAdapter<String> arrayAdapter;
    static ArrayList<String> arrayIndex = new ArrayList<String>();
    static ArrayList<String> arrayData = new ArrayList<String>();
    private DBOpenHelper mDBOpenHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_db);

        btn_insert = (Button) findViewById(R.id.btn_insert);
        btn_insert.setOnClickListener(this);
        btn_select = (Button) findViewById(R.id.btn_select);
        btn_select.setOnClickListener(this);
        btn_update = (Button) findViewById(R.id.btn_update);
        btn_update.setOnClickListener(this);
        btn_delete = (Button) findViewById(R.id.btn_delete);
        btn_delete.setOnClickListener(this);
        btn_import = (Button) findViewById(R.id.btn_import);
        btn_import.setOnClickListener(this);
        btn_export = (Button) findViewById(R.id.btn_export);
        btn_export.setOnClickListener(this);

        edit_size = (EditText) findViewById(R.id.edit_size);
        edit_price = (EditText) findViewById(R.id.edit_price);
        edit_loc = (EditText) findViewById(R.id.edit_Loc);
        edit_phone = (EditText) findViewById(R.id.edit_phone);
        edit_option = (EditText) findViewById(R.id.edit_option);
        edit_etc = (EditText) findViewById(R.id.edit_etc);

        check_size_A1 = (CheckBox) findViewById(R.id.check_size_A1);
        check_size_A1.setOnClickListener(this);
        check_size_A2 = (CheckBox) findViewById(R.id.check_size_A2);
        check_size_A2.setOnClickListener(this);
        check_size_A3 = (CheckBox) findViewById(R.id.check_size_A3);
        check_size_A3.setOnClickListener(this);

        check_junse = (CheckBox) findViewById(R.id.check_junse);
        check_junse.setOnClickListener(this);
        check_walse = (CheckBox) findViewById(R.id.check_walse);
        check_walse.setOnClickListener(this);
        check_mama = (CheckBox) findViewById(R.id.check_mama);
        check_mama.setOnClickListener(this);

        check_size = (CheckBox) findViewById(R.id.check_size);
        check_size.setOnClickListener(this);
        check_direction = (CheckBox) findViewById(R.id.check_direction);
        check_direction.setOnClickListener(this);
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

        check_starhills.callOnClick();
        check_starhills.setChecked(true);
        check_junse.callOnClick();
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
        check_size.setChecked(true);
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

            if(tempData[6].trim().equals("서희스타힐스")){
                check_starhills.setChecked(true);
                check_centom.setChecked(false);
                check_sang_ga.setChecked(false);
                DIRECTION="서희스타힐스";
                if(edit_size.getText().toString().equals("24"))
                {
                    SIZE="24";
                    check_size_A1.setChecked(true);
                    check_size_A2.setChecked(false);
                    check_size_A3.setChecked(false);
                } else if(edit_size.getText().toString().equals("30"))
                {
                    SIZE="30";
                    check_size_A1.setChecked(false);
                    check_size_A2.setChecked(true);
                    check_size_A3.setChecked(false);
                } else
                {
                    SIZE="34";
                    check_size_A1.setChecked(false);
                    check_size_A2.setChecked(false);
                    check_size_A3.setChecked(true);
                }
            }else if(tempData[6].trim().equals("센텀시티")){
                check_starhills.setChecked(false);
                check_centom.setChecked(true);
                check_sang_ga.setChecked(false);
                DIRECTION="센텀시티";
                if(edit_size.getText().toString().equals("25"))
                {
                    SIZE="25";
                    check_size_A1.setChecked(true);
                    check_size_A2.setChecked(false);
                    check_size_A3.setChecked(false);
                } else if(edit_size.getText().toString().equals("29"))
                {
                    SIZE="29";
                    check_size_A1.setChecked(false);
                    check_size_A2.setChecked(true);
                    check_size_A3.setChecked(false);
                } else
                {
                    SIZE="33";
                    check_size_A1.setChecked(false);
                    check_size_A2.setChecked(false);
                    check_size_A3.setChecked(true);
                }
            }else if(tempData[6].trim().equals("상가")){
                check_starhills.setChecked(false);
                check_centom.setChecked(false);
                check_sang_ga.setChecked(true);
                DIRECTION="상가";
            }

            edit_price.setText(tempData[9].trim());

            if(tempData[13].trim().equals("전세")){
                check_junse.setChecked(true);
                JUNWALMA = "전세";
            } else if (tempData[13].trim().equals("월세")){
                check_walse.setChecked(true);
                JUNWALMA = "월세";
            } else if(tempData[13].trim().equals("매매")){
                check_mama.setChecked(true);
                JUNWALMA = "매매";
            }

            edit_loc.setText(tempData[16].trim());
            edit_phone.setText(tempData[19].trim());
            edit_option.setText(tempData[22].trim());
            edit_etc.setText(tempData[25].trim());
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
            String tempSize = "평수 : "+iCursor.getString(iCursor.getColumnIndex("size"))+" 평형";
            String tempDirection = "\n"+"종류 : "+iCursor.getString(iCursor.getColumnIndex("direction"))+" ";
            String tempPrice = "\n"+"시세 : "+iCursor.getString(iCursor.getColumnIndex("price"))+" 만원";
            String tempJunWalMa = "\n"+"판매형태 : "+iCursor.getString(iCursor.getColumnIndex("junwalma"))+" ";
            String tempLoc = "\n"+"호수 : "+iCursor.getString(iCursor.getColumnIndex("loc"))+" ";
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
                if(check_starhills.isChecked())
                {
                    DIRECTION="서희스타힐스";
                    if(check_size_A1.isChecked())
                    {
                        SIZE="24";
                    }
                    else if(check_size_A2.isChecked())
                    {
                        SIZE="30";
                    }
                    else if(check_size_A3.isChecked())
                    {
                        SIZE="34";
                    }
                }
                else if(check_centom.isChecked())
                {
                    DIRECTION="센텀시티";
                    if(check_size_A1.isChecked())
                    {
                        SIZE="25";
                    }
                    else if(check_size_A2.isChecked())
                    {
                        SIZE="29";
                    }
                    else if(check_size_A3.isChecked())
                    {
                        SIZE="33";
                    }
                }
                else
                {
                    SIZE=edit_size.getText().toString().trim();
                    DIRECTION="상가";
                }
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
                if(check_starhills.isChecked() || check_centom.isChecked() || check_sang_ga.isChecked())
                {

                } else {
                    Toast.makeText(getApplicationContext(),"건물 종류를 선택해주세요",Toast.LENGTH_SHORT).show();
                    break;
                }

                if(check_junse.isChecked() || check_walse.isChecked() || check_mama.isChecked())
                {

                } else {
                    Toast.makeText(getApplicationContext(),"판매 형태를 선택해주세요",Toast.LENGTH_SHORT).show();
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
                if(check_starhills.isChecked())
                {
                    DIRECTION="서희스타힐스";
                    if(check_size_A1.isChecked())
                    {
                        SIZE="24";
                    }
                    else if(check_size_A2.isChecked())
                    {
                        SIZE="30";
                    }
                    else if(check_size_A3.isChecked())
                    {
                        SIZE="34";
                    }
                }
                else if(check_centom.isChecked())
                {
                    DIRECTION="센텀시티";
                    if(check_size_A1.isChecked())
                    {
                        SIZE="25";
                    }
                    else if(check_size_A2.isChecked())
                    {
                        SIZE="29";
                    }
                    else if(check_size_A3.isChecked())
                    {
                        SIZE="33";
                    }
                }
                else
                {
                    SIZE=edit_size.getText().toString().trim();
                    DIRECTION="상가";
                }
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

            case R.id.btn_import:
            {
                mDBOpenHelper.open();
                try {
                    File sd = Environment.getExternalStorageDirectory();
                    File data = Environment.getDataDirectory();

                    if (sd.canWrite()) {
                        File backupDB = new File(data, "/data/com.cookandroid.MP_Project//databases/manager_SQLite.db");
                        File currentDB = new File(sd, "Backup/DB_backup.db");

                        FileChannel src = new FileInputStream(currentDB).getChannel();
                        FileChannel dst = new FileOutputStream(backupDB).getChannel();

                        dst.transferFrom(src, 0, src.size());
                        src.close();
                        dst.close();

                        Toast.makeText(getApplicationContext(),
                                "복원 완료", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(),
                            "복원이 실패 하였습니다.", Toast.LENGTH_SHORT).show();
                }
                setInsertMode();
                showDatabase(sort);
                mDBOpenHelper.close();
                break;
            }

            case R.id.btn_export:
            {
                mDBOpenHelper.open();
                try
                {
                    File sd = Environment.getExternalStorageDirectory();
                    File data = Environment.getDataDirectory();

                    if (sd.canWrite())
                    {
                        File BackupDir = new File(sd, "Backup");
                        BackupDir.mkdir();

                        File currentDB =
                                new File(data, "/data/com.cookandroid.MP_Project//databases/manager_SQLite.db");
                        File backupDB = new File(sd, "Backup/DB_backup.db");

                        FileChannel src = new FileInputStream(currentDB).getChannel();
                        FileChannel dst = new FileOutputStream(backupDB).getChannel();
                        dst.transferFrom(src, 0, src.size());
                        src.close();
                        dst.close();
                    }

                    Toast.makeText(this,"백업 완료", Toast.LENGTH_SHORT).show();
                }
                catch (Exception e)
                {
                    Toast.makeText(this, "백업이 실패 하였습니다.", Toast.LENGTH_SHORT).show();
                }
                setInsertMode();
                showDatabase(sort);
                mDBOpenHelper.close();
                break;
            }

            case R.id.check_size_A1:
            {
                check_size_A2.setChecked(false);
                check_size_A3.setChecked(false);
                if(check_starhills.isChecked())
                {
                    SIZE="24";
                } else {
                    SIZE="25";
                }
                break;
            }

            case R.id.check_size_A2:
            {
                check_size_A1.setChecked(false);
                check_size_A3.setChecked(false);
                if(check_starhills.isChecked())
                {
                    SIZE="30";
                } else {
                    SIZE="29";
                }
                break;
            }

            case R.id.check_size_A3:
            {
                check_size_A1.setChecked(false);
                check_size_A2.setChecked(false);
                if(check_starhills.isChecked())
                {
                    SIZE="34";
                } else {
                    SIZE="33";
                }
                break;
            }

            case R.id.check_starhills:
            {
                check_size_A1.setVisibility(View.VISIBLE);
                check_size_A1.setText("24평");
                check_size_A2.setVisibility(View.VISIBLE);
                check_size_A2.setText("30평");
                check_size_A3.setVisibility(View.VISIBLE);
                check_size_A3.setText("34평");

                edit_size.setVisibility(View.INVISIBLE);

                check_centom.setChecked(false);
                check_sang_ga.setChecked(false);
                DIRECTION="서희스타힐스";
                break;
            }

            case R.id.check_centom:
            {
                check_size_A1.setVisibility(View.VISIBLE);
                check_size_A1.setText("25평");
                check_size_A2.setVisibility(View.VISIBLE);
                check_size_A2.setText("29평");
                check_size_A3.setVisibility(View.VISIBLE);
                check_size_A3.setText("33평");
                edit_size.setVisibility(View.INVISIBLE);

                check_starhills.setChecked(false);
                check_sang_ga.setChecked(false);
                DIRECTION="센텀시티";
                break;
            }

            case R.id.check_sang_ga:
            {
                check_size_A1.setVisibility(View.INVISIBLE);
                check_size_A2.setVisibility(View.INVISIBLE);
                check_size_A3.setVisibility(View.INVISIBLE);
                edit_size.setVisibility(View.VISIBLE);

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
                check_direction.setChecked(false);
                check_price.setChecked(false);
                sort="size";
                break;
            }

            case R.id.check_direction:
            {
                check_size.setChecked(false);
                check_price.setChecked(false);
                sort="direction";
                break;
            }

            case R.id.check_price:
            {
                check_direction.setChecked(false);
                check_size.setChecked(false);
                sort="price";
                break;
            }
        }
    }

}