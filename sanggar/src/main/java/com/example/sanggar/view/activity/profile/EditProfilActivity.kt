package com.example.sanggar.view.activity.profile

import android.content.Intent
import android.os.Bundle
import com.example.sanggar.GlobalClass
import com.example.sanggar.R
import com.example.sanggar.common.Preferences
import com.example.sanggar.common.Utilities
import com.example.sanggar.common.clickWithDebounce
import com.example.sanggar.common.loadImage
import com.example.sanggar.data.model.profile.ProfileData
import com.example.sanggar.data.model.profile.ProfileResponse
import com.example.sanggar.data.model.sanggar.ProfilSanggarListResponse
import com.example.sanggar.data.model.sanggar.ProfilSanggarResponse
import com.example.sanggar.presenter.profile.ProfileContract
import com.example.sanggar.presenter.profile.ProfilePresenter
import com.example.sanggar.presenter.sanggar.ProfilSanggarContract
import com.example.sanggar.presenter.sanggar.ProfilSanggarPresenter
import com.example.sanggar.view.activity.auth.LoginActivity
import com.example.sanggar.view.activity.common.BaseActivity
import com.theartofdev.edmodo.cropper.CropImage
import com.theartofdev.edmodo.cropper.CropImageView
import kotlinx.android.synthetic.main.activity_edit_profil.*
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File

class EditProfilActivity : BaseActivity(),  ProfileContract.View, ProfilSanggarContract.View {
    var preferences = Preferences(GlobalClass.context)

    var data : ProfileData? = null
    var presenter = ProfilePresenter(this)
    var presenterSanggar = ProfilSanggarPresenter(this)
    private var profilePictFile: File? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profil)
        setToolbar()
        presenter.getProfile()
        initListener()
    }

    private fun initListener() {

        cv_logout?.clickWithDebounce {
            preferences.userLoggedOut()
            finishAffinity()
            startActivity(Intent(this, LoginActivity::class.java))
        }

        btn_simpan_perubahan?.clickWithDebounce {
            editProfile()
        }

        iv_foto_profil?.clickWithDebounce {
            openImageResources()
        }

        sr_profile?.setOnRefreshListener {
            fetchData()
        }
    }

    private fun uploadImage(profilePictFile: File) {
        val requestBody =
                RequestBody.create(MediaType.parse("multipart/form-data"), profilePictFile)
        val part = MultipartBody.Part.createFormData(
                "image",
                profilePictFile.name,
                requestBody
        )
        presenter.updateProfilePhot(part)
    }

    private fun openImageResources() {
        CropImage.activity()
                .setAspectRatio(2, 2)
                .setGuidelines(CropImageView.Guidelines.ON)
                .start(this)

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            val result = CropImage.getActivityResult(data)
            if (resultCode == RESULT_OK) {
                iv_foto_profil?.setImageURI(result.uri)
                profilePictFile = File(result.uri.path)
            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                toast(result.error.toString())
            }
        }
    }

    private fun initView(data: ProfileData) {
        data.run{
            til_nama_pengelola?.editText?.setText(data.username)
            til_telepon?.editText?.setText(data.telepon)
            til_email?.editText?.setText(data.email)
            til_nama_sanggar?.editText?.setText(data.sanggar?.nama)
            til_alamat_sanggar?.editText?.setText(data.sanggar?.alamat)
            data.photoUrl?.let { iv_foto_profil?.loadImage(it) }
        }
    }

    private fun editProfile() {
        val sanggar_id = data?.sanggar?.id
        val nama_sanggar = til_nama_sanggar?.editText?.text.toString()
        val alamat_sanggar = til_alamat_sanggar?.editText?.text.toString()

        val telepon = til_telepon?.editText?.text.toString()
        val email = til_email?.editText?.text.toString()
        val nama_pengelola = til_nama_pengelola?.editText?.text.toString()

        val editData = HashMap<String, Any?>()
        editData["username"] = nama_pengelola
        editData["telepon"] = telepon
        editData["email"] = email

        val editSanggar = HashMap<String, Any?>()
        editSanggar["nama"] = nama_sanggar
        editSanggar["alamat"] = alamat_sanggar

        isLoading(true)
//        data?.id?.let { presenter.editProfile( tambahData) }
        presenter.editProfile(editData)
        profilePictFile?.let { uploadImage(it) }
        if (sanggar_id != null) {
            presenterSanggar.editProfilSanggar(sanggar_id, editSanggar)
        }
    }

    fun fetchData() {
        isLoading(true)
        presenter.getProfile()
    }

    override fun onResume() {
        super.onResume()
        fetchData()
    }

    private fun isLoading(isLoad: Boolean) {
        if (isLoad) Utilities.showProgress(this)
        else {
            Utilities.hideProgress()
            sr_profile.isRefreshing = false
        }
    }

    override fun getProfileResponse(response: ProfileResponse) {
        isLoading(false)
        data = response.data
        data?.let { initView(it) }
    }

    override fun editProfileResponse(response: ProfileResponse) {
        isLoading(false)
        this.showCustomDialogBack("Berhasil", "Profil berhasil diubah")
    }

    override fun profilSanggarResponse(response: ProfilSanggarResponse) {
        isLoading(false)
        this.showCustomDialogBack("Berhasil", "Profil berhasil diubah")
    }

    override fun getProfilSanggarResponse(response: ProfilSanggarListResponse) {
        TODO("Not yet implemented")
    }

    override fun showError(title: String, message: String) {
        this.showErrorAlert(title, message)
    }

}