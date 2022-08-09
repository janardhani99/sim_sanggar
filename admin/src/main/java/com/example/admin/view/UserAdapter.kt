package com.example.admin.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.admin.R
import com.example.admin.data.model.user.UserData
import kotlinx.android.synthetic.main.recycler_list_user.view.*

class UserAdapter( val editListener: (UserData)-> Unit, val deleteListener: (UserData)-> Unit): RecyclerView.Adapter<UserAdapter.ViewHolder>() {

    var userList = mutableListOf<UserData>()
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.recycler_list_user, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return userList.count()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = userList[position]
        holder.itemView.apply {
            tv_nama_user_recycler?.text = item.username
            btn_edit_user.setOnClickListener {
                editListener(item)
            }
            btn_delete_user.setOnClickListener {
                deleteListener(item)
            }
        }
    }

    fun setData(data: List<UserData>) {
        userList = data.toMutableList()
        notifyDataSetChanged()
    }

}