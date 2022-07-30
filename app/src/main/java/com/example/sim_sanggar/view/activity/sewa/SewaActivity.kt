package com.example.sim_sanggar.view.activity.sewa

import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sim_sanggar.R
import com.example.sim_sanggar.common.clickWithDebounce
import com.example.sim_sanggar.view.activity.common.BaseActivity
import com.example.sim_sanggar.view.adapter.sewa.PlatformTransaksiActivity
import com.example.sim_sanggar.view.adapter.sewaadapter.SewaAdapter
import com.example.sim_sanggar.view.adapter.sewabelumbayaradapter.SewaBelumBayarAdapter
import kotlinx.android.synthetic.main.activity_riwayat_sewa.*
import kotlinx.android.synthetic.main.activity_sewa.*
import kotlinx.android.synthetic.main.activity_sewa.rv_riwayat_sewa

class SewaActivity : BaseActivity() {

    lateinit var adapter : SewaBelumBayarAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sewa)

        initListener()
        initAdapter()
    }

    private fun initListener() {
        cv_tambah_data_sewa?.clickWithDebounce {
            startActivity(Intent(this, TambahSewaActivity::class.java))
        }
    }

    private fun initAdapter() {

        adapter = SewaBelumBayarAdapter {
            detailItem -> val intent = Intent(this, PlatformTransaksiActivity::class.java)
            intent.putExtra("data_sewa", detailItem)
            startActivity(intent)
        }
        rv_riwayat_sewa?.layoutManager = LinearLayoutManager(this)
        rv_riwayat_sewa?.adapter = adapter
    }

}