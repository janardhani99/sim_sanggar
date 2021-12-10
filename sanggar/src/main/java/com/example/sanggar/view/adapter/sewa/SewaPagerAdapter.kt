package com.example.sanggar.view.adapter.sewa

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.sanggar.view.fragment.daftar.DaftarListFragment
import com.example.sanggar.view.fragment.sewa.SewaListFragment

class SewaPagerAdapter(fm: FragmentManager):
        FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    val title = listOf<String>(
            "Menunggu Verifikasi",
            "Terverifikasi",
            "Batal"
    )

    val status = listOf("0", "1", "2")
//
//    private val fragment = listOf(
//            SewaListFragment(),
//            SewaListFragment(),
//            SewaListFragment()
//    )

    override fun getItem(position: Int): Fragment {
        return DaftarListFragment.newInstance(status[position])
    }

    override fun getCount(): Int {
        return status.count()
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return title[position]
    }


}