package com.example.sim_sanggar.view.fragment.anak

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
import com.example.sim_sanggar.data.model.daftar.PendaftaranAnak
import com.example.sim_sanggar.presenter.anak.AnakContract
import com.example.sim_sanggar.presenter.anak.AnakPresenter
import com.example.sim_sanggar.presenter.daftar.DaftarListContract
import com.example.sim_sanggar.presenter.daftar.DaftarListPresenter
import com.example.sim_sanggar.view.activity.anak.AnakActivity
import com.example.sim_sanggar.view.activity.daftar.PembayaranActivity
import com.example.sim_sanggar.view.adapter.anakterdaftar.AnakTerdaftarAdapter
import kotlinx.android.synthetic.main.fragment_daftar_anak.*
import kotlinx.android.synthetic.main.fragment_daftar_anak.rv_anak_terdaftar

class AnakFragment : Fragment(), AnakContract.View {

    var data : AnakListItem? = null
    var dataDaftar : PendaftaranAnak? = null
    private var presenter = AnakPresenter(this)
//    private var presenterDaftar = DaftarListPresenter(this)
    lateinit var adapter: AnakTerdaftarAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_daftar_anak, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initAdapter()

        btn_daftar_sekarang?.clickWithDebounce {
            startActivity(Intent(context, AnakActivity::class.java))
        }

//        btn_platform_transaksi?.clickWithDebounce {
//            startActivity(Intent(context, PlatformTransaksiActivity::class.java))
//        }
    }

    private fun initAdapter() {

        adapter = AnakTerdaftarAdapter {detailItem ->
            val intent = Intent(context, PembayaranActivity::class.java)
            intent.putExtra("data", detailItem)
            startActivity(intent)}
        rv_anak_terdaftar?.layoutManager = LinearLayoutManager(this.activity)
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

//        btn_daftarkan.isClickable = false

//    }
    private fun fetchData() {
        isLoading(true)
        presenter.getAnak()
    }

    private fun isLoading(isLoad: Boolean) {
        if (isLoad) this.context?.let { Utilities.showProgress(it) }
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

//    override fun daftarListResponse(response: DaftarResponse) {
//
//    }
//
//    override fun getDaftarListResponse(response: DaftarListResponse) {
//
//    }
//
//    override fun deleteDaftarListResponse(response: EmptyResponse) {
//        TODO("Not yet implemented")
//    }
//
//    override fun uploadImageResponse() {
//        TODO("Not yet implemented")
//    }

    override fun showError(title: String, message: String) {

    }

}