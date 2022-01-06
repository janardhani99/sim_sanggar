package com.example.sanggar.view.activity.pembelajaran

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.sanggar.R
import com.example.sanggar.common.Utilities
import com.example.sanggar.common.clickWithDebounce
import com.example.sanggar.data.model.common.EmptyResponse
import com.example.sanggar.data.model.pembelajaran.PembelajaranData
import com.example.sanggar.data.model.pembelajaran.PembelajaranListResponse
import com.example.sanggar.data.model.pembelajaran.PembelajaranResponse
import com.example.sanggar.presenter.pembelajaran.PembelajaranContract
import com.example.sanggar.presenter.pembelajaran.PembelajaranPresenter
import com.example.sanggar.view.activity.common.BaseActivity
import kotlinx.android.synthetic.main.activity_detail_pembelajaran.*
import kotlinx.android.synthetic.main.fragment_toolbar.*

class DetailPembelajaranActivity : BaseActivity(), PembelajaranContract.View {

    var data: PembelajaranData? = null
    val presenter = PembelajaranPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_pembelajaran)

        setToolbar()
        toolbar_title.setText("Pembelajaran")
        data = intent.getParcelableExtra<PembelajaranData>("data")
        initListener()
        data?.let { setView(it) }
    }

    private fun initListener() {
        btn_simpan?.clickWithDebounce {
            addOrEditPembelajaran()
        }
    }

    private fun addOrEditPembelajaran() {
        val judul = til_judul_pembelajaran.editText?.text.toString()
        val deskripsi = til_deskripsi_pembelajaran.editText?.text.toString()
        val link_video = til_link_video_pembelajaran.editText?.text.toString()

        val tambahData = HashMap<String, Any?>()
        tambahData["judul"] = judul
        tambahData["deskripsi"] = deskripsi
        tambahData["link_video"] = link_video

        isLoading(true)
        if(data == null) {
            presenter.addPembelajaran(tambahData)
        } else {
            data?.id?.let { presenter.editPembelajaran(it, tambahData) }
        }
    }

    private fun setView(data: PembelajaranData) {
        data?.run {
            til_judul_pembelajaran.editText?.setText(data.judul)
            til_deskripsi_pembelajaran.editText?.setText(data.deskripsi)
            til_link_video_pembelajaran.editText?.setText(data.link_video)
        }

        btn_simpan?.clickWithDebounce {
            addOrEditPembelajaran()
        }
    }
    private fun isLoading(isLoad: Boolean) {
        if (isLoad) Utilities.showProgress(this)
        else Utilities.hideProgress()
    }

    override fun pembelajaranResponse(response: PembelajaranResponse) {

        isLoading(false)
        showCustomDialogBack("Berhasil", "Data berhasil disimpan")
    }

    override fun getPembelajaranResponse(response: PembelajaranListResponse) {
        TODO("Not yet implemented")
    }

    override fun deletePembelajaranResponse(response: EmptyResponse) {
        TODO("Not yet implemented")
    }

    override fun showError(title: String, message: String) {
        showErrorAlert(title, message)
    }
}