package com.example.admin

import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import com.example.admin.view.activity.common.BaseActivity
import com.example.admin.view.fragment.BerandaFragment
import com.example.admin.view.fragment.ProfilFragment
import com.example.admin.view.fragment.UserFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main_tab.*

class MainTabActivity: BaseActivity() {

    private val navigationListener = BottomNavigationView.OnNavigationItemSelectedListener {
        when (it.itemId) {
            R.id.menu_beranda -> {
                if (!isDisplaying(BerandaFragment()))
                    replaceFragment(BerandaFragment())
                return@OnNavigationItemSelectedListener true
            }

            R.id.menu_user -> {
                if (!isDisplaying(UserFragment()))
                    replaceFragment(UserFragment())
                return@OnNavigationItemSelectedListener true
            }

            R.id.menu_profil -> {
                if (!isDisplaying(ProfilFragment()))
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
            addFragment(BerandaFragment())
        }
    }

    fun replaceFragment(fragment: Fragment) {
        supportFragmentManager
                .beginTransaction()
                .replace(R.id.frame_layout, fragment, fragment.javaClass.simpleName)
                .addToBackStack(null)
                .commit()
    }

    private fun addFragment(fragment: Fragment) {
        supportFragmentManager
                .beginTransaction()
                .replace(R.id.frame_layout, fragment, fragment.javaClass.simpleName)
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