package com.sunul.sunul.presentation.personal

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import com.runnect.runnect.presentation.state.UiState
import com.sunul.sunul.R
import com.sunul.sunul.binding.BindingFragment
import com.sunul.sunul.databinding.FragmentPersonalBinding
import com.sunul.sunul.presentation.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class PersonalFragment : BindingFragment<FragmentPersonalBinding>(R.layout.fragment_personal) {
    private val viewModel: MainViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.vm = viewModel
        binding.lifecycleOwner = this.viewLifecycleOwner
        addObservers()
        viewModel.postQnAs()

    }

    private fun addObservers() {
        viewModel.uiState.observe(viewLifecycleOwner) {
            when (it) {
                UiState.Empty -> binding.indeterminateBar.isVisible = false //visible 옵션으로 처리하는 게 맞나
                UiState.Loading -> {
                    with(binding){
                        tvWaitingAlert.isVisible = true
                        indeterminateBar.isVisible = true
                        scrollView.isVisible = false
                        testBtn.isVisible = false
                    }

                }
                UiState.Success -> {
                    with(binding){
                        tvWaitingAlert.isVisible = false
                        indeterminateBar.isVisible = false
                        scrollView.isVisible = true
                        testBtn.isVisible = true
                        val personalInfo = viewModel.mbtiHashMap[viewModel.personalMbti.value.toString()]
                        imageView3.setImageResource(personalInfo!!.image)
                        textView2.text = getString(personalInfo.title)
                        textView4.text = getString(personalInfo.desc)
                    }
                }
                UiState.Failure -> {
                    with(binding){
                        tvWaitingAlert.isVisible = true
                        tvWaitingAlert.text = "알 수 없는 오류가 발생했습니다!"
                        binding.indeterminateBar.isVisible = false
                    }
                }
            }
        }
    }
}