package com.example.sim_sanggar.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.sim_sanggar.R;

public class ProfilActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_profil);

    }

//    public void Edit_Profil(View view) {
//        Intent intent = new Intent(MenuProfil.this, EditProfilFragment.class);
//        startActivity(intent);

//        FragmentManager fragmentManager = getSupportFragmentManager();
//        FragmentTransaction transaction = fragmentManager.beginTransaction();
//
//        FragmentEditProfil fragmentEditProfil = new FragmentEditProfil();
//        transaction.add(R.id.frame_content, fragmentEditProfil);
//        transaction.addToBackStack("fragmentPertama");
//        transaction.commit()
//    }
}
