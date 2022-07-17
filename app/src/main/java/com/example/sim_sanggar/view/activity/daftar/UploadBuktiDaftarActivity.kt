package com.example.sim_sanggar.view.activity.daftar

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.sim_sanggar.R
import com.example.sim_sanggar.common.Utilities
import com.example.sim_sanggar.common.clickWithDebounce
import com.example.sim_sanggar.data.model.common.EmptyResponse
import com.example.sim_sanggar.data.model.daftar.DaftarListResponse
import com.example.sim_sanggar.data.model.daftar.DaftarResponse
import com.example.sim_sanggar.data.model.daftar.PendaftaranAnak
import com.example.sim_sanggar.presenter.daftar.DaftarListContract
import com.example.sim_sanggar.presenter.daftar.DaftarListPresenter
import com.example.sim_sanggar.view.activity.common.BaseActivity
import com.theartofdev.edmodo.cropper.CropImage
import com.theartofdev.edmodo.cropper.CropImageView
import kotlinx.android.synthetic.main.activity_upload_bukti_daftar.*
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File

class UploadBuktiDaftarActivity : BaseActivity(), DaftarListContract.View {

    var data_daftar : PendaftaranAnak? = null
    var imageFile: File? = null
    val presenter = DaftarListPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_upload_bukti_daftar)

        data_daftar = intent.getParcelableExtra<PendaftaranAnak>("data_daftar")

        initListener()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            val result = CropImage.getActivityResult(data)
            if (resultCode == RESULT_OK) {
                iv_upload_bukti_bayar?.setImageURI(result.uri)
                imageFile = File(result.uri.path)
            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                toast(result.error.toString())
            }
        }
    }

    private fun kirimData() {

        val kelasId = data_daftar?.jadwal_sanggar_id?.id
        val anakId = data_daftar?.anak_id?.id
        val transfer_via = til_transfer_via?.editText?.text.toString()

        val tambahData = HashMap<String, Any?>()
        tambahData["jadwal_sanggar_id"] = kelasId
        tambahData["anak_id"] = anakId
        tambahData["transfer_via"] = transfer_via

        isLoading(true)
        presenter.addListDaftar(tambahData)

    }

    private fun initListener() {
        btn_image_bukti_pembayaran?.clickWithDebounce {
            openImageResource()
        }

        btn_kirim_data?.clickWithDebounce {
//            data?.let { it1 -> kirimData(it1) }
            kirimData()
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

    override fun daftarListResponse(response: DaftarResponse) {
        if (imageFile != null) {
            imageFile?.let { uploadImage(response.data?.id,it) }
        } else {
            isLoading(false)
            this.showCustomDialogBack("Data berhasil", "Data berhasil dikirim")
        }

    }


    override fun getDaftarListResponse(response: DaftarListResponse) {
        TODO("Not yet implemented")
    }

    override fun getBiayaPendaftaran(response: DaftarListResponse) {
        TODO("Not yet implemented")
    }

    override fun deleteDaftarListResponse(response: EmptyResponse) {
        TODO("Not yet implemented")
    }

    override fun uploadImageResponse() {
        TODO("Not yet implemented")
    }

    override fun showError(title: String, message: String) {
        this.showErrorAlert(title, message)
    }
}