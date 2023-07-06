package com.sunul.sunul.presentation.spot

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import com.runnect.runnect.presentation.state.UiState
import com.sunul.sunul.R
import com.sunul.sunul.binding.BindingFragment
import com.sunul.sunul.databinding.FragmentSpotBinding
import com.sunul.sunul.presentation.MainViewModel


class SpotFragment : BindingFragment<FragmentSpotBinding>(R.layout.fragment_spot) {
    private val viewModel: MainViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.vm = viewModel
        binding.lifecycleOwner = this.viewLifecycleOwner
    }

    private fun addObservers(){
        viewModel.uiState.observe(viewLifecycleOwner){
            when (it) {
                UiState.Empty -> binding.indeterminateBar.isVisible = false //visible 옵션으로 처리하는 게 맞나
                UiState.Loading -> {
                    with(binding){
                        svSpot.isVisible = false
                        tvWaitingAlert.isVisible = true
                        indeterminateBar.isVisible = true
                    }

                }
                UiState.Success -> {
                    with(binding){
                        svSpot.isVisible = true
                        tvWaitingAlert.isVisible = false
                        indeterminateBar.isVisible = false

                    }
                }
                UiState.Failure -> {
                    with(binding){
                        svSpot.isVisible = false
                        tvWaitingAlert.isVisible = true
                        tvWaitingAlert.text = "알 수 없는 오류가 발생했습니다!"
                        indeterminateBar.isVisible = false
                    }
                }
            }
        }
    }


}