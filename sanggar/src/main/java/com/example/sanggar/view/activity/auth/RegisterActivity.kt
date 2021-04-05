package com.example.sanggar.view.activity.auth

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.sanggar.MainTabActivity
import com.example.sanggar.R
import com.example.sanggar.common.clickWithDebounce
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        initListener()
    }

    private fun initListener() {
        btn_register?.clickWithDebounce {
            startActivity(Intent(this, MainTabActivity::class.java))
        }
        btn_login_register?.clickWithDebounce {
            onBackPressed()
//            startActivity(Intent(this, LoginActivity::class.java))
        }
    }
}