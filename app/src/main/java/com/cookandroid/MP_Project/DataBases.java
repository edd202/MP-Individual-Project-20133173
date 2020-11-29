package com.cookandroid.MP_Project;

import android.provider.BaseColumns;

public final class DataBases {

    public static final class CreateDB implements BaseColumns{
        public static final String SIZE = "size";
        public static final String DIRECTION = "direction";
        public static final String PRICE = "price";
        public static final String JUNWALMA = "junwalma";
        public static final String LOC = "loc";
        public static final String PHONE = "phone";
        public static final String OPTION = "option";
        public static final String ETC = "etc";
        public static final String _TABLENAME0 = "usertable";
        public static final String _CREATE0 = "create table if not exists "+_TABLENAME0+"("
                +_ID+" integer primary key autoincrement, "
                +SIZE+" text not null , "
                +DIRECTION+" text not null , "
                +PRICE+" text not null , "
                +JUNWALMA+" text not null , "
                +LOC+" text not null , "
                +PHONE+" text not null , "
                +OPTION+", "
                +ETC+");";
    }
}