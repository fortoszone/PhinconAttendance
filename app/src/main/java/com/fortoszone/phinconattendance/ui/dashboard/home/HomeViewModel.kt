package com.fortoszone.phinconattendance.ui.dashboard.home

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fortoszone.phinconattendance.R

class HomeViewModel : ViewModel() {
    private var officeList = mutableListOf<String>()
    private var officeAddrList = mutableListOf<String>()
    private var imageList = mutableListOf<Int>()

    private fun addToList(office: String, officeAddr: String, image: Int) {
        officeList.add(office)
        officeAddrList.add(officeAddr)
        imageList.add(image)
    }

    fun postToList(context: Context) {
        addToList(
            context.getString(R.string.pt_phincon),
            context.getString(R.string.office_88_k),
            R.drawable.img_office_phincon
        )
        addToList(
            context.getString(R.string.telkomsel),
            context.getString(R.string.jl_jend),
            R.drawable.img_office_telkomsel
        )
        addToList(context.getString(R.string.rumah), context.getString(R.string.jakarta), R.drawable.img_rumah)
    }
}