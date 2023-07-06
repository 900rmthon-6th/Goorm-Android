package com.sunul.sunul.presentation.onboarding

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.viewpager2.widget.ViewPager2
import com.runnect.runnect.presentation.state.UiState
import com.sunul.sunul.R
import com.sunul.sunul.binding.BindingFragment
import com.sunul.sunul.data.dto.OnBoardingDTO
import com.sunul.sunul.databinding.FragmentOnBoardingBinding
import com.sunul.sunul.presentation.MainActivity
import com.sunul.sunul.presentation.MainViewModel
import com.sunul.sunul.util.callback.OnBoardingItemClick
import com.sunul.sunul.util.extension.showToast
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class OnBoardingFragment :
    BindingFragment<FragmentOnBoardingBinding>(R.layout.fragment_on_boarding),OnBoardingItemClick {
    private val viewModel: MainViewModel by activityViewModels()
    private lateinit var onBoardingAdapter: OnBoardingAdapter
    private var currentPosition = 0

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.vm = viewModel
        binding.lifecycleOwner = this.viewLifecycleOwner

        addObservers()
        viewModel.getQnAs()
    }
    private fun setQnA(vp:ViewPager2,qnaList:MutableList<OnBoardingDTO>){
        setOnBoardingAdapter(qnaList)
        setOnBoardingViewPager(vp)
    }

    private fun setOnBoardingAdapter(qnaList:MutableList<OnBoardingDTO>){
        onBoardingAdapter = OnBoardingAdapter(requireContext(),this)
        onBoardingAdapter.submitList(qnaList)
        binding.vpOnboarding.adapter = onBoardingAdapter
    }
    private fun setOnBoardingViewPager(vp:ViewPager2){
        vp.orientation = ViewPager2.ORIENTATION_HORIZONTAL
        vp.currentItem = 0
        vp.isUserInputEnabled = false
    }

    private fun addObservers() {
        with(viewModel) {
            uiState.observe(viewLifecycleOwner) {
                with(binding) {
                    if (uiState.value == UiState.Loading) {

                    } else if (uiState.value == UiState.Success) {
                        setQnA(binding.vpOnboarding,viewModel.qnaList)
                    }
                }
            }
            curIndex.observe(viewLifecycleOwner) {
                val progress = "${it}/8"
                binding.tvProgressOnboarding.text = progress
                binding.indicatorOnboarding.progress = it*13
            }
        }
    }

    override fun selectItem(answer: String,isLast:Boolean) {
        if (isLast){
            viewModel.answersToPost.add(answer)
            (requireActivity() as MainActivity).changeFragment("personal")

        }
        else{
            binding.vpOnboarding.currentItem = ++currentPosition
            viewModel.increaseIndex()
            viewModel.answersToPost.add(answer)
        }
    }
}