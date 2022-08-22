package com.example.sim_sanggar.view.activity.sewa

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sim_sanggar.GlobalClass
import com.example.sim_sanggar.R
import com.example.sim_sanggar.common.Utilities
import com.example.sim_sanggar.common.clickWithDebounce
import com.example.sim_sanggar.common.onTextChanged
import com.example.sim_sanggar.data.model.sewa.SewaListItem
import com.example.sim_sanggar.data.model.sewa.SewaListResponse
import com.example.sim_sanggar.data.model.sewa.SewaResponse
import com.example.sim_sanggar.data.model.studio.StudioData
import com.example.sim_sanggar.data.model.studio.StudioListResponse
import com.example.sim_sanggar.presenter.DatePickerHelper
import com.example.sim_sanggar.presenter.sewa.SewaContract
import com.example.sim_sanggar.presenter.sewa.SewaPresenter
import com.example.sim_sanggar.presenter.studio.StudioContract
import com.example.sim_sanggar.presenter.studio.StudioPresenter
import com.example.sim_sanggar.view.activity.common.BaseActivity
import com.example.sim_sanggar.view.activity.studio.StudioActivity
import com.example.sim_sanggar.view.adapter.sewapertanggaladapter.SewaPerTanggalAdapter
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import kotlinx.android.synthetic.main.activity_report_anak.*
import kotlinx.android.synthetic.main.activity_tambah_sewa.*
import kotlinx.android.synthetic.main.layout_dropdown_item.view.*
import java.util.*
import kotlin.collections.HashMap

class TambahSewaActivity : BaseActivity(), SewaContract.View, StudioContract.View {

    var data: SewaListItem? = null
    var listSudio: List<StudioData>? = null

    val presenter = SewaPresenter(this)
    val presenterStudio = StudioPresenter(this)

    lateinit var datePicker: DatePickerHelper
    lateinit var adapter: SewaPerTanggalAdapter

    var selectedStudio: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tambah_sewa)

        setToolbar()
//        toolbar_title?.text = getString(R.string.sewa)

        datePicker = DatePickerHelper(this)
        presenterStudio.getStudio()

        initListener()
        initView(data)
        initAdapterTersewa()
        initAdapterStudio()
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
                selectedStudio?.let { presenter.getTanggalTersewa(tanggalTerpilih, it) }
                Log.i("selectedStudio", selectedStudio.toString())
            }


        })
    }

    fun AutoCompleteTextView.setArrayAdapter(list: List<String?>?) {
        val adapter = list?.let { ArrayAdapter(GlobalClass.context, R.layout.layout_dropdown_item, it) }
        this.setAdapter(adapter)
    }

    private fun initView(data: SewaListItem?) {
        data?.run {
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

    fun hitungJam() {
//        val jamMulai = data.jam_mulai?.substring(0,2)?.toInt()
//        val jamSelesai= data.jam_selesai?.substring(0,2)?.toInt()

        val jamMulai = til_jam_mulai_sewa.editText?.text?.substring(0,2)?.toInt()
        val jamSelesai = til_jam_selesai_sewa.editText?.text?.substring(0,2)?.toInt()

        val difference = jamSelesai?.minus(jamMulai!!)
        Log.i("pengurangan", difference.toString())

//        val harga = data?.studio_id?.harga?.toInt()
        val harga = listSudio?.find { it.nama_studio == ac_pilih_studio.text.toString() }?.harga?.toInt()

        val total_bayar = harga?.let { difference?.times(it) }
        Log.i("total", total_bayar.toString())

        til_total_bayar?.editText?.setText(total_bayar.toString())
    }

    private fun initListener() {
        btn_sewa?.clickWithDebounce {
            addSewa()
        }

        btn_pilih_tanggal?.clickWithDebounce {
            showDatePickerDialog()
        }

        btn_lihat_studio?.clickWithDebounce {
            startActivity(Intent(this, StudioActivity::class.java))
        }

        btn_hitung_total?.clickWithDebounce {
            hitungJam()
        }

    }

    private fun initAdapterStudio() {
        val studio = listSudio?.map { it.nama_studio }

        ac_pilih_studio?.run {
            if (studio != null) {
                setArrayAdapter(studio)

            }

            onTextChanged {
                selectedStudio = listSudio?.find { it.nama_studio == ac_pilih_studio.text.toString() }?.id
                initListener()

//                til_biaya_perjam.editText?.setText(listSudio?.find { it.nama_studio == ac_pilih_studio.text.toString() }?.harga)
            }

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
        tambahData["studio_id"] = selectedStudio
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
        initListener()
        initAdapterStudio()
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
        if (adapter.itemCount !== 0) {
            tv_belum_ada.text = "Jam Sudah Tersewa"
        } else {
            tv_belum_ada.text = "Belum Ada Sewa"
        }

        Log.i("tersewa", adapter.itemCount.toString())
    }

    override fun getTanggalSewaResponse(response: SewaListResponse) {
        isLoading(false)
        response.data?.let { adapter.setData(it)}
    }

    override fun uploadImageResponse() {
        TODO("Not yet implemented")
    }

    override fun getStudioResponse(response: StudioListResponse) {
        isLoading(false)
        listSudio = response.data
        initAdapterStudio()
    }

    override fun showError(title: String, message: String) {
        showErrorAlert(title, message)
        isLoading(false)
    }
}