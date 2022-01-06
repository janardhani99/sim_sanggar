package com.example.sim_sanggar.view.activity.anak

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.sim_sanggar.R
import com.example.sim_sanggar.common.Utilities
import com.example.sim_sanggar.common.Validations
import com.example.sim_sanggar.common.clickWithDebounce
import com.example.sim_sanggar.data.model.anak.AnakListItem
import com.example.sim_sanggar.data.model.anak.AnakListResponse
import com.example.sim_sanggar.data.model.anak.AnakResponse
import com.example.sim_sanggar.data.model.common.EmptyResponse
import com.example.sim_sanggar.data.model.daftar.DaftarListResponse
import com.example.sim_sanggar.data.model.daftar.DaftarResponse
import com.example.sim_sanggar.data.model.daftar.PendaftaranAnak
import com.example.sim_sanggar.presenter.DatePickerHelper
import com.example.sim_sanggar.presenter.anak.AnakContract
import com.example.sim_sanggar.presenter.anak.AnakPresenter
import com.example.sim_sanggar.presenter.daftar.DaftarListContract
import com.example.sim_sanggar.presenter.daftar.DaftarListPresenter
import com.example.sim_sanggar.view.activity.common.BaseActivity
import kotlinx.android.synthetic.main.activity_anak.*
import kotlinx.android.synthetic.main.activity_anak.btn_pilih_tanggal
import kotlinx.android.synthetic.main.activity_sewa.*
import kotlinx.android.synthetic.main.fragment_toolbar.*
import java.util.*
import javax.xml.validation.Validator
import kotlin.collections.HashMap

class AnakActivity : BaseActivity(), AnakContract.View {

    var data: AnakListItem? = null
    var presenter = AnakPresenter(this)
    lateinit var datePicker: DatePickerHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_anak)

        setToolbar()
        toolbar_title?.text = "Pendaftaran Anak"
        initListener()
        datePicker = DatePickerHelper(this)

    }

    private fun showDatePickerDialog() {
        val cal = Calendar.getInstance()
        val d = cal.get(Calendar.DAY_OF_MONTH)
        val m = cal.get(Calendar.MONTH)
        val y = cal.get(Calendar.YEAR)

//        val minDate = Calendar.getInstance()
//        minDate.set(Calendar.HOUR_OF_DAY, 0)
//        minDate.set(Calendar.MINUTE, 0)
//        minDate.set(Calendar.SECOND, 0)
//        datePicker.setMinDate(minDate.timeInMillis)

        val maxDate = Calendar.getInstance()
        maxDate.add(Calendar.DAY_OF_MONTH, 30)
        datePicker.setMaxDate(maxDate.timeInMillis)

        datePicker.showDialog(y, m, d, object: DatePickerHelper.Callback {
            override fun onDateSelected(year: Int, month: Int, dayOfMonth: Int) {
                val dayStr = if(dayOfMonth<10) "0${dayOfMonth}" else "${dayOfMonth}"
                val mon = month + 1
                val monthStr = if (mon<10) "0${mon}" else "${mon}"
                tv_tanggal_lahir.text = "${year}-${monthStr}-${dayStr}"
            }
        })
    }
    private fun initListener() {

        btn_daftar.clickWithDebounce {
            addOrEditAnak()
        }
        btn_pilih_tanggal.setOnClickListener {
            showDatePickerDialog()
        }
    }

    private fun addOrEditAnak() {
        val nama_anak = til_nama_anak?.editText?.text.toString()
        val umur = til_umur_anak?.editText?.text.toString()
        val tanggal_lahir = tv_tanggal_lahir?.text.toString()
        val telepon = til_telepon_anak?.editText?.text.toString()

        val tambahData = HashMap<String, Any?>()
        tambahData["nama"] = nama_anak
        tambahData["umur"] = umur
        tambahData["tanggal_lahir"] = tanggal_lahir
        tambahData["telepon"] = telepon

        isLoading(true)

        if (data == null) {
            presenter.addAnak(tambahData)
        } else {
            Toast.makeText(this, "edit data", Toast.LENGTH_SHORT).show()
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