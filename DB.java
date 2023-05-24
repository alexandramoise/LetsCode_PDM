package com.example.letscodeapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DB extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "devices.db";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_USERS = "users";
    public static final String COLUMN_DEVICE = "device";
    public static final String COLUMN_LEVEL = "level";

    private static final String CREATE_TABLE_USERS =
            "CREATE TABLE " + TABLE_USERS + "(" +
                    COLUMN_DEVICE + " TEXT," +
                    COLUMN_LEVEL + " INTEGER" +
                    ")";

    public DB(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_USERS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        onCreate(db);
    }

    public void addDevice(String device) {
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues content = new ContentValues();
        content.put(COLUMN_DEVICE,device);
        content.put(COLUMN_LEVEL,1);

        long newRow = db.insert(TABLE_USERS,null,content);
    }

    public int getDeviceLevel(String device) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] projection = {
                COLUMN_DEVICE,
                COLUMN_LEVEL
        };

        String select = COLUMN_DEVICE + " = ?";
        String[] selectParam = {String.valueOf(device)};

        Cursor cursor = db.query(TABLE_USERS,projection,select,selectParam,null,null,null);
        int res = 0;

        if(cursor.moveToFirst()) {
            int level = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_LEVEL));
            res = level;
        }
        cursor.close();
        return res;
    }

    public boolean isUser(String device) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] projection = {
                COLUMN_DEVICE,
                COLUMN_LEVEL
        };

        String select = COLUMN_DEVICE + " = ?";
        String[] selectParam = {String.valueOf(device)};

        Cursor cursor = db.query(TABLE_USERS,projection,select,selectParam,null,null,null);

        if(cursor.moveToFirst())
            return true;
        return false;
    }

    public void updateLevel(String device,int newLevel) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_LEVEL,newLevel);

        String where = COLUMN_DEVICE + " = ?";
        String[] whereParam = {String.valueOf(device)};

        db.update(TABLE_USERS,values,where,whereParam);
        db.close();
    }

    public String getDevice() { return COLUMN_DEVICE; }
    public String getTableUsers() { return TABLE_USERS; }
}
