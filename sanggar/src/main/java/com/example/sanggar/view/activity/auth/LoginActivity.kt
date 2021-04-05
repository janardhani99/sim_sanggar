package com.example.sanggar.view.activity.auth

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.sanggar.MainTabActivity
import com.example.sanggar.R
import com.example.sanggar.common.clickWithDebounce
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        initListener()
    }

    private fun initListener() {
        btn_login?.clickWithDebounce {
            startActivity(Intent(this, MainTabActivity::class.java))
        }
        btn_register_login?.clickWithDebounce {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
    }
}