package com.fortoszone.phinconattendance.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.fortoszone.phinconattendance.R

class LocationAdapter(
    private val context: Context,
    private var tvOffice: List<String>,
    private var tvOfficeAddress: List<String>,
    private var ivOffice: List<Int>
) :
    RecyclerView.Adapter<LocationAdapter.LocationViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): LocationAdapter.LocationViewHolder {
        val view: View =
            LayoutInflater.from(context).inflate(R.layout.row_office_location, parent, false)
        return LocationViewHolder(view)
    }

    override fun onBindViewHolder(holder: LocationAdapter.LocationViewHolder, position: Int) {
        holder.tvOffice.text = tvOffice[position]
        holder.tvOfficeAddress.text = tvOfficeAddress[position]
        holder.ivOffice.setImageResource(ivOffice[position])
    }

    override fun getItemCount(): Int {
        return 3
    }

    inner class LocationViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ivOffice: ImageView = itemView.findViewById(R.id.iv_office)
        val tvOffice: TextView = itemView.findViewById(R.id.tv_office)
        val tvOfficeAddress: TextView = itemView.findViewById(R.id.tv_office_address)
    }
}