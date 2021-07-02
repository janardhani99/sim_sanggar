package com.example.sanggar.view.fragment.daftar

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Spinner
import com.example.sanggar.R
import com.example.sanggar.view.adapter.daftar.DaftarPagerAdapter
import kotlinx.android.synthetic.main.fragment_daftar_anak.*

class DaftarFragment : Fragment() {
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
        val pagerAdapter = DaftarPagerAdapter(childFragmentManager)
        vp_daftar?.adapter = pagerAdapter
        tl_daftar.setupWithViewPager(vp_daftar)
    }
}