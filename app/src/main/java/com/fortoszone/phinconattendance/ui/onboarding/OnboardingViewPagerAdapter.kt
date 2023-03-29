package com.fortoszone.phinconattendance.ui.onboarding

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.fortoszone.phinconattendance.R

class OnboardingViewPagerAdapter(private var title: List<String>, private var desc: List<String>, private var images: List<Int>, private val context: Context) :
    RecyclerView.Adapter<OnboardingViewPagerAdapter.PagerViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): OnboardingViewPagerAdapter.PagerViewHolder {
        val view: View =
            LayoutInflater.from(context).inflate(R.layout.row_onboarding, parent, false)
        return PagerViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: OnboardingViewPagerAdapter.PagerViewHolder,
        position: Int
    ) {
        holder.tvTitle.text = title[position]
        holder.tvDesc.text = desc[position]
        holder.ivOnboarding.setImageResource(images[position])
    }

    override fun getItemCount(): Int {
        return 3
    }

    inner class PagerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvTitle: TextView = itemView.findViewById(R.id.tv_onboarding_title)
        val tvDesc: TextView = itemView.findViewById(R.id.tv_onboarding_desc)
        val ivOnboarding: ImageView = itemView.findViewById(R.id.iv_onboarding)
    }
}