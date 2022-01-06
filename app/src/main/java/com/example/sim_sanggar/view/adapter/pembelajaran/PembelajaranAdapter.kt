package com.example.sim_sanggar.view.adapter.pembelajaran

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sim_sanggar.R
import com.example.sim_sanggar.common.clickWithDebounce
import com.example.sim_sanggar.data.model.pembelajaran.PembelajaranData
import kotlinx.android.synthetic.main.recycler_pembelajaran_item.view.*

class PembelajaranAdapter(): RecyclerView.Adapter<PembelajaranAdapter.ViewHolder>() {

    var pembelajaranList = mutableListOf<PembelajaranData>()
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.recycler_pembelajaran_item, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return pembelajaranList.count()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = pembelajaranList[position]
        holder.itemView.apply {

            tv_judul_pembelajaran.text = item.judul
            tv_deskripsi_pembelajaran.text = item.deskripsi
            tv_link_pembelajaran.text = item.link_video
        }
    }


    fun setData(data: List<PembelajaranData>) {
        pembelajaranList = data.toMutableList()
        notifyDataSetChanged()
    }
}