package com.example.sanggar.view.adapter.daftar

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sanggar.R
import com.example.sanggar.common.clickWithDebounce
import com.example.sanggar.data.model.daftar.DaftarListItem
import kotlinx.android.synthetic.main.recycler_list_daftar.view.*

class DaftarListAdapter(val detailListener: (DaftarListItem)-> Unit, val deleteItem: (DaftarListItem)->Unit): RecyclerView.Adapter<DaftarListAdapter.ViewHolder>() {

    var daftarlist = mutableListOf<DaftarListItem>()
    inner class ViewHolder(view: View) :RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DaftarListAdapter.ViewHolder {
        return ViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.recycler_list_daftar, parent, false)
        )

    }

    override fun getItemCount(): Int {
        return daftarlist.count()
    }

    override fun onBindViewHolder(holder: DaftarListAdapter.ViewHolder, position: Int) {
        val item = daftarlist[position]
        holder.itemView.apply {
            cv_list_daftar.clickWithDebounce {
                detailListener(item)
            }

            cv_list_daftar.setOnLongClickListener {
                deleteItem(item)
                return@setOnLongClickListener true
            }
            tv_user_name?.text = item.user_name
            tv_anak_name?.text = item.name
            tv_anak_umur?.text = item.umur

        }
    }

    fun setData(data: List<DaftarListItem>) {
        daftarlist = data.toMutableList()
        notifyDataSetChanged()
    }
}