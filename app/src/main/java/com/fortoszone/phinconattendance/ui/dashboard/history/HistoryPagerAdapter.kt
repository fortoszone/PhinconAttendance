package com.fortoszone.phinconattendance.ui.dashboard.history

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class HistoryPagerAdapter(fragment: Fragment): FragmentStateAdapter(fragment){
    override fun getItemCount(): Int {
        return 4
    }

    override fun createFragment(position: Int): Fragment {
        TODO("Not yet implemented")
    }
}