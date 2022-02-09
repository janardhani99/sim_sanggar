package com.example.sanggar.view.activity.absensi_anak

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import com.example.sanggar.GlobalClass.Companion.context
import com.example.sanggar.R
import com.example.sanggar.common.Utilities
import com.example.sanggar.common.clickWithDebounce
import com.example.sanggar.data.model.absensi.PertemuanData
import com.example.sanggar.data.model.absensi.ProgressAnakData
import com.example.sanggar.data.model.absensi.ProgressAnakListResponse
import com.example.sanggar.data.model.absensi.ProgressAnakResponse
import com.example.sanggar.data.model.anak.AnakListItem
import com.example.sanggar.data.model.daftar.PendaftaranAnak
import com.example.sanggar.presenter.absensi.ProgressAnakContract
import com.example.sanggar.presenter.absensi.ProgressAnakPresenter
import com.example.sanggar.view.activity.common.BaseActivity
import kotlinx.android.synthetic.main.activity_detail_progress.*
import kotlinx.android.synthetic.main.fragment_jadwal_sanggar_bottom_sheet.*
import kotlinx.android.synthetic.main.fragment_toolbar.*

class DetailProgressAnakActivity() : BaseActivity(), ProgressAnakContract.View {

    val presenter = ProgressAnakPresenter(this)
    var data: ProgressAnakData? = null
    var data_anak: PendaftaranAnak? = null
    var data_pertemuan: PertemuanData? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_progress)

        setToolbar()
        toolbar_title.text = "Progress Anak"

        data_anak = intent.getParcelableExtra<PendaftaranAnak>("data_anak")
        data_pertemuan = intent.getParcelableExtra<PertemuanData>("data_pertemuan")
        data?.let { initView(it)}

        initListener()
        initAdapter()
    }

    private fun initListener(){
        btn_simpan_progress?.clickWithDebounce {
            addOrEditProgressAnak()
        }
    }

    private fun initAdapter() {
        val kehadiranAdapter = ArrayAdapter<String>(context, R.layout.layout_dropdown_item, resources.getStringArray(R.array.kehadiran))
        ac_kehadiran?.setAdapter(kehadiranAdapter)
    }

    private fun initView(data: ProgressAnakData) {
        data.run {
            til_nama_anak?.editText?.setText(data_anak?.transfer_via)
            ac_kehadiran?.setText(data.kehadiran, false)
            til_catatan_progress?.editText?.setText(data.catatan_progress)
        }

        btn_simpan_progress?.clickWithDebounce {
            addOrEditProgressAnak()
        }
    }

    private fun addOrEditProgressAnak() {
        val anak_id = data_anak?.id
        val pertemuan_id = data_pertemuan?.id
//        val nama_anak = til_nama_anak.editText?.text.toString()
        val kehadiran  = til_kehadiran.editText?.text.toString()
        val catatan_progress = til_catatan_progress.editText?.text.toString()

        val tambahData = HashMap<String, Any?>()

//        tambahData["nama_anak"] = nama_anak
        tambahData["pendaftaran_siswa_id"] = anak_id
        tambahData["pertemuan_id"] = pertemuan_id
        tambahData["kehadiran"] = kehadiran.toLowerCase()
        tambahData["catatan_progress"] = catatan_progress

        isLoading(true)
        if (data == null) {
            presenter.addProgressAnak(tambahData)
        } else {
            data?.id?.let { presenter.editProgressAnak(it, tambahData) }
        }
    }

    fun isLoading(isLoad: Boolean) {
        if(isLoad) { Utilities.showProgress(this) }
        else Utilities.hideProgress()

    }

    override fun progressAnakResponse(response: ProgressAnakResponse) {
        isLoading(false)
        this.showCustomDialogBack("Berhasil", "Data Berhasil disimpan")
    }

    override fun getProgressAnakResponse(response: ProgressAnakListResponse) {
        TODO("Not yet implemented")
    }

    override fun showError(title: String, message: String) {
        this.showErrorAlert(title, message)
    }

}