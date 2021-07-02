package com.example.sim_sanggar.view.activity.anak

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.sim_sanggar.R
import com.example.sim_sanggar.common.Utilities
import com.example.sim_sanggar.common.Validations
import com.example.sim_sanggar.common.clickWithDebounce
import com.example.sim_sanggar.data.model.anak.AnakListItem
import com.example.sim_sanggar.data.model.anak.AnakResponse
import com.example.sim_sanggar.presenter.anak.AnakContract
import com.example.sim_sanggar.presenter.anak.AnakPresenter
import com.example.sim_sanggar.view.activity.common.BaseActivity
import kotlinx.android.synthetic.main.activity_anak.*
import kotlinx.android.synthetic.main.fragment_toolbar.*
import javax.xml.validation.Validator

class AnakActivity : BaseActivity(), AnakContract.View {

    var data: AnakListItem? = null
    var presenter = AnakPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_anak)

        setToolbar()
        toolbar_title?.text = getString(R.string.daftar)
        initListener()
    }

    private fun initListener() {
        Validations.removeError(til_nama_anak, til_umur_anak, til_tanggal_lahir, til_telepon_anak)

        btn_daftar.clickWithDebounce {
            addOrEditAnak()
        }
    }

    private fun addOrEditAnak() {
        val nama_anak = til_nama_anak?.editText?.text.toString()
        val umur = til_umur_anak?.editText?.text.toString()
        val tanggal_lahir = til_tanggal_lahir?.editText?.text.toString()
        val telepon = til_telepon_anak?.editText?.text.toString()

        val tambahData = HashMap<String, Any?>()
        tambahData["nama"] = nama_anak
        tambahData["umur"] = umur
        tambahData["tanggal_lahir"] = tanggal_lahir
        tambahData["telepon"] = telepon

        isLoading(true)

        if (data == null) {
            presenter.addAnak(tambahData)
        } else {
            Toast.makeText(this, "edit data", Toast.LENGTH_SHORT).show()
        }
    }

    private fun isLoading(isLoad: Boolean) {
        if(isLoad) Utilities.showProgress(this)
        else Utilities.hideProgress()
    }

    override fun anakResponse(response: AnakResponse) {
        isLoading(false)
        this.showCustomDialogBack("Data Berhasil", "Pendaftaran diproses, tunggu konfirmasi dari Admin sanggar")
        finish()
    }

    override fun showError(title: String, message: String) {
        this.showErrorAlert(title, message)
    }
}