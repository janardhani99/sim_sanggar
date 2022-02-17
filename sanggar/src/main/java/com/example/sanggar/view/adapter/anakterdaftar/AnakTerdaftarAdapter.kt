package com.example.sanggar.view.adapter.anakterdaftar

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sanggar.R
import com.example.sanggar.common.clickWithDebounce
import com.example.sanggar.data.model.absensi.ProgressAnakData
import com.example.sanggar.data.model.anak.AnakListItem
import com.example.sanggar.data.model.daftar.PendaftaranAnak
import kotlinx.android.synthetic.main.recycler_progress_anak.view.*

class AnakTerdaftarAdapter(val detailListener: (ProgressAnakData)-> Unit): RecyclerView.Adapter<AnakTerdaftarAdapter.ViewHolder>() {

//    var anakTerdaftarList : AnakListItem? = null
    var anakTerdaftarList = mutableListOf<PendaftaranAnak>()
    var progressAnak = ProgressAnakData()
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.recycler_progress_anak, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return anakTerdaftarList.count()
//        return progressAnak.count()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = anakTerdaftarList[position]

        holder.itemView.apply {
//            tv_nama_anak?.text = itemProgress.anak?.anak?.nama
            tv_nama_anak?.text = item.anak?.nama
//            tv_kehadiran?.text = itemProgress.kehadiran

            cv_progress_anak_item?.clickWithDebounce {
                detailListener(progressAnak)
            }
        }
    }

    fun setData(data: List<PendaftaranAnak>) {
        anakTerdaftarList = data.toMutableList()
        notifyDataSetChanged()
    }

//    fun deleteItem(position: Int) {
//        anakTerdaftarList.removeAt(position);
//        notifyItemRemoved(position)
//        notifyItemRangeChanged(position, anakTerdaftarList.size)
//    }
}