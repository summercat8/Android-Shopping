package com.example.shopping4.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

/**
 * created by: xy
 * on: 2023/6/14
 * description:
 */
public class MyHelper extends SQLiteOpenHelper {
    private static MyHelper mInstance;
    public static synchronized MyHelper getmInstance(Context context){
        if (mInstance==null){
            mInstance=new MyHelper(context,"mall.db",null,1);
        }
        return mInstance;
    }
    private MyHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version){
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "create table if not exists user(phone text,password text)";
        String sql1 = "create table if not exists feedback(feedback text,contact text)";
        sqLiteDatabase.execSQL(sql);
        sqLiteDatabase.execSQL(sql1);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

}
