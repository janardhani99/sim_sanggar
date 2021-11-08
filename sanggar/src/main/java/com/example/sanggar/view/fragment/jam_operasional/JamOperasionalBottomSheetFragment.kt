package com.example.sanggar.view.fragment.jam_operasional

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.sanggar.GlobalClass
import com.example.sanggar.R
import com.example.sanggar.common.Preferences
import com.example.sanggar.common.Utilities
import com.example.sanggar.common.clickWithDebounce
import com.example.sanggar.data.model.jam_operasional.JamOperasionalItem
import com.example.sanggar.data.model.jam_operasional.JamOperasionalListResponse
import com.example.sanggar.data.model.jam_operasional.JamOperasionalResponse
import com.example.sanggar.presenter.jam_operasional.JamOperasionalContract
import com.example.sanggar.presenter.jam_operasional.JamOperasionalPresenter
import com.example.sanggar.view.activity.common.BaseActivity
import com.example.sanggar.view.activity.jadwal_sanggar.JadwalSanggarActivity
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.MaterialTimePicker.INPUT_MODE_KEYBOARD
import com.google.android.material.timepicker.TimeFormat
import kotlinx.android.synthetic.main.fragment_jadwal_sanggar_bottom_sheet.*
import kotlinx.android.synthetic.main.fragment_jam_operasional_bottom_sheet.*
import kotlinx.android.synthetic.main.fragment_jam_operasional_bottom_sheet.et_jam_mulai
import kotlinx.android.synthetic.main.fragment_jam_operasional_bottom_sheet.et_jam_selesai
import kotlinx.android.synthetic.main.fragment_jam_operasional_bottom_sheet.til_jam_mulai
import kotlinx.android.synthetic.main.fragment_jam_operasional_bottom_sheet.til_jam_selesai

class JamOperasionalBottomSheetFragment(val data: JamOperasionalItem? = null) : BottomSheetDialogFragment(), JamOperasionalContract.View {

    val presenter = JamOperasionalPresenter(this)
    val preferences = Preferences(GlobalClass.context)
    private lateinit var baseActivity: BaseActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.AppTheme_BottomSheetDialog)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_jam_operasional_bottom_sheet, container, false)
    }

    override fun onStart() {
        super.onStart()
        baseActivity = (activity as BaseActivity)
        initView()
        initListener()
    }

    private fun initListener() {
        btn_simpan_jam_operasional?.clickWithDebounce {
            editJamOperasional()
            this.dismiss()
        }

        btn_batal?.clickWithDebounce {
            dismiss()
        }

        switch_status?.setOnCheckedChangeListener { _, isChecked ->
            data?.status = isChecked
            switch_status?.text = getStatusText(isChecked)
        }

        val jamMulai = data?.jamMulai?.split(":")?.get(0)?.toInt()
        val menitMulai = data?.jamMulai?.split(":")?.get(1)?.toInt()
        val jamSelesai = data?.jamSelesai?.split(":")?.get(0)?.toInt()
        val menitSelesai = data?.jamSelesai?.split(":")?.get(1)?.toInt()

        et_jam_mulai?.setOnClickListener {
            val jamMulaiPicker = MaterialTimePicker.Builder().setInputMode(INPUT_MODE_KEYBOARD).setTimeFormat(TimeFormat.CLOCK_24H).setHour(jamMulai
                    ?: 0).setMinute(menitMulai ?: 0).build()
            jamMulaiPicker.addOnPositiveButtonClickListener {
                val value = "${getTimeStringFormat(jamMulaiPicker.hour.toString())}:${getTimeStringFormat(jamMulaiPicker.minute.toString())}"
                et_jam_mulai?.setText(value)
                data?.jamMulai = value
            }
            jamMulaiPicker.show(childFragmentManager, "")
        }
        et_jam_selesai?.setOnClickListener {
            val jamSelesaiPicker = MaterialTimePicker.Builder().setInputMode(INPUT_MODE_KEYBOARD).setTimeFormat(TimeFormat.CLOCK_24H).setHour(jamSelesai
                    ?: 0).setMinute(menitSelesai ?: 0).build()
            jamSelesaiPicker.addOnPositiveButtonClickListener {
                val value = "${getTimeStringFormat(jamSelesaiPicker.hour.toString())}:${getTimeStringFormat(jamSelesaiPicker.minute.toString())}"
                et_jam_selesai?.setText(value)
                data?.jamSelesai = value
            }
            jamSelesaiPicker.show(childFragmentManager, "")
        }

    }

    private fun getTimeStringFormat(time: String): String {
        var waktu = time
        if (time.length == 1) waktu = "0$time"
        return waktu
    }

    private fun getStatusText(boolean: Boolean): String {
        return if (boolean) "Buka" else "Tutup"
    }

    private fun initView() {
        til_jam_mulai?.editText?.setText(data?.jamMulai)
        til_jam_selesai?.editText?.setText(data?.jamSelesai)

        switch_status?.text = getStatusText(data?.status == true)
        switch_status?.isChecked = data?.status == true


    }

    private fun editJamOperasional() {
        val hari = til_hari_jadwal?.editText?.text.toString()
        val jam_mulai = til_jam_mulai?.editText?.text.toString()
        val jam_selesai = til_jam_selesai?.editText?.text.toString()

        val tambahData = HashMap<String, Any?>()
        tambahData["hari"] = hari.toLowerCase()
        tambahData["jam_mulai"] = jam_mulai
        tambahData["jam_selesai"] = jam_selesai

        isLoadingProcess(true)
        data?.id?.let {presenter.editJamOperasional(it, tambahData) }

    }

    fun isLoadingProcess(isLoad: Boolean) {
        if(isLoad) this.context?.let { Utilities.showProgress(it) }
        else Utilities.hideProgress()

    }

    override fun jamOperasionalResponse(response: JamOperasionalResponse) {
        this.dismiss()
        isLoadingProcess(false)
        baseActivity.showCustomDialog("Data Berhasil", "Data berhasil ditambahkan")

        (activity as JadwalSanggarActivity?)?.fetchData()

    }

    override fun getJamOperasionalResponse(response: JamOperasionalListResponse) {
        TODO("Not yet implemented")
    }

    override fun showError(title: String, message: String) {
        baseActivity.showErrorAlert(title, message)
    }

}