package com.example.sanggar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.sanggar.view.activity.common.BaseActivity
import com.example.sanggar.view.fragment.daftar.DaftarFragment
import com.example.sanggar.view.fragment.lainnya.LainnyaFragment
import com.example.sanggar.view.fragment.profil.ProfilFragment
import com.example.sanggar.view.fragment.sewa.SewaFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main_tab.*

class MainTabActivity : BaseActivity() {
    val navigationListener = BottomNavigationView.OnNavigationItemSelectedListener {
        when (it.itemId) {
            R.id.menu_daftar -> {
                replaceFragment(DaftarFragment())
                return@OnNavigationItemSelectedListener true
            }

            R.id.menu_sewa -> {
                replaceFragment(SewaFragment())
                return@OnNavigationItemSelectedListener true
            }

            R.id.menu_profil -> {
                replaceFragment(ProfilFragment())
                return@OnNavigationItemSelectedListener true
            }

            R.id.menu_lainnya -> {
                replaceFragment(LainnyaFragment())
                return@OnNavigationItemSelectedListener true
            }

        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_tab)
        btmnav_menu.setOnNavigationItemSelectedListener(navigationListener)

        if(savedInstanceState == null) {
            replaceFragment(DaftarFragment())
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