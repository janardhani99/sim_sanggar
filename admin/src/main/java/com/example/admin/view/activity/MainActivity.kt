package com.example.admin.view.activity

import android.content.Intent
import android.os.Bundle
import com.example.admin.R
import com.example.admin.common.clickWithDebounce
import com.example.admin.view.activity.common.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity :  BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        cv_kelola_user?.clickWithDebounce {
            val intent = Intent(this, KelolaUserActivity::class.java)
            startActivity(intent)
        }
    }
}