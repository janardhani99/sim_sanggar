package com.example.sanggar.view.fragment.daftar

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sanggar.R
import com.example.sanggar.common.Utilities
import com.example.sanggar.common.Utilities.doRequest
import com.example.sanggar.data.model.common.EmptyResponse
import com.example.sanggar.data.model.daftar.DaftarListResponse
import com.example.sanggar.data.model.daftar.DaftarResponse
import com.example.sanggar.data.model.daftar.PendaftaranAnak
import com.example.sanggar.presenter.daftar.DaftarListContract
import com.example.sanggar.presenter.daftar.DaftarListPresenter
import com.example.sanggar.view.activity.daftar.PendaftarDetailActivity
import com.example.sanggar.view.adapter.daftar.DaftarListAdapter
import kotlinx.android.synthetic.main.fragment_daftar_list.*

private const val STATUS = "status"

class DaftarListFragment : Fragment(), DaftarListContract.View {
    private var status: String? = null
    var data : PendaftaranAnak? = null
    private var presenter = DaftarListPresenter(this)
    lateinit var adapter: DaftarListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            status = it.getString(STATUS)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initAdapter()
        initListener()
//        fetchData()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_daftar_list, container, false)

    }

    companion object {
        @JvmStatic
        fun newInstance(status: String) =
                DaftarListFragment().apply {
                    arguments = Bundle().apply {
                        putString(STATUS, status)
                    }
                }
    }

    private fun initListener() {
        sr_list_daftar?.setOnRefreshListener {
            fetchData()
        }
    }

    override fun onResume() {
        super.onResume()
        fetchData()
    }

    private fun fetchData() {
        doRequest {
            status?.let { presenter.getListDaftar(it) }
        }
    }
//    (status) { detailItem->
//        val intent = Intent(context, PendaftarDetailActivity::class.java)
//        intent.putExtra("data", detailItem)
//        startActivity(intent)
//    }

    private fun initAdapter(){
        adapter = DaftarListAdapter { detailItem ->
        val intent = Intent(context, PendaftarDetailActivity::class.java)
        intent.putExtra("data", detailItem)
        startActivity(intent)}

//        rv_daftar_list?.hasFixedSize()
        rv_daftar_list?.layoutManager = LinearLayoutManager(this.activity)
        rv_daftar_list?.adapter = adapter
    }

    private fun isLoading(isLoad: Boolean) {
        if (isLoad) this.context?.let { Utilities.showProgress(it) }
        else {
            Utilities.hideProgress()
            sr_list_daftar.isRefreshing = false
        }
    }

//    fun fetchData() {
////        isLoading(true)
//        presenter.getListDaftar()
//    }

//    override fun onResume() {
//        super.onResume()
//
//    }
    override fun daftarListResponse(response: DaftarResponse) {
        TODO("Not yet implemented")
    }

    override fun getDaftarListResponse(response: DaftarListResponse) {
        isLoading(false)

        response.data?.let { adapter.setData(it) }

    }

    override fun getAnakTerdaftarResponse(response: DaftarListResponse) {
        TODO("Not yet implemented")
    }

    override fun getAnakOnKelasResponse(response: DaftarListResponse) {
        TODO("Not yet implemented")
    }

    override fun deleteDaftarListResponse(response: EmptyResponse) {
        TODO("Not yet implemented")
    }

//    override fun LinearLayoutManager(daftarListFragment: DaftarListFragment): LinearLayoutManager {
//        TODO("Not yet implemented")
//    }

    override fun showError(title: String, message: String) {
        this.showError(title, message)
    }

}