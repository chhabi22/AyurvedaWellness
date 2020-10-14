package com.example.teamcapstonercr.journal;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;

public class DatabaseHelper extends SQLiteOpenHelper {

    static final String DATABASE_NAME = "TEAMCAPSTONERCR.DB";

    static final int DB_VERSION = 1;

    //journal text table name

    public static final String NOTES_TABLE_NAME = "NOTES";

    // journal text table columns

    public static final String NOTES_ID = "notes_id";
    public static final String DATE = "date";
    public static final String DESCRIPTION = "description";

    //create notes table query

    private static final String CREATE_NOTES_TABLE = "create table "+
            NOTES_TABLE_NAME +" ("+
            NOTES_ID +" INTEGER , "+
            DATE +" TEXT NOT NULL, "+
            DESCRIPTION +" TEXT, " + " INTEGER NOT NULL); ";

    // images table name

    public static final String NOTES_TABLE_IMAGES = "IMAGES";

    // image table columns

    public static final String IMAGE = "image";
    public static final String NOTES_IMAGE_ID = "notes_id";
    public static final String IMAGE_CATEGORY_ID = "category_id";

    //create image table query

    private static final String CREATE_IMAGE_TABLE = "create table "+
            NOTES_TABLE_IMAGES +" ( "+
            IMAGE + " text , " +
            NOTES_IMAGE_ID + " INTEGER NOT NULL)";

    public DatabaseHelper(Context context ) {

        super(context, DATABASE_NAME , null , DB_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
