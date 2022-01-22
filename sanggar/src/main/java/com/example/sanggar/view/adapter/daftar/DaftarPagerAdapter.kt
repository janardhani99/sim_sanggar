package com.example.sanggar.view.adapter.daftar

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.sanggar.view.fragment.daftar.DaftarListFragment
import java.lang.reflect.Array.newInstance

class DaftarPagerAdapter(fm: FragmentManager):
        FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    private val title = listOf(
            "Menunggu Verifikasi",
            "Terdaftar",
            "Batal"
    )
    private val status = listOf("0", "1", "2")
//    private val status = listOf( "Menunggu Verifikasi",
//        "Terdaftar",
//        "Dibatalkan")

//    private val fragment = listOf(
//            DaftarListFragment(),
//            DaftarListFragment(),
//            DaftarListFragment(),
//            DaftarListFragment()
//    )
    override fun getItem(position: Int): Fragment {

//        return fragment[position]
        return DaftarListFragment.newInstance(status[position])
    }

    override fun getCount(): Int {
        return status.count()
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return title[position]
    }
}