package com.example.sanggar.view.fragment.sanggar

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sanggar.GlobalClass
import com.example.sanggar.R
import com.example.sanggar.common.Preferences
import com.example.sanggar.common.Utilities
import com.example.sanggar.common.clickWithDebounce
import com.example.sanggar.data.model.sanggar.ProfilSanggarListResponse
import com.example.sanggar.data.model.sanggar.ProfilSanggarResponse
import com.example.sanggar.data.model.sanggar.SanggarData
import com.example.sanggar.presenter.sanggar.ProfilSanggarContract
import com.example.sanggar.presenter.sanggar.ProfilSanggarPresenter
import com.example.sanggar.view.activity.auth.LoginActivity
import com.example.sanggar.view.activity.kegiatan.DetailKegiatanActivity
import com.example.sanggar.view.activity.sanggar.EditProfilActivity
import com.example.sanggar.view.adapter.sanggar.ProfilSanggarAdapter
import kotlinx.android.synthetic.main.activity_kegiatan.*
import kotlinx.android.synthetic.main.fragment_profil_sanggar.*
import kotlinx.android.synthetic.main.recycler_profile_sanggar.*


class ProfilFragment(): Fragment(), ProfilSanggarContract.View {

//    var data : SanggarData? = null
//    private var presenter = ProfilSanggarPresenter(this)
//    lateinit var adapter: ProfilSanggarAdapter

    var preferences = Preferences(GlobalClass.context)
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profil_sanggar, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        data = getActivity()?.intent?.getParcelableExtra<SanggarData>("data")
//        initAdapter()
        initListener()
//        data?.let { setView(it) }
    }

    private fun initListener() {
        btn_edit?.clickWithDebounce {
            val intent = Intent(context, EditProfilActivity::class.java)
            startActivity(intent)
        }

        btn_logout?.clickWithDebounce {
            preferences.userLoggedOut()
            activity?.finishAffinity()
            startActivity(Intent(context, LoginActivity::class.java))
        }

    }
//
//    private fun initAdapter() {
//        adapter = ProfilSanggarAdapter {
//            detailItem -> val intent = (Intent(context, EditProfilActivity::class.java ))
//            intent.putExtra("data", detailItem)
//            startActivity(intent)
//        }
//        rv_profile_sanggar?.layoutManager = LinearLayoutManager(activity)
//        rv_profile_sanggar?.adapter = adapter
//    }
//
//    private fun isLoading(isLoad: Boolean) {
//        if (isLoad) this.context?.let { Utilities.showProgress(it) }
//        else Utilities.hideProgress()
//    }
//
//    private fun setView(data: SanggarData) {
//
//        data?.run {
//            tv_nama_sanggar?.text = data.nama_sanggar
////            tv_alamat_sanggar?.text = data.alamat
////            tv_no_telepon?.text = data.telepon
////            tv_bank?.text = data.bank
////            tv_no_rekening?.text = data.nomor_rekening
////
//////            tv_harga_pendafataran.setText(data.harga_pendaftaran_siswa)
//////            tv_harga_sewa.setText(data.harga_penyewaan_siswa)
//        }
//
////        var data = SanggarData()
////        tv_nama_sanggar?.setText(data?.nama_sanggar)
////        tv_alamat_sanggar?.setText(data?.alamat)
////        tv_no_telepon?.setText(data?.telepon)
////        tv_bank?.setText(data?.bank)
////        tv_no_rekening?.setText(data?.nomor_rekening)
////        tv_harga_pendafataran?.setText(data?.harga_pendaftaran_siswa?.let { Integer.toString(it) })
////        tv_harga_sewa?.setText(data?.harga_penyewaan_siswa?.let { Integer.toString(it) })
//
//    }
//
    override fun profilSanggarResponse(response: ProfilSanggarResponse) {
        TODO("Not yet implemented")
    }

    override fun getProfilSanggarResponse(response: ProfilSanggarListResponse) {
//        isLoading(false)
//        response.data?.let { adapter.setData(it) }

    }

    override fun showError(title: String, message: String) {
        TODO("Not yet implemented")
    }
//
}