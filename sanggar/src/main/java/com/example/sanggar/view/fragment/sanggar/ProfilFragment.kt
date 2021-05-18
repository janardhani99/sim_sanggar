package com.example.sanggar.view.fragment.sanggar

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.sanggar.R
import com.example.sanggar.common.clickWithDebounce
import com.example.sanggar.view.activity.sanggar.EditProfilActivity
import kotlinx.android.synthetic.main.fragment_profil_sanggar.*


class ProfilFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profil_sanggar, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btn_edit_profil?.clickWithDebounce {
            startActivity(Intent(context, EditProfilActivity::class.java ))
        }
    }


}