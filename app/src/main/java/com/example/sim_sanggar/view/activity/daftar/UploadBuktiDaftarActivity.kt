package com.example.sim_sanggar.view.activity.daftar

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.sim_sanggar.R
import com.example.sim_sanggar.common.Utilities
import com.example.sim_sanggar.common.clickWithDebounce
import com.example.sim_sanggar.data.model.anak.AnakListItem
import com.example.sim_sanggar.data.model.common.EmptyResponse
import com.example.sim_sanggar.data.model.daftar.DaftarListResponse
import com.example.sim_sanggar.data.model.daftar.DaftarResponse
import com.example.sim_sanggar.data.model.daftar.PendaftaranAnak
import com.example.sim_sanggar.data.model.jadwal_sanggar.JadwalSanggarItem
import com.example.sim_sanggar.presenter.daftar.DaftarListContract
import com.example.sim_sanggar.presenter.daftar.DaftarListPresenter
import com.example.sim_sanggar.view.activity.common.BaseActivity
import com.example.sim_sanggar.view.fragment.beranda.BerandaFragment
import com.example.sim_sanggar.view.fragment.daftar.DaftarFragment
import com.theartofdev.edmodo.cropper.CropImage
import com.theartofdev.edmodo.cropper.CropImageView
import kotlinx.android.synthetic.main.activity_upload_bukti_daftar.*
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File

class UploadBuktiDaftarActivity : BaseActivity(), DaftarListContract.View {

    var data_daftar : PendaftaranAnak? = null
    var data_kelas: JadwalSanggarItem? = null
    var selectedAnak : AnakListItem? = null
    var imageFile: File? = null
    val presenter = DaftarListPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_upload_bukti_daftar)

        setToolbar()

//        data_daftar = intent.getParcelableExtra<PendaftaranAnak>("data_daftar")
        data_kelas = intent.getParcelableExtra<JadwalSanggarItem>("data_kelas")
        selectedAnak = intent.getParcelableExtra<AnakListItem>("selected_anak")

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

        val transfer_via = til_transfer_via?.editText?.text.toString()

        val tambahData = HashMap<String, Any?>()
        tambahData["jadwal_sanggar_id"] = data_kelas?.id
        tambahData["anak_id"] = selectedAnak?.id
        tambahData["transfer_via"] = transfer_via
        tambahData["status"] = "1"

        isLoading(true)

        if (imageFile != null) {
//            presenter.uploadBuktiPembayaran(data?.id!!, imageFile!!, transfer_via, status)
            presenter.addListDaftar(tambahData)
        }

    }

    private fun initListener() {
        btn_image_bukti_pembayaran?.clickWithDebounce {
            openImageResource()
        }

        btn_kirim_data?.clickWithDebounce {
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
            this.showCustomDialogNext("Berhasil", "Pendaftaran Berhasil", Intent(this, DaftarKelasActivity::class.java))
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
        isLoading(false)
        this.showCustomDialogNext("Berhasil", "Pendaftaran Berhasil", Intent(this, DaftarKelasActivity::class.java))
    }

    override fun getAnakOnKelasResponse(response: DaftarListResponse) {
        TODO("Not yet implemented")
    }

    override fun showError(title: String, message: String) {
        this.showErrorAlert(title, message)
    }
}