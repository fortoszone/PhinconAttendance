package com.fortoszone.phinconattendance.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.fortoszone.phinconattendance.R
import com.fortoszone.phinconattendance.databinding.RowLogsBinding
import com.fortoszone.phinconattendance.model.Log

class HistoryAdapter(private val context: Context, private val logList: ArrayList<Log>) :
    RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HistoryViewHolder {
        val view: View =
            LayoutInflater.from(context).inflate(R.layout.row_logs, parent, false)
        return HistoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        holder.bind(logList[position], holder.itemView)
    }

    override fun getItemCount(): Int {
        return logList.size
    }

    inner class HistoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding = RowLogsBinding.bind(itemView)
        fun bind(log: Log, itemView: View) {
            binding.tvOffice.text = log.site
            binding.tvOfficeAddress.text = log.address
            Glide.with(itemView).load(log.image)
                .into(binding.ivOffice)

            binding.tvTime.text = log.time

            binding.tvCheckIn.text.apply {
                if (!log.isCheckedIn) {
                    binding.tvCheckIn.text = context.getString(R.string.check_out)
                } else {
                    binding.tvCheckIn.text = context.getString(R.string.check_in)
                }
            }
        }
    }
}