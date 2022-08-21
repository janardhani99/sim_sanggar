package com.example.sim_sanggar.view.adapter.studio

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sim_sanggar.R
import com.example.sim_sanggar.common.clickWithDebounce
import com.example.sim_sanggar.common.loadImage
import com.example.sim_sanggar.data.model.studio.StudioData
import kotlinx.android.synthetic.main.recycler_kegiatan_item.view.*
import kotlinx.android.synthetic.main.recycler_studio_item.view.*

class StudioAdapter(): RecyclerView.Adapter<StudioAdapter.ViewHolder>() {

    var studioList = mutableListOf<StudioData>()
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.recycler_studio_item, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return studioList.count()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = studioList[position]
        holder.itemView.apply {

            tv_nama_studio_recycler?.text = item.nama_studio
            tv_harga_perjam_recycler?.text = "Harga Sewa Perjam: ${item.harga}"
            tv_ukuran_studio_recycler.text = "Luas: ${item.ukuran} meter"
            tv_deskripsi_studio_recycler?.text = item.deskripsi
            item.foto?.let { iv_studio_recycler?.loadImage(it) }
        }
    }

    fun setData(data: List<StudioData>) {
        studioList = data.toMutableList()
        notifyDataSetChanged()
    }
}