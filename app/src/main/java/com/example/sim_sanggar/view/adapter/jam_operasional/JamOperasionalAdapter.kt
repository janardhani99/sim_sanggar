package com.example.sim_sanggar.view.adapter.jam_operasional

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sim_sanggar.R
import com.example.sim_sanggar.data.model.jam_operasional.JamOperasionalItem
import kotlinx.android.synthetic.main.recycler_jam_operasional.view.*

class JamOperasionalAdapter : RecyclerView.Adapter<JamOperasionalAdapter.ViewHolder>() {
    var jamOperasionalList = mutableListOf<JamOperasionalItem>()

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.recycler_jam_operasional, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = jamOperasionalList[position]
        holder.itemView.apply {
            tv_hari_operasional?.text = "${item.hari}"
            tv_jam_operasional?.text = "${item.jam_mulai?.let { getTimeFormat(it) }}-${item.jam_selesai?.let { getTimeFormat(it) }}"
            tv_status?.text = if(item.status == true) "Buka" else "Tutup"
        }
    }

    override fun getItemCount(): Int {
        return jamOperasionalList.count()
    }

    fun getTimeFormat(time: String): String {
        return time.substring(0,5)
    }

    fun setData(data: List<JamOperasionalItem>) {
        jamOperasionalList = data.toMutableList()
        notifyDataSetChanged()
    }
}