package com.sunul.sunul.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.sunul.sunul.R
import com.sunul.sunul.binding.BindingActivity
import com.sunul.sunul.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BindingActivity<ActivityMainBinding>(R.layout.activity_main) {
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.vm = viewModel
        binding.lifecycleOwner = this
        addObserver()
        viewModel.getTest()
    }
    private fun addObserver(){
        viewModel.testData.observe(this){

        }
    }
}