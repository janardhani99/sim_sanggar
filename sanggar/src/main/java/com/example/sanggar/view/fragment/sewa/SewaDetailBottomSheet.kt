package com.example.sanggar.view.fragment.sewa

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.sanggar.R
import com.example.sanggar.common.Utilities
import com.example.sanggar.common.clickWithDebounce
import com.example.sanggar.data.model.common.EmptyResponse
import com.example.sanggar.data.model.sewa.SewaListItem
import com.example.sanggar.data.model.sewa.SewaListResponse
import com.example.sanggar.data.model.sewa.SewaResponse
import com.example.sanggar.presenter.sewa.SewaListContract
import com.example.sanggar.presenter.sewa.SewaListPresenter
import com.example.sanggar.view.activity.common.BaseActivity
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.fragment_sewa_detail.*

class SewaDetailBottomSheet(val data: SewaListItem? = null): BottomSheetDialogFragment(), SewaListContract.View {

    val presenter = SewaListPresenter(this)
    private lateinit var baseActivity: BaseActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.AppTheme_BottomSheetDialog)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sewa_detail, container, false)
    }

    override fun onStart() {
        super.onStart()
        baseActivity = (activity as BaseActivity)
        initAdapter()
        initView(data)
    }

    private fun initAdapter() {

    }

    private fun initView(data: SewaListItem?) {

        data?.run {
            til_tanggal_sewa?.editText?.setText(data.tanggal)
        }

        btn_verifikasi_sewa.clickWithDebounce {
            ubahStatus(data)
        }

        btn_batalkan.clickWithDebounce {
            this.dismiss()
        }
    }

    private fun ubahStatus(data: SewaListItem?) {
//        val status = data?.status
        val tambahData = HashMap<String, Any?>()
        tambahData["status"] = "1"
        isLoading(true)
        data?.id?.let { presenter.editStatusSewa(it, tambahData) }
    }

    private fun isLoading(isLoad: Boolean) {
        if (isLoad) this.context?.let { Utilities.showProgress(it) }
        else Utilities.hideProgress()
    }

    override fun sewaListResponse(response: SewaResponse) {
        this.dismiss()
        isLoading(false)
        baseActivity.showCustomDialogBack("Berhasil", "Berhasil Verifikasi")
    }

    override fun getSewaListResponse(response: SewaListResponse) {
        TODO("Not yet implemented")
    }

    override fun deleteSewaListResponse(response: EmptyResponse) {
        TODO("Not yet implemented")
    }

    override fun showError(title: String, message: String) {
        baseActivity.showErrorAlert(title, message)
    }
}