package com.example.sim_sanggar.view.adapter.sanggar

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sim_sanggar.R
import com.example.sim_sanggar.data.model.sanggar.SanggarData
import kotlinx.android.synthetic.main.layout_dropdown_item.view.*

class SanggarAdapter:RecyclerView.Adapter<SanggarAdapter.ViewHolder>() {

    var sanggarList : List<SanggarData>? = null
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SanggarAdapter.ViewHolder {
        return ViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.layout_dropdown_item, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return sanggarList!!.count()
    }

    override fun onBindViewHolder(holder: SanggarAdapter.ViewHolder, position: Int) {
        val sanggarItem = sanggarList?.get(position)
        holder.itemView.apply {
            text?.text = sanggarItem?.nama
        }
    }

    fun setData(data: List<SanggarData>) {
        sanggarList = data.toMutableList()
        notifyDataSetChanged()
    }

}