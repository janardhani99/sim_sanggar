package com.example.sim_sanggar.view.activity.report_anak

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import com.example.sim_sanggar.GlobalClass
import com.example.sim_sanggar.R
import com.example.sim_sanggar.common.clickWithDebounce
import com.example.sim_sanggar.data.model.anak.AnakListItem
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
import com.example.sim_sanggar.presenter.anak.AnakPresenter
import com.example.sim_sanggar.presenter.daftar.DaftarListContract
import com.example.sim_sanggar.presenter.jadwal_sanggar.JadwalSanggarContract
import com.example.sim_sanggar.presenter.jadwal_sanggar.JadwalSanggarPresenter
import com.example.sim_sanggar.presenter.report_anak.PertemuanContract
import com.example.sim_sanggar.presenter.report_anak.ReportAnakContract
import com.example.sim_sanggar.view.activity.common.BaseActivity
import kotlinx.android.synthetic.main.activity_report_anak.*

class ReportAnakActivity : BaseActivity(), AnakContract.View, JadwalSanggarContract.View, PertemuanContract.View {

    var listAnak : List<AnakListItem>? = null
    private var presenterAnak = AnakPresenter(this)

    var listKelas : List<JadwalSanggarItem>? = null
    var presenterKelas = JadwalSanggarPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_report_anak)

        setToolbar()

        presenterAnak.getAnak()
        presenterKelas.getJadwal()


        initAdapter()
        initListener()
    }


    fun AutoCompleteTextView.setArrayAdapter(list: List<String?>) {
        val adapter = ArrayAdapter(GlobalClass.context, R.layout.layout_dropdown_item, list)
        this.setAdapter(adapter)
    }

    private fun initAdapter() {
        val nama_anak = listAnak?.map { it.nama }
        val kategori_latihan = listKelas?.map { it.kategori_latihan }

        ac_pilih_anak?.run {
            if (nama_anak != null) {
                setArrayAdapter(nama_anak)
            }
        }

        ac_pilih_kelas?.run {
            if (kategori_latihan != null) {
                setArrayAdapter(kategori_latihan)
            }
        }
    }

    private fun initListener() {
        cv_cari_report?.clickWithDebounce {
            val selectedAnak = listAnak?.find { it.nama == ac_pilih_anak.text.toString() }?.id
            val selectedKelas = listKelas?.find { it.kategori_latihan == ac_pilih_kelas.text.toString() }?.id
            val pertemuan = til_pertemuan_ke.editText?.text.toString()

            cariReport(selectedAnak, selectedKelas, pertemuan)
        }
    }

    fun cariReport(anak: Int?, kelas: Int?, pertemuan: String?) {

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
        listKelas =  response.data
        initAdapter()
    }

    override fun deleteJadwalResponse(response: EmptyResponse) {
        TODO("Not yet implemented")
    }

    override fun anakResponse(response: AnakResponse) {
        TODO("Not yet implemented")
    }

    override fun getAnakResponse(response: AnakListResponse) {
        listAnak = response.data
        initAdapter()
    }

    override fun showError(title: String, message: String) {
        TODO("Not yet implemented")
    }


}