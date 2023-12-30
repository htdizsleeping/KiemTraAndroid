package com.example.kiemtraandroid;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

public class SanPhamDAO {
    private DbHelper dbHelper;

    public SanPhamDAO(Context context) {
        dbHelper = new DbHelper(context);
    }

    public ArrayList<SanPham> getListSanPham() {
        ArrayList<SanPham> list = new ArrayList<>();
        SQLiteDatabase database = dbHelper.getReadableDatabase();
        database.beginTransaction();
        try {
            Cursor cursor = database.rawQuery("SELECT * FROM SanPham", null);
            if (cursor.getCount() > 0) {
                cursor.moveToFirst();
                do {
                    list.add(new SanPham(cursor.getInt(0),
                            cursor.getString(1),
                            cursor.getString(2),
                            cursor.getString(3)));
                } while (cursor.moveToNext());
                database.setTransactionSuccessful();
            }
        } catch (Exception e) {
            Log.e("SanPhamDAO", "getListSanPham" + e);
        } finally {
            database.endTransaction();
        }
        return list;
    }

    public boolean addSanPham(SanPham sanPham) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("TenSP", sanPham.getTenSP());
        cv.put("GiaTien", sanPham.getGiaTien());
        cv.put("Image", sanPham.getImage());
        long check = db.insert("SanPham", null, cv);
        return check != -1;
    }

    public boolean deleteSanPham(int maSP) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        int deletedRows = db.delete("SanPham", "MaSP=?", new String[]{String.valueOf(maSP)});
        return deletedRows > 0;
    }
}
