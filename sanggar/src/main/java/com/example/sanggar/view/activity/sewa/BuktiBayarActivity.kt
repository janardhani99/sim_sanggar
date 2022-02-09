package com.example.sanggar.view.activity.sewa

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
import com.example.sanggar.data.model.sewa.SewaListResponse
import com.example.sanggar.data.model.sewa.SewaResponse
import com.example.sanggar.presenter.daftar.DaftarListContract
import com.example.sanggar.presenter.daftar.DaftarListPresenter
import com.example.sanggar.presenter.sewa.SewaListContract
import com.example.sanggar.presenter.sewa.SewaListPresenter
import com.example.sanggar.view.activity.common.BaseActivity
import kotlinx.android.synthetic.main.activity_pendaftar_detail.*
import kotlinx.android.synthetic.main.activity_pendaftar_detail.btn_batalkan
import kotlinx.android.synthetic.main.activity_sewa_detail.*
import kotlinx.android.synthetic.main.fragment_toolbar.*

class BuktiBayarActivity(): BaseActivity(), SewaListContract.View {

    var data: SewaListItem? = null
    val presenter = SewaListPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bukti_bayar_sewa)

        setToolbar()
        toolbar_title?.text = "Bukti Pembayaran Sewa"

        data = intent.getParcelableExtra<SewaListItem>("data")
        data?.let { initView(it) }
    }


    private fun initView(data: SewaListItem) {

        data?.run {
            til_bayar_via?.editText?.setText(data.transfer_via)
            bukti_pembayaran?.let { iv_bukti_transfer?.loadImage(it) }
        }

    }

    override fun sewaListResponse(response: SewaResponse) {
        TODO("Not yet implemented")
    }

    override fun getSewaListResponse(response: SewaListResponse) {
        TODO("Not yet implemented")
    }

    override fun deleteSewaListResponse(response: EmptyResponse) {
        TODO("Not yet implemented")
    }

    override fun showError(title: String, message: String) {
        showErrorAlert(title, message)
    }
}