package com.fortoszone.phinconattendance.ui.auth.register

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.fortoszone.phinconattendance.R

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        supportActionBar?.hide()
    }
}