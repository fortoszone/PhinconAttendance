package com.fortoszone.phinconattendance.ui.auth.register

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.fortoszone.phinconattendance.databinding.ActivityRegisterBinding
import com.fortoszone.phinconattendance.ui.dashboard.DashboardActivity
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    private val auth = Firebase.auth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        FirebaseApp.initializeApp(this)

        binding.btnSignUp.setOnClickListener {
            createAccount()
        }
    }

    private fun createAccount() {
        if (isPwdIdentical()) {
            val username = binding.tvUsername.text.toString().trim()
            val fullName = binding.tvFullName.text.toString().trim()
            val pwd = binding.tvPwd.text.toString().trim()
            val repeatPwd = binding.tvPwd.text.toString().trim()

            if (username.isEmpty()) {
                binding.tvUsername.error = "Username required"
                binding.tvUsername.requestFocus()
                return
            }

            if (fullName.isEmpty()) {
                binding.tvFullName.error = "Full name required"
                binding.tvFullName.requestFocus()
                return
            }

            if (pwd.isEmpty()) {
                binding.tvPwd.error = "Password required"
                binding.tvPwd.requestFocus()
                return
            }

            if (repeatPwd.isEmpty()) {
                binding.tvPwdRepeat.error = "Password confirmation required"
                binding.tvPwdRepeat.requestFocus()
                return
            }

            auth.createUserWithEmailAndPassword(username, pwd).addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "createUserWithEmail:success")
                    Toast.makeText(
                        baseContext, "Account Created.",
                        Toast.LENGTH_SHORT
                    ).show()
                    val user = auth.currentUser
                    startActivity(Intent(this, DashboardActivity::class.java))
                    //updateUI(user)
                } else {
                    // If sign in fails, display a message to the user.
                    Toast.makeText(
                        baseContext, "${Log.w(TAG, "createUserWithEmail:failure", task.exception)}",
                        Toast.LENGTH_SHORT
                    ).show()
                    //updateUI(null)
                }
            }
        } else {
            Toast.makeText(this, "Password does not match", Toast.LENGTH_SHORT).show()
            return
        }
    }

    private fun isPwdIdentical(): Boolean {
        var identical = false
        if (binding.tvPwd.text.toString().trim() != binding.tvPwdRepeat.text.toString().trim()) {
            identical = true
        }

        return identical
    }
}