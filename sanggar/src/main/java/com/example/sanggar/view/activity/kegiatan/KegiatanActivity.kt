package com.example.sanggar.view.activity.kegiatan

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.sanggar.R
import com.example.sanggar.common.Constants
import com.example.sanggar.common.clickWithDebounce
import com.example.sanggar.view.activity.common.BaseActivity
import kotlinx.android.synthetic.main.activity_kegiatan.*
import kotlinx.android.synthetic.main.fragment_toolbar.*

class KegiatanActivity : BaseActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kegiatan)
        setToolbar()
        toolbar_title?.text = getString(R.string.kegiatan)

        cv_tambah_kegiatan?.clickWithDebounce {
            val intent = Intent(this, DetailKegiatanActivity::class.java)
            intent.putExtra("intent",0)
            startActivity(intent)
        }
    }
}