package com.example.du_an_mau.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.du_an_mau.Adapter.LoaiSachAdapter;
import com.example.du_an_mau.R;
import com.example.du_an_mau.data.DAO.LoaiSachDAO;
import com.example.du_an_mau.data.model.LoaiSach;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;


public class LoaiSachFragment extends Fragment {
    ListView lv;
    ArrayList<LoaiSach> list;
    FloatingActionButton fab;
    Dialog dialog;
    EditText edMaLoaiSach, edTenLoaiSach;
    Button btnSave, btnCancel;
    static LoaiSachDAO dao;
    LoaiSachAdapter adapter;
    LoaiSach item;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_loai_sach, container, false);
        lv = v.findViewById(R.id.lvLoaiSach);
        fab = v.findViewById(R.id.fab);
        dao = new LoaiSachDAO(getActivity());
        capNhatLv();

        fab.setOnClickListener(view -> {
            openDialog(getActivity(), 0);
        });

        lv.setOnItemClickListener((parent, view, position, id)-> {
            item = list.get(position);
            openDialog(getActivity(), 1);
            return;
        });
        return v;
    }

    protected void openDialog(final Context context, final int type) {
        dialog = new Dialog(context);
        dialog.setContentView(R.layout.loai_sach_dialog);
        edMaLoaiSach = dialog.findViewById(R.id.edMaLoaiSach);
        edTenLoaiSach = dialog.findViewById(R.id.edTenLoaiSach);
        btnSave = dialog.findViewById(R.id.btnSaveLS);
        btnCancel = dialog.findViewById(R.id.btnCancelLS);

        edMaLoaiSach.setEnabled(false);
        if (type != 0) {
            edMaLoaiSach.setText(String.valueOf(item.maLoai));
            edTenLoaiSach.setText(item.tenLoai);
        }

        btnCancel.setOnClickListener(view -> {
            dialog.dismiss();
        });

        btnSave.setOnClickListener(view -> {
            item = new LoaiSach();
            item.tenLoai = edTenLoaiSach.getText().toString();
            if (validate()>0) {
                if (type == 0) {
                    if (dao.insert(item) > 0) {
                        Toast.makeText(context, "", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(context, "", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    item.maLoai = Integer.parseInt(edMaLoaiSach.getText().toString());
                    if (dao.update(item)>0) {
                        Toast.makeText(context, "", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(context, "", Toast.LENGTH_SHORT).show();
                    }
                }
                capNhatLv();
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    public void xoa(final String Id) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Delete");
        builder.setMessage("Ban co muon xoa khong");
        builder.setCancelable(true);

        builder.setPositiveButton("Yes", (dialog, id) -> {
            dao.delete(Id);
            capNhatLv();
            dialog.cancel();
        });

        builder.setNegativeButton("No", (dialog, id) -> {
            dialog.cancel();
        });
        AlertDialog alert = builder.create();
        builder.show();
    }

    void capNhatLv() {
        list = (ArrayList<LoaiSach>) dao.getAll();
        adapter = new LoaiSachAdapter(getActivity(), this, list);
        lv.setAdapter(adapter);
    }

    public int validate() {
        int check = 1;
        if (edTenLoaiSach.getText().length() == 0) {
            Toast.makeText(getContext(), "", Toast.LENGTH_SHORT).show();
            check = -1;
        }
        return check;
    }
}