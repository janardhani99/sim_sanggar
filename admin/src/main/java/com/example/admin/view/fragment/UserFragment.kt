package com.example.admin.view.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.admin.R
import com.example.admin.common.Utilities
import com.example.admin.data.model.user.UserData
import com.example.admin.data.model.user.UserListResponse
import com.example.admin.data.model.user.UserResponse
import com.example.admin.presenter.user.UserContract
import com.example.admin.presenter.user.UserPresenter
import com.example.admin.view.adapter.user.UserAdapter
import kotlinx.android.synthetic.main.fragment_user.*

class UserFragment : Fragment(), UserContract.View {

    var data_user : UserData? = null
    lateinit var adapter: UserAdapter
    private var presenter = UserPresenter(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initAdapter()
        presenter.getUser()

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user, container, false)
    }

    private fun initAdapter() {
        adapter = UserAdapter({ editItem ->
//            val intent = Intent(this, AnakActivity::class.java)
//            intent.putExtra("data_anak", detailItem)
//            startActivity(intent)
        }, {deleteItem ->

            })
        rv_user?.layoutManager = LinearLayoutManager(context)
        rv_user?.adapter = adapter
    }

    fun fetchData() {
        isLoading(true)
        presenter.getUser()
    }

    private fun isLoading(isLoad: Boolean) {
        if(isLoad) context?.let { Utilities.showProgress(it) }
        else Utilities.hideProgress()
    }
    override fun userResponse(response: UserResponse) {
        TODO("Not yet implemented")
    }

    override fun getUserResponse(response: UserListResponse) {
        isLoading(false)
        response.data?.let{ adapter.setData(it)}
    }

    override fun showError(title: String, message: String) {
        this.showError(title, message)
    }


}