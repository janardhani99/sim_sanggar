package com.example.sanggar.view.adapter.daftar

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sanggar.R
import com.example.sanggar.common.clickWithDebounce
import com.example.sanggar.data.model.daftar.PendaftaranAnak
import kotlinx.android.synthetic.main.recycler_list_daftar.view.*


class DaftarListAdapter( val detailListener: (PendaftaranAnak)-> Unit ) : RecyclerView.Adapter<DaftarListAdapter.ViewHolder>() {

    var daftarList : List<PendaftaranAnak>? = null

    inner class ViewHolder(view: View) :RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.recycler_list_daftar, parent, false)
        )

    }

    override fun getItemCount(): Int {

        return daftarList?.size ?: 0
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = daftarList?.get(position)
        holder.itemView.apply {
            cv_list_daftar?.setOnClickListener {
                detailListener(item!!)
            }

//            cv_list_daftar?.setOnLongClickListener {
//                deleteItem(item)
//                return@setOnLongClickListener true
//            }
//            tv_user_name?.text = item.user_name

            tv_anak_name?.text = item?.anak?.nama
            tv_anak_umur?.text = item?.status
//            tv_anak_umur?.text = context.getString(R.string.id_pendaftaran, item?.id)
        }
    }

    fun setData(data: List<PendaftaranAnak>) {
        daftarList = data
        notifyDataSetChanged()
    }
}