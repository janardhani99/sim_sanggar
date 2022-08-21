package com.example.sanggar.view.activity.studio

import android.content.Intent
import android.os.Bundle
import com.example.sanggar.R
import com.example.sanggar.common.Utilities
import com.example.sanggar.common.Validations
import com.example.sanggar.common.clickWithDebounce
import com.example.sanggar.common.loadImage
import com.example.sanggar.data.model.common.EmptyResponse
import com.example.sanggar.data.model.studio.StudioData
import com.example.sanggar.data.model.studio.StudioListResponse
import com.example.sanggar.data.model.studio.StudioResponse
import com.example.sanggar.presenter.studio.StudioContract
import com.example.sanggar.presenter.studio.StudioPresenter
import com.example.sanggar.view.activity.common.BaseActivity
import com.theartofdev.edmodo.cropper.CropImage
import com.theartofdev.edmodo.cropper.CropImageView
import kotlinx.android.synthetic.main.activity_detail_studio.*
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File

class DetailStudioActivity : BaseActivity(), StudioContract.View {
    var data: StudioData? = null
    private var presenter= StudioPresenter(this)
    var imageFile : File? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_studio)

        data = intent.getParcelableExtra<StudioData>("data_studio")
        setToolbar()
        data?.let { setView(it) }
        initListener()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            val result = CropImage.getActivityResult(data)
            if (resultCode == RESULT_OK) {
                iv_studio_detail?.setImageURI(result.uri)
                imageFile = File(result.uri.path)
            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE){
                toast(result.error.toString())
            }
        }
    }

    private fun initListener() {
        Validations.removeError(til_nama_studio, til_harga_sewa, til_luas_studio, til_deskripsi_studio)

        btn_simpan_studio?.clickWithDebounce {
            addOrEditStudio()
        }

        btn_image_studio?.clickWithDebounce {
            openImageResource()
        }
    }

    private fun openImageResource() {
        CropImage.activity()
                .setAspectRatio(2, 2)
                .setGuidelines(CropImageView.Guidelines.ON)
                .start(this)
    }

    private fun addOrEditStudio() {
        val nama_studio = til_nama_studio?.editText?.text.toString()
        val harga_sewa = til_harga_sewa?.editText?.text.toString()
        val luas_studio= til_luas_studio?.editText?.text.toString()
        val deskripsi = til_deskripsi_studio?.editText?.text.toString()

        val tambahData = HashMap<String, Any?>()
        tambahData["nama_studio"] = nama_studio
        tambahData["harga"] = harga_sewa
        tambahData["ukuran"] = luas_studio
        tambahData["deskripsi"] = deskripsi

        isLoading(true)

        if(data == null) {
            presenter.addStudio(tambahData)
        } else {
            data?.id?.let { presenter.editStudio(it, tambahData) }
        }
    }

    private fun isLoading(isLoad: Boolean) {
        if (isLoad) Utilities.showProgress(this)
        else {
            Utilities.hideProgress()
        }
    }

    private fun setView(data: StudioData) {
        data?.run {
            til_nama_studio?.editText?.setText(data.nama_studio)
            til_harga_sewa?.editText?.setText(data.harga)
            til_luas_studio?.editText?.setText(data.ukuran)
            til_deskripsi_studio?.editText?.setText(data.deskripsi)
            foto?.let { iv_studio_detail?.loadImage(it) }
        }

        btn_simpan_studio?.clickWithDebounce {
            addOrEditStudio()
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
        id?.let { presenter.addImage(it,part) }
    }

    override fun studioResponse(response: StudioResponse) {
        if (imageFile != null) {
            imageFile?.let { uploadImage(response.data?.id,it) }
        } else {
            isLoading(false)
            this.showCustomDialogBack("Berhasil", "Data berhasil diubah")
        }
    }

    override fun getStudioResponse(response: StudioListResponse) {
        TODO("Not yet implemented")
    }

    override fun deleteStudioResponse(response: EmptyResponse) {
        TODO("Not yet implemented")
    }

    override fun uploadImageResponse() {
        isLoading(false)
        this.showCustomDialogBack("Berhasil", "Data berhasil ditambahkan")
    }

    override fun showError(title: String, message: String) {
        this.showErrorAlert(title, message)
        isLoading(false)
    }


}