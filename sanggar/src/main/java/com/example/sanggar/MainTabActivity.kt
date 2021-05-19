package com.example.sanggar

import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import com.example.sanggar.data.model.sanggar.SanggarData
import com.example.sanggar.view.activity.common.BaseActivity
import com.example.sanggar.view.fragment.daftar.DaftarFragment
import com.example.sanggar.view.fragment.lainnya.LainnyaFragment
import com.example.sanggar.view.fragment.sanggar.ProfilFragment
import com.example.sanggar.view.fragment.sewa.SewaFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main_tab.*

class MainTabActivity : BaseActivity() {
    private val navigationListener = BottomNavigationView.OnNavigationItemSelectedListener {
        when (it.itemId) {
            R.id.menu_daftar -> {
                if (!isDisplaying(DaftarFragment())) replaceFragment(DaftarFragment())
                return@OnNavigationItemSelectedListener true
            }

            R.id.menu_sewa -> {
                if (!isDisplaying(SewaFragment())) replaceFragment(SewaFragment())
                return@OnNavigationItemSelectedListener true
            }

            R.id.menu_profil -> {
                if (!isDisplaying(ProfilFragment())) replaceFragment(ProfilFragment())
                return@OnNavigationItemSelectedListener true
            }

            R.id.menu_lainnya -> {
                if (!isDisplaying(LainnyaFragment())) replaceFragment(LainnyaFragment())
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_tab)
        btmnav_menu?.setOnNavigationItemSelectedListener(navigationListener)

        if (savedInstanceState == null) {
            addFragment(DaftarFragment())
        }
    }

    private fun addFragment(fragment: Fragment) {
        supportFragmentManager
                .beginTransaction()
                .replace(R.id.frame_layout, fragment, fragment.javaClass.simpleName)
                .commit()
    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction
                .replace(R.id.frame_layout, fragment)
                .commit()
    }

    private fun isDisplaying(fragment: Fragment): Boolean {
        val currentFragment = supportFragmentManager.findFragmentById(R.id.frame_layout)
        return currentFragment?.javaClass?.simpleName == fragment.javaClass.simpleName
    }

    private var doubleBackToExitPressedOnce = false
    override fun onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed()
            return
        }
        doubleBackToExitPressedOnce = true
        toast("Ketuk sekali lagi untuk keluar aplikasi")
        Handler().postDelayed({
            doubleBackToExitPressedOnce = false
        }, 2000)
    }
}