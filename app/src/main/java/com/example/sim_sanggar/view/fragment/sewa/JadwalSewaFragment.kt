package com.example.sim_sanggar.view.fragment.sewa

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.sim_sanggar.R
import com.example.sim_sanggar.view.activity.sewa.UploadBuktiActivity
import com.example.sim_sanggar.common.clickWithDebounce
import com.example.sim_sanggar.view.activity.sewa.RiwayatSewaActivity
import com.example.sim_sanggar.view.activity.sewa.SewaActivity
import kotlinx.android.synthetic.main.fragment_jadwal_sewa.*


class JadwalSewaFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_jadwal_sewa, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btn_sewa?.clickWithDebounce {
            startActivity(Intent(context, SewaActivity::class.java))
        }

        btn_riwayat_sewa?.clickWithDebounce {
            startActivity(Intent(context, RiwayatSewaActivity::class.java))
        }
    }
}

