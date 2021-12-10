package com.example.sanggar.view.adapter.daftar

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.sanggar.view.fragment.daftar.DaftarListFragment
import java.lang.reflect.Array.newInstance

class DaftarPagerAdapter(fm: FragmentManager):
        FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    val title = listOf<String>(
            "Menunggu Verifikasi",
            "Terverifikasi",
//            "Tes",
            "Batal"
    )
    val status = listOf("0", "1", "2")

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