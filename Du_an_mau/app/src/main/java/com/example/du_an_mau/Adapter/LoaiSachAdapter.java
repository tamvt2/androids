package com.example.du_an_mau.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.du_an_mau.R;
import com.example.du_an_mau.data.model.LoaiSach;
import com.example.du_an_mau.fragment.LoaiSachFragment;

import java.util.ArrayList;

public class LoaiSachAdapter extends ArrayAdapter<LoaiSach> {
    private Context context;
    LoaiSachFragment fragment;
    private ArrayList<LoaiSach> lists;
    TextView tvMaLoai, tvTenLoai;
    ImageView imgDel;

    public LoaiSachAdapter(@NonNull Context context, LoaiSachFragment fragment, ArrayList<LoaiSach> lists) {
        super(context, 0, lists);
        this.context = context;
        this.fragment = fragment;
        this.lists = lists;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.loai_sach_item, null);
        }
        final LoaiSach item = lists.get(position);
        if (item != null) {
            tvMaLoai = v.findViewById(R.id.tvMaLoaiSach);
            tvMaLoai.setText("Mã loại: " + item.maLoai);
            tvTenLoai = v.findViewById(R.id.tvTenLoaiSach);
            tvTenLoai.setText("Tên loại: " + item.tenLoai);
            imgDel = v.findViewById(R.id.imgDeleteLS);
        }
        imgDel.setOnClickListener(view -> {
            fragment.xoa(String.valueOf(item.maLoai));
        });
        return v;
    }
}
