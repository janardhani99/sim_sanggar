package com.example.sim_sanggar.view.activity.anak

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.sim_sanggar.R
import com.example.sim_sanggar.common.Utilities
import com.example.sim_sanggar.common.clickWithDebounce
import com.example.sim_sanggar.data.model.anak.AnakListItem
import com.example.sim_sanggar.data.model.anak.AnakListResponse
import com.example.sim_sanggar.data.model.anak.AnakResponse
import com.example.sim_sanggar.presenter.DatePickerHelper
import com.example.sim_sanggar.presenter.anak.AnakContract
import com.example.sim_sanggar.presenter.anak.AnakPresenter
import com.example.sim_sanggar.view.activity.common.BaseActivity
import kotlinx.android.synthetic.main.activity_anak.*
import java.util.*
import kotlin.collections.HashMap

class AnakActivity : BaseActivity(), AnakContract.View {

    var data: AnakListItem? = null
    var presenter = AnakPresenter(this)
    lateinit var datePicker: DatePickerHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_anak)

        setToolbar()
//        toolbar_title?.text = "Pendaftaran Anak"

        data = intent.getParcelableExtra("data_anak")
        data?.let { setView(it) }

        initListener()
        datePicker = DatePickerHelper(this)

    }

    private fun showDatePickerDialog() {
        val cal = Calendar.getInstance()
        val d = cal.get(Calendar.DAY_OF_MONTH)
        val m = cal.get(Calendar.MONTH)
        val y = cal.get(Calendar.YEAR)

//        minDate.set(Calendar.HOUR_OF_DAY, 0)
//        minDate.set(Calendar.MINUTE, 0)
//        minDate.set(Calendar.SECOND, 0)
//        datePicker.setMinDate(minDate.timeInMillis)

//        val default = Calendar.getInstance()
//        default.set(Calendar.YEAR, 2000)
//        datePicker.
        val maxDate = Calendar.getInstance()
        maxDate.add(Calendar.DAY_OF_MONTH, 30)
        datePicker.setMaxDate(maxDate.timeInMillis)

        datePicker.showDialog(y, m, d, object: DatePickerHelper.Callback {
            override fun onDateSelected(year: Int, month: Int, dayOfMonth: Int) {
                val dayStr = if(dayOfMonth<10) "0${dayOfMonth}" else "${dayOfMonth}"
                val mon = month + 1
                val monthStr = if (mon<10) "0${mon}" else "${mon}"
                tv_tanggal_lahir.editText?.setText("${year}-${monthStr}-${dayStr}")
            }
        })
    }
    private fun initListener() {

        btn_daftar?.clickWithDebounce {
            addOrEditAnak()
        }
        btn_pilih_tanggal?.clickWithDebounce {
            showDatePickerDialog()
        }
    }

    private fun addOrEditAnak() {
        val nama_anak = til_nama_anak?.editText?.text.toString()
        val alamat = til_alamat_anak?.editText?.text.toString()
        val tanggal_lahir = tv_tanggal_lahir?.editText?.text.toString()
        val telepon = til_telepon_anak?.editText?.text.toString()

        val tambahData = HashMap<String, Any?>()
        tambahData["nama"] = nama_anak
        tambahData["alamat"] = alamat
        tambahData["tanggal_lahir"] = tanggal_lahir
        tambahData["telepon"] = telepon

        isLoading(true)

        if (data == null) {
            presenter.addAnak(tambahData)
        } else {
//            Toast.makeText(this, "edit data", Toast.LENGTH_SHORT).show()
            data?.id?.let { presenter.editAnak(it, tambahData) }
        }

    }

    private fun setView(data: AnakListItem) {
        data.run {
            tv_judul?.text = "Kelola Data Anak"
            til_nama_anak?.editText?.setText(data.nama)
            til_alamat_anak?.editText?.setText(data.alamat)
            tv_tanggal_lahir?.editText?.setText(data.tanggal_lahir)
            til_telepon_anak?.editText?.setText(data.telepon)
        }
    }

    private fun isLoading(isLoad: Boolean) {
        if(isLoad) Utilities.showProgress(this)
        else Utilities.hideProgress()
    }

    override fun anakResponse(response: AnakResponse) {
        isLoading(false)
        this.showCustomDialogBack("Berhasil", "Data Tersimpan. Kembali untuk mendaftarkan Anak pada Menu Daftar.")
//        finish()
    }

    override fun getAnakResponse(response: AnakListResponse) {
        TODO("Not yet implemented")
    }

//    override fun deleteDaftarListResponse(response: EmptyResponse) {
//        TODO("Not yet implemented")
//    }

    override fun showError(title: String, message: String) {
        this.showErrorAlert(title, message)
    }
}