package com.example.sim_sanggar.view.fragment.daftar

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sim_sanggar.R
import com.example.sim_sanggar.common.Utilities
import com.example.sim_sanggar.common.clickWithDebounce
import com.example.sim_sanggar.data.model.common.EmptyResponse
import com.example.sim_sanggar.data.model.daftar.DaftarListResponse
import com.example.sim_sanggar.data.model.daftar.DaftarResponse
import com.example.sim_sanggar.data.model.jadwal_sanggar.JadwalSanggarItem
import com.example.sim_sanggar.data.model.jadwal_sanggar.JadwalSanggarListResponse
import com.example.sim_sanggar.data.model.jadwal_sanggar.JadwalSanggarResponse
import com.example.sim_sanggar.presenter.daftar.DaftarListContract
import com.example.sim_sanggar.presenter.daftar.DaftarListPresenter
import com.example.sim_sanggar.presenter.jadwal_sanggar.JadwalSanggarContract
import com.example.sim_sanggar.presenter.jadwal_sanggar.JadwalSanggarPresenter
import com.example.sim_sanggar.view.activity.anak.AnakActivity
import com.example.sim_sanggar.view.activity.anak.AnakTerdaftarActivity
import com.example.sim_sanggar.view.activity.daftar.DaftarKelasActivity
import com.example.sim_sanggar.view.activity.daftar.FormDaftarKelasActivity
import com.example.sim_sanggar.view.adapter.daftar.DaftarKelasAdapter
import kotlinx.android.synthetic.main.fragment_daftar.*

class DaftarFragment : Fragment(){

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_daftar, container, false)


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        btn_daftar_kelas?.clickWithDebounce {
            startActivity(Intent(context, DaftarKelasActivity::class.java))
        }

    }

}