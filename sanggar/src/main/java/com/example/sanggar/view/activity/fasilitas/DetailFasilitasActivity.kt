package com.example.sanggar.view.activity.fasilitas

import android.content.Intent
import android.os.Bundle
import com.example.sanggar.R
import com.example.sanggar.common.Utilities
import com.example.sanggar.common.Validations
import com.example.sanggar.common.clickWithDebounce
import com.example.sanggar.common.loadImage
import com.example.sanggar.data.model.common.EmptyResponse
import com.example.sanggar.data.model.fasilitas.FasilitasListItem
import com.example.sanggar.data.model.fasilitas.FasilitasListResponse
import com.example.sanggar.data.model.fasilitas.FasilitasResponse
import com.example.sanggar.presenter.fasilitas.FasilitasContract
import com.example.sanggar.presenter.fasilitas.FasilitasPresenter
import com.example.sanggar.view.activity.common.BaseActivity
import com.theartofdev.edmodo.cropper.CropImage
import com.theartofdev.edmodo.cropper.CropImageView
import kotlinx.android.synthetic.main.activity_detail_fasilitas.*
import kotlinx.android.synthetic.main.activity_detail_kegiatan.*
import kotlinx.android.synthetic.main.fragment_toolbar.*
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File

class DetailFasilitasActivity :  BaseActivity(), FasilitasContract.View  {

    var data: FasilitasListItem? = null
    val presenter = FasilitasPresenter(this)
    var imageFile : File? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_fasilitas)

        data = intent.getParcelableExtra<FasilitasListItem>("data")
        setToolbar()
        toolbar_title?.text = getString(R.string.fasilitas)
        data?.let { setView(it) }
        initListener()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            val result = CropImage.getActivityResult(data)
            if (resultCode == RESULT_OK) {
                iv_fasilitas_detail?.setImageURI(result.uri)
                imageFile = File(result.uri.path)
            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE){
                toast(result.error.toString())
            }
        }
    }

    private fun initListener() {
        Validations.removeError(til_judul_fasilitas, til_deskripsi_fasilitas)

        btn_simpan_fasilitas?.clickWithDebounce {
            addOrEditFasilitas()
        }

        btn_image_fasilitas?.clickWithDebounce {
            openImageResource()
        }
    }

    private fun openImageResource() {
        CropImage.activity()
                .setAspectRatio(2, 2)
                .setGuidelines(CropImageView.Guidelines.ON)
                .start(this)
    }

    private fun addOrEditFasilitas() {
        val judul = til_judul_fasilitas?.editText?.text.toString()
        val deskripsi = til_deskripsi_fasilitas?.editText?.text.toString()

        val tambahData = HashMap<String, Any?>()
        tambahData["judul"] = judul
        tambahData["deskripsi"] = deskripsi

        isLoading(true)

        if(data == null) {
            presenter.addFasilitas(tambahData)
        } else {
            data?.id?.let { presenter.editFasilitas(it, tambahData) }
        }
    }

    private fun isLoading(isLoad: Boolean) {
        if (isLoad) Utilities.showProgress(this)
        else {
            Utilities.hideProgress()
        }
    }

    private fun setView(data: FasilitasListItem) {
        data?.run {
            til_judul_fasilitas?.editText?.setText(data.judul)
            til_deskripsi_fasilitas?.editText?.setText(data.deskripsi)
            foto?.let { iv_fasilitas_detail?.loadImage(it) }
        }

        btn_simpan_fasilitas?.clickWithDebounce {
            addOrEditFasilitas()
        }
    }

    override fun fasilitasResponse(response: FasilitasResponse) {
        if (imageFile != null) {
            imageFile?.let { uploadImage(response.data?.id,it) }
        } else {
            isLoading(false)
            this.showCustomDialogBack("Data Berhasil", "Data berhasil diubah")
        }
    }

    private fun uploadImage(id: Int?, it: File) {
        val requestBody =
                RequestBody.create(MediaType.parse("multipart/form-data"), imageFile)
        val part = MultipartBody.Part.createFormData(
                "image",
                it.name,
                requestBody
        )
        id?.let { presenter.addImage(it, part) }

    }

    override fun getFasilitasResponse(response: FasilitasListResponse) {
        TODO("Not yet implemented")
    }

    override fun deleteFasilitasResponse(response: EmptyResponse) {
        TODO("Not yet implemented")
    }

    override fun uploadImageResponse() {
        isLoading(false)
        this.showCustomDialogBack("Berhasil", "Data berhasil ditambahkan")
    }

    override fun showError(title: String, message: String) {
        this.showErrorAlert(title, message)
    }
}