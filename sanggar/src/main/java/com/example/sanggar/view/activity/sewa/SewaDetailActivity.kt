package com.example.sanggar.view.activity.sewa

import android.os.Bundle
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
        toolbar_title?.text = "Detail Penyewaan"

        data = intent.getParcelableExtra<SewaListItem>("data")
        data?.let { initView(it) }
//        data?.let { initListener() }
//        initView(data)
    }


//    private fun initListener() {
//        Validations.removeError(til_tanggal_sewa, til_jam_mulai, til_jam_selesai)
//        btn_verifikasi_sewa.clickWithDebounce {
//
//                verifikasiSewa(data)
//
//        }
//
//        btn_batalkan.clickWithDebounce {
//
//                batalkanSewa(data)
//
//        }
//    }

    private fun initView(data: SewaListItem) {

        data?.run {
            til_tanggal_sewa?.editText?.setText(data.tanggal)
            til_jam_mulai?.editText?.setText(data.jam_mulai?.substring(0,5))
            til_jam_selesai?.editText?.setText(data.jam_selesai?.substring(0,5))
        }

        btn_verifikasi_sewa.clickWithDebounce {
//            if (data != null) {
                verifikasiSewa()
//            }
        }

        btn_selesai.clickWithDebounce {
            booked()
        }

        btn_batalkan.clickWithDebounce {
//            if (data != null) {
                batalkanSewa()
//            }
        }
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