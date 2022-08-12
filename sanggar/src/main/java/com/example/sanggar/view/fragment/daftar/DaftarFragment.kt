package com.example.sanggar.view.fragment.daftar

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Spinner
import com.example.sanggar.R
import com.example.sanggar.data.model.sanggar.ProfilSanggarListResponse
import com.example.sanggar.data.model.sanggar.ProfilSanggarResponse
import com.example.sanggar.data.model.sanggar.SanggarData
import com.example.sanggar.presenter.sanggar.ProfilSanggarContract
import com.example.sanggar.view.activity.common.BaseActivity
import com.example.sanggar.view.adapter.daftar.DaftarPagerAdapter
import kotlinx.android.synthetic.main.fragment_daftar_pager.*

class DaftarFragment() : Fragment(), ProfilSanggarContract.View {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//
//
//    }

    private lateinit var baseActivity: BaseActivity
    var data = SanggarData()

    override fun onStart() {
        super.onStart()
        baseActivity = (activity as BaseActivity)
//        setView(data)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_daftar_pager, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val pagerAdapter = DaftarPagerAdapter(childFragmentManager)
        vp_daftar?.adapter = pagerAdapter

//        judul view pager
        tl_daftar.setupWithViewPager(vp_daftar)


    }

    private fun setView(data: SanggarData) {
        data?.run {
            tv_nama_sanggar?.text = data.nama
//            til_nama_sanggar?.editText?.setText(data.telepon)
        }

    }

    override fun profilSanggarResponse(response: ProfilSanggarResponse) {
        TODO("Not yet implemented")
    }

    override fun getProfilSanggarResponse(response: ProfilSanggarListResponse) {
        TODO("Not yet implemented")
    }

    override fun showError(title: String, message: String) {

        baseActivity.showErrorAlert(title, message)
    }
}