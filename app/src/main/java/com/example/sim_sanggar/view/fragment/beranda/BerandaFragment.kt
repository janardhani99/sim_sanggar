package com.example.sim_sanggar.view.fragment.beranda

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
//import androidx.cardview.widget.CardView
import com.example.sim_sanggar.R
import com.example.sim_sanggar.common.clickWithDebounce
import kotlinx.android.synthetic.main.fragment_beranda.*
import com.example.sim_sanggar.view.activity.jadwalsanggar.JadwalLatihanActivity
import com.example.sim_sanggar.view.activity.sewa.SewaActivity
import com.example.sim_sanggar.view.activity.kegiatan.KegiatanActivity


class BerandaFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
//    super.onCreate(savedInstanceState)
        return inflater.inflate(R.layout.fragment_beranda, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        initView()

        cv_jadwalsanggar?.clickWithDebounce {
            startActivity(Intent(context, JadwalLatihanActivity::class.java))
        }

//        cv_fasilitas?.clickWithDebounce {
//            startActivity(Intent(context, FasilitasActivity::class.java))
//        }

        cv_kegiatansanggar?.clickWithDebounce {
            startActivity(Intent(context, KegiatanActivity::class.java))
        }
    }

//    fun initView() {
//        rv_home.layoutManager = layoutManager
//        cardViewAdapter = HomeCardviewAdapter(activity!!)
//        rv_home.adapter = cardViewAdapter
//
//        addCardviewList.add(HomeCardview("android.resource://" + getPackage("com.example.sim_sanggar") + "/" + R.drawable.iconjadwal,"Jadwal Sanggar", "Lihat Hari Apa Sanggarmu Berlatih"))
//        addCardviewList.add(HomeCardview("android.resource://" + getPackage("com.example.sim_sanggar") + + R.drawable.iconsewa,"Jadwal Booking Studio", "Cek Slot Booking"))
//        addCardviewList.add(HomeCardview("android.resource://" + getPackage("com.example.sim_sanggar") + + R.drawable.iconinfo,"Info Sanggar", "Lihat Informasi Sanggarmu"))
//        cardViewAdapter.setCardview(addCardviewList)
//
//    }

}