package com.fortoszone.phinconattendance.ui.dashboard.history

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.fortoszone.phinconattendance.R
import com.fortoszone.phinconattendance.adapter.HistoryAdapter
import com.fortoszone.phinconattendance.databinding.FragmentHistoryBinding
import com.fortoszone.phinconattendance.model.Log
import com.fortoszone.phinconattendance.adapter.LogPagerAdapter

class HistoryFragment : Fragment() {
    private lateinit var binding: FragmentHistoryBinding
    private lateinit var adapter: HistoryAdapter
    private lateinit var pagerAdapter: LogPagerAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHistoryBinding.inflate(inflater, container, false)

        binding.btnNotification.setOnClickListener {
            Toast.makeText(requireContext(), "Notification", Toast.LENGTH_SHORT).show()
        }

        val logs = ArrayList<Log>()
        adapter = HistoryAdapter(requireContext(), logs)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        loadData()
    }

    override fun onResume() {
        super.onResume()
        loadData()
    }

    @SuppressLint("InflateParams")
    private fun loadData() {
        val viewPager = binding.viewPager
        pagerAdapter = LogPagerAdapter(childFragmentManager)
        binding.viewPager.adapter = pagerAdapter

        val tabLayout = binding.tlLogs
        tabLayout.setupWithViewPager(viewPager)

        for (i in 0..4) {
            val textView = LayoutInflater.from(requireContext()).inflate(R.layout.tab_title, null) as TextView
            binding.tlLogs.getTabAt(i)?.customView = textView
        }
    }
}