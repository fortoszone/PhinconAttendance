package com.fortoszone.phinconattendance.ui.dashboard.history.log

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.fortoszone.phinconattendance.adapter.HistoryAdapter
import com.fortoszone.phinconattendance.databinding.FragmentLogBinding
import com.fortoszone.phinconattendance.model.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.getValue

class MonthFragment : Fragment() {
    private lateinit var binding: FragmentLogBinding
    private lateinit var adapter: HistoryAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLogBinding.inflate(layoutInflater, container, false)

        val logs = ArrayList<Log>()
        adapter = HistoryAdapter(requireContext(), logs)

        val userid = FirebaseAuth.getInstance().currentUser!!.uid
        val ref = FirebaseDatabase.getInstance().getReference("logs")

        ref.child(userid).addValueEventListener(object : ValueEventListener {
            @SuppressLint("NotifyDataSetChanged")
            override fun onDataChange(snapshot: DataSnapshot) {
                for (data in snapshot.children) {
                    val model = data.getValue<Log>()
                    logs.add(model as Log)
                }

                if (logs.size > 0) {
                    binding.rvLogs.adapter = adapter
                    binding.rvLogs.setHasFixedSize(true)
                    binding.rvLogs.layoutManager =
                        LinearLayoutManager(context, LinearLayoutManager.VERTICAL, true)
                } else
                    Toast.makeText(context, "no data", Toast.LENGTH_SHORT).show()

                adapter.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(context, error.message, Toast.LENGTH_SHORT).show()
            }
        })

        return binding.root
    }
}