package com.example.sim_sanggar.view.adapter.sewapertanggaladapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sim_sanggar.R
import com.example.sim_sanggar.data.model.sewa.SewaListItem
import kotlinx.android.synthetic.main.recycler_booked_tanggal.view.*

class SewaPerTanggalAdapter(): RecyclerView.Adapter<SewaPerTanggalAdapter.ViewHolder>() {

    var sewaList = mutableListOf<SewaListItem>()
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.recycler_booked_tanggal, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return sewaList.count()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = sewaList[position]
        holder.itemView.apply {
//            tv_nama_penyewa.text = "Oleh ${item.user_id}"

            tv_jam_sewa.text = "${item.jam_mulai?.let { getTimeFormat(it) }}--${item.jam_selesai?.let { getTimeFormat(it) }}"
        }
    }

    fun setData(data: List<SewaListItem>) {
        sewaList = data.toMutableList()
        notifyDataSetChanged()
    }

    fun getTimeFormat(time: String): String {
        return time.substring(0,5)
    }
}