package com.sunul.sunul.presentation

import android.os.Bundle
import androidx.activity.viewModels
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import com.sunul.sunul.R
import com.sunul.sunul.binding.BindingActivity
import com.sunul.sunul.databinding.ActivityMainBinding
import com.sunul.sunul.presentation.onboarding.OnBoardingFragment
import com.sunul.sunul.presentation.personal.PersonalFragment
import com.sunul.sunul.presentation.spot.SpotDetailFragment
import com.sunul.sunul.presentation.spot.SpotFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BindingActivity<ActivityMainBinding>(R.layout.activity_main) {
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.vm = viewModel
        binding.lifecycleOwner = this

        initView()
    }
    private fun initView(){
        viewModel.divideMbti()
        changeFragment(ONBOARDING)
    }
    fun changeFragment(tag: String) {
        when (tag) {
            ONBOARDING -> supportFragmentManager.commit {
                replace<OnBoardingFragment>(R.id.fl_main)
            }
            PERSONAL->supportFragmentManager.commit {
                replace<PersonalFragment>(R.id.fl_main)
            }
            SPOT -> supportFragmentManager.commit {
                replace<SpotFragment>(R.id.fl_main)
            }
            DETAIL -> supportFragmentManager.commit {
                replace<SpotDetailFragment>(R.id.fl_main)
            }
        }
    }
    companion object{
        const val ONBOARDING = "onBoarding"
        const val PERSONAL = "personal"
        const val SPOT = "spot"
        const val DETAIL = "detail"
    }
}