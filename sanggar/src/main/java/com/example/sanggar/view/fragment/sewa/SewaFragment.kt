package com.example.sanggar.view.fragment.sewa

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.sanggar.R
import com.example.sanggar.view.adapter.sewa.SewaPagerAdapter
import kotlinx.android.synthetic.main.fragment_sewa_pager.*

class SewaFragment:Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_sewa_pager, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val pagerAdapter = SewaPagerAdapter(childFragmentManager)
        vp_sewa?.adapter = pagerAdapter
        tl_sewa.setupWithViewPager(vp_sewa)
    }
}