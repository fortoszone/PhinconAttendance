package com.fortoszone.phinconattendance.ui.onboarding

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.fortoszone.phinconattendance.R
import com.fortoszone.phinconattendance.adapter.OnboardingViewPagerAdapter
import com.fortoszone.phinconattendance.databinding.ActivityOnboardingBinding
import com.fortoszone.phinconattendance.ui.auth.login.LoginActivity
import com.fortoszone.phinconattendance.ui.auth.register.RegisterActivity
import me.relex.circleindicator.CircleIndicator3

class OnboardingActivity : AppCompatActivity() {
    private lateinit var binding: ActivityOnboardingBinding
    private lateinit var viewPager: ViewPager2

    private var titleList = mutableListOf<String>()
    private var descList = mutableListOf<String>()
    private var imageList = mutableListOf<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOnboardingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        postToList()

        viewPager = binding.viewPager
        viewPager.adapter = OnboardingViewPagerAdapter(titleList, descList, imageList, this)

        val indicator: CircleIndicator3 = binding.pageIndicator
        indicator.setViewPager(viewPager)

        viewPager.offscreenPageLimit = 1

        binding.btnLogin.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        binding.btnSignUp.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }

    private fun addToList(title: String, desc: String, image: Int) {
        titleList.add(title)
        descList.add(desc)
        imageList.add(image)
    }

    private fun postToList() {
        addToList(
            getString(R.string.digital_abs),
            getString(R.string.guna_menceg),
            R.drawable.icons_time_attendance_clocks
        )
        addToList(
            getString(R.string.attendance_sys),
            getString(R.string.pengelolaan_kar),
            R.drawable.icons_employee_self_service
        )
        addToList(getString(R.string.masker), getString(R.string.guna_menceg), R.drawable.mask)
    }
}