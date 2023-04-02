package com.fortoszone.phinconattendance.ui.auth.login

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.fortoszone.phinconattendance.databinding.ActivityLoginBinding
import com.fortoszone.phinconattendance.ui.auth.forgot.ForgotActivity
import com.fortoszone.phinconattendance.ui.auth.register.RegisterActivity
import com.fortoszone.phinconattendance.ui.dashboard.DashboardActivity
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private val auth = Firebase.auth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        FirebaseApp.initializeApp(this)

        supportActionBar?.hide()

        binding.btnToRegister.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

        binding.btnToForgot.setOnClickListener {
            val intent = Intent(this, ForgotActivity::class.java)
            startActivity(intent)
        }

        binding.btnLogin.setOnClickListener {
            login()
        }
    }

    private fun login() {
        val username = binding.tvUsername.text.toString().trim()
        val password = binding.tvPwd.text.toString().trim()

        auth.signInWithEmailAndPassword(username, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Log.d(TAG, "signInWithEmail:success")
                    Toast.makeText(this, "Sign in success", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, DashboardActivity::class.java)
                    startActivity(intent)
                } else {
                    Toast.makeText(
                        baseContext, "${Log.w(TAG, "signInWithEmail:failure", task.exception)}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
    }
}