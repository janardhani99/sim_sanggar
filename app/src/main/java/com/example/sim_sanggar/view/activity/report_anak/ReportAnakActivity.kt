package com.example.sim_sanggar.view.activity.report_anak

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Toast
import androidx.lifecycle.Transformations.map
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sim_sanggar.GlobalClass
import com.example.sim_sanggar.R
import com.example.sim_sanggar.common.Utilities
import com.example.sim_sanggar.common.clickWithDebounce
import com.example.sim_sanggar.common.onTextChanged
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
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_report_anak.*

class ReportAnakActivity : BaseActivity(), DaftarListContract.View, JadwalSanggarContract.View, PertemuanContract.View, ReportAnakContract.View {

    var listAnakTerdaftar: List<PendaftaranAnak>? = null
    private var presenterAnakTerdaftar = DaftarListPresenter(this)

        var listKelas : List<JadwalSanggarItem>? = null
//    lateinit var kategori_kelas: List<String?>
//    var listKelas: JadwalSanggarItem? = null

    var presenterKelas = JadwalSanggarPresenter(this)

//    var listReport: List<ReportAnakData>? = null
    var presenterReportAnak = ReportAnakPresenter(this)

    var selectedKelas: Int? = null
    var selectedAnak: Int? = null

    lateinit var adapter: ReportAnakAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_report_anak)

        setToolbar()

//        listKelas = intent.getParcelableExtra<JadwalSanggarItem>("data_kelas")

//        presenterAnakTerdaftar.getListDaftar()
//        presenterAnak.getAnak()
        presenterKelas.getJadwal()

        initListener()
        initAdapterKelas()
        initAdapterReport()
    }


    fun AutoCompleteTextView.setArrayAdapter(list: List<String?>?) {
        val adapter = list?.let { ArrayAdapter(GlobalClass.context, R.layout.layout_dropdown_item, it) }
        this.setAdapter(adapter)
    }


    private fun initAdapterKelas() {
        val kategori_kelas = listKelas?.map { it.kategori_latihan }

        ac_pilih_kelas?.run {
            if (kategori_kelas != null) {
                setArrayAdapter(kategori_kelas)

            }

            onTextChanged {
                selectedKelas = listKelas?.find { it.kategori_latihan == ac_pilih_kelas.text.toString() }?.id
                Log.i("adakah", selectedKelas.toString())
                selectedKelas?.let { presenterAnakTerdaftar.getAnakOnKelas(it) }
            }
        }

    }

    fun clicked(view: View) {



    }

//    private fun fetchData() {
//    initAdapter()
//    }

    private fun initAdapterAnak() {
//        WLog(listAnakTerdaftar)
//        val notNullAnak = data?.filter { it.anak != null }
//        WLog(notNullAnak)
//        val nama_anak = notNullAnak?.map { it.anak?.nama ?: "Jena" }

        val nama_anak = listAnakTerdaftar?.map { it.anak?.nama }
        ac_pilih_anak?.run {
//            Log.i("manaa", nama_anak.toString())
            if (nama_anak != null) {
            setArrayAdapter(nama_anak)
            } else {
                toast("Belum ada Data Anak di Kelas ini")
            }

            onTextChanged {
                selectedAnak = listAnakTerdaftar?.find { it.anak?.nama == ac_pilih_anak.text.toString() }?.id
                Log.d("anaknya mana", selectedAnak.toString())
                initListener()
            }

        }
    }

    private fun initAdapterReport() {
        adapter = ReportAnakAdapter()
        rv_report_anak?.layoutManager = LinearLayoutManager(this)
        rv_report_anak?.adapter = adapter
    }

//    fun WLog (any: Any?){
//        val gsonPretty = GsonBuilder().setPrettyPrinting().create()
//        val pretty = gsonPretty.toJson(any)
//        Log.d("WIDI", pretty)
//    }

    private fun initListener() {
        cv_cari_report?.clickWithDebounce {
            Log.d("anaknya mana", selectedAnak.toString())
            isLoading(true)
            selectedAnak?.let { it1 -> presenterReportAnak.loadDataSearch(it1) }

        }
    }
    override fun onResume() {
        super.onResume()
        initAdapterKelas()
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
        isLoading(false)
        listKelas =  response.data
        initAdapterKelas()

    }

    override fun deleteJadwalResponse(response: EmptyResponse) {
        TODO("Not yet implemented")
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
//        listReport = response.data
//        initListener()
        isLoading(false)
        Log.i("dapet", response.data.toString())
        response.data?.let { adapter.setData(it) }
    }

    override fun daftarListResponse(response: DaftarResponse) {
        TODO("Not yet implemented")
    }

    override fun getDaftarListResponse(response: DaftarListResponse) {

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
//        WLog(response)
        listAnakTerdaftar = response.data
        initAdapterAnak()

    }

    override fun showError(title: String, message: String) {
        this.showErrorAlert(title, message)
    }


}