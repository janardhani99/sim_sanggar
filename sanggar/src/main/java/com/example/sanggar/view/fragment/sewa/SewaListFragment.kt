package com.example.sanggar.view.fragment.sewa

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sanggar.R
import com.example.sanggar.common.Utilities
import com.example.sanggar.common.clickWithDebounce
import com.example.sanggar.data.model.common.EmptyResponse
import com.example.sanggar.data.model.sewa.SewaListItem
import com.example.sanggar.data.model.sewa.SewaListResponse
import com.example.sanggar.data.model.sewa.SewaResponse
import com.example.sanggar.presenter.sewa.SewaListContract
import com.example.sanggar.presenter.sewa.SewaListPresenter
import com.example.sanggar.view.activity.kegiatan.DetailKegiatanActivity
import com.example.sanggar.view.activity.sewa.SewaDetailActivity
import com.example.sanggar.view.adapter.sewa.SewaListAdapter
import kotlinx.android.synthetic.main.activity_kegiatan.*
import kotlinx.android.synthetic.main.fragment_sewa_list.*

private const val STATUS = "status"

class SewaListFragment: Fragment(), SewaListContract.View {
    private var status: String? = null
    var data : SewaListItem? = null
    private var presenter = SewaListPresenter(this)
    lateinit var adapter: SewaListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            status = it.getString(STATUS)
        }

//        data = startActivity(Intent(context, SewaDetailBottomSheet::class.java)).getParcelableExtra<SewaListItem>("data")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter()

//
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sewa_list, container, false)

    }
    companion object {
        @JvmStatic
        fun newInstance(status: String) =
                SewaListFragment().apply {
                    arguments = Bundle().apply {
                        putString(STATUS, status)
                    }
                }
    }

    override fun onResume() {
        super.onResume()
        Utilities.doRequest {
            status?.let { presenter.getListSewa(it) }
        }
    }

    private fun initAdapter(){
        adapter = SewaListAdapter{ detailItem ->
            val intent = Intent(context, SewaDetailActivity::class.java)
            intent.putExtra("data", detailItem)
            startActivity(intent)
        }
//        rv_sewa_list?.hasFixedSize()
        rv_sewa_list?.layoutManager = LinearLayoutManager(this.activity)
        rv_sewa_list?.adapter = adapter
    }

    private fun isLoading(isLoad: Boolean) {
        if (isLoad) this.context?.let { Utilities.showProgress(it) }
        else Utilities.hideProgress()
    }

    override fun sewaListResponse(response: SewaResponse) {
        TODO("Not yet implemented")
    }

    override fun getSewaListResponse(response: SewaListResponse) {
        isLoading(false)
        response.data?.let { adapter.setData(it) }
    }

    override fun deleteSewaListResponse(response: EmptyResponse) {
        TODO("Not yet implemented")
    }

    override fun showError(title: String, message: String) {
        this.showError(title, message)
    }
}