package com.example.sim_sanggar.view.adapter.daftar

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sim_sanggar.R
import com.example.sim_sanggar.data.model.jadwal_sanggar.JadwalSanggarItem
import kotlinx.android.synthetic.main.recycler_daftar_kelas.view.*

class DaftarKelasAdapter(val detailListener: (JadwalSanggarItem)-> Unit): RecyclerView.Adapter<DaftarKelasAdapter.ViewHolder>() {
    var jadwalList = mutableListOf<JadwalSanggarItem>()
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.recycler_daftar_kelas, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return jadwalList.count()

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = jadwalList[position]
        holder.itemView.apply {
            tv_jadwal_sanggar.text = item.kategori_latihan
            tv_waktu.text = "${item.hari}, ${item.jam_mulai?.let { getTimeFormat(it) }}-${item.jam_selesai?.let { getTimeFormat(it) }}"

            btn_daftar_kelas.setOnClickListener {
                detailListener(item)
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