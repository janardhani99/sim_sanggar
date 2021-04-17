package com.example.sim_sanggar.view.activity.jadwalsanggar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.sim_sanggar.R
import kotlinx.android.synthetic.main.activity_jadwal_latihan.*
import kotlinx.android.synthetic.main.recycler_jadwal_sanggar_item.*

class JadwalLatihanActivity : AppCompatActivity() {
    var b : Bundle? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_jadwal_latihan)

//        initView()
    }

    fun initView() {
        b = intent.extras

        kategori_latihan.text = b?.getString("judul")
        hari_latihan.text = b?.getString("deskripsi")

    }
}