package com.example.sanggar.view.activity.absensi_anak

import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sanggar.R
import com.example.sanggar.common.Utilities
import com.example.sanggar.common.Utilities.doRequest
import com.example.sanggar.common.clickWithDebounce
import com.example.sanggar.data.model.absensi.PertemuanData
import com.example.sanggar.data.model.absensi.PertemuanDataListResponse
import com.example.sanggar.data.model.absensi.PertemuanDataResponse
import com.example.sanggar.data.model.common.EmptyResponse
import com.example.sanggar.data.model.daftar.DaftarListResponse
import com.example.sanggar.data.model.daftar.DaftarResponse
import com.example.sanggar.data.model.daftar.PendaftaranAnak
import com.example.sanggar.data.model.jadwal_sanggar.JadwalSanggarItem
import com.example.sanggar.presenter.DatePickerHelper
import com.example.sanggar.presenter.absensi.PertemuanContract
import com.example.sanggar.presenter.absensi.PertemuanPresenter
import com.example.sanggar.presenter.anak.AnakContract
import com.example.sanggar.presenter.daftar.DaftarListContract
import com.example.sanggar.presenter.daftar.DaftarListPresenter
import com.example.sanggar.view.activity.common.BaseActivity
import com.example.sanggar.view.adapter.absensi.ProgressAnakAdapter
import com.example.sanggar.view.adapter.anakterdaftar.AnakTerdaftarAdapter
import com.example.sanggar.view.fragment.daftar.DaftarListFragment
import com.example.sim_sanggar.presenter.anak.AnakPresenter
import kotlinx.android.synthetic.main.activity_detail_pertemuan.btn_pilih_tanggal
import kotlinx.android.synthetic.main.activity_detail_pertemuan.btn_simpan_pertemuan
import kotlinx.android.synthetic.main.activity_detail_pertemuan.til_pertemuan_ke
import kotlinx.android.synthetic.main.activity_detail_pertemuan.til_tanggal_sewa
import kotlinx.android.synthetic.main.activity_progress_anak.*
import kotlinx.android.synthetic.main.fragment_toolbar.*
import java.util.*
import kotlin.collections.HashMap

//private const val STATUS = "status"

class ProgressAnakActivity : BaseActivity(), PertemuanContract.View, DaftarListContract.View{

    var data_pertemuan: PertemuanData? = null
    var data_anak : PendaftaranAnak? = null
    var data_kelas: JadwalSanggarItem? = null

    private var presenter = PertemuanPresenter(this)
    private var presenterAnak = DaftarListPresenter(this)

    lateinit var datePicker: DatePickerHelper
    lateinit var adapter : AnakTerdaftarAdapter
//    lateinit var adapter : ProgressAnakAdapter

//    private var status: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_progress_anak)

        setToolbar()
        toolbar_title.text = "Edit Pertemuan"

//        data_anak = intent.getParcelableExtra<PendaftaranAnak>("data_anak")
        data_pertemuan = intent.getParcelableExtra<PertemuanData>("data_pertemuan")
        data_kelas = intent.getParcelableExtra<JadwalSanggarItem>("data_kelas")

        datePicker = DatePickerHelper(this)
        data_pertemuan?.let { setView(it) }

        initListener()
        initAdapter()

//        status = intent.getStringExtra(STATUS).toString()

    }

    private fun showDatePickerDialog() {
        val cal = Calendar.getInstance()
        val d = cal.get(Calendar.DAY_OF_MONTH)
        val m = cal.get(Calendar.MONTH)
        val y = cal.get(Calendar.YEAR)

        val minDate = Calendar.getInstance()
        minDate.set(Calendar.YEAR, 2022)
        minDate.set(Calendar.HOUR_OF_DAY, 8)
        minDate.set(Calendar.MINUTE, 0)
        minDate.set(Calendar.SECOND, 0)
        datePicker.setMinDate(minDate.timeInMillis)

        val maxDate = Calendar.getInstance()
        maxDate.add(Calendar.DAY_OF_MONTH, 30)
        datePicker.setMaxDate(maxDate.timeInMillis)

        datePicker.showDialog(y, m, d, object: DatePickerHelper.Callback {
            override fun onDateSelected(year: Int, month: Int, dayOfMonth: Int) {
                val dayStr = if(dayOfMonth<10) "0${dayOfMonth}" else "${dayOfMonth}"
                val mon = month + 1
                val monthStr = if (mon<10) "0${mon}" else "${mon}"
                til_tanggal_sewa.editText?.setText("${year}-${monthStr}-${dayStr}")

            }
        })
    }

    private fun initListener() {
        btn_pilih_tanggal?.clickWithDebounce {
            showDatePickerDialog()
        }
        btn_simpan_pertemuan?.clickWithDebounce {
            editPertemuan()
        }
    }

    private fun editPertemuan() {

        val pertemuan_ke = til_pertemuan_ke?.editText?.text.toString()
        val tanggal = til_tanggal_sewa?.editText?.text.toString()
//        val jadwal_sanggar = data_kelas?.id

        val editData = HashMap<String, Any?>()
        editData["pertemuan_ke"] = pertemuan_ke
        editData["tanggal"] = tanggal
//        tambahData["jadwal_sanggar_id"] = jadwal_sanggar

        isLoading(true)

        data_pertemuan?.id?.let { presenter.editPertemuan(it, editData)}

    }

    private fun setView(data: PertemuanData) {
        data?.run {
//            data?.id?.let { til_kelas.editText?.setText(it) }
//            til_kelas?.editText?.setText(data.id)
            til_pertemuan_ke?.editText?.setText(data.pertemuan_ke)
            til_tanggal_sewa?.editText?.setText(data.tanggal)
        }

        btn_simpan_pertemuan?.clickWithDebounce {
            editPertemuan()
        }
    }

    private fun initAdapter() {

        adapter = AnakTerdaftarAdapter { detailItem->
            val intent = Intent(this, DetailProgressAnakActivity::class.java)
            intent.putExtra("data_anak", data_anak)
//            intent.putExtra("data_pertemuan", data_pertemuan)
            intent.putExtra("data_progress", detailItem)
            startActivity(intent)
        }
        rv_progress_anak?.layoutManager = LinearLayoutManager(this)
        rv_progress_anak?.adapter = adapter
    }

    override fun onResume() {
        super.onResume()
        fetchData()
    }
    

    private fun fetchData() {
        isLoading(true)
        data_kelas?.id?.let { presenterAnak.getAnakOnKelas(it) }
//        doRequest {
//            status?.let { presenterAnak.getAnakTerdaftar() }
//        }

//        presenterAnak.getAnakOnKelas()

    }

    private fun isLoading(isLoad: Boolean) {
        if (isLoad) Utilities.showProgress(this)
        else Utilities.hideProgress()
    }

    override fun pertemuanResponse(response: PertemuanDataResponse) {
        isLoading(false)
        this.showCustomDialogBack("Berhasil", "Data berhasil disimpan")
    }

    override fun getPertemuanResponse(response: PertemuanDataListResponse) {

    }

    override fun deletePertemuanResponse(response: EmptyResponse) {
        TODO("Not yet implemented")
    }


    override fun daftarListResponse(response: DaftarResponse) {
        TODO("Not yet implemented")
    }

    override fun getDaftarListResponse(response: DaftarListResponse) {

    }

    override fun getAnakTerdaftarResponse(response: DaftarListResponse) {

    }

    override fun getAnakOnKelasResponse(response: DaftarListResponse) {
        isLoading(false)
        response.data?.let { adapter.setData(it) }
    }

    override fun deleteDaftarListResponse(response: EmptyResponse) {
        TODO("Not yet implemented")
    }

    override fun showError(title: String, message: String) {
        this.showErrorAlert(title, message)
    }
}