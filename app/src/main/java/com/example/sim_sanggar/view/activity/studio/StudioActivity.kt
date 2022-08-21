package com.example.sim_sanggar.view.activity.studio

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sim_sanggar.R
import com.example.sim_sanggar.common.Utilities
import com.example.sim_sanggar.data.model.studio.StudioData
import com.example.sim_sanggar.data.model.studio.StudioListResponse
import com.example.sim_sanggar.presenter.studio.StudioContract
import com.example.sim_sanggar.presenter.studio.StudioPresenter
import com.example.sim_sanggar.view.activity.common.BaseActivity
import com.example.sim_sanggar.view.adapter.studio.StudioAdapter
import kotlinx.android.synthetic.main.activity_studio.*

class StudioActivity : BaseActivity(), StudioContract.View {

//    var data_studio: StudioData? = null
    var presenter = StudioPresenter(this)
    lateinit var adapter: StudioAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_studio)
        setToolbar()
        initAdapter()
    }

    fun initAdapter() {
        adapter = StudioAdapter()
        rv_studio?.layoutManager = LinearLayoutManager(this)
        rv_studio.adapter = adapter
    }

    fun fetchData() {
        isLoading(true)
        presenter.getStudio()
    }

    private fun isLoading(isLoad: Boolean) {
        if (isLoad) Utilities.showProgress(this)
        else {
            Utilities.hideProgress()
        }
    }

    override fun onResume() {
        super.onResume()
        fetchData()
    }

    override fun getStudioResponse(response: StudioListResponse) {
        isLoading(false)
        response.data?.let { adapter.setData(it) }
    }

    override fun showError(title: String, message: String) {
        this.showErrorAlert(title, message)
    }
}