package com.example.sanggar.view.adapter.absensi

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sanggar.R
import com.example.sanggar.common.clickWithDebounce
import com.example.sanggar.data.model.jadwal_sanggar.JadwalSanggarItem
import kotlinx.android.synthetic.main.recycler_kelas.view.*

class AbsensiAdapter(val editListener: (JadwalSanggarItem)->Unit): RecyclerView.Adapter<AbsensiAdapter.ViewHolder>() {
    var jadwalList = mutableListOf<JadwalSanggarItem>()
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.recycler_kelas, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return jadwalList.count()

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = jadwalList[position]
        holder.itemView.apply {
            tv_id_kelas.text= "ID: ${item.id}"
            tv_kategori_kelas.text = "${item.kategori_latihan}"
            tv_waktu.text = "${item.hari}, ${item.jam_mulai?.let { getTimeFormat(it) }}-${item.jam_selesai?.let { getTimeFormat(it) }}"

            cv_kelas_recycler?.clickWithDebounce {
                editListener(item)
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