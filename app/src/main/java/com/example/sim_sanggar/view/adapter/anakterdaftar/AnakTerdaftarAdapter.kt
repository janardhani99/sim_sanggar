package com.example.sim_sanggar.view.adapter.anakterdaftar

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sim_sanggar.R
import com.example.sim_sanggar.common.clickWithDebounce
import com.example.sim_sanggar.data.model.anak.AnakListItem
import kotlinx.android.synthetic.main.recycler_anak_terdaftar.view.*

class AnakTerdaftarAdapter: RecyclerView.Adapter<AnakTerdaftarAdapter.ViewHolder>() {

    var anakTerdaftarList = mutableListOf<AnakListItem>()
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.recycler_anak_terdaftar, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return anakTerdaftarList.count()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = anakTerdaftarList[position]
        holder.itemView.apply {
//            cv_anak_terdaftar_recycler.clickWithDebounce {
//                detailListener(item)
//            }

            tv_nama_anak_recycler?.text = item.namaAnak
            tv_umur_anak_recycler?.text = item.umurAnak
        }


    }

    fun setData(data: List<AnakListItem>) {
        anakTerdaftarList = data.toMutableList()
        notifyDataSetChanged()
    }
}