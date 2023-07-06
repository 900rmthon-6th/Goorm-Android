package com.sunul.sunul.presentation.personal

import android.content.ContentValues
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
    }
    private fun addObservers(){
        viewModel.uiState.observe(viewLifecycleOwner){
            when (it) {
                UiState.Empty -> binding.indeterminateBar.isVisible = false //visible 옵션으로 처리하는 게 맞나
                UiState.Loading -> binding.indeterminateBar.isVisible = true
                UiState.Success -> {
                    binding.indeterminateBar.isVisible = false

                }
                UiState.Failure -> {
                    binding.indeterminateBar.isVisible = false
                }
            }
        }
    }
}