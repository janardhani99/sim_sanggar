package com.example.sanggar.view.adapter.daftar

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.sanggar.view.fragment.daftar.DaftarListFragment

class DaftarPagerAdapter(fm: FragmentManager):
        FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    val title = listOf<String>(
            "Menunggu Verifikasi",
            "Terverifikasi",
//            "Tes",
            "Batal"

    )
    private val fragment = listOf(
            DaftarListFragment(),
            DaftarListFragment(),
//            DaftarListFragment(),
            DaftarListFragment()
    )
    override fun getItem(position: Int): Fragment {

        return fragment[position]
    }

    override fun getCount(): Int {
        return title.count()
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return title[position]
    }
}