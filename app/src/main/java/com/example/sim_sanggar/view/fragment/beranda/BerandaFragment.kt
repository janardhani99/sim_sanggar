package com.example.sim_sanggar.view.fragment.beranda

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
//import androidx.cardview.widget.CardView
import com.example.sim_sanggar.R
import com.example.sim_sanggar.common.clickWithDebounce
import com.example.sim_sanggar.view.activity.edit_profil.EditProfilActivity
import com.example.sim_sanggar.view.activity.fasilitas.FasilitasActivity
import com.example.sim_sanggar.view.activity.jadwal_sanggar.JadwalSanggarActivity
import com.example.sim_sanggar.view.activity.jam_operasional.JamOperasionalActivity
import kotlinx.android.synthetic.main.fragment_beranda.*
import com.example.sim_sanggar.view.activity.kegiatan.KegiatanActivity
import com.example.sim_sanggar.view.activity.pembelajaran.PembelajaranActivity
import com.example.sim_sanggar.view.activity.studio.StudioActivity


class BerandaFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
//    super.onCreate(savedInstanceState)
        return inflater.inflate(R.layout.fragment_beranda, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btn_detail_profil?.clickWithDebounce {
            startActivity(Intent(context, EditProfilActivity::class.java))
        }

        cv_jam_operasional?.clickWithDebounce {
            startActivity(Intent(context, JamOperasionalActivity::class.java))
        }

        cv_jadwal_sanggar?.clickWithDebounce {
            startActivity(Intent(context, JadwalSanggarActivity::class.java))
        }

        cv_studio_sanggar?.clickWithDebounce {
            startActivity(Intent(context, StudioActivity::class.java))
        }

        cv_fasilitas_sanggar?.clickWithDebounce {
            startActivity(Intent(context, FasilitasActivity::class.java))
        }

        cv_kegiatan_sanggar?.clickWithDebounce {
            startActivity(Intent(context, KegiatanActivity::class.java))
        }

        cv_pembelajaran?.clickWithDebounce {
            startActivity(Intent(context, PembelajaranActivity::class.java))
        }
    }

}