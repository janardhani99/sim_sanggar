package com.example.sim_sanggar.view.activity.jam_operasional

import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.example.sim_sanggar.R
import com.example.sim_sanggar.data.model.jam_operasional.JamOperasionalItem
import com.example.sim_sanggar.view.activity.common.BaseActivity
import com.example.sim_sanggar.view.adapter.jam_operasional.JamOperasionalAdapter
import kotlinx.android.synthetic.main.activity_jam_operasional.*
import kotlinx.android.synthetic.main.fragment_toolbar.*

class JamOperasionalActivity : BaseActivity() {
    private lateinit var adapter: JamOperasionalAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_jam_operasional)

        //set toolbar
        setToolbar()
        toolbar_title?.text = getString(R.string.jam_operasional)

        setAdapter()
    }

    private fun setAdapter() {
        adapter = JamOperasionalAdapter()
        rv_jam_operasional?.addItemDecoration(DividerItemDecoration(this, RecyclerView.VERTICAL))
        rv_jam_operasional?.adapter = adapter
        adapter.setData(getDefaultData())
    }

    //temporary data jen karna blum nyambung api
    private fun getDefaultData(): MutableList<JamOperasionalItem> {
        val list = mutableListOf<JamOperasionalItem>()
        list.add(JamOperasionalItem("Senin", "00:00", "23:59", true))
        list.add(JamOperasionalItem("Selasa", "00:00", "23:59", true))
        list.add(JamOperasionalItem("Rabu", "00:00", "23:59", true))
        list.add(JamOperasionalItem("Kamis", "00:00", "23:59", true))
        list.add(JamOperasionalItem("Jumat", "00:00", "23:59", true))
        list.add(JamOperasionalItem("Sabtu", "00:00", "23:59", true))
        list.add(JamOperasionalItem("Minggu", "00:00", "23:59", true))
        return list
    }
}