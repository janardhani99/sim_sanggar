package com.example.sanggar.view.adapter.sewa

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sanggar.R
import com.example.sanggar.common.clickWithDebounce
import com.example.sanggar.data.model.sewa.SewaListItem
import kotlinx.android.synthetic.main.recycler_list_sewa.view.*

class SewaListAdapter : RecyclerView.Adapter<SewaListAdapter.ViewHolder>() {

    var sewaList = mutableListOf<SewaListItem>()
    inner class ViewHolder(view: View) :RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.recycler_list_sewa, parent, false)
        )

    }

    override fun getItemCount(): Int {
        return sewaList.count()
    }

    fun setData(data: List<SewaListItem>) {
        sewaList = data.toMutableList()
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = sewaList[position]
        holder.itemView.apply {
//            cv?.clickWithDebounce {
////                detailListener(item)
//            }
//
//            cv_list_daftar?.setOnLongClickListener {
////                deleteItem(item)
//                return@setOnLongClickListener true
//            }
//            tv_user_name?.text = item.user_name
            tv_username?.text = item.id.toString()
            tv_tanggal?.text = item.tanggal

        }
    }
}