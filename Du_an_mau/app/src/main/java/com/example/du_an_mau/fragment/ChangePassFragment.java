package com.example.du_an_mau.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.du_an_mau.R;
import com.example.du_an_mau.data.DAO.ThuThuDAO;
import com.example.du_an_mau.data.model.ThuThu;
import com.google.android.material.textfield.TextInputEditText;


public class ChangePassFragment extends Fragment {
    TextInputEditText edPassOld, edPass, edRePass;
    Button btnSave, btnCancel;
    ThuThuDAO dao;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_change_pass, container, false);
    edPassOld = v.findViewById(R.id.edPassOld);
    edPass = v.findViewById(R.id.edPass);
    edRePass = v.findViewById(R.id.edRePass);
    btnSave = v.findViewById(R.id.btnSave);
    btnCancel = v.findViewById(R.id.btnCancel);

    dao = new ThuThuDAO(getActivity());

        btnCancel.setOnClickListener(view -> {
        edPassOld.setText("");
        edPass.setText("");
        edRePass.setText("");
    });

        btnSave.setOnClickListener(view -> {
        SharedPreferences pref = getActivity().getSharedPreferences("USER_FILE", Context.MODE_PRIVATE);
        String user = pref.getString("USERNAME", "");
        if (validate()>0) {
            ThuThu thuThu = dao.getID(user);
            thuThu.matKhau = edPass.getText().toString();
            dao.updatePass(thuThu);
            if (dao.updatePass(thuThu)>0) {
                Toast.makeText(getContext(), "Thay đổi mật khẩu thành công", Toast.LENGTH_SHORT).show();
                edPassOld.setText("");
                edPass.setText("");
                edRePass.setText("");
            } else {
                Toast.makeText(getContext(), "Thay đổi mật khẩu thất bại", Toast.LENGTH_SHORT).show();
            }
        }
    });
        return v;
}

    public int validate() {
        int check = 1;
        if (edPassOld.getText().length()==0 || edPass.getText().length()==0 || edRePass.getText().length()==0) {
            Toast.makeText(getContext(), "Bạn phải nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            check = -1;
        } else {
            SharedPreferences pref = getActivity().getSharedPreferences("USER_FILE", Context.MODE_PRIVATE);
            String passOld = pref.getString("PASSWORD", "");
            String pass = edPass.getText().toString();
            String rePass = edRePass.getText().toString();
            if (!passOld.equals(edPassOld.getText().toString())) {
                Toast.makeText(getContext(), "Mật khẩu cũ sai", Toast.LENGTH_SHORT).show();
                check = -1;
            }
            if (!pass.equals(rePass)) {
                Toast.makeText(getContext(), "Mật khẩu không trùng khớp", Toast.LENGTH_SHORT).show();
                check = -1;
            }
        }
        return check;
    }
}