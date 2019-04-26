package com.canhdinh.bai4_tuan7;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

public class SQL extends SQLiteOpenHelper {
    SQL(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    void TruyVan(String sql)
    {
        SQLiteDatabase db=getWritableDatabase();
        db.execSQL(sql);
    }

    Cursor TruyVanTraVe(String sql)
    {
        SQLiteDatabase db=getWritableDatabase();
        return db.rawQuery(sql,null);
    }

    void Insertsanpham(String ten, Integer gia, byte[] hinh)
    {
        SQLiteDatabase db=getWritableDatabase();
        String sql="Insert into SanPham values (null,?,?,?)";
        SQLiteStatement statement=db.compileStatement(sql);
        statement.clearBindings();
        statement.bindString(1,ten);
        statement.bindLong(2,gia);
        statement.bindBlob(3,hinh);
        statement.executeInsert();
    }
    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
