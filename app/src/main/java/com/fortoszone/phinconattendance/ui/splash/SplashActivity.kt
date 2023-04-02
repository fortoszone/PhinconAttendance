package com.fortoszone.phinconattendance.ui.splash

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.fortoszone.phinconattendance.databinding.ActivitySplashBinding
import com.fortoszone.phinconattendance.ui.dashboard.DashboardActivity
import com.fortoszone.phinconattendance.ui.onboarding.OnboardingActivity
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        FirebaseApp.initializeApp(this)
        val auth = Firebase.auth

        supportActionBar?.hide()

        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Handler(Looper.getMainLooper()).postDelayed({
            if (auth.currentUser != null) {
                startActivity(Intent(this, DashboardActivity::class.java))
            } else {
                val intent = Intent(this, OnboardingActivity::class.java)
                startActivity(intent)
            }
            finish()
        }, 2000)
    }
}