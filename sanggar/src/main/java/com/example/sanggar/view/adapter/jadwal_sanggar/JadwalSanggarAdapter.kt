package com.example.sanggar.view.adapter.jadwal_sanggar

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sanggar.R
import com.example.sanggar.data.model.jadwal_sanggar.JadwalSanggarItem
import com.example.sanggar.data.model.jam_operasional.JamOperasionalItem
import com.example.sanggar.view.adapter.jam_operasional.JamOperasionalAdapter
import kotlinx.android.synthetic.main.fragment_lainnya.view.*
import kotlinx.android.synthetic.main.layout_dropdown_item.view.*
import kotlinx.android.synthetic.main.recycler_jadwal_sanggar.view.*

class JadwalSanggarAdapter(val editListener: (JadwalSanggarItem)->Unit, val deleteListener:(JadwalSanggarItem)->Unit): RecyclerView.Adapter<JadwalSanggarAdapter.ViewHolder>() {
    var jadwalList = mutableListOf<JadwalSanggarItem>()
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.recycler_jadwal_sanggar, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return jadwalList.count()

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = jadwalList[position]
        holder.itemView.apply {
            tv_jadwal_sanggar.text = "${item.kategori_latihan}"
            tv_waktu.text = "${item.hari}, ${item.jam_mulai?.let { getTimeFormat(it) }}-${item.jam_selesai?.let { getTimeFormat(it) }}"
            btn_edit_jadwal.setOnClickListener{
                editListener(item)
            }

            btn_delete_jadwal.setOnClickListener{
                deleteListener(item)
            }
        }
    }

    fun setData(data: List<JadwalSanggarItem>) {
        jadwalList = data.toMutableList()
        //reload data
        notifyDataSetChanged()
    }

    //ambil sebagian string jam
    fun getTimeFormat(time: String): String {
        return time.substring(0,5)
    }
}