package com.example.sanggar.view.activity.sewa

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View.*
import com.example.sanggar.R
import com.example.sanggar.common.Utilities
import com.example.sanggar.common.clickWithDebounce
import com.example.sanggar.data.model.common.EmptyResponse
import com.example.sanggar.data.model.sewa.SewaListItem
import com.example.sanggar.data.model.sewa.SewaListResponse
import com.example.sanggar.data.model.sewa.SewaResponse
import com.example.sanggar.presenter.sewa.SewaListContract
import com.example.sanggar.presenter.sewa.SewaListPresenter
import com.example.sanggar.view.activity.common.BaseActivity
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import kotlinx.android.synthetic.main.activity_sewa_detail.*
import kotlin.time.times

class SewaDetailActivity(): BaseActivity(), SewaListContract.View {

    var data : SewaListItem? = null
    val presenter = SewaListPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sewa_detail)
        setToolbar()

        data = intent.getParcelableExtra<SewaListItem>("data")
        data?.let { initView(it) }
//        data?.let { initListener() }
//        initView(data)

        initListener()
    }


    private fun initListener() {
//        Validations.removeError(til_tanggal_sewa, til_jam_mulai, til_jam_selesai)
        btn_verifikasi_sewa?.clickWithDebounce {
//            if (data != null) {
            verifikasiSewa()
//            }
        }

        btn_selesai?.clickWithDebounce {
            booked()
        }

        btn_batalkan?.clickWithDebounce {
//            if (data != null) {
            batalkanSewa()
//            }
        }
//
        if (data?.status == "Menunggu Verifikasi" ) {
            btn_verifikasi_sewa.visibility = VISIBLE
            btn_batalkan.visibility = VISIBLE

            btn_bukti_bayar.visibility = INVISIBLE
            btn_selesai.visibility = INVISIBLE
        }
        else if (data?.status == "Menunggu Pembayaran") {
            btn_selesai.visibility = VISIBLE
            btn_batalkan.visibility = VISIBLE

            btn_bukti_bayar.visibility = INVISIBLE
            btn_verifikasi_sewa.visibility = INVISIBLE

        } else if (data?.status == "Terdaftar") {
            btn_bukti_bayar.visibility = VISIBLE
            btn_batalkan.visibility = VISIBLE
            btn_batalkan.setText("Batalkan")

            btn_verifikasi_sewa.visibility = INVISIBLE

        } else if (data?.status == "Dibatalkan"){
            btn_verifikasi_sewa.visibility = VISIBLE
            btn_verifikasi_sewa.setText("Pulihkan")

            btn_bukti_bayar.visibility = INVISIBLE
            btn_selesai.visibility = INVISIBLE
            btn_batalkan.visibility = INVISIBLE

        } else {
            btn_verifikasi_sewa.visibility = INVISIBLE
            btn_bukti_bayar.visibility = INVISIBLE
            btn_selesai.visibility = INVISIBLE
            btn_batalkan.visibility = INVISIBLE
        }

        btn_bukti_bayar?.clickWithDebounce {
            val intent = Intent(this, BuktiBayarActivity::class.java)
            intent.putExtra("data", data)
            startActivity(intent)
        }
    }

    private fun initView(data: SewaListItem) {
        initListener()

        data?.run {
            til_studio?.editText?.setText(data.studio_id?.nama_studio)
            til_tanggal_sewa?.editText?.setText(data.tanggal)

            et_jam_mulai_sewa?.setText(data.jam_mulai?.substring(0,5))
            et_jam_selesai_sewa?.setText(data.jam_selesai?.substring(0,5))

            val jamMulai = data.jam_mulai?.substring(0,2)?.toInt()
            val jamSelesai= data.jam_selesai?.substring(0,2)?.toInt()

            val difference = jamSelesai?.minus(jamMulai!!)
            val harga = data.studio_id?.harga?.toInt()

            val total_bayar = harga?.let { difference?.times(it) }
            til_total_bayar?.editText?.setText(total_bayar.toString())
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

    private fun verifikasiSewa() {

        val tambahData = HashMap<String, Any?>()
        tambahData["tanggal"] = til_tanggal_sewa?.editText?.text.toString()
        tambahData["jam_mulai"] = til_jam_mulai?.editText?.text.toString()
        tambahData["jam_selesai"] = til_jam_selesai?.editText?.text.toString()
        tambahData["status"] = "1"
        isLoading(true)
        data?.id?.let { presenter.editStatusSewa(it, tambahData) }
        isLoading(false)
        this.showCustomDialogBack("Message", "Berhasil diverifikasi")
    }

    private fun booked() {

        val tambahData = HashMap<String, Any?>()
        tambahData["tanggal"] = til_tanggal_sewa?.editText?.text.toString()
        tambahData["jam_mulai"] = til_jam_mulai?.editText?.text.toString()
        tambahData["jam_selesai"] = til_jam_selesai?.editText?.text.toString()
        tambahData["status"] = "2"
        isLoading(true)
        data?.id?.let { presenter.editStatusSewa(it, tambahData) }
        isLoading(false)
        this.showCustomDialogBack("Message", "Booking Berhasil")
    }

    private fun batalkanSewa() {

        val tambahData = HashMap<String, Any?>()
        tambahData["tanggal"] = til_tanggal_sewa?.editText?.text.toString()
        tambahData["jam_mulai"] = til_jam_mulai?.editText?.text.toString()
        tambahData["jam_selesai"] = til_jam_selesai?.editText?.text.toString()
        tambahData["status"] = "3"
        isLoading(true)
        data?.id?.let { presenter.editStatusSewa(it, tambahData) }
        isLoading(false)
        this.showCustomDialogBack("Message", "Berhasil dibatalkan")
    }

    private fun isLoading(isLoad: Boolean) {
        if (isLoad) Utilities.showProgress(this)
        else Utilities.hideProgress()
    }

    override fun sewaListResponse(response: SewaResponse) {


    }

    override fun getSewaListResponse(response: SewaListResponse) {
        TODO("Not yet implemented")
    }

    override fun deleteSewaListResponse(response: EmptyResponse) {
        TODO("Not yet implemented")
    }

    override fun showError(title: String, message: String) {
        this.showErrorAlert(title, message)
    }
}