package com.example.sanggar.view.fragment.daftar

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.sanggar.R
import com.example.sanggar.common.Utilities
import com.example.sanggar.common.clickWithDebounce
import com.example.sanggar.data.model.common.EmptyResponse
import com.example.sanggar.data.model.daftar.DaftarListResponse
import com.example.sanggar.data.model.daftar.DaftarResponse
import com.example.sanggar.data.model.daftar.PendaftaranAnak
import com.example.sanggar.presenter.daftar.DaftarListContract
import com.example.sanggar.presenter.daftar.DaftarListPresenter
import com.example.sanggar.view.activity.common.BaseActivity
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.fragment_pendaftar_detail.*

class PendaftarDetailBottomSheet(val data: PendaftaranAnak? = null): BottomSheetDialogFragment(), DaftarListContract.View {

    val presenter = DaftarListPresenter(this)
    private lateinit var baseActivity: BaseActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.AppTheme_BottomSheetDialog)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pendaftar_detail, container, false)
    }

    override fun onStart() {
        super.onStart()
        baseActivity = (activity as BaseActivity)
        initAdapter()
        initView(data)
    }

    private fun initAdapter() {

    }

    private fun initView(data: PendaftaranAnak?) {

        data?.run {
            til_nama_anak?.editText?.setText(data.anak?.nama)
            til_umur_anak?.editText?.setText(data.anak?.umur)
            til_telepon?.editText?.setText(data.anak?.telepon)
        }

        btn_verifikasi_pendaftar.clickWithDebounce {
            statusVerifikasi(data)
        }

        btn_batalkan.clickWithDebounce {
            statusBatal(data)
        }
    }

    private fun statusVerifikasi(data: PendaftaranAnak?) {
//        val status = data?.status
        val tambahData = HashMap<String, Any?>()
        tambahData["status"] = "1"
        isLoading(true)
        data?.id?.let { presenter.editStatusDaftar(it, tambahData) }
    }

    private fun statusBatal(data: PendaftaranAnak?) {
//        val status = data?.status
        val tambahData = HashMap<String, Any?>()
        tambahData["status"] = "2"
        isLoading(true)
        data?.id?.let { presenter.editStatusDaftar(it, tambahData) }
    }
    override fun daftarListResponse(response: DaftarResponse) {
        this.dismiss()
        isLoading(false)
//        if (data?.status == "1") {
//            Toast.makeText(context, "Berhasil Verifikasi!", Toast.LENGTH_SHORT).show()
//        } else if (data?.status == "2") {
//            Toast.makeText(context, "Berhasil dibatalkan!", Toast.LENGTH_SHORT).show()
//        }

    }

    private fun isLoading(isLoad: Boolean) {
        if (isLoad) this.context?.let { Utilities.showProgress(it) }
        else Utilities.hideProgress()
    }

    override fun getDaftarListResponse(response: DaftarListResponse) {
        TODO("Not yet implemented")
    }

    override fun deleteDaftarListResponse(response: EmptyResponse) {
        TODO("Not yet implemented")
    }

    override fun showError(title: String, message: String) {
        baseActivity.showErrorAlert(title, message)
    }
}