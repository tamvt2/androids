package com.example.du_an_mau.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.du_an_mau.R;
import com.example.du_an_mau.data.model.Sach;

import java.util.ArrayList;

public class SachSpinnerAdapter extends ArrayAdapter<Sach> {
    private Context context;
    private ArrayList<Sach> lists;
    TextView tvMaSach, tvTenSach;

    public SachSpinnerAdapter(@NonNull Context context, ArrayList<Sach> lists) {
        super(context, 0 , lists);
        this.context = context;
        this.lists = lists;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.sach_item_spinner, null);
        }
        final Sach item = lists.get(position);
        if (item != null) {
            tvMaSach = v.findViewById(R.id.tvMaSachSp);
            tvMaSach.setText(item.maSach + ". ");
            tvTenSach = v.findViewById(R.id.tvTenSachSp);
            tvTenSach.setText(item.tenSach);
        }
        return v;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.sach_item_spinner, null);
        }
        final Sach item = lists.get(position);
        if (item != null) {
            tvMaSach = v.findViewById(R.id.tvMaSachSp);
            tvMaSach.setText(item.maSach + ". ");
            tvTenSach = v.findViewById(R.id.tvTenSachSp);
            tvTenSach.setText(item.tenSach);
        }
        return v;
    }


}
