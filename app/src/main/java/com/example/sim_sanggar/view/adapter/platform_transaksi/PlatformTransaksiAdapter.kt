package com.example.sim_sanggar.view.adapter.platform_transaksi

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sim_sanggar.R
import com.example.sim_sanggar.data.model.platform_transaksi.PlatformTransaksiListItem
import kotlinx.android.synthetic.main.recycler_platform_transaksi.view.*

class PlatformTransaksiAdapter() : RecyclerView.Adapter<PlatformTransaksiAdapter.ViewHolder>() {

    var platformTransaksiList = mutableListOf<PlatformTransaksiListItem>()
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlatformTransaksiAdapter.ViewHolder {
        return ViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.recycler_platform_transaksi, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return platformTransaksiList.count()
    }

    override fun onBindViewHolder(holder: PlatformTransaksiAdapter.ViewHolder, position: Int) {
        val item = platformTransaksiList[position]
        holder.itemView.apply {
            tv_nama_platform_recycler?.text = item.nama_platform
            tv_no_rekening_recycler?.text = item.nomor_rekening
        }
    }


    fun setData(data: List<PlatformTransaksiListItem>) {
        platformTransaksiList = data.toMutableList()
        notifyDataSetChanged()
    }
}