package com.example.sim_sanggar.view.adapter.profile

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sim_sanggar.R
import com.example.sim_sanggar.data.model.profile.ProfileData
import kotlinx.android.synthetic.main.recycler_profile.view.*

class ProfileAdapter: RecyclerView.Adapter<ProfileAdapter.ViewHolder>() {

    var profileList : List<ProfileData>? = null

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.recycler_profile, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return profileList?.size ?: 0
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = profileList?.get(position)
        holder.itemView.apply {
            tv_username?.text = item?.username
            tv_sanggar?.text = item?.sanggar?.nama
        }
    }

    fun setData(data: List<ProfileData>) {
        profileList = data
        notifyDataSetChanged()
    }
}