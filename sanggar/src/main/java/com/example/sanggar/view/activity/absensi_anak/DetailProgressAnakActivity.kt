package com.example.sanggar.view.activity.absensi_anak

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
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
import java.lang.Exception

class DetailProgressAnakActivity() : BaseActivity(), ProgressAnakContract.View {

    val presenter = ProgressAnakPresenter(this)
    var data_anak: PendaftaranAnak? = null
    var data_progress: ProgressAnakData? = null
    var pertemuan_id: Int? = null
    var pendaftaran_siswa_id: Int? = null
    var idEdit = false

    var idProgress : Int?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_progress)

        setToolbar()
//        toolbar_title.text = "Progress Anak"

//        data_anak = intent.getParcelableExtra<PendaftaranAnak>("data_anak")
//        data_pertemuan = intent.getParcelableExtra<PertemuanData>("data_pertemuan")
//        data_progress = intent.getParcelableExtra<ProgressAnakData>("data_progress")
//        data_progress?.let { initView(it)}

        pertemuan_id = intent.getIntExtra("pertemuan_id", 0)
        pendaftaran_siswa_id = intent.getIntExtra("pendaftaran_siswa_id", 0)
        Log.d("test", "httty" + pendaftaran_siswa_id)
        presenter.getDetailProgress(hashMapOf(
                "pendaftaran_siswa_id" to pendaftaran_siswa_id,
                "pertemuan_id" to pertemuan_id
        ))

        initListener()
        initAdapter()
    }

    private fun initListener() {
        btn_simpan_progress?.clickWithDebounce {
            addOrEditProgressAnak()
        }
    }

    private fun initAdapter() {
        val kehadiranAdapter = ArrayAdapter<String>(context, R.layout.layout_dropdown_item, resources.getStringArray(R.array.kehadiran))
        val bayarAdapter = ArrayAdapter<String>(context, R.layout.layout_dropdown_item, resources.getStringArray(R.array.bayarHarian))

        ac_kehadiran?.setAdapter(kehadiranAdapter)
        ac_bayar_harian?.setAdapter(bayarAdapter)
    }

    private fun initView(data: ProgressAnakData) {
        data.run {
            tv_nama_anak?.setText(data.anak?.anak?.nama)
            ac_kehadiran?.setText(data.kehadiran, false)
            til_catatan_progress?.editText?.setText(data.catatan_progress)
            ac_bayar_harian?.setText(data.bayar_harian, false)
        }

        btn_simpan_progress?.clickWithDebounce {
            addOrEditProgressAnak()
        }
    }

    private fun addOrEditProgressAnak() {
        val anak_id = pendaftaran_siswa_id
        val pertemuan_id = pertemuan_id
//        val nama_anak = til_nama_anak.editText?.text.toString()
        val kehadiran = til_kehadiran.editText?.text.toString()
        val catatan_progress = til_catatan_progress.editText?.text.toString()
        val bayar_harian = til_bayar_harian.editText?.text.toString()

        val tambahData = HashMap<String, Any?>()

//        tambahData["nama_anak"] = nama_anak
        tambahData["pendaftaran_siswa_id"] = anak_id
        tambahData["pertemuan_id"] = pertemuan_id
        tambahData["kehadiran"] = kehadiran.toLowerCase()
        tambahData["catatan_progress"] = catatan_progress
        tambahData["bayar_harian"] = bayar_harian

        isLoading(true)
        if (idEdit == false) {
            presenter.addProgressAnak(tambahData)
        } else {
            idProgress?.let { presenter.editProgressAnak(it, tambahData) }
        }
    }

    fun isLoading(isLoad: Boolean) {
        if (isLoad) {
            Utilities.showProgress(this)
        } else Utilities.hideProgress()

    }

    override fun progressAnakResponse(response: ProgressAnakResponse) {
        isLoading(false)
        this.showCustomDialogBack("Berhasil", "Data Berhasil disimpan")
    }

    override fun getProgressAnakResponse(response: ProgressAnakListResponse) {
        TODO("Not yet implemented")
    }

    override fun getDetailProgress(response: ProgressAnakResponse) {
         try {
             idEdit =response.data?.id != null
            idProgress = response.data?.id
        } catch (e: Exception) {
             idEdit = false
        }
        response.data?.let { initView(it) }
    }

    override fun showError(title: String, message: String) {
        this.showErrorAlert(title, message)
    }

}