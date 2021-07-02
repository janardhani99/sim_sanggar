package com.example.sim_sanggar.view.fragment.profil

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.sim_sanggar.GlobalClass
import com.example.sim_sanggar.R
import com.example.sim_sanggar.common.Preferences
import com.example.sim_sanggar.common.clickWithDebounce
import com.example.sim_sanggar.view.activity.auth.LoginActivity
import com.example.sim_sanggar.view.activity.edit_profil.EditProfilActivity
import kotlinx.android.synthetic.main.fragment_profil.*

class ProfilFragment : Fragment() {

    var preferences = Preferences(GlobalClass.context)
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profil, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btn_edit.clickWithDebounce {
            startActivity(Intent(context, EditProfilActivity::class.java))
        }

        btn_logout?.clickWithDebounce {
            preferences.userLoggedOut()
            activity?.finishAffinity()
            startActivity(Intent(context, LoginActivity::class.java))
        }
    }

}