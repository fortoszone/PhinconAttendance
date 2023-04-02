package com.fortoszone.phinconattendance.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.fortoszone.phinconattendance.ui.dashboard.history.log.DayFragment
import com.fortoszone.phinconattendance.ui.dashboard.history.log.MonthFragment
import com.fortoszone.phinconattendance.ui.dashboard.history.log.WeekFragment
import com.fortoszone.phinconattendance.ui.dashboard.history.log.YearFragment

class LogPagerAdapter(fragmentManager: FragmentManager) :
    FragmentPagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    override fun getCount(): Int {
        return 4
    }

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> DayFragment()
            1 -> WeekFragment()
            2 -> MonthFragment()
            3 -> YearFragment()
            else -> throw IllegalArgumentException("Invalid position: $position")
        }
    }

    override fun getPageTitle(position: Int): CharSequence {
        return when (position) {
            0 -> "Day"
            1 -> "Week"
            2 -> "Month"
            3 -> "Year"
            else -> throw IllegalArgumentException("Invalid position: $position")
        }
    }
}