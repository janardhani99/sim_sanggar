package com.example.sanggar.view.activity.jadwal_sanggar

import android.os.Bundle
import com.example.sanggar.R
import com.example.sanggar.view.activity.common.BaseActivity
import kotlinx.android.synthetic.main.fragment_toolbar.*

class JadwalSanggarActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_jadwal_sanggar)

        //init toolbar
        setToolbar()
        toolbar_title?.text = getString(R.string.jadwal_sanggar)
    }
}