package com.example.sim_sanggar.view.activity.report_anak

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.sim_sanggar.R
import com.example.sim_sanggar.data.model.anak.AnakListResponse
import com.example.sim_sanggar.data.model.anak.AnakResponse
import com.example.sim_sanggar.data.model.common.EmptyResponse
import com.example.sim_sanggar.data.model.daftar.DaftarListResponse
import com.example.sim_sanggar.data.model.daftar.DaftarResponse
import com.example.sim_sanggar.data.model.jadwal_sanggar.JadwalSanggarItem
import com.example.sim_sanggar.data.model.jadwal_sanggar.JadwalSanggarListResponse
import com.example.sim_sanggar.data.model.jadwal_sanggar.JadwalSanggarResponse
import com.example.sim_sanggar.data.model.report_anak.PertemuanDataListResponse
import com.example.sim_sanggar.data.model.report_anak.PertemuanDataResponse
import com.example.sim_sanggar.data.model.report_anak.ReportAnakListResponse
import com.example.sim_sanggar.data.model.report_anak.ReportAnakResponse
import com.example.sim_sanggar.presenter.anak.AnakContract
import com.example.sim_sanggar.presenter.daftar.DaftarListContract
import com.example.sim_sanggar.presenter.jadwal_sanggar.JadwalSanggarContract
import com.example.sim_sanggar.presenter.jadwal_sanggar.JadwalSanggarPresenter
import com.example.sim_sanggar.presenter.report_anak.PertemuanContract
import com.example.sim_sanggar.presenter.report_anak.ReportAnakContract
import com.example.sim_sanggar.view.activity.common.BaseActivity

class ReportAnakActivity : BaseActivity(), JadwalSanggarContract.View, PertemuanContract.View {

    var listKelas : List<JadwalSanggarItem>? = null
    var presenterKelas = JadwalSanggarPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_report_anak)

        setToolbar()

    }

    override fun pertemuanResponse(response: PertemuanDataResponse) {
        TODO("Not yet implemented")
    }

    override fun getPertemuanResponse(response: PertemuanDataListResponse) {
        TODO("Not yet implemented")
    }

    override fun deletePertemuanResponse(response: EmptyResponse) {
        TODO("Not yet implemented")
    }

    override fun jadwalSanggarResponse(response: JadwalSanggarResponse) {
        TODO("Not yet implemented")
    }

    override fun getJadwalSanggarResponse(response: JadwalSanggarListResponse) {
        TODO("Not yet implemented")
    }

    override fun deleteJadwalResponse(response: EmptyResponse) {
        TODO("Not yet implemented")
    }

    override fun showError(title: String, message: String) {
        TODO("Not yet implemented")
    }


}