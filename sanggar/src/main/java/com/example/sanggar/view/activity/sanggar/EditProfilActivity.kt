package com.example.sanggar.view.activity.sanggar

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.sanggar.GlobalClass
import com.example.sanggar.R
import com.example.sanggar.common.Preferences
import com.example.sanggar.common.Utilities
import com.example.sanggar.common.clickWithDebounce
import com.example.sanggar.data.model.auth.AuthData
import com.example.sanggar.data.model.auth.AuthResponse
import com.example.sanggar.data.model.sanggar.ProfilSanggarListResponse
import com.example.sanggar.data.model.sanggar.ProfilSanggarResponse
import com.example.sanggar.data.model.sanggar.SanggarData
import com.example.sanggar.data.model.user_sanggar.UserSanggarItem
import com.example.sanggar.data.model.user_sanggar.UserSanggarListResponse
import com.example.sanggar.data.model.user_sanggar.UserSanggarResponse
import com.example.sanggar.presenter.auth.AuthContract
import com.example.sanggar.presenter.auth.AuthPresenter
import com.example.sanggar.presenter.sanggar.ProfilSanggarContract
import com.example.sanggar.presenter.sanggar.ProfilSanggarPresenter
import com.example.sanggar.presenter.user_sanggar.UserSanggarContract
import com.example.sanggar.presenter.user_sanggar.UserSanggarPresenter
import com.example.sanggar.view.activity.common.BaseActivity
import com.example.sanggar.view.fragment.sanggar.ProfilFragment
import kotlinx.android.synthetic.main.activity_edit_profil.*
import kotlinx.android.synthetic.main.activity_jadwal_sanggar.*
import kotlinx.android.synthetic.main.activity_register.*
import kotlinx.android.synthetic.main.fragment_toolbar.*

class EditProfilActivity : BaseActivity(), UserSanggarContract.View, ProfilSanggarContract.View {

    var data: UserSanggarItem? = null
    val presenter = ProfilSanggarPresenter(this)
    val presenterUser = UserSanggarPresenter(this)
//    val preferences = Preferences(GlobalClass.context)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profil)

        setToolbar()

        data?.let { setView(it) }
    }


//    private fun initListener() {
//        btn_simpan?.clickWithDebounce {
//            addProfilProcess()
//        }
//    }

    private fun setView(data: UserSanggarItem) {

        data?.run {
            til_email_sanggar?.editText?.setText(data.email)
//            til_nama_sanggar?.editText?.setText(data.nama_sanggar)
//            til_alamat?.editText?.setText(data.alamat)
//            til_nomor_telepon?.editText?.setText(data.telepon)
//            til_bank?.editText?.setText(data.bank)
//            til_no_rekening?.editText?.setText(data.nomor_rekening)
//            til_harga_pendaftaran?.editText?.setText(data.harga_pendaftaran_siswa)
//            til_harga_sewa?.editText?.setText(data.harga_penyewaan_perjam)
        }

        btn_simpan_profil?.clickWithDebounce {
            editProfil()
        }

    }
    private fun editProfil() {
        val email_sanggar = til_email_sanggar.editText?.text.toString()
//        val nama_sanggar = til_nama_sanggar.editText?.text.toString()
//        val alamat = til_alamat.editText?.text.toString()
//        val nomor_telepon = til_nomor_telepon.editText?.text.toString()
//        val bank = til_bank.editText?.text.toString()
//        val no_rekening = til_no_rekening.editText?.text.toString()
//        val harga_pendaftaran = til_harga_pendaftaran.editText?.text.toString()
//        val harga_sewa = til_harga_sewa.editText?.text.toString()

        val tambahData = HashMap<String, Any?>()
        tambahData["email"] = email_sanggar
//        tambahData["nama"] = nama_sanggar
//        tambahData["alamat"] = alamat
//        tambahData["bank"] = bank
//        tambahData["telepon"] = nomor_telepon
//        tambahData["nomor_rekening"] = no_rekening
//        tambahData["harga_pendaftaran_siswa"] = harga_pendaftaran
//        tambahData["harga_penyewaan_perjam"] = harga_sewa

//        isLoadingProcess(true)
//        data?.id?.let { presenterUser(it, tambahData) }
    }

    override fun profilSanggarResponse(response: ProfilSanggarResponse) {
//        isLoadingProcess(false)
        this.showCustomDialogBack("Data berhasil", "Data berhasil diubah")
//        onBackPressed()
//        val data = response.data
//        preferences.apply {
//            userId = data?.id!!
//            userLoggedIn = true
//        }
//        finishAffinity()
//        startActivity(Intent(this, ProfilFragment::class.java))
    }

    override fun getProfilSanggarResponse(response: ProfilSanggarListResponse) {

    }

    override fun getUserListResponse(response: UserSanggarListResponse) {
        TODO("Not yet implemented")
    }

    override fun userResponse(response: UserSanggarResponse) {
        TODO("Not yet implemented")
    }

    override fun showError(title: String, message: String) {
        showErrorAlert(title, message)
    }

    private fun isLoadingProcess(isLoad: Boolean) {
        if(isLoad) Utilities.showProgress(this)
        else Utilities.hideProgress()
    }
}