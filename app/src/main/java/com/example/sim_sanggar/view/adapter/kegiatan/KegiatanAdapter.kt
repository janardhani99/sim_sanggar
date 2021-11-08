package com.example.sim_sanggar.view.adapter.kegiatan

import android.content.DialogInterface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sim_sanggar.R
import com.example.sim_sanggar.common.clickWithDebounce
import com.example.sim_sanggar.common.loadImage
import com.example.sim_sanggar.data.model.kegiatan.KegiatanListItem
import com.example.sim_sanggar.view.activity.common.BaseActivity
import com.example.sim_sanggar.view.activity.common.ButtonDialogListener
import kotlinx.android.synthetic.main.recycler_kegiatan_item.view.*

class KegiatanAdapter() : RecyclerView.Adapter<KegiatanAdapter.ViewHolder>() {

    var kegiatanList = mutableListOf<KegiatanListItem>()
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.recycler_kegiatan_item, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return kegiatanList.count()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = kegiatanList[position]
        holder.itemView.apply {

            tv_judul_kegiatan_recycler?.text = item.judul
            tv_deskripsi_kegiatan_recycler?.text = item.deskripsi
            item.foto?.let { iv_kegiatan_recycler?.loadImage(it) }
        }
    }

    fun setData(data: List<KegiatanListItem>) {
        kegiatanList = data.toMutableList()
        notifyDataSetChanged()
    }
}