package com.example.sanggar.view.activity.sewa

import android.content.Intent
import android.os.Bundle
import android.view.View
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
import kotlinx.android.synthetic.main.activity_sewa_detail.*
import kotlinx.android.synthetic.main.fragment_toolbar.*

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

        data?.run {
            til_tanggal_sewa?.editText?.setText(data.tanggal)
            til_jam_mulai?.editText?.setText(data.jam_mulai?.substring(0,5))
            til_jam_selesai?.editText?.setText(data.jam_selesai?.substring(0,5))
        }

        initListener()
    }

    private fun ubahData(data: SewaListItem?) {
//        val status = data?.status
        val tanggal = til_tanggal_sewa?.editText?.text.toString()
        val jam_mulai = til_jam_mulai?.editText?.text.toString()
        val jam_selesai = til_jam_selesai?.editText?.text.toString()

        val tambahData = HashMap<String, Any?>()
        tambahData["tanggal"] = tanggal
        tambahData["jam_mulai"] = jam_mulai
        tambahData["jam_selesai"] = jam_selesai
//        tambahData["status"] = "1"
        isLoading(true)

//        if (data?.id != null) {
//            presenter.editStatusSewa(data?.id, tambahData)
//        }

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

//        isLoading(false)
//        this.showCustomDialogBack("Berhasil", "Status Berhasil diubah")
//        Toast.makeText(activity, "Berhasil!", Toast.LENGTH_SHORT).show()

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