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
import com.example.sanggar.data.model.user_sanggar.UserSanggarItem
import com.example.sanggar.view.activity.absensi_anak.AbsensiActivity
import com.example.sanggar.view.activity.auth.LoginActivity
import com.example.sanggar.view.activity.fasilitas.FasilitasActivity
import com.example.sanggar.view.activity.jadwal_sanggar.JadwalSanggarActivity
import com.example.sanggar.view.activity.jam_operasional.JamOperasionalActivity
import com.example.sanggar.view.activity.kegiatan.KegiatanActivity
import com.example.sanggar.view.activity.pembelajaran.PembelajaranActivity
import com.example.sanggar.view.activity.platform_transaksi.PlatformTransaksiActivity
import com.example.sanggar.view.activity.sewa.PenyewaanActivity
import com.example.sanggar.view.activity.studio.StudioActivity
import com.example.sanggar.view.activity.user_sanggar.UserSanggarActivity
import kotlinx.android.synthetic.main.fragment_lainnya.*

class LainnyaFragment : Fragment() {

    val preferences = Preferences(GlobalClass.context)
//    var data
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

        cv_studio_sanggar?.clickWithDebounce {
            startActivity(Intent(context, StudioActivity::class.java))
        }

        cv_penyewaan?.clickWithDebounce {
            startActivity(Intent(context, PenyewaanActivity::class.java))
        }

        cv_kegiatan_sanggar?.clickWithDebounce {
            startActivity(Intent(context, KegiatanActivity::class.java))
        }

        cv_fasilitas_sanggar?.clickWithDebounce {
            startActivity(Intent(context, FasilitasActivity::class.java))
        }

        cv_platform_transaksi?.clickWithDebounce {
            startActivity(Intent(context, PlatformTransaksiActivity::class.java))
        }

        cv_pembelajaran?.clickWithDebounce {
            startActivity(Intent(context, PembelajaranActivity::class.java))
        }

        btn_logout?.clickWithDebounce {
            preferences.userLoggedOut()
            activity?.finishAffinity()
            startActivity(Intent(context, LoginActivity::class.java))
        }

//        setView(data)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_lainnya, container, false)
    }

    private fun setView(data: UserSanggarItem) {
        data?.run {
            txt_namauser?.setText(data.sanggar?.nama)
        }
    }

}