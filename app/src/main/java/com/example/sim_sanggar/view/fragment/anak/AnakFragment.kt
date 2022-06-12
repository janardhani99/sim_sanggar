package com.example.sim_sanggar.view.fragment.anak

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.sim_sanggar.R
import com.example.sim_sanggar.common.clickWithDebounce
import com.example.sim_sanggar.view.activity.anak.AnakActivity
import com.example.sim_sanggar.view.activity.anak.AnakTerdaftarActivity
import com.example.sim_sanggar.view.activity.anak.PembayaranActivity
import com.example.sim_sanggar.view.activity.report_anak.ReportAnakActivity
import kotlinx.android.synthetic.main.fragment_pendaftaran.*

class AnakFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pendaftaran, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        cv_daftar?.clickWithDebounce {
            startActivity(Intent(context, AnakTerdaftarActivity::class.java))
        }

        cv_report_anak?.clickWithDebounce {
            startActivity(Intent(context, ReportAnakActivity::class.java))
        }

    }



}