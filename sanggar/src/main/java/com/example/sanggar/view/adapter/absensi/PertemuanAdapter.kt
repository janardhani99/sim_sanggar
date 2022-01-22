package com.example.sanggar.view.adapter.absensi

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sanggar.R
import com.example.sanggar.common.clickWithDebounce
import com.example.sanggar.data.model.absensi.PertemuanData
import kotlinx.android.synthetic.main.recycler_pertemuan.view.*

class PertemuanAdapter(val detailListener: (PertemuanData)-> Unit):RecyclerView.Adapter<PertemuanAdapter.ViewHolder>() {
    var pertemuanList = mutableListOf<PertemuanData>()
    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.recycler_pertemuan, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return pertemuanList.count()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = pertemuanList[position]
        holder.itemView.apply {
//            tv_id_jadwal_sanggar.text = item?.jadwal_sanggar?.id.toString()
            tv_pertemuan_ke.text = item.pertemuan_ke
            tv_tanggal_pertemuan.text = "Tanggal : ${item.tanggal}"

            cv_pertemuan_recycler?.clickWithDebounce {
                detailListener(item)
            }
        }
    }

    fun setData(data: List<PertemuanData>) {
        pertemuanList = data.toMutableList()
        notifyDataSetChanged()
    }


}