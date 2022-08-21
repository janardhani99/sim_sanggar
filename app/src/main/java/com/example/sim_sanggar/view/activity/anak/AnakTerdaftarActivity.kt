package com.example.sim_sanggar.view.activity.anak

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sim_sanggar.R
import com.example.sim_sanggar.common.Utilities
import com.example.sim_sanggar.common.clickWithDebounce
import com.example.sim_sanggar.data.model.anak.AnakListItem
import com.example.sim_sanggar.data.model.anak.AnakListResponse
import com.example.sim_sanggar.data.model.anak.AnakResponse
import com.example.sim_sanggar.data.model.common.EmptyResponse
import com.example.sim_sanggar.data.model.daftar.DaftarListResponse
import com.example.sim_sanggar.data.model.daftar.DaftarResponse
import com.example.sim_sanggar.presenter.anak.AnakContract
import com.example.sim_sanggar.presenter.anak.AnakPresenter
import com.example.sim_sanggar.presenter.daftar.DaftarListContract
import com.example.sim_sanggar.view.activity.common.BaseActivity
import com.example.sim_sanggar.view.activity.common.ButtonDialogListener
import com.example.sim_sanggar.view.adapter.anakterdaftar.AnakTerdaftarAdapter
import kotlinx.android.synthetic.main.activity_anak_terdaftar.*

class AnakTerdaftarActivity :BaseActivity(), AnakContract.View {
    var data : AnakListItem? = null
//    var dataDaftar : PendaftaranAnak? = null
    private var presenter = AnakPresenter(this)
//    private var presenterDaftar = DaftarListPresenter(this)
    lateinit var adapter: AnakTerdaftarAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_anak_terdaftar)

        data = intent.getParcelableExtra("data")

        setToolbar()
//        toolbar_title?.text = getString(R.string.riwayat_daftar_anak)

        initListener()
        initAdapter()
    }

    private fun initListener() {
        cv_tambah_data_anak?.clickWithDebounce {
            startActivity(Intent(this, AnakActivity::class.java))
        }

//        cv_platform_transaksi?.clickWithDebounce {
//            startActivity(Intent(this, PlatformTransaksiActivity::class.java))
//        }
    }
    private fun initAdapter() {
        adapter = AnakTerdaftarAdapter ({detailItem ->
            val intent = Intent(this, AnakActivity::class.java)
            intent.putExtra("data_anak", detailItem)
            startActivity(intent)},{ deleteItem ->
            showConfirmationDialog("Konfirmasi", "Hapus Anak Ini?", object : ButtonDialogListener {
                override fun onOkButton(dialog: DialogInterface) {
                    isLoading(true)
                    deleteItem.id?.let { presenter.deleteAnak(it) }
                    dialog.dismiss()
                    showCustomDialog("Berhasil", "Data berhasil dihapus")
                }
            })
        })
        rv_anak_terdaftar?.layoutManager = LinearLayoutManager(this)
        rv_anak_terdaftar?.adapter = adapter
    }

//    private fun addPendaftaran(daftarItem: AnakListItem) {
//
//        val anak_id = daftarItem.id
//
//        val tambahData = HashMap<String, Any?>()
//        tambahData["anak_id"] = anak_id
//
//        isLoading(true)
//        presenterDaftar.addListDaftar(tambahData)
//
//        btn_daftarkan.isClickable = false
//
//    }
    private fun fetchData() {
        isLoading(true)
        presenter.getAnak()
    }

    private fun isLoading(isLoad: Boolean) {
        if (isLoad) Utilities.showProgress(this)
        else Utilities.hideProgress()
    }

    override fun onResume() {
        super.onResume()
        fetchData()
    }

    override fun anakResponse(response: AnakResponse) {
        TODO("Not yet implemented")
    }

    override fun getAnakResponse(response: AnakListResponse) {
        isLoading(false)
        response.data?.let { adapter.setData(it) }
    }

    override fun deleteAnakResponde(response: EmptyResponse) {
        isLoading(false)
        fetchData()

    }

    override fun showError(title: String, message: String) {
        this.showErrorAlert(title, message)

    }

}