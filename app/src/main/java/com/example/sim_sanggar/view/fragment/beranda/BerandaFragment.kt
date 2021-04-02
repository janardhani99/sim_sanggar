package com.example.sim_sanggar.view.fragment.beranda

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
//import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sim_sanggar.R
import com.example.sim_sanggar.view.adapter.HomeCardviewAdapter
import kotlinx.android.synthetic.main.fragment_beranda.*
import com.example.sim_sanggar.data.model.HomeCardview


class BerandaFragment : Fragment() {

    lateinit var cardViewAdapter: HomeCardviewAdapter
    val layoutManager = LinearLayoutManager(activity)
    val addCardviewList: MutableList<HomeCardview> = ArrayList()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_beranda, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
    }

    fun initView() {
        rv_home.layoutManager = layoutManager
        cardViewAdapter = HomeCardviewAdapter(activity!!)
        rv_home.adapter = cardViewAdapter

        addCardviewList.add(HomeCardview("android.resource://sim_sanggar" + R.drawable.iconjadwal,"Jadwal Sanggar", "Lihat Hari Apa Sanggarmu Berlatih"))
        addCardviewList.add(HomeCardview("android.resource://sim_sanggar" + R.drawable.iconsewa,"Jadwal Booking Studio", "Cek Slot Booking"))
        addCardviewList.add(HomeCardview("android.resource://sim_sanggar" + R.drawable.iconinfo,"Info Sanggar", "Lihat Informasi Sanggarmu"))
        cardViewAdapter.setCardview(addCardviewList)

    }

}