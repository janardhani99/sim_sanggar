package com.example.sanggar.view.fragment.lainnya

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.sanggar.GlobalClass
import com.example.sanggar.R
import com.example.sanggar.common.Preferences
import com.example.sanggar.common.clickWithDebounce
import com.example.sanggar.view.activity.auth.LoginActivity
import com.example.sanggar.view.activity.fasilitas.FasilitasActivity
import com.example.sanggar.view.activity.jadwal_sanggar.JadwalSanggarActivity
import com.example.sanggar.view.activity.jam_operasional.JamOperasionalActivity
import com.example.sanggar.view.activity.kegiatan.KegiatanActivity
import com.example.sanggar.view.activity.user_sanggar.UserSanggarActivity
import kotlinx.android.synthetic.main.fragment_lainnya.*

class LainnyaFragment : Fragment() {

    val preferences = Preferences(GlobalClass.context)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        cv_jadwal_sanggar?.clickWithDebounce {
            startActivity(Intent(context, JadwalSanggarActivity::class.java))
        }

        cv_jam_operasional_sanggar?.clickWithDebounce {
            startActivity(Intent(context, JamOperasionalActivity::class.java))
        }

        cv_kegiatan_sanggar?.clickWithDebounce {
            startActivity(Intent(context, KegiatanActivity::class.java))
        }

        cv_fasilitas_sanggar?.clickWithDebounce {
            startActivity(Intent(context, FasilitasActivity::class.java))
        }

        cv_user_sanggar?.clickWithDebounce {
            startActivity(Intent(context, UserSanggarActivity::class.java))
        }

        btn_logout?.clickWithDebounce {
            preferences.userLoggedOut()
            activity?.finishAffinity()
            startActivity(Intent(context, LoginActivity::class.java))
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_lainnya, container, false)
    }

}