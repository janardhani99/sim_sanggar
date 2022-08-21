package com.example.sanggar.view.activity.daftar

import android.os.Bundle
import com.example.sanggar.R
import com.example.sanggar.common.Utilities
import com.example.sanggar.common.clickWithDebounce
import com.example.sanggar.common.loadImage
import com.example.sanggar.data.model.common.EmptyResponse
import com.example.sanggar.data.model.daftar.DaftarListResponse
import com.example.sanggar.data.model.daftar.DaftarResponse
import com.example.sanggar.data.model.daftar.PendaftaranAnak
import com.example.sanggar.data.model.sewa.SewaListItem
import com.example.sanggar.presenter.daftar.DaftarListContract
import com.example.sanggar.presenter.daftar.DaftarListPresenter
import com.example.sanggar.view.activity.common.BaseActivity
import kotlinx.android.synthetic.main.activity_pendaftar_detail.*
import kotlinx.android.synthetic.main.activity_pendaftar_detail.btn_batalkan
import kotlinx.android.synthetic.main.activity_sewa_detail.*
import kotlinx.android.synthetic.main.fragment_toolbar.*

class PendaftarDetailActivity(): BaseActivity(), DaftarListContract.View {

    var data: PendaftaranAnak? = null
    val presenter = DaftarListPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pendaftar_detail)

        setToolbar()
        toolbar_title?.text = "Detail Pendaftaran"

        data = intent.getParcelableExtra<PendaftaranAnak>("data")
        data?.let { initView(it) }
    }


    private fun initView(data: PendaftaranAnak) {

        data?.run {
            til_nama_anak?.editText?.setText(data.anak?.nama)
            til_alamat_anak?.editText?.setText(data.anak?.alamat)
            til_telepon?.editText?.setText(data.anak?.telepon)
            til_bayar_via?.editText?.setText(data.transfer_via)
            bukti_pembayaran?.let { iv_bukti_transfer?.loadImage(it) }
        }

        btn_verifikasi_pendaftar?.clickWithDebounce {
            verifikasiDaftar()
        }

        btn_batalkan?.clickWithDebounce {
            batalkanDaftar()
        }
    }

    private fun verifikasiDaftar() {

        val transfer_via = data?.transfer_via
        val tambahData = HashMap<String, Any?>()
//        tambahData["anak_id"] = til_tanggal_sewa?.editText?.text.toString()

        tambahData["transfer_via"] = transfer_via
        tambahData["status"] = "1"
        isLoading(true)
        data?.id?.let { presenter.editStatusDaftar(it, tambahData) }
        this.showCustomDialogBack("Message", "Berhasil diverifikasi")
    }

    private fun batalkanDaftar() {
//        val status = data?.status
        val transfer_via = data?.transfer_via
        val tambahData = HashMap<String, Any?>()
        tambahData["transfer_via"] = transfer_via
        tambahData["status"] = "2"
        isLoading(true)
        data?.id?.let { presenter.editStatusDaftar(it, tambahData) }
        this.showCustomDialogBack("Message", "Berhasil dibatalkan")
    }
    override fun daftarListResponse(response: DaftarResponse) {
        isLoading(false)
//        if (data?.status == "1") {
//            Toast.makeText(context, "Berhasil Verifikasi!", Toast.LENGTH_SHORT).show()
//        } else if (data?.status == "2") {
//            Toast.makeText(context, "Berhasil dibatalkan!", Toast.LENGTH_SHORT).show()
//        }

    }

    private fun isLoading(isLoad: Boolean) {
        if (isLoad)  Utilities.showProgress(this)
        else Utilities.hideProgress()
    }

    override fun getDaftarListResponse(response: DaftarListResponse) {
        TODO("Not yet implemented")
    }

    override fun getAnakTerdaftarResponse(response: DaftarListResponse) {
        TODO("Not yet implemented")
    }

    override fun getAnakOnKelasResponse(response: DaftarListResponse) {
        TODO("Not yet implemented")
    }

    override fun deleteDaftarListResponse(response: EmptyResponse) {
        TODO("Not yet implemented")
    }

    override fun showError(title: String, message: String) {
        showErrorAlert(title, message)
    }
}