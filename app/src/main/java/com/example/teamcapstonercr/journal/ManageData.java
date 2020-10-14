package com.example.teamcapstonercr.journal;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class ManageData {
    private DatabaseHelper sqlHelper ;

    private Context context; // context is the middle layer

    private SQLiteDatabase database ;
    public ManageData(Context context)
    {
        this.context = context;
    } // constructor

    public ManageData open() {
        sqlHelper = new DatabaseHelper(context);
        database = sqlHelper.getWritableDatabase();
        return this;
    }

    public void close()
    {
        sqlHelper.close();
    }
}
