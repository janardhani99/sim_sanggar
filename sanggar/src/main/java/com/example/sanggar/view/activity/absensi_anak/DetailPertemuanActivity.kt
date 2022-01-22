package com.example.sanggar.view.activity.absensi_anak

import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sanggar.R
import com.example.sanggar.common.Utilities
import com.example.sanggar.common.clickWithDebounce
import com.example.sanggar.data.model.absensi.PertemuanData
import com.example.sanggar.data.model.absensi.PertemuanDataListResponse
import com.example.sanggar.data.model.absensi.PertemuanDataResponse
import com.example.sanggar.data.model.common.EmptyResponse
import com.example.sanggar.data.model.jadwal_sanggar.JadwalSanggarItem
import com.example.sanggar.presenter.DatePickerHelper
import com.example.sanggar.presenter.absensi.PertemuanContract
import com.example.sanggar.presenter.absensi.PertemuanPresenter
import com.example.sanggar.view.activity.common.BaseActivity
import com.example.sanggar.view.adapter.absensi.ProgressAnakAdapter
import kotlinx.android.synthetic.main.activity_detail_pertemuan.*
import kotlinx.android.synthetic.main.activity_pertemuan.*
import kotlinx.android.synthetic.main.fragment_toolbar.*
import java.util.*
import kotlin.collections.HashMap

class DetailPertemuanActivity : BaseActivity(), PertemuanContract.View {

    var data: PertemuanData? = null
    var data_kelas: JadwalSanggarItem? = null
    val presenter = PertemuanPresenter(this)

    lateinit var datePicker: DatePickerHelper
    lateinit var adapter : ProgressAnakAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_pertemuan)

        setToolbar()
        toolbar_title.text = "Detail Pertemuan"

        data_kelas = intent.getParcelableExtra<JadwalSanggarItem>("data")
        datePicker = DatePickerHelper(this)
        initListener()
//        initAdapter()
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
            addPertemuan()
        }
    }

    private fun addPertemuan() {
        val pertemuan_ke = til_pertemuan_ke.editText?.text.toString()
        val tanggal = til_tanggal_sewa.editText?.text.toString()
        val jadwal_sanggar = data_kelas?.id

        val tambahData = HashMap<String, Any?>()
        tambahData["pertemuan_ke"] = pertemuan_ke
        tambahData["tanggal"] = tanggal
        tambahData["jadwal_sanggar_id"] = jadwal_sanggar

        isLoading(true)

        presenter.addPertemuan(tambahData)
    }

//    private fun initAdapter() {
//        adapter = ProgressAnakAdapter { itemEdit->
//            val intent = Intent(this, DetailProgressAnakActivity::class.java)
////            intent.putExtra("data", itemEdit)
//            startActivity(intent)
//        }
//
//        rv_pertemuan?.layoutManager = LinearLayoutManager(this)
//        rv_pertemuan?.adapter = adapter
//    }

    private fun isLoading(isLoad: Boolean) {
        if (isLoad) Utilities.showProgress(this)
        else Utilities.hideProgress()
    }
    override fun pertemuanResponse(response: PertemuanDataResponse) {
        isLoading(false)
        this.showCustomDialogBack("Berhasil", "Data berhasil disimpan")
    }

    override fun getPertemuanResponse(response: PertemuanDataListResponse) {
        TODO("Not yet implemented")
    }

    override fun deletePertemuanResponse(response: EmptyResponse) {
        TODO("Not yet implemented")
    }

    override fun showError(title: String, message: String) {
        this.showErrorAlert(title, message)
    }
}