package com.fortoszone.phinconattendance.ui.dashboard.home

import android.content.Context
import androidx.lifecycle.ViewModel
import com.fortoszone.phinconattendance.R
import com.fortoszone.phinconattendance.model.Location
import com.fortoszone.phinconattendance.model.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class HomeViewModel : ViewModel() {
    fun addLocationData(context: Context, locationList: ArrayList<Location>) {
        locationList.clear()
        locationList.add(
            Location(
                R.drawable.img_office_phincon,
                context.getString(R.string.pt_phincon),
                context.getString(R.string.office_88_k)
            )
        )

        locationList.add(
            Location(
                R.drawable.img_office_telkomsel,
                context.getString(R.string.telkomsel),
                context.getString(R.string.jl_jend)
            )
        )

        locationList.add(
            Location(
                R.drawable.img_rumah,
                context.getString(R.string.rumah),
                context.getString(R.string.jakarta_selatan)
            )
        )
    }

    fun addSelectedLocationData(
        context: Context,
        locationList: ArrayList<Location>,
        image: Int,
        site: String,
        address: String
    ) {
        locationList.clear()
        locationList.add(Location(image, site, address))
    }

    fun writeLog(isCheckedIn: Boolean, img: Int, name: String, addr: String, time: String) {
        val log = Log(isCheckedIn, img, name, addr, time)

        val currentUser = FirebaseAuth.getInstance().currentUser!!.uid

        val databaseReference =
            FirebaseDatabase.getInstance().getReference("logs")
        val logId = databaseReference.push().key
        databaseReference.child(currentUser).child(logId!!).setValue(log)
    }
}