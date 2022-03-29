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
import com.example.sanggar.data.model.common.EmptyResponse
import com.example.sanggar.data.model.jam_operasional.*
import com.example.sanggar.presenter.DatePickerHelper
import com.example.sanggar.presenter.jam_operasional.TanggalLiburContract
import com.example.sanggar.presenter.jam_operasional.TanggalLiburPresenter
import com.example.sanggar.view.activity.common.BaseActivity
import com.example.sanggar.view.activity.jam_operasional.JamOperasionalActivity
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.fragment_jam_operasional_bottom_sheet.btn_batal
import kotlinx.android.synthetic.main.fragment_tanggal_libur_bottom_sheet.*
import kotlinx.android.synthetic.main.layout_dropdown_item.view.*
import kotlinx.android.synthetic.main.recycler_tanggal_libur.*
import java.util.*
import kotlin.collections.HashMap

class TanggalLiburBottomSheetFragment(val data: TanggalLiburItem? = null) : BottomSheetDialogFragment(), TanggalLiburContract.View {

    val presenter = TanggalLiburPresenter(this)
    val preferences = Preferences(GlobalClass.context)
    private lateinit var baseActivity: BaseActivity
    lateinit var datePicker: DatePickerHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.AppTheme_BottomSheetDialog)

        datePicker = DatePickerHelper(this.requireContext())
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tanggal_libur_bottom_sheet, container, false)
    }

    override fun onStart() {
        super.onStart()
        baseActivity = (activity as BaseActivity)
        initView(data)
    }

    private fun showDatePickerDialog() {
        val cal = Calendar.getInstance()
        val d = cal.get(Calendar.DAY_OF_MONTH)
        val m = cal.get(Calendar.MONTH)
        val y = cal.get(Calendar.YEAR)

        val minDate = Calendar.getInstance()
        minDate.set(Calendar.YEAR, 2022)
        minDate.set(Calendar.HOUR_OF_DAY, 8)
        minDate.set(Calendar.MINUTE, 0)
        minDate.set(Calendar.SECOND, 0)
        datePicker.setMinDate(minDate.timeInMillis)

        val maxDate = Calendar.getInstance()
        maxDate.add(Calendar.DAY_OF_MONTH, 30)
        datePicker.setMaxDate(maxDate.timeInMillis)

        datePicker.showDialog(y, m, d, object: DatePickerHelper.Callback {
            override fun onDateSelected(year: Int, month: Int, dayOfMonth: Int) {
                val dayStr = if(dayOfMonth<10) "0${dayOfMonth}" else "${dayOfMonth}"
                val mon = month + 1
                val monthStr = if (mon<10) "0${mon}" else "${mon}"
                til_tanggal_libur.editText?.setText("${year}-${monthStr}-${dayStr}")
            }


        })
    }

    private fun initView(data: TanggalLiburItem?) {
        data?.run {
            til_tanggal_libur?.editText?.setText(data.tanggal_libur)
            til_keterangan?.editText?.setText(data.keterangan)
        }

        btn_simpan_tanggal_libur?.clickWithDebounce {
            editTanggalLibur()
//            this.dismiss()
        }

        btn_batal?.clickWithDebounce {
            dismiss()
        }


        btn_pilih_tanggal?.setOnClickListener {
            showDatePickerDialog()
        }
    }

    private fun editTanggalLibur() {
        val tanggal_libur = til_tanggal_libur?.editText?.text.toString()
        val keterangan = til_keterangan?.editText?.text.toString()

        val tambahData = HashMap<String, Any?>()
        tambahData["tanggal_libur"] = tanggal_libur
        tambahData["keterangan"] = keterangan

        isLoadingProcess(true)
        if(data == null) {
            presenter.tambahTanggalLibur(tambahData)
        }else{
            data.id?.let {presenter.editTanggalLibur(it, tambahData) }
        }
    }

    fun isLoadingProcess(isLoad: Boolean) {
        if(isLoad) this.context?.let { Utilities.showProgress(it) }
        else Utilities.hideProgress()
    }

    override fun tanggalLiburResponse(response: TanggalLiburResponse) {
        this.dismiss()
        isLoadingProcess(false)
        baseActivity.showCustomDialog("Data Berhasil", "Data berhasil ditambahkan")

        (activity as JamOperasionalActivity?)?.fetchData()

    }

    override fun getTanggalLiburResponse(response: TanggalLiburListResponse) {
        TODO("Not yet implemented")
    }

    override fun deleteTanggalLiburResponse(response: EmptyResponse) {
        TODO("Not yet implemented")
    }

    override fun showError(title: String, message: String) {
        baseActivity.showErrorAlert(title, message)
    }

}