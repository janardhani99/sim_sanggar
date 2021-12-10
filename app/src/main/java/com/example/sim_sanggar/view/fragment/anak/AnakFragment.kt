package com.example.sim_sanggar.view.fragment.anak

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.sim_sanggar.R
import com.example.sim_sanggar.common.clickWithDebounce
import com.example.sim_sanggar.view.activity.anak.AnakActivity
import com.example.sim_sanggar.view.activity.anak.AnakTerdaftarActivity
import com.example.sim_sanggar.view.activity.platform_transaksi.PlatformTransaksiActivity
import kotlinx.android.synthetic.main.fragment_daftar_anak.*
import kotlinx.android.synthetic.main.fragment_daftar_anak.btn_platform_transaksi
import kotlinx.android.synthetic.main.fragment_jadwal_sewa.*

class AnakFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_daftar_anak, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btn_daftar_sekarang?.clickWithDebounce {
            startActivity(Intent(context, AnakActivity::class.java))
        }

        btn_list_anak?.clickWithDebounce {
            startActivity(Intent(context, AnakTerdaftarActivity::class.java))
        }

        btn_platform_transaksi?.clickWithDebounce {
            startActivity(Intent(context, PlatformTransaksiActivity::class.java))
        }
    }

}