package com.example.admin.view.activity

import android.content.Intent
import android.os.Bundle
import com.example.admin.GlobalClass
import com.example.admin.R
import com.example.admin.common.Preferences
import com.example.admin.common.clickWithDebounce
import com.example.admin.view.activity.auth.LoginActivity
import com.example.admin.view.activity.common.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity :  BaseActivity() {
    var preferences = Preferences(GlobalClass.context)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        cv_kelola_user?.clickWithDebounce {
            val intent = Intent(this, KelolaUserActivity::class.java)
            startActivity(intent)
        }

        cv_kelola_user_sanggar?.clickWithDebounce {
            val intent = Intent(this, KelolaUserSanggarActivity::class.java)
            startActivity(intent)
        }

        btn_logout?.clickWithDebounce {
            preferences.userLoggedOut()
            finishAffinity()
            startActivity(Intent(this, LoginActivity::class.java))
        }
    }
}