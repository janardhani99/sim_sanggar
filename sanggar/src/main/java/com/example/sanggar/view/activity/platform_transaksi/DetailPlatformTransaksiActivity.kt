package com.example.sanggar.view.activity.platform_transaksi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.sanggar.R
import com.example.sanggar.common.Utilities
import com.example.sanggar.common.Validations
import com.example.sanggar.common.clickWithDebounce
import com.example.sanggar.common.loadImage
import com.example.sanggar.data.model.common.EmptyResponse
import com.example.sanggar.data.model.kegiatan.KegiatanListItem
import com.example.sanggar.data.model.platform_transaksi.PlatformTransaksiListItem
import com.example.sanggar.data.model.platform_transaksi.PlatformTransaksiListResponse
import com.example.sanggar.data.model.platform_transaksi.PlatformTransaksiResponse
import com.example.sanggar.presenter.kegiatan.KegiatanPresenter
import com.example.sanggar.presenter.platform_transaksi.PlatformTransaksiContract
import com.example.sanggar.presenter.platform_transaksi.PlatformTransaksiPresenter
import com.example.sanggar.view.activity.common.BaseActivity
import kotlinx.android.synthetic.main.activity_detail_kegiatan.*
import kotlinx.android.synthetic.main.activity_detail_platform_transaksi.*
import kotlinx.android.synthetic.main.fragment_toolbar.*
import java.io.File

class DetailPlatformTransaksiActivity : BaseActivity(), PlatformTransaksiContract.View {

    var data: PlatformTransaksiListItem? = null
    val presenter = PlatformTransaksiPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_platform_transaksi)

        data = intent.getParcelableExtra<PlatformTransaksiListItem>("data")
        setToolbar()
        toolbar_title?.text = getString(R.string.platform_transaksi)
        data?.let { setView(it) }
        initListener()
    }

    private fun initListener() {
        Validations.removeError(til_nama_platform, til_nomor_rekening)

        btn_simpan_platform_transaksi?.clickWithDebounce {
            addOrEditPlatform()
        }

    }

    private fun setView(data: PlatformTransaksiListItem) {

        data?.run {
            til_nama_platform?.editText?.setText(data.nama_platform)
            til_nama_pemilik?.editText?.setText(data.nama_pemilik)
            til_nomor_rekening?.editText?.setText(data.nomor_rekening)
        }

        btn_simpan_kegiatan?.clickWithDebounce {
            addOrEditPlatform()
        }
    }

    private fun addOrEditPlatform() {

        val nama_platform = til_nama_platform?.editText?.text.toString()
        val nama_pemilik = til_nama_pemilik?.editText?.text.toString()
        val nomor_rekening = til_nomor_rekening?.editText?.text.toString()

        val tambahData = HashMap<String, Any?>()
        tambahData["nama_platform"] = nama_platform
        tambahData["nama_pemilik"] = nama_pemilik
        tambahData["nomor_rekening"] = nomor_rekening
        isLoading(true)

        if(data == null) {
            presenter.addPlatformTransaksi(tambahData)
        } else {
            data?.id?.let { presenter.editPlatformTransaksi(it, tambahData) }
        }
    }

    private fun isLoading(isLoad: Boolean) {
        if (isLoad) Utilities.showProgress(this)
        else {
            Utilities.hideProgress()
        }
    }

    override fun platformTransaksiResponse(response: PlatformTransaksiResponse) {
        isLoading(false)
        this.showCustomDialogBack("Data berhasil", "Data berhasil ditambahkan")
    }

    override fun getPlatformTransaksiResponse(response: PlatformTransaksiListResponse) {
        TODO("Not yet implemented")
    }

    override fun deletePlatformTransaksiResponse(response: EmptyResponse) {
        TODO("Not yet implemented")
    }

    override fun showError(title: String, message: String) {
        this.showErrorAlert(title, message)
        isLoading(false)
    }
}