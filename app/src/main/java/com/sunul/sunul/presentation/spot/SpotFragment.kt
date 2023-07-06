package com.sunul.sunul.presentation.spot

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import com.runnect.runnect.presentation.state.UiState
import com.sunul.sunul.R
import com.sunul.sunul.binding.BindingFragment
import com.sunul.sunul.data.dto.SpotDTO
import com.sunul.sunul.databinding.FragmentSpotBinding
import com.sunul.sunul.presentation.MainActivity
import com.sunul.sunul.presentation.MainViewModel
import com.sunul.sunul.util.callback.OnSpotItemClick
import timber.log.Timber


class SpotFragment : BindingFragment<FragmentSpotBinding>(R.layout.fragment_spot),OnSpotItemClick {
    private val viewModel: MainViewModel by activityViewModels()
    private lateinit var spotAdapter: SpotAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.vm = viewModel
        binding.lifecycleOwner = this.viewLifecycleOwner
        Timber.d("SpotFragment 진입")

        viewModel.getSpotsChat()
        addObservers()
        setSpotAdapter(viewModel.spots)

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
    private fun setSpotAdapter(spotList:MutableList<SpotDTO>){
        spotAdapter = SpotAdapter(this).apply{
            submitList(spotList)
        }
        binding.rvSpot.setHasFixedSize(true)
        binding.rvSpot.adapter = spotAdapter
    }

    override fun selectItem() {
        (requireActivity() as MainActivity).changeFragment("detail")
    }


}