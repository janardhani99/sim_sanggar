package com.example.sim_sanggar.view.activity.edit_profil

import android.content.Intent
import android.os.Bundle
import com.example.sim_sanggar.GlobalClass
import com.example.sim_sanggar.R
import com.example.sim_sanggar.common.Preferences
import com.example.sim_sanggar.common.Utilities
import com.example.sim_sanggar.common.clickWithDebounce
import com.example.sim_sanggar.common.loadImage
import com.example.sim_sanggar.data.model.profile.ProfileData
import com.example.sim_sanggar.data.model.profile.ProfileResponse
import com.example.sim_sanggar.presenter.profile.ProfileContract
import com.example.sim_sanggar.presenter.profile.ProfilePresenter
import com.example.sim_sanggar.view.activity.auth.LoginActivity
import com.example.sim_sanggar.view.activity.common.BaseActivity
import com.example.sim_sanggar.view.adapter.profile.ProfileAdapter
import com.theartofdev.edmodo.cropper.CropImage
import com.theartofdev.edmodo.cropper.CropImageView
import kotlinx.android.synthetic.main.activity_edit_profil.*
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File

class EditProfilActivity : BaseActivity(), ProfileContract.View {

    var preferences = Preferences(GlobalClass.context)

    var data : ProfileData? = null
    var presenter = ProfilePresenter(this)
    lateinit var adapter : ProfileAdapter

    private var profilePictFile: File? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profil)

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
            til_username?.editText?.setText(data.username)
            til_telepon?.editText?.setText(data.telepon)
            til_email?.editText?.setText(data.email)
            til_sanggar?.editText?.setText(data.sanggar?.nama)
            data.photoUrl?.let { iv_foto_profil?.loadImage(it) }
        }
    }

    private fun editProfile() {
        val username = til_username?.editText?.text.toString()
        val telepon = til_telepon?.editText?.text.toString()
        val email = til_email?.editText?.text.toString()

        val tambahData = HashMap<String, Any?>()
        tambahData["username"] = username
        tambahData["telepon"] = telepon
        tambahData["email"] = email

        isLoading(true)
//        data?.id?.let { presenter.editProfile( tambahData) }
        presenter.editProfile(tambahData)
        profilePictFile?.let { uploadImage(it) }
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

    override fun showError(title: String, message: String) {
        this.showErrorAlert(title, message)
    }
}