package com.fortoszone.phinconattendance.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.fortoszone.phinconattendance.R
import com.fortoszone.phinconattendance.databinding.RowOfficeLocationBinding
import com.fortoszone.phinconattendance.model.Location
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

class LocationAdapter(
    private val context: Context,
    private val locationList: ArrayList<Location>,
) :
    RecyclerView.Adapter<LocationAdapter.LocationViewHolder>() {

    private var itemSelected = -1
    var lastItemSelectedPos = -1

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): LocationAdapter.LocationViewHolder {
        val view: View =
            LayoutInflater.from(context).inflate(R.layout.row_office_location, parent, false)
        return LocationViewHolder(view)
    }

    override fun onBindViewHolder(holder: LocationAdapter.LocationViewHolder, position: Int) {
        if (position == itemSelected) {
            holder.selectedBg()
        } else
            holder.defaultBg()
        holder.bind(locationList[position], holder.itemView)
    }

    override fun getItemCount(): Int {
        return locationList.size
    }

    inner class LocationViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = RowOfficeLocationBinding.bind(itemView)

        @SuppressLint("ResourceAsColor")
        fun bind(location: Location, itemView: View) {
            binding.tvOffice.text = location.name
            binding.tvOfficeAddress.text = location.address
            Glide.with(itemView).load(location.image)
                .into(binding.ivOffice)

            binding.location.setOnClickListener {
                itemSelected = adapterPosition
                lastItemSelectedPos = if (lastItemSelectedPos == -1)
                    itemSelected
                else {
                    notifyItemChanged(lastItemSelectedPos)
                    itemSelected
                }
                notifyItemChanged(itemSelected)

                val time = LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm"))
                val currentTime = time.format(Date())

                val sharedPreferences = context.getSharedPreferences("logs", MODE_PRIVATE)
                val log = sharedPreferences.edit()

                log.putString("name", location.name)
                log.putString("address", location.address)
                log.putInt("image", location.image)
                log.putString("currentTime", currentTime)
                log.apply()
            }
        }

        @SuppressLint("ResourceAsColor", "UseCompatLoadingForDrawables")
        fun defaultBg() {
            binding.btnLocation.background = context.getDrawable(R.drawable.cv_dashboard_home)
            binding.tvOffice.setTextColor(context.getColor(R.color.text_primary))
            binding.tvOfficeAddress.setTextColor(context.getColor(R.color.text_secondary))
        }

        @SuppressLint("UseCompatLoadingForDrawables")
        fun selectedBg() {
            binding.btnLocation.background = context.getDrawable(R.drawable.btn_loc_selected)
            binding.tvOffice.setTextColor(context.getColor(R.color.white))
            binding.tvOfficeAddress.setTextColor(context.getColor(R.color.white))
        }
    }
}