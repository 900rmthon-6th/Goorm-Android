package com.sunul.sunul.presentation.onboarding

import android.animation.LayoutTransition
import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.runnect.runnect.presentation.state.UiState
import com.sunul.sunul.R
import com.sunul.sunul.binding.BindingFragment
import com.sunul.sunul.databinding.FragmentOnBoardingBinding
import com.sunul.sunul.util.extension.showToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OnBoardingFragment :
    BindingFragment<FragmentOnBoardingBinding>(R.layout.fragment_on_boarding) {
    private val viewModel: OnBoardingVIewModel by viewModels()
    private lateinit var layoutTransition: LayoutTransition
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.vm = viewModel
        binding.lifecycleOwner = this.viewLifecycleOwner
        layoutTransition = (binding.constTopOnboarding as ViewGroup).layoutTransition
        initView()
        addListeners()
        setNextAnimation()
        addObservers()

    }

    private fun initView() {
        viewModel.getQnA("q${viewModel.curIndex.value}")
    }

    private fun addObservers() {
        with(viewModel) {
            uiState.observe(viewLifecycleOwner) {
                with(binding) {
                    if (uiState.value == UiState.Loading) {
                        binding.groupQna.visibility = View.GONE
                    } else if (uiState.value == UiState.Success) {
                        tvQuestionOnboarding.text = viewModel.question
                        btnAnswer1Onboarding.text = viewModel.answers[0]
                        btnAnswer2Onboarding.text = viewModel.answers[1]
                        binding.groupQna.visibility = View.VISIBLE
                    }

                }
            }
            curIndex.observe(viewLifecycleOwner) {
            }

        }
    }

    private fun setNextAnimation() {
        layoutTransition.apply {

            val fadeAnimator = ObjectAnimator.ofFloat(view, "alpha", 0.1f, 1f).apply {
                duration = 250
            }
            val appearingAnimator = ObjectAnimator.ofFloat(view, "translationX", 1000f, 0f)

            val disappearingAnimator = ObjectAnimator.ofFloat(view, "translationX", 0f, -1000f)
            this.setAnimator(LayoutTransition.APPEARING, appearingAnimator)
            this.setAnimator(LayoutTransition.APPEARING, appearingAnimator)
            this.setAnimator(LayoutTransition.DISAPPEARING, disappearingAnimator)
            this.setStartDelay(LayoutTransition.APPEARING, 1000L)
            this.setDuration(LayoutTransition.APPEARING, 1000L)
        }
    }

    private fun setBeforeAnimation() {
        layoutTransition.apply {
            val appearingAnimator = ObjectAnimator.ofFloat(view, "translationX", -1000f, 0f)
            val disappearingAnimator = ObjectAnimator.ofFloat(view, "translationX", 0f, 1000f)
            this.setAnimator(LayoutTransition.APPEARING, appearingAnimator)
            this.setAnimator(LayoutTransition.DISAPPEARING, disappearingAnimator)
            this.setStartDelay(LayoutTransition.APPEARING, 2000L)
            this.setDuration(LayoutTransition.APPEARING, 1000L)
        }
    }

    private fun addListeners() {
        binding.btnAnswer1Onboarding.setOnClickListener {
            loadNextQnA()
        }
        binding.btnAnswer2Onboarding.setOnClickListener {
            loadNextQnA()
        }
        binding.btnBackQuestion.setOnClickListener {
            if (viewModel.curIndex.value == 1) {
                layoutTransition.apply {
                    disableTransitionType(LayoutTransition.DISAPPEARING)
                }
            } else {
                loadBeforeQnA()
                viewModel.getQnA("q${viewModel.curIndex.value}")
            }

        }
    }

    private fun loadNextQnA() {
        if (viewModel.curIndex.value == viewModel.maxSize.value?.minus(1)) {
            requireContext().showToast("API호출 후 상세 페이지로 이동")
        } else {
            viewModel.increasingIndex()
            setNextAnimation()
            viewModel.getQnA("q${viewModel.curIndex.value}")
        }
    }

    private fun loadBeforeQnA() {
        viewModel.decreasingIndex()
        setBeforeAnimation()
        viewModel.getQnA("q${viewModel.curIndex.value}")
    }
}