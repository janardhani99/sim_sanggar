package com.example.sanggar.view.adapter.sanggar

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sanggar.R
import com.example.sanggar.common.clickWithDebounce
import com.example.sanggar.data.model.sanggar.SanggarData
import kotlinx.android.synthetic.main.fragment_profil_sanggar.view.*
import kotlinx.android.synthetic.main.recycler_profile_sanggar.view.*

class ProfilSanggarAdapter(val detailListener: (SanggarData)-> Unit): RecyclerView.Adapter<ProfilSanggarAdapter.ViewHolder>() {

    var profileList = mutableListOf<SanggarData>()
    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.recycler_profile_sanggar, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return profileList.count()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = profileList[position]
        holder.itemView.apply {
            btn_edit_profil?.clickWithDebounce {
                detailListener(item)
            }

            tv_nama_sanggar?.text = item.nama_sanggar
        }
    }
    fun setData(data: List<SanggarData>) {
        profileList = data.toMutableList()
        notifyDataSetChanged()
    }
}