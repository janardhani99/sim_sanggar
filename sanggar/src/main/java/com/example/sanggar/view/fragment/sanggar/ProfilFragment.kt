package com.example.sanggar.view.fragment.sanggar

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.sanggar.R
import com.example.sanggar.common.clickWithDebounce
import com.example.sanggar.data.model.sanggar.SanggarData
import com.example.sanggar.view.activity.sanggar.EditProfilActivity
import kotlinx.android.synthetic.main.activity_edit_profil.*
import kotlinx.android.synthetic.main.fragment_profil_sanggar.*


class ProfilFragment(): Fragment() {

    var data: SanggarData? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        data?.let { setView(it) }

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profil_sanggar, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btn_edit_profil?.clickWithDebounce {
            startActivity(Intent(context, EditProfilActivity::class.java ))
        }
    }

//    override fun onStart() {
//        super.onStart()
//        initView()
//    }

    private fun setView(data: SanggarData) {

        data?.run {
            tv_nama_sanggar?.text = data.nama_sanggar
            tv_alamat_sanggar?.text = data.alamat
            tv_no_telepon?.text = data.telepon
            tv_bank?.text = data.bank
            tv_no_rekening?.text = data.nomor_rekening

//            tv_harga_pendafataran.setText(data.harga_pendaftaran_siswa)
//            tv_harga_sewa.setText(data.harga_penyewaan_siswa)
        }

//        var data = SanggarData()
//        tv_nama_sanggar?.setText(data?.nama_sanggar)
//        tv_alamat_sanggar?.setText(data?.alamat)
//        tv_no_telepon?.setText(data?.telepon)
//        tv_bank?.setText(data?.bank)
//        tv_no_rekening?.setText(data?.nomor_rekening)
//        tv_harga_pendafataran?.setText(data?.harga_pendaftaran_siswa?.let { Integer.toString(it) })
//        tv_harga_sewa?.setText(data?.harga_penyewaan_siswa?.let { Integer.toString(it) })

    }

}