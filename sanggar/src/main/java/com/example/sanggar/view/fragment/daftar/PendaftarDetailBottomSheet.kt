package com.example.sanggar.view.fragment.daftar

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.sanggar.R
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
            ubahStatus()
        }

        btn_batalkan.clickWithDebounce {
            ubahStatus()
        }
    }

    private fun ubahStatus() {

    }
    override fun daftarListResponse(response: DaftarResponse) {
        TODO("Not yet implemented")
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