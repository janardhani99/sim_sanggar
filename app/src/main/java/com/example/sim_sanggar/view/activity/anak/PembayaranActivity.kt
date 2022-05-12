package com.example.sim_sanggar.view.activity.anak

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import com.example.sim_sanggar.GlobalClass
import com.example.sim_sanggar.R
import com.example.sim_sanggar.common.Utilities
import com.example.sim_sanggar.common.clickWithDebounce
import com.example.sim_sanggar.common.onTextChanged
import com.example.sim_sanggar.data.model.anak.AnakListItem
import com.example.sim_sanggar.data.model.anak.AnakListResponse
import com.example.sim_sanggar.data.model.anak.AnakResponse
import com.example.sim_sanggar.data.model.common.EmptyResponse
import com.example.sim_sanggar.data.model.daftar.DaftarListResponse
import com.example.sim_sanggar.data.model.daftar.DaftarResponse
import com.example.sim_sanggar.data.model.daftar.PendaftaranAnak
import com.example.sim_sanggar.data.model.jadwal_sanggar.JadwalSanggarItem
import com.example.sim_sanggar.data.model.jadwal_sanggar.JadwalSanggarListResponse
import com.example.sim_sanggar.data.model.jadwal_sanggar.JadwalSanggarResponse
import com.example.sim_sanggar.presenter.anak.AnakContract
import com.example.sim_sanggar.presenter.daftar.DaftarListContract
import com.example.sim_sanggar.presenter.daftar.DaftarListPresenter
import com.example.sim_sanggar.presenter.jadwal_sanggar.JadwalSanggarContract
import com.example.sim_sanggar.presenter.jadwal_sanggar.JadwalSanggarPresenter
import com.example.sim_sanggar.view.activity.common.BaseActivity
import com.example.sim_sanggar.view.adapter.anakterdaftar.AnakTerdaftarAdapter
import com.theartofdev.edmodo.cropper.CropImage
import com.theartofdev.edmodo.cropper.CropImageView
import kotlinx.android.synthetic.main.activity_bayar_pendaftaran.*
import kotlinx.android.synthetic.main.activity_upload_bukti.btn_image_bukti_pembayaran
import kotlinx.android.synthetic.main.activity_upload_bukti.til_transfer_via
import kotlinx.android.synthetic.main.layout_dropdown_item.view.*
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File

class PembayaranActivity() : BaseActivity(), DaftarListContract.View, AnakContract.View, JadwalSanggarContract.View {

    var data: AnakListItem? = null
    var dataPendaftaran : PendaftaranAnak? = null

    val presenter = DaftarListPresenter(this)
    var imageFile: File? = null
    lateinit var adapter: AnakTerdaftarAdapter
//    var dataAnak : List<AnakListItem>? = null
    var dataKelas: JadwalSanggarItem? = null
    var listKelas: List<JadwalSanggarItem>? = null
    private var presenterKelas = JadwalSanggarPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bayar_pendaftaran)

        setToolbar()
//        toolbar_title?.text = "Daftarkan Anak"

        data = intent.getParcelableExtra<AnakListItem>("data")
//        adapter = AnakTerdaftarAdapter
        data?.let { setView(it) }
        initListener()
        presenterKelas.getJadwal()

        initAdapter()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            val result = CropImage.getActivityResult(data)
            if (resultCode == RESULT_OK) {
                iv_upload_bukti_bayar?.setImageURI(result.uri)
                imageFile = File(result.uri.path)
            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                toast(result.error.toString())
            }
        }
    }

    private fun initAdapter() {
//        val kelasAdapter = listKelas?.let { ArrayAdapter(this, R.layout.layout_dropdown_item, it) }
//        val kopiList = arrayListOf("cappuccino", "latte", "flat white")
//        ac_kategori_kelas?.run {
//            setArrayAdapter(kopiList)
//        }

        val kategori_latihan = listKelas?.map { it.kategori_latihan }

        ac_kategori_kelas?.run {
            if (kategori_latihan != null) {
                setArrayAdapter(kategori_latihan)
            }

//            onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
//                override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
//
//                }
//            }
//            onTextChanged {
//
//            }
        }

    }
//        ac_kategori_kelas?.setAdapter(kelasAdapter)
//        adapter = AnakTerdaftarAdapter { daftarItem-> kirimData(daftarItem) }



    fun AutoCompleteTextView.setArrayAdapter(list: List<String?>) {
        val adapter = ArrayAdapter(GlobalClass.context, R.layout.layout_dropdown_item, list)
        this.setAdapter(adapter)
    }



    private fun setView(data: AnakListItem) {
        data.run {
            til_nama_anak?.editText?.setText(data.nama)
            til_alamat?.editText?.setText(data.alamat)
            til_tanggal_lahir?.editText?.setText(data.tanggal_lahir)
            til_no_telepon?.editText?.setText(data.telepon)
//            foto?.let { iv_upload_bukti.loadImage(it) }
        }

//        btn_upload_foto?.clickWithDebounce {
//
//            uploadBukti()
//        }
    }

    private fun kirimData() {
//        val anak_id = daftarItem.id
        val transfer_via = til_transfer_via?.editText?.text.toString()
        val anak_id = data?.id
        val selectedKelasId = listKelas?.find{it.kategori_latihan == ac_kategori_kelas?.text.toString()}?.id
        val kategori_kelas = til_kategori_kelas.editText?.text.toString()

//        ac_kategori_kelas.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->
//            val selectedKelasId = parent.getItemIdAtPosition(position)
//        }

        val tambahData = HashMap<String, Any?>()
        tambahData["transfer_via"] = transfer_via
        tambahData["anak_id"] = anak_id
        tambahData["jadwal_sanggar_id"] = selectedKelasId
        tambahData["kategori_kelas"] = kategori_kelas

        isLoading(true)
//        val isValid = imageFile != null && data?.id != null
//        if (data?.id == null) {
          presenter.addListDaftar(tambahData)
//        } else {
//            showCustomDialogBack("Gagal", "gagal")
//        }
    }

    private fun initListener() {
        btn_image_bukti_pembayaran?.clickWithDebounce {
            openImageResource()
        }

        btn_kirim_data?.clickWithDebounce {
//            data?.let { it1 -> kirimData(it1) }
            kirimData()
        }

    }

    private fun openImageResource() {
        CropImage.activity()
            .setAspectRatio(2, 3)
            .setGuidelines(CropImageView.Guidelines.ON)
            .start(this)
    }

    private fun uploadImage(id: Int?, it: File) {
        val requestBody =
            RequestBody.create(MediaType.parse("multipart/form-data"), imageFile)
        val part = MultipartBody.Part.createFormData(
            "image",
            it.name,
            requestBody
        )
        id?.let { presenter.addImage(it, part) }
    }

    private fun isLoading(isLoad: Boolean) {
        if (isLoad) Utilities.showProgress(this)
        else Utilities.hideProgress()
    }

    override fun daftarListResponse(response: DaftarResponse) {
        if (imageFile != null) {
            imageFile?.let { uploadImage(response.data?.id,it) }
        } else {
            isLoading(false)
            this.showCustomDialogBack("Data berhasil", "Data berhasil dikirim")
        }

//        adapter.deleteItem()

    }

    override fun getDaftarListResponse(response: DaftarListResponse) {
        TODO("Not yet implemented")
    }

    override fun getBiayaPendaftaran(response: DaftarListResponse) {
//        val kategori_latihan_selected = til_kategori_kelas.editText?.text.toString()
//        isLoading(true)
//        til_total_bayar.text.setText(presenter.getBiayaPendaftaran(kategori_latihan_selected))
    }

    override fun deleteDaftarListResponse(response: EmptyResponse) {
        TODO("Not yet implemented")
    }

    override fun uploadImageResponse() {
        isLoading(false)
        this.showCustomDialogBack("Data berhasil", "Data berhasil dikirim")
    }

    override fun anakResponse(response: AnakResponse) {
        TODO("Not yet implemented")
    }

    override fun getAnakResponse(response: AnakListResponse) {
        TODO("Not yet implemented")
    }

    override fun jadwalSanggarResponse(response: JadwalSanggarResponse) {
        TODO("Not yet implemented")
    }

    override fun getJadwalSanggarResponse(response: JadwalSanggarListResponse) {
        listKelas = response.data
        initAdapter()
    }

    override fun deleteJadwalResponse(response: EmptyResponse) {
        TODO("Not yet implemented")
    }

    override fun showError(title: String, message: String) {
        this.showErrorAlert(title, message)
    }
}