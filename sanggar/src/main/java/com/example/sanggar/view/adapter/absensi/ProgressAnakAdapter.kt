package com.example.sanggar.view.adapter.absensi

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sanggar.R
import com.example.sanggar.common.clickWithDebounce
import com.example.sanggar.data.model.absensi.ProgressAnakData
import kotlinx.android.synthetic.main.recycler_progress_anak.view.*

class ProgressAnakAdapter(val detailListener: (ProgressAnakData)-> Unit): RecyclerView.Adapter<ProgressAnakAdapter.ViewHolder>() {

    var progressAnakList = mutableListOf<ProgressAnakData>()

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.recycler_progress_anak, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return progressAnakList.count()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = progressAnakList[position]
        holder.itemView.apply {
            cv_progress_anak_item.clickWithDebounce {
                detailListener(item)
            }
            tv_nama_anak?.text = item.anak?.transfer_via
            tv_kehadiran?.text = item.kehadiran

        }
    }

    fun setData(data: List<ProgressAnakData>) {
        progressAnakList = data.toMutableList()
        notifyDataSetChanged()
    }

}