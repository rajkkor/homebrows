package com.example.vincentg.project;

import android.provider.BaseColumns;

/**
 * Created by vince on 2017-01-23.
 */

class DBData {
    public DBData(){

    }
    public static abstract class TableInfo implements BaseColumns{
        static final String  DATABASE_NAME = "hemnet";

        //User Table
        static final String USER_TABLE_NAME = "users";
        static final String USER_NAME = "username";
        static final String USER_PASSWD = "PASSWD";



        //House table
        static final String HOUSE_TABLE = "houses";
        static final String HOUSE_STREET = "street";
        static final String HOUSE_SPACE = "yta";
        static final String HOUSE_KOMMUN = "vallentuna";
        static final String HOUSE_salesPerson = "salesperson";
    }
}
