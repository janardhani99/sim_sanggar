package com.example.sim_sanggar.view.fragment.sewa

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.sim_sanggar.R
import com.example.sim_sanggar.view.activity.sewa.SewaActivity
import kotlinx.android.synthetic.main.fragment_jadwal_sewa.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [SewaFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class JadwalSewaFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_jadwal_sewa, container, false)

        when(view?.id) {
            R.id.btn_sewa ->
                sendMessage(btn_sewa)
        }
//        sendMessage()

    }

    fun sendMessage(view: View) {

//        val btn_sewa: Button = view.findViewById(R.id.btn_sewa)

        btn_sewa.setOnClickListener {
            var intent = Intent(context, SewaActivity::class.java)
            context?.startActivity(intent)
        }

    }

}

