package com.example.sanggar.view.adapter.jam_operasional

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sanggar.R
import com.example.sanggar.common.clickWithDebounce
import com.example.sanggar.data.model.jam_operasional.JamOperasionalItem
import kotlinx.android.synthetic.main.recycler_jam_operasional.view.*

class JamOperasionalAdapter(val editListener: (JamOperasionalItem) -> Unit) : RecyclerView.Adapter<JamOperasionalAdapter.ViewHolder>() {
    var jamOperasionalList = mutableListOf<JamOperasionalItem>()

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.recycler_jam_operasional, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = jamOperasionalList[position]
        val jamOperasional = "${item.hari}, ${item.jamMulai} - ${item.jamSelesai}"
        holder.itemView.apply {
            tv_jam_operasional?.text = jamOperasional
            tv_status?.text = if(item.status == true) "Buka" else "Tutup"
            btn_edit_jam_sanggar?.clickWithDebounce {
                editListener(item)
            }
        }
    }

    override fun getItemCount(): Int {
        return jamOperasionalList.count()
    }

    fun setData(data: List<JamOperasionalItem>) {
        jamOperasionalList = data.toMutableList()
        notifyDataSetChanged()
    }
}