package com.example.sim_sanggar.view.adapter.sewabelumbayaradapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sim_sanggar.R
import com.example.sim_sanggar.common.clickWithDebounce
import com.example.sim_sanggar.data.model.sewa.SewaListItem
import kotlinx.android.synthetic.main.recycler_riwayat_sewa.view.*
import kotlinx.android.synthetic.main.recycler_riwayat_sewa.view.tv_jam_sewa
import kotlinx.android.synthetic.main.recycler_riwayat_sewa.view.tv_tanggal_sewa
import kotlinx.android.synthetic.main.recycler_sewa_belum_bayar.view.*

class SewaBelumBayarAdapter(val sewaListener: (SewaListItem)-> Unit): RecyclerView.Adapter<SewaBelumBayarAdapter.ViewHolder>() {

    var sewaList = mutableListOf<SewaListItem>()
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.recycler_sewa_belum_bayar, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return sewaList.count()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = sewaList[position]
        holder.itemView.apply {
            tv_studio.text = item.studio_id?.nama_studio
            tv_tanggal_sewa.text = "Tanggal : ${item.tanggal}"
            tv_jam_sewa.text = "${item.jam_mulai?.let { getTimeFormat(it) }} - ${item.jam_selesai?.let { getTimeFormat(it) }}"
            tv_total_bayar.text = item.studio_id?.harga

            btn_upload?.clickWithDebounce {
                sewaListener(item)
            }
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