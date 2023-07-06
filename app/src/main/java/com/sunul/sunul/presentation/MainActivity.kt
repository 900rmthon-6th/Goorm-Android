package com.sunul.sunul.presentation

import android.os.Bundle
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import com.sunul.sunul.R
import com.sunul.sunul.binding.BindingActivity
import com.sunul.sunul.databinding.ActivityMainBinding
import com.sunul.sunul.presentation.onboarding.OnBoardingFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BindingActivity<ActivityMainBinding>(R.layout.activity_main) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
    }
    private fun initView(){
        changeFragment(ONBOARDING)
    }
    private fun changeFragment(tag: String) {
        when (tag) {
            ONBOARDING -> supportFragmentManager.commit {
                replace<OnBoardingFragment>(R.id.fl_main)
            }
        }
    }
    companion object{
        const val ONBOARDING = "onBoarding"
    }
}