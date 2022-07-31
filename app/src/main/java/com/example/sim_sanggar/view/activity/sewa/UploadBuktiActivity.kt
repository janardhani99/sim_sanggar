package com.example.sim_sanggar.view.activity.sewa

import android.content.Intent
import android.os.Bundle
import com.example.sim_sanggar.R
import com.example.sim_sanggar.common.Utilities
import com.example.sim_sanggar.common.clickWithDebounce
import com.example.sim_sanggar.common.loadImage
import com.example.sim_sanggar.data.model.sewa.SewaListItem
import com.example.sim_sanggar.data.model.sewa.SewaListResponse
import com.example.sim_sanggar.data.model.sewa.SewaResponse
import com.example.sim_sanggar.presenter.sewa.SewaContract
import com.example.sim_sanggar.presenter.sewa.SewaPresenter
import com.example.sim_sanggar.view.activity.common.BaseActivity
import com.example.sim_sanggar.view.activity.daftar.DaftarKelasActivity
import com.example.sim_sanggar.view.adapter.sewaadapter.SewaAdapter
import com.theartofdev.edmodo.cropper.CropImage
import com.theartofdev.edmodo.cropper.CropImageView
import kotlinx.android.synthetic.main.activity_upload_bukti.*
import kotlinx.android.synthetic.main.fragment_toolbar.*
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File

class UploadBuktiActivity : BaseActivity(), SewaContract.View {

    var data_sewa: SewaListItem? = null
    val presenter = SewaPresenter(this)
    var imageFile: File? = null
    lateinit var adapter: SewaAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_upload_bukti)

        setToolbar()
        toolbar_title?.text = getString(R.string.sewa)
        data_sewa = intent.getParcelableExtra<SewaListItem>("data_sewa")
        data_sewa?.let { setView(it) }
        initListener()
//        initAdapter()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            val result = CropImage.getActivityResult(data)
            if (resultCode == RESULT_OK) {
                iv_upload_bukti?.setImageURI(result.uri)
                imageFile = File(result.uri.path)
            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                toast(result.error.toString())
            }
        }
    }

    //    private fun initAdapter() {
//        adapter = SewaAdapter { sewaItem-> uploadBukti(sewaItem) }
//    }
//
    private fun setView(data: SewaListItem) {
        data?.run {
            foto?.let { iv_upload_bukti.loadImage(it) }
        }

        btn_upload_foto?.clickWithDebounce {

            uploadBukti(data)
        }
    }

    private fun uploadBukti(data: SewaListItem?) {

        val transfer_via = til_transfer_via?.editText?.text.toString()
        val status = "2"
        val isValid = imageFile != null && data?.id != null
        if (isValid) {
            presenter.uploadBuktiPembayaran(data?.id!!, imageFile!!, transfer_via, status)
        }


////        val sewa_id = sewaItem.id
//        val tanggal = data?.tanggal
//        val jamMulai = data?.jam_mulai
//        val jamSelesai = data?.jam_selesai
//        val transfer_via = til_transfer_via?.editText?.text.toString()
//
//        val tambahData = HashMap<String, Any?>()
//
//        tambahData["tanggal"] = tanggal
//        tambahData["jam_mulai"] = jamMulai
//        tambahData["jam_selesai"] = jamSelesai
//        tambahData["transfer_via"] = transfer_via
//        isLoading(true)
//
////        presenter.addSewa(tambahData)
//        data?.id?.let { presenter.uploadBukti(it, tambahData) }
    }

    private fun initListener() {
        btn_image_bukti_pembayaran?.clickWithDebounce {
            openImageResource()
        }

        btn_upload_foto?.clickWithDebounce {
            uploadBukti(data_sewa)
        }
    }

    private fun openImageResource() {
        CropImage.activity()
                .setAspectRatio(2, 3)
                .setGuidelines(CropImageView.Guidelines.ON)
                .start(this)
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

    private fun isLoading(isLoad: Boolean) {
        if (isLoad) Utilities.showProgress(this)
        else Utilities.hideProgress()
    }

    override fun sewaResponse(response: SewaResponse) {

//        if (imageFile != null) {
//            imageFile?.let { uploadImage(data?.id,it) }
//        } else {
        isLoading(false)
        this.showCustomDialogNext("Berhasil", "Pendaftaran Berhasil", Intent(this, SewaActivity::class.java))
//        }
    }

    override fun getSewaResponse(response: SewaListResponse) {
        TODO("Not yet implemented")
    }

    override fun getTanggalSewaResponse(response: SewaListResponse) {
        TODO("Not yet implemented")
    }

    override fun uploadImageResponse() {
        isLoading(false)
        this.showCustomDialogNext("Berhasil", "Pendaftaran Berhasil", Intent(this, SewaActivity::class.java))
    }

    override fun showError(title: String, message: String) {
        this.showErrorAlert(title, message)
    }
}