package com.example.du_an_mau;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.TextView;

import com.example.du_an_mau.data.DAO.ThuThuDAO;
import com.example.du_an_mau.data.model.ThuThu;
import com.example.du_an_mau.fragment.AddUserFragment;
import com.example.du_an_mau.fragment.ChangePassFragment;
import com.example.du_an_mau.fragment.DoanhThuFragment;
import com.example.du_an_mau.fragment.LoaiSachFragment;
import com.example.du_an_mau.fragment.PhieuMuonFragment;
import com.example.du_an_mau.fragment.SachFragment;
import com.example.du_an_mau.fragment.ThanhVienFragment;
import com.example.du_an_mau.fragment.TopFragment;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;



public class Main2Activity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private static final int FRAGMENT_PHIEUMUON = 0;
    private static final int FRAGMENT_LOAISACH = 1;
    private static final int FRAGMENT_SACH = 2;
    private static final int FRAGMENT_THANHVIEN = 3;
    private static final int FRAGMENT_TOP = 4;
    private static final int FRAGMENT_DOANHTHU = 5;
    private static final int FRAGMENT_ADDUSER = 6;
    private static final int FRAGMENT_REPASS = 7;
    private int currentFragment = FRAGMENT_PHIEUMUON;

    View mHeaderView;
    TextView edUser;
    ThuThuDAO thuThuDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.menu);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
//        toggle.syncState();

        NavigationView nv = (NavigationView) findViewById(R.id.nav_view);
        nv.setNavigationItemSelectedListener(this);

        mHeaderView = nv.getHeaderView(0);
        edUser = mHeaderView.findViewById(R.id.tvUser);
        Intent i = getIntent();
        String user = i.getStringExtra("user");
        thuThuDAO = new ThuThuDAO(this);
        ThuThu thuThu = thuThuDAO.getID(user);
        String username = thuThu.hoTen;
        edUser.setText("Welcome " + username + "!");

        if (user.equalsIgnoreCase("admin")) {
            nv.getMenu().findItem(R.id.sub_AddUser).setVisible(true);
        }

        toolbar.setNavigationOnClickListener(view -> {
            drawer.openDrawer(GravityCompat.START);
        });
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.nav_PhieuMuon) {
            if (FRAGMENT_PHIEUMUON != currentFragment) {
                replaceFragment(new PhieuMuonFragment());
                currentFragment = FRAGMENT_PHIEUMUON;
            }
        } else if (id == R.id.nav_LoaiSach) {
            if (FRAGMENT_LOAISACH != currentFragment) {
                replaceFragment(new LoaiSachFragment());
                currentFragment = FRAGMENT_LOAISACH;
            }
        } else if (id == R.id.nav_Sach) {
            if (FRAGMENT_SACH != currentFragment) {
                replaceFragment(new SachFragment());
                currentFragment = FRAGMENT_SACH;
            }
        } else if (id == R.id.nav_ThanhVien) {
            if (FRAGMENT_THANHVIEN != currentFragment) {
                replaceFragment(new ThanhVienFragment());
                currentFragment = FRAGMENT_THANHVIEN;
            }
        } else if (id == R.id.sub_Top) {
            if (FRAGMENT_TOP != currentFragment) {
                replaceFragment(new TopFragment());
                currentFragment = FRAGMENT_TOP;
            }
        } else if (id == R.id.sub_DoanhThu) {
            if (FRAGMENT_DOANHTHU != currentFragment) {
                replaceFragment(new DoanhThuFragment());
                currentFragment = FRAGMENT_DOANHTHU;
            }
        } else if (id == R.id.sub_AddUser) {
            if (FRAGMENT_ADDUSER != currentFragment) {
                replaceFragment(new AddUserFragment());
                currentFragment = FRAGMENT_ADDUSER;
            }
        } else if (id == R.id.sub_Pass) {
            if (FRAGMENT_REPASS != currentFragment) {
                replaceFragment(new ChangePassFragment());
                currentFragment = FRAGMENT_REPASS;
            }
        } else if (id == R.id.sub_Logout) {
            startActivity(new Intent(getApplicationContext(), LoginActivity.class));
            finish();
        }
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void replaceFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.content_main, fragment);
        fragmentTransaction.commit();
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}