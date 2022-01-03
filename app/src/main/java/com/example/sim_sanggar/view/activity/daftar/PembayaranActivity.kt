package com.example.sim_sanggar.view.activity.daftar

import android.content.Intent
import android.os.Bundle
import com.example.sim_sanggar.R
import com.example.sim_sanggar.common.Utilities
import com.example.sim_sanggar.common.clickWithDebounce
import com.example.sim_sanggar.data.model.anak.AnakListItem
import com.example.sim_sanggar.data.model.common.EmptyResponse
import com.example.sim_sanggar.data.model.daftar.DaftarListResponse
import com.example.sim_sanggar.data.model.daftar.DaftarResponse
import com.example.sim_sanggar.data.model.daftar.PendaftaranAnak
import com.example.sim_sanggar.data.model.sewa.SewaListItem
import com.example.sim_sanggar.data.model.sewa.SewaListResponse
import com.example.sim_sanggar.data.model.sewa.SewaResponse
import com.example.sim_sanggar.presenter.daftar.DaftarListContract
import com.example.sim_sanggar.presenter.daftar.DaftarListPresenter
import com.example.sim_sanggar.presenter.sewa.SewaContract
import com.example.sim_sanggar.presenter.sewa.SewaPresenter
import com.example.sim_sanggar.view.activity.common.BaseActivity
import com.example.sim_sanggar.view.adapter.anakterdaftar.AnakTerdaftarAdapter
import com.example.sim_sanggar.view.adapter.sewaadapter.SewaAdapter
import com.theartofdev.edmodo.cropper.CropImage
import com.theartofdev.edmodo.cropper.CropImageView
import kotlinx.android.synthetic.main.activity_bayar_pendaftaran.*
import kotlinx.android.synthetic.main.activity_upload_bukti.*
import kotlinx.android.synthetic.main.activity_upload_bukti.btn_image_bukti_pembayaran
import kotlinx.android.synthetic.main.activity_upload_bukti.iv_upload_bukti
import kotlinx.android.synthetic.main.activity_upload_bukti.til_transfer_via
import kotlinx.android.synthetic.main.fragment_toolbar.*
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File

class PembayaranActivity : BaseActivity(), DaftarListContract.View {

    lateinit var data: AnakListItem
    var dataPendaftaran : PendaftaranAnak? = null
    val presenter = DaftarListPresenter(this)
    var imageFile: File? = null
    lateinit var adapter: AnakTerdaftarAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bayar_pendaftaran)

        setToolbar()
        toolbar_title?.text = getString(R.string.daftar_anak)
        data = intent.getParcelableExtra<AnakListItem>("data")!!
//        data?.let { setView(it) }
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
//    private fun setView(data: SewaListItem) {
//        data?.run {
//            foto?.let { iv_upload_bukti.loadImage(it) }
//        }
//
//        btn_upload_foto?.clickWithDebounce {
//
//            uploadBukti()
//        }
//    }

    private fun kirimData() {
        val transfer_via = til_transfer_via?.editText?.text.toString()
        val anak_id = data.id
        val tambahData = HashMap<String, Any?>()
        tambahData["transfer_via"] = transfer_via
        tambahData["anak_id"] = anak_id
//        val isValid = imageFile != null && data?.id != null
//        if (data?.id == null) {
          presenter.addListDaftar(tambahData)
//        } else {
//            showCustomDialogBack("Gagal", "gagal")
//        }
    }

    private fun initListener() {
        btn_image_bukti_pembayaran.clickWithDebounce {
            openImageResource()
        }

        btn_kirim_data.clickWithDebounce {
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
            this.showCustomDialogBack("Data berhasil", "Data berhasil diubah")
        }
    }

    override fun getDaftarListResponse(response: DaftarListResponse) {
        TODO("Not yet implemented")
    }

    override fun deleteDaftarListResponse(response: EmptyResponse) {
        TODO("Not yet implemented")
    }

    override fun uploadImageResponse() {
        isLoading(false)
        this.showCustomDialogBack("Data berhasil", "Data berhasil ditambahkan")
    }

    override fun showError(title: String, message: String) {
        this.showErrorAlert(title, message)
    }
}