package com.fortoszone.phinconattendance.ui.dashboard.profile

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.fortoszone.phinconattendance.databinding.FragmentProfileBinding
import com.fortoszone.phinconattendance.ui.onboarding.OnboardingActivity
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class ProfileFragment : Fragment() {
    private lateinit var binding: FragmentProfileBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val viewModel =
            ViewModelProvider(this)[ProfileViewModel::class.java]

        binding = FragmentProfileBinding.inflate(inflater, container, false)

        binding.btnEdit.setOnClickListener {
            Toast.makeText(requireContext(), "Edit profile", Toast.LENGTH_SHORT).show()
        }

        binding.btnSignOut.setOnClickListener {
            Firebase.auth.signOut()
            startActivity(Intent(requireContext(), OnboardingActivity::class.java))
        }

        getInfo()

        return binding.root
    }

    private fun getInfo() {
        val user = Firebase.auth.currentUser
        user?.let {
            val name = it.displayName
            binding.tvUserFullName.text = name
        }
    }
}