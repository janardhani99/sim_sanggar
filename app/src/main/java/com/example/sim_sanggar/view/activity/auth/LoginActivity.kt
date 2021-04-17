package com.example.sim_sanggar.view.activity.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.sim_sanggar.R
import com.example.sim_sanggar.common.clickWithDebounce
import com.example.sim_sanggar.view.fragment.beranda.BerandaFragment
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        initListener()
    }

    private fun initListener() {
        btn_login?.clickWithDebounce {
            startActivity(Intent (this, BerandaFragment::class.java))
        }

        btn_register?.clickWithDebounce {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
    }
}