package com.example.aturgajian.helper;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SqliteHelper extends SQLiteOpenHelper{

    private static final String DATABASE_NAME = "mobile_banking";
    private static final int DATABASE_VERSION = 1;

    public SqliteHelper(Context context){
        super(context,DATABASE_NAME,null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "CREATE TABLE transaksi(transaksi_id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, status TEXT, " +
                "jumlah DOUBLE, keterangan TEXT, tanggal DATE DEFAULT CURRENT_DATE);"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS transaksi");
    }

    public Cursor executeRead(String sql){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(sql, null);

        return cursor;
    }

    public void executeWrite(String sql){
        SQLiteDatabase db = this.getWritableDatabase();

        db.execSQL(sql);
    }
}
