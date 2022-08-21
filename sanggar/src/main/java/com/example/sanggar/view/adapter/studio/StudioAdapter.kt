package com.example.sanggar.view.adapter.studio

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sanggar.R
import com.example.sanggar.common.clickWithDebounce
import com.example.sanggar.data.model.studio.StudioData
import kotlinx.android.synthetic.main.recycler_studio_item.view.*

class StudioAdapter(val detailListener: (StudioData) -> Unit, val deleteItem: (StudioData)-> Unit): RecyclerView.Adapter<StudioAdapter.ViewHolder>() {

    var studioList = mutableListOf<StudioData>()
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.recycler_studio_item, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return studioList.count()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = studioList[position]
        holder.itemView.apply {
            cv_studio_recycler?.clickWithDebounce {
                detailListener(item)
            }
            cv_studio_recycler?.setOnLongClickListener{
                deleteItem(item)
                return@setOnLongClickListener true
            }
            tv_nama_studio_recycler?.text = item.nama_studio

        }
    }

    fun setData(data: List<StudioData>) {
        studioList = data.toMutableList()
        notifyDataSetChanged()
    }
}