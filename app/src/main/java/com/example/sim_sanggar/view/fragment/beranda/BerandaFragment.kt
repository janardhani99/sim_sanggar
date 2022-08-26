package com.example.sim_sanggar.view.fragment.beranda

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sim_sanggar.R
import com.example.sim_sanggar.common.Utilities
import com.example.sim_sanggar.common.clickWithDebounce
import com.example.sim_sanggar.common.loadImage
import com.example.sim_sanggar.data.model.profile.ProfileData
import com.example.sim_sanggar.data.model.profile.ProfileListResponse
import com.example.sim_sanggar.data.model.profile.ProfileResponse
import com.example.sim_sanggar.presenter.profile.ProfileContract
import com.example.sim_sanggar.presenter.profile.ProfilePresenter
import com.example.sim_sanggar.view.activity.common.BaseActivity
import com.example.sim_sanggar.view.activity.edit_profil.EditProfilActivity
import com.example.sim_sanggar.view.activity.fasilitas.FasilitasActivity
import com.example.sim_sanggar.view.activity.jadwal_sanggar.JadwalSanggarActivity
import com.example.sim_sanggar.view.activity.jam_operasional.JamOperasionalActivity
import kotlinx.android.synthetic.main.fragment_beranda.*
import com.example.sim_sanggar.view.activity.kegiatan.KegiatanActivity
import com.example.sim_sanggar.view.activity.pembelajaran.PembelajaranActivity
import com.example.sim_sanggar.view.activity.studio.StudioActivity
import com.example.sim_sanggar.view.adapter.profile.ProfileAdapter


class BerandaFragment : Fragment(), ProfileContract.View {

    private lateinit var baseActivity: BaseActivity
    var data : ProfileData? = null
    var presenter = ProfilePresenter(this)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
//    super.onCreate(savedInstanceState)
        return inflater.inflate(R.layout.fragment_beranda, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.getProfile()

        btn_detail_profil?.clickWithDebounce {
            val intent = Intent(context, EditProfilActivity::class.java)
            startActivity(intent)
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

    private fun initView(data: ProfileData) {
        data.run{
            tv_username?.setText(data.username)
            tv_sanggar_user?.setText(data.sanggar?.nama)
            data.photoUrl?.let { iv_profile_pict?.loadImage(it) }
        }
    }

    fun fetchData() {
        isLoading(true)
        presenter.getProfile()
    }

    override fun onResume() {
        super.onResume()
        fetchData()
    }

    private fun isLoading(isLoad: Boolean) {
        if (isLoad) this.context?.let { Utilities.showProgress(it) }
        else Utilities.hideProgress()
    }

    override fun getProfileResponse(response: ProfileResponse) {
        isLoading(false)
        data = response.data
        data?.let { initView(it) }
    }

    override fun editProfileResponse(response: ProfileResponse) {
        TODO("Not yet implemented")
    }

    override fun showError(title: String, message: String) {
        baseActivity.showErrorAlert(title, message)
    }

}