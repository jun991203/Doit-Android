package com.june.databasetest;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static String NAME = "employee.db";
    public static int VERSION = 1;

    public DatabaseHelper(@Nullable Context context) {
        super(context, NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        Log.d("DatabaseHelper", "onCreate 호출");

        String sql = "create table if not exists emp(" +
                " _id integer PRIMARY KEY autoincrement," +
                " name text," +
                " age integer," +
                " mobile text);";

        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        Log.d("DatabaseHelper", "onOpen 호출");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int old, int recent) {
        Log.d("DatabaseHelper", "onUpgrade 호출 : " + old + " -> " + recent);

        if (recent > 1) {
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS emp");
        }
    }
}
