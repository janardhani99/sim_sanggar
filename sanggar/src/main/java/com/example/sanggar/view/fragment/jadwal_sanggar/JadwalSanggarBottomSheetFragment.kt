package com.example.sanggar.view.fragment.jadwal_sanggar

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.DialogFragment.STYLE_NORMAL
import com.example.sanggar.GlobalClass
import com.example.sanggar.R
import com.example.sanggar.common.Preferences
import com.example.sanggar.common.Utilities
import com.example.sanggar.common.clickWithDebounce
import com.example.sanggar.data.model.common.EmptyResponse
import com.example.sanggar.data.model.jadwal_sanggar.JadwalSanggarItem
import com.example.sanggar.data.model.jadwal_sanggar.JadwalSanggarListResponse
import com.example.sanggar.data.model.jadwal_sanggar.JadwalSanggarResponse
import com.example.sanggar.presenter.auth.AuthPresenter
import com.example.sanggar.presenter.jadwal_sanggar.JadwalSanggarContract
import com.example.sanggar.presenter.jadwal_sanggar.JadwalSanggarPresenter
import com.example.sanggar.view.activity.common.BaseActivity
import com.example.sanggar.view.activity.jadwal_sanggar.JadwalSanggarActivity
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import kotlinx.android.synthetic.main.fragment_jadwal_sanggar_bottom_sheet.*

class JadwalSanggarBottomSheetFragment(val data: JadwalSanggarItem? = null) : BottomSheetDialogFragment(), JadwalSanggarContract.View {

    val presenter = JadwalSanggarPresenter(this)
    val preferences = Preferences(GlobalClass.context)
    private lateinit var baseActivity: BaseActivity

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
        baseActivity = (activity as BaseActivity)
        initAdapter()
        initView(data)
    }

    private fun initAdapter() {
        val hariAdapter = context?.let { ArrayAdapter<String>(it, R.layout.layout_dropdown_item, resources.getStringArray(R.array.nama_hari)) }
        ac_hari_jadwal?.setAdapter(hariAdapter)
    }

    private fun initView(data: JadwalSanggarItem?) {
        data?.run {
            ac_hari_jadwal?.setText(data.hari, false)
            et_jam_mulai?.setText(data.jam_mulai?.substring(0,5))
            et_jam_selesai?.setText(data.jam_selesai?.substring(0,5))
        }

            //create data
            btn_simpan_jadwal?.clickWithDebounce {
//                (activity as BaseActivity?)?.toast("create data")

                addOrEditJadwalSanggarProcess()
            }


        btn_cancel?.clickWithDebounce {
            dismiss()
        }

        val jamMulai = data?.jam_mulai?.split(":")?.get(0)?.toInt() ?: 0
        val menitMulai = data?.jam_mulai?.split(":")?.get(1)?.toInt() ?: 0
        val jamSelesai = data?.jam_selesai?.split(":")?.get(0)?.toInt() ?: 0
        val menitSelesai = data?.jam_selesai?.split(":")?.get(1)?.toInt() ?: 0
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

    private fun addOrEditJadwalSanggarProcess() {
        val hari = til_hari_jadwal?.editText?.text.toString()
        val jam_mulai = til_jam_mulai?.editText?.text.toString()
        val jam_selesai = til_jam_selesai?.editText?.text.toString()

        val tambahData = HashMap<String, Any?>()
        tambahData["hari"] = hari.toLowerCase()
        tambahData["jam_mulai"] = jam_mulai
        tambahData["jam_selesai"] = jam_selesai

        isLoadingProcess(true)
        if(data == null) {
            presenter.tambahJadwalSanggar(tambahData)
        }else{
            data.id?.let {presenter.editJadwal(it, tambahData) }

        }


//        Preferences(GlobalClass.context).accessToken?.let { Log.d("ACCESS TOKENKU", it) }

    }

    override fun jadwalSanggarResponse(response: JadwalSanggarResponse) {
        this.dismiss()
        baseActivity.showCustomDialog("Data Berhasil", "Data berhasil ditambahkan")

        (activity as JadwalSanggarActivity?)?.fetchData()

//        startActivity(Intent (context, JadwalSanggarActivity::class.java))
    }

    override fun getJadwalSanggarResponse(response: JadwalSanggarListResponse) {

    }

    override fun deleteJadwalResponse(response: EmptyResponse) {

    }

    override fun showError(title: String, message: String) {

//        showErrorAlert(title, message)
        baseActivity.showErrorAlert(title, message)
    }

    fun isLoadingProcess(isLoad: Boolean) {
        if(isLoad) this.context?.let { Utilities.showProgress(it) }
        else Utilities.hideProgress()

    }

}