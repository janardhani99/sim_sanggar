package com.example.sanggar.view.adapter.anakterdaftar

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sanggar.R
import com.example.sanggar.common.clickWithDebounce
import com.example.sanggar.data.model.anak.AnakListItem
import com.example.sanggar.data.model.daftar.PendaftaranAnak
import kotlinx.android.synthetic.main.recycler_progress_anak.view.*

class AnakTerdaftarAdapter(val detailListener: (AnakListItem)-> Unit): RecyclerView.Adapter<AnakTerdaftarAdapter.ViewHolder>() {

    var anakTerdaftarList = mutableListOf<AnakListItem>()
    var pendaftaranAnak : List<PendaftaranAnak>? = null
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.recycler_progress_anak, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return anakTerdaftarList.count()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = anakTerdaftarList[position]
        val daftarAnak = pendaftaranAnak?.get(position)
        holder.itemView.apply {
            cv_progress_anak_item.clickWithDebounce {
                detailListener(item)
            }
            tv_nama_anak?.text = item.nama
//            tv_kehadiran?.text = item.umur

        }
    }

    fun setData(data: List<AnakListItem>) {
        anakTerdaftarList = data.toMutableList()
        notifyDataSetChanged()
    }

    fun deleteItem(position: Int) {
        anakTerdaftarList.removeAt(position);
        notifyItemRemoved(position)
        notifyItemRangeChanged(position, anakTerdaftarList.size)
    }
}