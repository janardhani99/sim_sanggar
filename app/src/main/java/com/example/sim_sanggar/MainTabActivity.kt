package com.example.sim_sanggar

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.sim_sanggar.view.fragment.beranda.BerandaFragment
import com.example.sim_sanggar.view.fragment.daftar.DaftarFragment
import com.example.sim_sanggar.view.fragment.profil.ProfilFragment
import com.example.sim_sanggar.view.fragment.sewa.JadwalSewaFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main_tab.*

class MainTabActivity: AppCompatActivity() {

    val navigationListener = BottomNavigationView.OnNavigationItemSelectedListener {
        when (it.itemId) {
            R.id.menu_beranda -> {
                replaceFragment(BerandaFragment())
                return@OnNavigationItemSelectedListener true
            }

            R.id.menu_daftar -> {
                replaceFragment(DaftarFragment())
                return@OnNavigationItemSelectedListener true
            }

            R.id.menu_sewa -> {
                replaceFragment(JadwalSewaFragment())
                return@OnNavigationItemSelectedListener true
            }

            R.id.menu_profil -> {
                replaceFragment(ProfilFragment())
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_tab)

        btmnav_menu.setOnNavigationItemSelectedListener(navigationListener)

        if (savedInstanceState == null) {
            replaceFragment(BerandaFragment())
        }
    }

    fun replaceFragment(fragment: Fragment) {
        supportFragmentManager
                .beginTransaction()
                .replace(R.id.frame_layout, fragment, fragment.javaClass.simpleName)
                .addToBackStack(null)
                .commit()
    }
}