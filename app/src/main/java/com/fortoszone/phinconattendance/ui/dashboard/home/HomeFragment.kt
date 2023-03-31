package com.fortoszone.phinconattendance.ui.dashboard.home

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.fortoszone.phinconattendance.R
import com.fortoszone.phinconattendance.adapter.LocationAdapter
import com.fortoszone.phinconattendance.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    private lateinit var locationAdapter: LocationAdapter
    private var officeList = mutableListOf<String>()
    private var officeAddrList = mutableListOf<String>()
    private var imageList = mutableListOf<Int>()

    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        val view: View = binding.root

        val homeViewModel =
            ViewModelProvider(this)[HomeViewModel::class.java]

        locationAdapter =
            LocationAdapter(requireContext(), officeList, officeAddrList, imageList)
        binding.rvLocation.setHasFixedSize(true)
        binding.rvLocation.adapter = locationAdapter
        binding.rvLocation.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        postToList(requireContext())

        return view
    }

    private fun addToList(office: String, officeAddr: String, image: Int) {
        officeList.add(office)
        officeAddrList.add(officeAddr)
        imageList.add(image)
    }

    private fun postToList(context: Context) {
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
        addToList(
            context.getString(R.string.rumah),
            context.getString(R.string.jakarta),
            R.drawable.img_rumah
        )
    }
}