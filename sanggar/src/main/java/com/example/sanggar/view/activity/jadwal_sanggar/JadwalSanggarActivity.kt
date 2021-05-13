package com.example.sanggar.view.activity.jadwal_sanggar

import android.content.Intent
import android.os.Bundle
import com.example.sanggar.GlobalClass
import com.example.sanggar.R
import com.example.sanggar.common.Preferences
import com.example.sanggar.common.clickWithDebounce
import com.example.sanggar.data.model.jadwal_sanggar.JadwalSanggarResponse
import com.example.sanggar.presenter.jadwal_sanggar.JadwalSanggarContract
import com.example.sanggar.presenter.jadwal_sanggar.JadwalSanggarPresenter
import com.example.sanggar.view.activity.common.BaseActivity
import com.example.sanggar.view.fragment.jadwal_sanggar.JadwalSanggarBottomSheetFragment
import kotlinx.android.synthetic.main.activity_jadwal_sanggar.*
import kotlinx.android.synthetic.main.fragment_toolbar.*

class JadwalSanggarActivity : BaseActivity(){


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_jadwal_sanggar)

        //init toolbar
        setToolbar()
        toolbar_title?.text = getString(R.string.jadwal_sanggar)

        initListener()
    }

    private fun initListener() {
        cv_tambah_jadwal?.clickWithDebounce {
            val bottomSheet = JadwalSanggarBottomSheetFragment()
            bottomSheet.show(supportFragmentManager, "")
        }
    }

}