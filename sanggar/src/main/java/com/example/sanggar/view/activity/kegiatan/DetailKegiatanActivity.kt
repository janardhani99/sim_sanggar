package com.example.sanggar.view.activity.kegiatan

import android.content.Intent
import android.os.Bundle
import com.example.sanggar.R
import com.example.sanggar.common.*
import com.example.sanggar.data.model.common.EmptyResponse
import com.example.sanggar.data.model.kegiatan.KegiatanListItem
import com.example.sanggar.data.model.kegiatan.KegiatanListResponse
import com.example.sanggar.data.model.kegiatan.KegiatanResponse
import com.example.sanggar.presenter.kegiatan.KegiatanContract
import com.example.sanggar.presenter.kegiatan.KegiatanPresenter
import com.example.sanggar.view.activity.common.BaseActivity
import com.theartofdev.edmodo.cropper.CropImage
import com.theartofdev.edmodo.cropper.CropImageView
import kotlinx.android.synthetic.main.activity_detail_kegiatan.*
import kotlinx.android.synthetic.main.fragment_toolbar.*
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File

class DetailKegiatanActivity : BaseActivity(), KegiatanContract.View {

    var data: KegiatanListItem? = null
    val presenter = KegiatanPresenter(this)
    var imageFile : File? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_kegiatan)

        data = intent.getParcelableExtra<KegiatanListItem>("data")
        setToolbar()
        toolbar_title?.text = getString(R.string.kegiatan)
        data?.let { setView(it) }
        initListener()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            val result = CropImage.getActivityResult(data)
            if (resultCode == RESULT_OK) {
                iv_kegiatan_detail?.setImageURI(result.uri)
                imageFile = File(result.uri.path)
            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                toast(result.error.toString())
            }
        }
    }

    private fun initListener() {
        Validations.removeError(til_judul_kegiatan, til_deskripsi_kegiatan)

        btn_simpan_kegiatan?.clickWithDebounce {
            addOrEditKegiatan()
        }

        btn_image_kegiatan?.clickWithDebounce {
            openImageResource()
        }
    }

    private fun openImageResource() {
        CropImage.activity()
                .setAspectRatio(2, 2)
                .setGuidelines(CropImageView.Guidelines.ON)
                .start(this)
    }

    private fun setView(data: KegiatanListItem) {
//        when (data) {
//            Constants.Action.CREATE -> createMode()
//            else -> editMode()
//        }
        data?.run {
            til_judul_kegiatan?.editText?.setText(data.judul)
            til_deskripsi_kegiatan?.editText?.setText(data.deskripsi)
            foto?.let { iv_kegiatan_detail?.loadImage(it) }
        }

        btn_simpan_kegiatan.clickWithDebounce {
            addOrEditKegiatan()
        }
    }

    private fun addOrEditKegiatan() {
//        Toast.makeText(this, "${isAllValid()}", Toast.LENGTH_SHORT).show()

        val judul = til_judul_kegiatan?.editText?.text.toString()
        val deskripsi = til_deskripsi_kegiatan?.editText?.text.toString()
//        val foto = Glide.with(applicationContext).load(data?.foto).into(iv_kegiatan_detail)

        val tambahData = HashMap<String, Any?>()
        tambahData["judul"] = judul
        tambahData["deskripsi"] = deskripsi
//        tambahData["foto"] = foto

        isLoading(true)

        if(data == null) {
            presenter.addKegiatan(tambahData)
        } else {
            data?.id?.let { presenter.editKegiatan(it, tambahData) }
        }
    }

    private fun isLoading(isLoad: Boolean) {
        if (isLoad) Utilities.showProgress(this)
        else {
            Utilities.hideProgress()
        }
    }

    override fun kegiatanResponse(response: KegiatanResponse) {

        if (imageFile != null) {
            imageFile?.let { uploadImage(response.data?.id,it) }
        } else {
            isLoading(false)
            this.showCustomDialogBack("Berhasil", "Data berhasil diubah")
        }
//        finish()
//        startActivity(Intent(this, KegiatanActivity::class.java))
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

    override fun getKegiatanResponse(response: KegiatanListResponse) {
        TODO("Not yet implemented")
    }

    override fun deleteKegiatanResponse(response: EmptyResponse) {
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

//private fun isAllValid(): Boolean {
//    val valid = mutableListOf<Boolean>()
//    valid.apply {
//        add(Validations.isTextValid(Constants.Validations.VALIDATION_EMPTY, textInputLayout = til_judul_kegiatan))
//        add(Validations.isTextValid(Constants.Validations.VALIDATION_EMPTY, textInputLayout = til_deskripsi_kegiatan))
//    }
//    return !valid.contains(false)
//}

