package com.fortoszone.phinconattendance.ui.dashboard.home

import android.annotation.SuppressLint
import android.content.Context.MODE_PRIVATE
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.SimpleItemAnimator
import com.fortoszone.phinconattendance.R
import com.fortoszone.phinconattendance.adapter.LocationAdapter
import com.fortoszone.phinconattendance.databinding.FragmentHomeBinding
import com.fortoszone.phinconattendance.model.Location
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

class HomeFragment : Fragment() {
    private lateinit var locationAdapter: LocationAdapter
    private var locationList: ArrayList<Location> = arrayListOf()
    private lateinit var binding: FragmentHomeBinding
    private var isCheckIn: Boolean = false

    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        binding.btnNotification.setOnClickListener {
            Toast.makeText(requireContext(), "Notification", Toast.LENGTH_SHORT).show()
        }

        return binding.root
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val homeViewModel =
            ViewModelProvider(this)[HomeViewModel::class.java]

        getCurrentTime()
        checkIfIsCheckedIn(homeViewModel, isCheckIn)
        showLocationList()

        binding.btnCheckIn.setOnClickListener {
            val sh = requireActivity().getSharedPreferences("logs", MODE_PRIVATE)
            val name = sh.getString("name", "")
            val addr = sh.getString("address", "")
            val img = sh.getInt("image", 0)
            val time = sh.getString("currentTime", "")

            isCheckIn = !isCheckIn
            if (isCheckIn) {
                isCheckIn = true
                binding.btnCheckIn.background = context?.getDrawable(R.drawable.btn_check_in_active)
                binding.btnCheckIn.text = getString(R.string.check_out)

                homeViewModel.addSelectedLocationData(
                    requireContext(),
                    locationList,
                    img,
                    name!!,
                    addr!!
                )
                showLocationList()

                homeViewModel.writeLog(isCheckIn, img, name, addr, time!!)

            } else {
                binding.btnCheckIn.background = context?.getDrawable(R.drawable.btn_check_in)
                binding.btnCheckIn.text = getString(R.string.check_in)
                homeViewModel.addLocationData(requireContext(), locationList)
                showLocationList()

                homeViewModel.writeLog(isCheckIn, img, name!!, addr!!, time!!)
            }
        }
    }

    private fun showLocationList() {
        locationAdapter =
            LocationAdapter(requireContext(), locationList)
        binding.rvLocation.setHasFixedSize(true)
        binding.rvLocation.adapter = locationAdapter
        binding.rvLocation.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        (binding.rvLocation.itemAnimator as SimpleItemAnimator).supportsChangeAnimations = false
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private fun checkIfIsCheckedIn(viewModel: HomeViewModel, checkIn: Boolean) {
        if (checkIn) {
            binding.btnCheckIn.background = context?.getDrawable(R.drawable.btn_check_in_active)
            binding.btnCheckIn.text = getString(R.string.check_out)

            val sh = requireActivity().getSharedPreferences("logs", MODE_PRIVATE)
            val name = sh.getString("name", "")
            val addr = sh.getString("address", "")
            val img = sh.getInt("image", 0)

            viewModel.addSelectedLocationData(requireContext(), locationList, img, name!!, addr!!)
        } else {
            binding.btnCheckIn.background = context?.getDrawable(R.drawable.btn_check_in)
            binding.btnCheckIn.text = getString(R.string.check_in)

            viewModel.addLocationData(requireContext(), locationList)
        }
    }

    @SuppressLint("SetTextI18n")
    private fun getCurrentTime() {
        val time = LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm"))
        val currentTime = time.format(Date())
        binding.tvCurrentTime.text = "Hour: $currentTime"

        val date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("d MMMM yyyy"))
        val currentDate = date.format(Date())
        binding.tvCurrentDate.text = currentDate
    }

    override fun onResume() {
        super.onResume()
        showLocationList()
    }
}