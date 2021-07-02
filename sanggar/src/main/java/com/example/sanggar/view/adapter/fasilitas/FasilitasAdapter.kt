package com.example.sanggar.view.adapter.fasilitas

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sanggar.R
import com.example.sanggar.common.clickWithDebounce
import com.example.sanggar.common.loadImage
import com.example.sanggar.data.model.fasilitas.FasilitasListItem
import kotlinx.android.synthetic.main.recycler_fasilitas_item.view.*

class FasilitasAdapter(val detailListener: (FasilitasListItem) -> Unit, val deleteItem: (FasilitasListItem)-> Unit): RecyclerView.Adapter<FasilitasAdapter.ViewHolder>() {

    var fasilitasList = mutableListOf<FasilitasListItem>()
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.recycler_fasilitas_item, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return fasilitasList.count()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = fasilitasList[position]
        holder.itemView.apply {
            cv_fasilitas_recycler?.clickWithDebounce {
                detailListener(item)
            }
            cv_fasilitas_recycler?.setOnLongClickListener{
                deleteItem(item)
                return@setOnLongClickListener true
            }
            tv_judul_fasilitas_recycler?.text = item.judul
            tv_deskripsi_fasilitas_recycler?.text = item.deskripsi
            item.foto?.let { iv_fasilitas_recycler?.loadImage(it) }

        }
    }

    fun setData(data: List<FasilitasListItem>) {
        fasilitasList = data.toMutableList()
        notifyDataSetChanged()
    }
}