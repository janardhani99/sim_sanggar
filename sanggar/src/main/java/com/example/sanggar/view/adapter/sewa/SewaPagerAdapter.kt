package com.example.sanggar.view.adapter.sewa

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.sanggar.view.fragment.sewa.SewaListFragment

class SewaPagerAdapter(fm: FragmentManager):
        FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    val title = listOf<String>(
            "Menunggu Verifikasi",
            "Terverifikasi",
            "Batal"
    )

    private val fragment = listOf(
            SewaListFragment(),
            SewaListFragment(),
            SewaListFragment()
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