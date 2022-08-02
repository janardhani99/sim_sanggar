package com.example.sim_sanggar.view.activity.report_anak

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.lifecycle.Transformations.map
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sim_sanggar.GlobalClass
import com.example.sim_sanggar.R
import com.example.sim_sanggar.common.Utilities
import com.example.sim_sanggar.common.clickWithDebounce
import com.example.sim_sanggar.data.model.anak.AnakListItem
import com.example.sim_sanggar.data.model.anak.AnakListResponse
import com.example.sim_sanggar.data.model.anak.AnakResponse
import com.example.sim_sanggar.data.model.common.EmptyResponse
import com.example.sim_sanggar.data.model.daftar.DaftarListResponse
import com.example.sim_sanggar.data.model.daftar.DaftarResponse
import com.example.sim_sanggar.data.model.daftar.PendaftaranAnak
import com.example.sim_sanggar.data.model.jadwal_sanggar.JadwalSanggarItem
import com.example.sim_sanggar.data.model.jadwal_sanggar.JadwalSanggarListResponse
import com.example.sim_sanggar.data.model.jadwal_sanggar.JadwalSanggarResponse
import com.example.sim_sanggar.data.model.report_anak.*
import com.example.sim_sanggar.presenter.anak.AnakContract
import com.example.sim_sanggar.presenter.anak.AnakPresenter
import com.example.sim_sanggar.presenter.daftar.DaftarListContract
import com.example.sim_sanggar.presenter.daftar.DaftarListPresenter
import com.example.sim_sanggar.presenter.jadwal_sanggar.JadwalSanggarContract
import com.example.sim_sanggar.presenter.jadwal_sanggar.JadwalSanggarPresenter
import com.example.sim_sanggar.presenter.report_anak.PertemuanContract
import com.example.sim_sanggar.presenter.report_anak.ReportAnakContract
import com.example.sim_sanggar.presenter.report_anak.ReportAnakPresenter
import com.example.sim_sanggar.view.activity.common.BaseActivity
import com.example.sim_sanggar.view.adapter.report_anak.ReportAnakAdapter
import kotlinx.android.synthetic.main.activity_report_anak.*

class ReportAnakActivity : BaseActivity(), DaftarListContract.View, AnakContract.View, JadwalSanggarContract.View, PertemuanContract.View, ReportAnakContract.View {

    var listAnakTerdaftar : List<PendaftaranAnak>? = null
    private var presenterAnakTerdaftar = DaftarListPresenter(this)

    var listAnak : List<AnakListItem>? = null
    private var presenterAnak = AnakPresenter(this)

    var listKelas : List<JadwalSanggarItem>? = null
//    lateinit var kategori_kelas: List<String?>
    var presenterKelas = JadwalSanggarPresenter(this)
    var selectedKelas: Int? = null
    var listReport : List<ReportAnakData>? = null
    var presenterReportAnak = ReportAnakPresenter(this)
    var selectedAnak: Int? = null

    lateinit var adapter : ReportAnakAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_report_anak)

        setToolbar()


        presenterAnakTerdaftar.getListDaftar()
//        presenterAnak.getAnak()
        presenterKelas.getJadwal()

        initListener()
        initAdapter()
    }


    fun AutoCompleteTextView.setArrayAdapter(list: List<String?>?) {
        val adapter = list?.let { ArrayAdapter(GlobalClass.context, R.layout.layout_dropdown_item, it) }
        this.setAdapter(adapter)
    }

    private fun initAdapter() {
//        val nama_anak = listAnak?.map { it.nama }
//        val anak_terdaftar = listAnakTerdaftar?.map { it.anak_id }

        val kategori_kelas = listKelas?.map { it.kategori_latihan }
//        Log.i("adapter anak", nama_anak.toString())

        ac_pilih_anak?.run {
            if (kategori_kelas != null) {
                setArrayAdapter(kategori_kelas)
//                isLoading(true)
                selectedKelas = listKelas?.find { it.kategori_latihan == ac_pilih_anak.text.toString() }?.id

                selectedKelas?.let { presenterAnakTerdaftar.getAnakOnKelas(it)}


            }
//            if (anak_terdaftar != null) {
//            anak_terdaftar?.let { setArrayAdapter(it) }
//            }
        }

//        ac_pilih_kelas?.run {
//            if (kategori_latihan != null) {
//                setArrayAdapter(kategori_latihan)
//            }
//        }
//
//        adapter = ReportAnakAdapter()
//
//        rv_report_anak?.layoutManager = LinearLayoutManager(this)
//        rv_report_anak?.adapter = adapter
    }
//
    private fun initAdapterAnak() {
        val nama_anak = listAnakTerdaftar?.map { it.anak_id?.nama }
        Log.i("manaa", nama_anak.toString())

        ac_pilih_kelas?.run {
            if (nama_anak != null) {
                setArrayAdapter(nama_anak)

            }

        }
    }

    private fun initListener() {
        cv_cari_report?.clickWithDebounce {
//            selectedAnak = listAnakTerdaftar?.find { it.anak_id?.nama == ac_pilih_anak.text.toString() }?.id



            if (selectedKelas != null) {
                Log.i("Yes", selectedAnak.toString())
                Log.i("adakah", selectedKelas.toString())
//                val contoh = 21
//                presenterReportAnak.loadDataSearch(contoh)
//                initAdapter()
//                adapter = ReportAnakAdapter { detailItem ->
                    

            }
            else {
                Log.i("No", "gaadaa")
            }

        }
    }

    private fun fetchData() {


    }

    override fun onResume() {
        super.onResume()
        initAdapter()

    }

    private fun isLoading(isLoad: Boolean) {
        if (isLoad) Utilities.showProgress(this)
        else {
            Utilities.hideProgress()
        }
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
        initListener()


    }

    override fun deleteJadwalResponse(response: EmptyResponse) {
        TODO("Not yet implemented")
    }

    override fun anakResponse(response: AnakResponse) {
        TODO("Not yet implemented")
    }

    override fun getAnakResponse(response: AnakListResponse) {
//        listAnak = response.data
//        initAdapter()
    }

    override fun progressAnakResponse(response: ReportAnakResponse) {
        TODO("Not yet implemented")
    }

    override fun getProgressAnakResponse(response: ReportAnakListResponse) {
        TODO("Not yet implemented")
    }

    override fun getDetailProgress(response: ReportAnakResponse) {
        TODO("Not yet implemented")
    }

    override fun loadDataSearch(response: ReportAnakListResponse) {
        listReport = response.data
        initListener()

    }

    override fun daftarListResponse(response: DaftarResponse) {
        TODO("Not yet implemented")
    }

    override fun getDaftarListResponse(response: DaftarListResponse) {
        listAnakTerdaftar = response.data
        initAdapter()
    }

    override fun getBiayaPendaftaran(response: DaftarListResponse) {
        TODO("Not yet implemented")
    }

    override fun deleteDaftarListResponse(response: EmptyResponse) {
        TODO("Not yet implemented")
    }

    override fun uploadImageResponse() {
        TODO("Not yet implemented")
    }

    override fun getAnakOnKelasResponse(response: DaftarListResponse) {
        isLoading(false)
        initAdapterAnak()
    }

    override fun showError(title: String, message: String) {
        this.showErrorAlert(title, message)
    }


}