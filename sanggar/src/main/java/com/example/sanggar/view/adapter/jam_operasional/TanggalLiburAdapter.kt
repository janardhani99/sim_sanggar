package com.example.sanggar.view.adapter.jam_operasional

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sanggar.R
import com.example.sanggar.data.model.jam_operasional.TanggalLiburItem
import kotlinx.android.synthetic.main.recycler_tanggal_libur.view.*

class TanggalLiburAdapter(val editListener: (TanggalLiburItem) -> Unit, val deleteListener: (TanggalLiburItem)-> Unit) : RecyclerView.Adapter<TanggalLiburAdapter.ViewHolder>() {
    var tanggalLiburList = mutableListOf<TanggalLiburItem>()

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.recycler_tanggal_libur, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = tanggalLiburList[position]
        holder.itemView.apply {
            tv_tanggal_libur?.text = "${item.tanggal_libur}"
            tv_keterangan?.text = "${item.keterangan}"
            btn_edit_tanggal_libur.setOnClickListener {
                editListener(item)
            }

            btn_delete_tanggal_libur.setOnClickListener {
                deleteListener(item)
            }
        }
    }

    override fun getItemCount(): Int {
        return tanggalLiburList.count()
    }

    fun setData(data: List<TanggalLiburItem>) {
        tanggalLiburList = data.toMutableList()
        notifyDataSetChanged()
    }
}