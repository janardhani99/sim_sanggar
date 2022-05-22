package com.example.sim_sanggar.view.activity.sewa

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.FrameLayout
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sim_sanggar.GlobalClass.Companion.context
import com.example.sim_sanggar.R
import com.example.sim_sanggar.common.Utilities
import com.example.sim_sanggar.common.Validations
import com.example.sim_sanggar.common.clickWithDebounce
import com.example.sim_sanggar.data.model.sewa.SewaListItem
import com.example.sim_sanggar.data.model.sewa.SewaListResponse
import com.example.sim_sanggar.data.model.sewa.SewaResponse
import com.example.sim_sanggar.presenter.DatePickerHelper
import com.example.sim_sanggar.presenter.sewa.SewaContract
import com.example.sim_sanggar.presenter.sewa.SewaPresenter
import com.example.sim_sanggar.view.activity.common.BaseActivity
import com.example.sim_sanggar.view.adapter.sewaadapter.SewaAdapter
import com.example.sim_sanggar.view.adapter.sewaadapter.SewaPerTanggalAdapter
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import kotlinx.android.synthetic.main.activity_fasilitas.*
import kotlinx.android.synthetic.main.activity_sewa.*
import kotlinx.android.synthetic.main.fragment_toolbar.*
import java.util.*
import kotlin.collections.HashMap

class SewaActivity : BaseActivity(), SewaContract.View {

    var data: SewaListItem? = null
    val presenter = SewaPresenter(this)

    lateinit var datePicker: DatePickerHelper
//    lateinit var adapter: SewaAdapter
    lateinit var adapter: SewaPerTanggalAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sewa)

        setToolbar()
//        toolbar_title?.text = getString(R.string.sewa)

        datePicker = DatePickerHelper(this)

        initListener()
        initView(data)
        initAdapterTersewa()
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
                tv_tanggal_sewa.text = "${year}-${monthStr}-${dayStr}"

                var tanggalTerpilih = tv_tanggal_sewa.text.toString()
                isLoading(true)
                presenter.getTanggalTersewa(tanggalTerpilih)
            }


        })
    }

//    private fun initAdapter() {
//        val metodePembayaranAdapter = context?.let { ArrayAdapter<String>(it, R.layout.layout_dropdown_item, resources.getStringArray(R.array.metode_pembayaran))}
//        ac_metode_pembayaran?.setAdapter(metodePembayaranAdapter)
//    }

    private fun initView(data: SewaListItem?) {
        data?.run {
//            ac_metode_pembayaran?.setText(data.metodePembayaran, false)
            et_jam_mulai_sewa?.setText(data.jam_mulai?.substring(0,5))
            et_jam_selesai_sewa?.setText(data.jam_selesai?.substring(0,5))
        }

        val jamMulai = data?.jam_mulai?.split(":")?.get(0)?.toInt() ?: 0
        val menitMulai = data?.jam_mulai?.split(":")?.get(1)?.toInt() ?: 0
        val jamSelesai = data?.jam_selesai?.split(":")?.get(0)?.toInt() ?: 0
        val menitSelesai = data?.jam_selesai?.split(":")?.get(1)?.toInt() ?: 0

        et_jam_mulai_sewa?.setOnClickListener {
            val jamMulaiPicker = MaterialTimePicker.Builder()
                    .setInputMode(MaterialTimePicker.INPUT_MODE_KEYBOARD)
                    .setTimeFormat(TimeFormat.CLOCK_24H)
                    .setHour(jamMulai?: 0).setMinute(menitMulai ?: 0).build()

            jamMulaiPicker.addOnPositiveButtonClickListener {
                val value = "${getTimeStringFormat(jamMulaiPicker.hour.toString())}:${
                getTimeStringFormat(jamMulaiPicker.minute.toString())}"
                et_jam_mulai_sewa?.setText(value)
            }
            jamMulaiPicker.show(supportFragmentManager, "")
        }

        et_jam_selesai_sewa?.setOnClickListener {
            val jamSelesaiPicker = MaterialTimePicker.Builder()
                    .setInputMode(MaterialTimePicker.INPUT_MODE_KEYBOARD)
                    .setTimeFormat(TimeFormat.CLOCK_24H)
                    .setHour(jamSelesai?: 0).setMinute(menitSelesai ?: 0).build()

            jamSelesaiPicker.addOnPositiveButtonClickListener {
                val value = "${getTimeStringFormat(jamSelesaiPicker.hour.toString())}:${
                getTimeStringFormat(jamSelesaiPicker.minute.toString())}"
                et_jam_selesai_sewa?.setText(value)
            }
            jamSelesaiPicker.show(supportFragmentManager, "")
        }
    }

    private fun getTimeStringFormat(time: String): String {
        var waktu = time
        if (time.length == 1) waktu ="0$time"
        return waktu
    }

    private fun initListener() {
        btn_sewa?.clickWithDebounce {
            addSewa()
        }

        btn_pilih_tanggal?.setOnClickListener {
            showDatePickerDialog()
        }
    }

    private fun initAdapterTersewa() {
//        val tanggal_sewa = tv_tanggal_sewa?.text.toString()
//        isLoading(true)

        adapter = SewaPerTanggalAdapter()
        rv_booked_tanggal?.layoutManager = LinearLayoutManager(this)
        rv_booked_tanggal?.adapter = adapter
    }

    private fun addSewa() {
        val tanggal_sewa = tv_tanggal_sewa?.text.toString()
        val jam_mulai = til_jam_mulai_sewa?.editText?.text.toString()
        val jam_selesai = til_jam_selesai_sewa?.editText?.text.toString()
//        val metode_pembayaran = til_metode_pembayaran?.editText?.text.toString()

        val tambahData = HashMap<String, Any?>()
        tambahData["tanggal"] = tanggal_sewa
        tambahData["jam_mulai"] = jam_mulai
        tambahData["jam_selesai"] = jam_selesai
//        tambahData["metode_pembayaran"] = metode_pembayaran.toLowerCase()

        isLoading(true)

//        if (data == null) {
            presenter.addSewa(tambahData)
//        }
//        else {
//            Toast.makeText(this, "edit data", Toast.LENGTH_SHORT).show()
//        }

    }

//    private fun fetchData() {
//        isLoading(true)
////        presenter.getTanggalTersewa(it)
//        presenter.getSewa()
//    }

    override fun onResume() {
        super.onResume()
//        fetchData()
        initAdapterTersewa()
    }

    private fun isLoading(isLoad: Boolean) {
        if (isLoad) Utilities.showProgress(this)
        else Utilities.hideProgress()
    }

    override fun sewaResponse(response: SewaResponse) {
        isLoading(false)
        this.showCustomDialogBack("Berhasil", "Penyewaan diproses, tunggu konfirmasi dari Admin Sanggar")
    }

    override fun getSewaResponse(response: SewaListResponse) {
        isLoading(false)
        response.data?.let { adapter.setData(it)}
    }

    override fun getTanggalSewaResponse(response: SewaListResponse) {
        isLoading(false)
        response.data?.let { adapter.setData(it)}
    }

    override fun uploadImageResponse() {
        TODO("Not yet implemented")
    }

    override fun showError(title: String, message: String) {
        showErrorAlert(title, message)
    }
}