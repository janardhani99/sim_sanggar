package com.example.sim_sanggar.view.activity.sewa

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.sim_sanggar.R
import com.example.sim_sanggar.common.Utilities
import com.example.sim_sanggar.common.Validations
import com.example.sim_sanggar.common.clickWithDebounce
import com.example.sim_sanggar.data.model.sewa.SewaListItem
import com.example.sim_sanggar.data.model.sewa.SewaResponse
import com.example.sim_sanggar.presenter.sewa.SewaContract
import com.example.sim_sanggar.presenter.sewa.SewaPresenter
import com.example.sim_sanggar.view.activity.common.BaseActivity
import kotlinx.android.synthetic.main.activity_sewa.*
import kotlinx.android.synthetic.main.fragment_toolbar.*

class SewaActivity : BaseActivity(),SewaContract.View {

    var data: SewaListItem? = null
    val presenter = SewaPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sewa)

        setToolbar()
        toolbar_title?.text = getString(R.string.sewa)
        initListener()
    }

    private fun initListener() {
        btn_sewa.clickWithDebounce {
            addSewa()
        }
    }

    private fun addSewa() {
        val tanggal_sewa = til_tanggal_sewa?.editText?.toString()
        val jam_mulai = til_jam_mulai?.editText?.toString()
        val jam_selesai = til_jam_selesai?.editText?.toString()
        val metode_pembayatan = til_metode_pembayaran?.editText?.toString()

        val tambahData = HashMap<String, Any?>()
        tambahData["tanggal"] = tanggal_sewa
        tambahData["jam_mulai"] = jam_mulai
        tambahData["jam_selesai"] = jam_selesai
        tambahData["metode_pembayaran"] = metode_pembayatan

        isLoading(true)

        if (data == null) {
            presenter.addSewa(tambahData)
        } else {
            Toast.makeText(this, "edit data", Toast.LENGTH_SHORT).show()
        }

    }

    private fun isLoading(isLoad: Boolean) {
        if (isLoad) Utilities.showProgress(this)
        else Utilities.hideProgress()
    }

    override fun sewaResponse(response: SewaResponse) {
        isLoading(false)
        this.showCustomDialogBack("data berhasil", "Penyewaan diproses, tunggu konfirmasi dari Admin Sanggar")
    }

    override fun showError(title: String, message: String) {
        showErrorAlert(title, message)
    }
}