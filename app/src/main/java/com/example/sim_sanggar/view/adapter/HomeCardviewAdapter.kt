package com.example.sim_sanggar.view.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.RecyclerView
import com.example.sim_sanggar.R
import com.example.sim_sanggar.data.model.HomeCardview
import com.example.sim_sanggar.view.activity.auth.JadwalLatihanActivity


class HomeCardviewAdapter(val context: Context): RecyclerView.Adapter<HomeCardviewAdapter.CardviewViewHolder>() {

    private val cardviews : MutableList<HomeCardview> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardviewViewHolder {
        return CardviewViewHolder(LayoutInflater.from(context).inflate(R.layout.item_cardview, parent, false))
    }

    override fun getItemCount(): Int {
        return cardviews.size
    }

    override fun onBindViewHolder(holder: HomeCardviewAdapter.CardviewViewHolder, position: Int) {

        holder.bindmodel(cardviews[position])
    }

    fun setCardview(data: List<HomeCardview>) {
        cardviews.clear()
        cardviews.addAll(data)
        notifyDataSetChanged()
    }
    inner class CardviewViewHolder(item: View): RecyclerView.ViewHolder(item) {

        val txtJudul: TextView = item.findViewById(R.id.item_judul)
        val txtDeskripsi: TextView = item.findViewById(R.id.item_deskripsi)
        val cvHome: CardView = item.findViewById(R.id.cardviewHome)

        fun bindmodel(c: HomeCardview) {
            txtJudul.text = c.getJudul()
            txtDeskripsi.text = c.getDeskripsi()

            cvHome.setOnClickListener {
//                val ft: FragmentTransaction = getFragmentManager().beginTransaction()
//                ft.replace(R.id.cardviewHome, JadwalLatihanFragment(), "NewFragmentTag")
//                ft.commit()

                var intent = Intent(context, JadwalLatihanActivity::class.java)

                intent.putExtra("judul", c.getJudul())
                intent.putExtra("deskripsi", c.getDeskripsi())

                context.startActivity(intent)
            }

            }
        }
}