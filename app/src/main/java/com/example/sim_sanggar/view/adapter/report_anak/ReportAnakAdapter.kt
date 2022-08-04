package com.example.sim_sanggar.view.adapter.report_anak

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sim_sanggar.R
import com.example.sim_sanggar.common.clickWithDebounce
import com.example.sim_sanggar.data.model.report_anak.ReportAnakData
import kotlinx.android.synthetic.main.recycler_report_anak.view.*

class ReportAnakAdapter(): RecyclerView.Adapter<ReportAnakAdapter.ViewHolder>() {

    var progressAnakList = mutableListOf<ReportAnakData>()

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.recycler_report_anak, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return progressAnakList.count()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = progressAnakList[position]
        holder.itemView.apply {
//            cv_recycler_report_anak.clickWithDebounce {
//                detailListener(item)
//            }
            tv_pertemuan_ke?.text = "Pertemuan ke: ${item.pertemuan?.pertemuan_ke}"
            tv_tanggal_pertemuan?.text = "Tanggal: ${item.pertemuan?.tanggal}"
            tv_kehadiran?.text = "Presensi: ${item.kehadiran}"
            tv_catatan_progress?.text = "Catatan: ${item.catatan_progress}"
        }
    }

    fun setData(data: List<ReportAnakData>) {
        progressAnakList = data.toMutableList()
        notifyDataSetChanged()
    }

}