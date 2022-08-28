package com.example.admin.view.activity

import android.content.Intent
import android.os.Bundle
import com.example.admin.GlobalClass
import com.example.admin.R
import com.example.admin.common.Preferences
import com.example.admin.common.Utilities
import com.example.admin.common.clickWithDebounce
import com.example.admin.common.loadImage
import com.example.admin.data.model.common.EmptyResponse
import com.example.admin.data.model.user.UserData
import com.example.admin.data.model.user.UserListResponse
import com.example.admin.data.model.user.UserResponse
import com.example.admin.presenter.user.UserContract
import com.example.admin.presenter.user.UserPresenter
import com.example.admin.view.activity.auth.LoginActivity
import com.example.admin.view.activity.common.BaseActivity
import com.theartofdev.edmodo.cropper.CropImage
import com.theartofdev.edmodo.cropper.CropImageView
import kotlinx.android.synthetic.main.activity_edit_profil.*
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File

class EditProfilActivity : BaseActivity(), UserContract.View {

    var preferences = Preferences(GlobalClass.context)

    var data : UserData? = null
    var presenter = UserPresenter(this)

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
    private fun initView(data: com.example.admin.data.model.user.UserData) {
        data.run{
            til_username?.editText?.setText(data.username)
            til_telepon?.editText?.setText(data.telepon)
            til_email?.editText?.setText(data.email)
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

    override fun userResponse(response: UserResponse) {
        TODO("Not yet implemented")
    }

    override fun getUserResponse(response: UserListResponse) {
        TODO("Not yet implemented")
    }

    override fun deleteUserResponse(response: EmptyResponse) {
        TODO("Not yet implemented")
    }

    override fun getProfileResponse(response: UserResponse) {
        isLoading(false)
        data = response.data
        data?.let { initView(it) }

    }

    override fun editProfileResponse(response: UserResponse) {
        isLoading(false)
        this.showCustomDialogBack("Berhasil", "Profil berhasil diubah")
    }

    override fun showError(title: String, message: String) {
        this.showErrorAlert(title, message)
    }
}