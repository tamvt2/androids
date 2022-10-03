package com.example.du_an_mau.data.DAO;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.du_an_mau.data.database.DBHelper;
import com.example.du_an_mau.data.model.PhieuMuon;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class PhieuMuonDAO {
    private SQLiteDatabase db;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    public PhieuMuonDAO(Context context) {
        DBHelper dbHelper = new DBHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public long insert(PhieuMuon obj) {
        ContentValues values = new ContentValues();
        values.put("maTT", obj.maTT);
        values.put("maTV", obj.maTV);
        values.put("maSach", obj.maSach);
        values.put("tienThue", obj.tienThue);
        values.put("ngay", sdf.format(obj.ngay));
        values.put("traSach", obj.traSach);
        return db.insert("PhieuMuon", null, values);
    }

    public int update(PhieuMuon obj) {
        ContentValues values = new ContentValues();
        values.put("maTT", obj.maTT);
        values.put("maTV", obj.maTV);
        values.put("maSach", obj.maSach);
        values.put("tienThue", obj.tienThue);
        values.put("ngay", sdf.format(obj.ngay));
        values.put("traSach", obj.traSach);
        return db.update("PhieuMuon", values,"maPM=?", new String[]{String.valueOf(obj.maPM)});
    }

    public int delete(String id) {
        return db.delete("PhieuMuon", "maPM=?", new String[]{id});
    }

    public List<PhieuMuon> getAll() {
        String sql = "SELECT * FROM PhieuMuon";
        return getData(sql);
    }

    public PhieuMuon getID(String id) {
        String sql = "SELECT * FROM PhieuMuon WHERE maPM=?";
        List<PhieuMuon> list = getData(sql, id);
        return list.get(0);
    }

    @SuppressLint("Range")
    private List<PhieuMuon> getData(String sql, String ... selecsionArgs) {
        List<PhieuMuon> list = new ArrayList<>();
        Cursor c = db.rawQuery(sql, selecsionArgs);
        while (c.moveToNext()) {
            PhieuMuon obj = new PhieuMuon();
            obj.maPM = Integer.parseInt(c.getString(c.getColumnIndex("maPM")));
            obj.maTT = c.getString(c.getColumnIndex("maTT"));
            obj.maTV = Integer.parseInt(c.getString(c.getColumnIndex("maTV")));
            obj.maSach = Integer.parseInt(c.getString(c.getColumnIndex("maSach")));
            obj.tienThue = Integer.parseInt(c.getString(c.getColumnIndex("tienThue")));
            try {
                obj.ngay = sdf.parse(c.getString(c.getColumnIndex("ngay")));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            obj.traSach = Integer.parseInt(c.getString(c.getColumnIndex("traSach")));
            list.add(obj);
        }
        return list;
    }
}
