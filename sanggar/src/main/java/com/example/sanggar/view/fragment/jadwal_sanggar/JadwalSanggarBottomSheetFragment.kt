package com.example.sanggar.view.fragment.jadwal_sanggar

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.example.sanggar.R
import com.example.sanggar.common.clickWithDebounce
import com.example.sanggar.data.model.jadwal_sanggar.JadwalSanggarItem
import com.example.sanggar.view.activity.common.BaseActivity
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import kotlinx.android.synthetic.main.fragment_jadwal_sanggar_bottom_sheet.*

class JadwalSanggarBottomSheetFragment(val data: JadwalSanggarItem? = null) : BottomSheetDialogFragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.AppTheme_BottomSheetDialog)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_jadwal_sanggar_bottom_sheet, container, false)
    }

    override fun onStart() {
        super.onStart()
        initAdapter()
        initView(data)
    }

    private fun initAdapter() {
        val hariAdapter = context?.let { ArrayAdapter<String>(it, R.layout.layout_dropdown_item, resources.getStringArray(R.array.nama_hari)) }
        ac_hari_jadwal?.setAdapter(hariAdapter)
    }

    private fun initView(data: JadwalSanggarItem?) {
        data?.run {
            ac_hari_jadwal?.setText(data.hari, true)
            et_jam_mulai?.setText(data.jamMulai)
            et_jam_selesai?.setText(data.jamSelesai)
        }

        if (data != null) {
            //edit data
            btn_simpan_jadwal?.clickWithDebounce {
                (activity as BaseActivity?)?.toast("update data")
            }
        } else {
            //create data
            btn_simpan_jadwal?.clickWithDebounce {
                (activity as BaseActivity?)?.toast("create data")
            }
        }

        btn_cancel?.clickWithDebounce {
            dismiss()
        }

        val jamMulai = data?.jamMulai?.split(":")?.get(0)?.toInt() ?: 0
        val menitMulai = data?.jamMulai?.split(":")?.get(1)?.toInt() ?: 0
        val jamSelesai = data?.jamSelesai?.split(":")?.get(0)?.toInt() ?: 0
        val menitSelesai = data?.jamSelesai?.split(":")?.get(1)?.toInt() ?: 0
        et_jam_mulai?.setOnClickListener {
            val jamMulaiPicker = MaterialTimePicker.Builder().setInputMode(MaterialTimePicker.INPUT_MODE_KEYBOARD).setTimeFormat(TimeFormat.CLOCK_24H).setHour(jamMulai
                    ?: 0).setMinute(menitMulai ?: 0).build()
            jamMulaiPicker.addOnPositiveButtonClickListener {
                val value = "${getTimeStringFormat(jamMulaiPicker.hour.toString())}:${getTimeStringFormat(jamMulaiPicker.minute.toString())}"
                et_jam_mulai?.setText(value)
            }
            jamMulaiPicker.show(childFragmentManager, "")
        }
        et_jam_selesai?.setOnClickListener {
            val jamSelesaiPicker = MaterialTimePicker.Builder().setInputMode(MaterialTimePicker.INPUT_MODE_KEYBOARD).setTimeFormat(TimeFormat.CLOCK_24H).setHour(jamSelesai
                    ?: 0).setMinute(menitSelesai ?: 0).build()
            jamSelesaiPicker.addOnPositiveButtonClickListener {
                val value = "${getTimeStringFormat(jamSelesaiPicker.hour.toString())}:${getTimeStringFormat(jamSelesaiPicker.minute.toString())}"
                et_jam_selesai?.setText(value)
            }
            jamSelesaiPicker.show(childFragmentManager, "")
        }
    }

    private fun getTimeStringFormat(time: String): String {
        var waktu = time
        if (time.length == 1) waktu = "0$time"
        return waktu
    }


}