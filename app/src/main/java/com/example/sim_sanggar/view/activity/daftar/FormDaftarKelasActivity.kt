package com.example.sim_sanggar.view.activity.daftar

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
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
import com.example.sim_sanggar.presenter.anak.AnakContract
import com.example.sim_sanggar.presenter.anak.AnakPresenter
import com.example.sim_sanggar.presenter.daftar.DaftarListContract
import com.example.sim_sanggar.presenter.daftar.DaftarListPresenter
import com.example.sim_sanggar.presenter.jadwal_sanggar.JadwalSanggarContract
import com.example.sim_sanggar.view.activity.common.BaseActivity
import kotlinx.android.synthetic.main.activity_bayar_pendaftaran.*
import kotlinx.android.synthetic.main.activity_form_daftar_kelas.*
import kotlinx.android.synthetic.main.activity_form_daftar_kelas.til_kategori_kelas
import kotlinx.android.synthetic.main.activity_form_daftar_kelas.til_total_bayar

class FormDaftarKelasActivity : BaseActivity(), AnakContract.View, JadwalSanggarContract.View, DaftarListContract.View{

    var data_anak: List<AnakListItem>? = null
    var data_kelas: JadwalSanggarItem? = null
    var data_daftar : PendaftaranAnak? = null
    private var presenterAnak = AnakPresenter(this)
    private var presenterDaftar = DaftarListPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form_daftar_kelas)

        data_kelas = intent.getParcelableExtra<JadwalSanggarItem>("data_kelas")
        data_kelas?.let { setView(it) }
        initListener()
        presenterAnak.getAnak()

        initAdapter()
        initListener()
    }

    private fun initListener() {
        btn_lanjutkan?.clickWithDebounce {
            simpanData()

            val intent = Intent(this, PlatformTransaksiActivity::class.java)
            intent.putExtra("data_daftar", data_daftar )
//            intent.putExtra("data_kelas", data_kelas)
            startActivity(intent)
        }
    }

    fun AutoCompleteTextView.setArrayAdapter(list: List<String?>) {
        val adapter = ArrayAdapter(GlobalClass.context, R.layout.layout_dropdown_item, list)
        this.setAdapter(adapter)
    }

    private fun initAdapter() {
        val nama_anak = data_anak?.map { it.nama }

        ac_nama_anak?.run {
            if (nama_anak != null) {
                setArrayAdapter(nama_anak)
            }

//            data_anak?.find{it.nama == ac_nama_anak?.text.toString()}?.id?.let { til_anak_id.editText?.setText(it).toString() }

        }


//        data_daftar?.jadwal_sanggar_id = data_kelas


    }

    private fun setView(data: JadwalSanggarItem) {
        data.run {
            til_kategori_kelas?.editText?.setText(data.kategori_latihan)
            til_total_bayar?.editText?.setText(data.biaya)
        }

    }

    private fun simpanData() {
        val selectedAnak  = data_anak?.find{it.nama == ac_nama_anak?.text.toString()}?.id
        val kelasId = data_kelas?.id

        val tambahData = HashMap<String, Any?>()
        tambahData["anak_id"] = selectedAnak
        tambahData["jadwal_sanggar_id"] = kelasId

        isLoading(true)
        presenterDaftar.addListDaftar(tambahData)

    }

    private fun isLoading(isLoad: Boolean) {
        if (isLoad) Utilities.showProgress(this)
        else Utilities.hideProgress()
    }

    override fun anakResponse(response: AnakResponse) {
        TODO("Not yet implemented")
    }

    override fun getAnakResponse(response: AnakListResponse) {
        data_anak = response.data
        initAdapter()
        initListener()
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

    override fun daftarListResponse(response: DaftarResponse) {
        isLoading(false)
        this.showCustomDialogBack("Berhasil", "Lanjutkan pembayaran")
    }

    override fun getDaftarListResponse(response: DaftarListResponse) {
        TODO("Not yet implemented")
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

    override fun showError(title: String, message: String) {
        this.showErrorAlert(title, message)
    }
}