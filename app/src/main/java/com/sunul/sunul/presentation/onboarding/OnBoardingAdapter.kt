package com.sunul.sunul.presentation.onboarding

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sunul.sunul.data.dto.OnBoardingDTO
import com.sunul.sunul.databinding.ItemOnBoardingBinding
import com.sunul.sunul.util.callback.OnBoardingItemClick
import timber.log.Timber

class OnBoardingAdapter(
    private val context: Context,
    private val onAnswerClick: OnBoardingItemClick,
) :
    ListAdapter<OnBoardingDTO, OnBoardingAdapter.OnBoardingViewHolder>(OnBoardingDiff) {
    lateinit var binding: ItemOnBoardingBinding
    private var isLastAnswer = false
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OnBoardingViewHolder {
        binding = ItemOnBoardingBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return OnBoardingViewHolder(binding)
    }

    override fun onBindViewHolder(holder: OnBoardingViewHolder, position: Int) {
        holder.onBind(getItem(position), position)
    }

    override fun getItemCount(): Int {
        return super.getItemCount()
    }

    inner class OnBoardingViewHolder(private val binding: ItemOnBoardingBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(item: OnBoardingDTO, position: Int) {
            binding.tvQuestionItemOnboarding.text = item.question
            binding.ivIconItemOnboarding.setImageDrawable(
                AppCompatResources.getDrawable(
                    context,
                    item.icon
                )
            )
            when (item.answers?.size) {
                0 -> {
                    with(binding) {
                        cvItemOnboarding1.visibility = View.GONE
                        cvItemOnboarding2.visibility = View.GONE
                        cvItemOnboarding3.visibility = View.GONE
                        cvItemOnboarding4.visibility = View.GONE
                        cvItemOnboarding5.visibility = View.VISIBLE
                        etAnswerItemOnboarding.visibility = View.VISIBLE
                        tvAnswerItemOnboarding5.visibility = View.VISIBLE
                        if (position == 7) {
                            tvAnswerItemOnboardingFinish.visibility = View.VISIBLE
                            cvItemOnboardingFinish.visibility = View.VISIBLE
                        }
                        cvItemOnboarding5.setOnClickListener {
                            if (etAnswerItemOnboarding.text.isNullOrEmpty()) {
                                binding.tvWarningItemOnboarding.visibility = View.VISIBLE
                            } else {
                                binding.tvWarningItemOnboarding.visibility = View.GONE
                                onAnswerClick.selectItem(
                                    etAnswerItemOnboarding.text.toString(),
                                    isLastAnswer
                                )
                                Timber.d("isLastAnswer $isLastAnswer")
                                isLastAnswer = true
                            }
                        }
                    }
                }

                1 -> {
                    with(binding) {
                        cvItemOnboarding1.visibility = View.VISIBLE
                        cvItemOnboarding2.visibility = View.GONE
                        cvItemOnboarding3.visibility = View.GONE
                        cvItemOnboarding4.visibility = View.GONE
                        cvItemOnboarding5.visibility = View.GONE
                        tvAnswerItemOnboarding1.text = item.answers[0]
                        cvItemOnboarding1.setOnClickListener {
                            onAnswerClick.selectItem(item.answers[0], isLastAnswer)
                        }
                        etAnswerItemOnboarding.visibility = View.GONE
                        tvAnswerItemOnboarding5.visibility = View.GONE
                    }
                }

                2 -> {
                    with(binding) {
                        cvItemOnboarding1.visibility = View.VISIBLE
                        cvItemOnboarding2.visibility = View.VISIBLE
                        cvItemOnboarding3.visibility = View.GONE
                        cvItemOnboarding4.visibility = View.GONE
                        cvItemOnboarding5.visibility = View.GONE
                        tvAnswerItemOnboarding1.text = item.answers[0]
                        tvAnswerItemOnboarding2.text = item.answers[1]
                        cvItemOnboarding1.setOnClickListener {
                            onAnswerClick.selectItem(item.answers[0], isLastAnswer)
                        }
                        cvItemOnboarding2.setOnClickListener {
                            onAnswerClick.selectItem(item.answers[1], isLastAnswer)
                        }
                        etAnswerItemOnboarding.visibility = View.GONE
                        tvAnswerItemOnboarding5.visibility = View.GONE
                    }
                }

                3 -> {
                    with(binding) {
                        cvItemOnboarding1.visibility = View.VISIBLE
                        cvItemOnboarding2.visibility = View.VISIBLE
                        cvItemOnboarding3.visibility = View.VISIBLE
                        cvItemOnboarding4.visibility = View.GONE
                        cvItemOnboarding5.visibility = View.GONE
                        tvAnswerItemOnboarding1.text = item.answers[0]
                        tvAnswerItemOnboarding2.text = item.answers[1]
                        tvAnswerItemOnboarding3.text = item.answers[2]
                        cvItemOnboarding1.setOnClickListener {
                            onAnswerClick.selectItem(item.answers[0], isLastAnswer)
                        }
                        cvItemOnboarding2.setOnClickListener {
                            onAnswerClick.selectItem(item.answers[1], isLastAnswer)
                        }
                        cvItemOnboarding3.setOnClickListener {
                            onAnswerClick.selectItem(item.answers[2], isLastAnswer)
                        }
                        etAnswerItemOnboarding.visibility = View.GONE
                        tvAnswerItemOnboarding5.visibility = View.GONE
                    }
                }

                4 -> {
                    with(binding) {
                        cvItemOnboarding1.visibility = View.VISIBLE
                        cvItemOnboarding2.visibility = View.VISIBLE
                        cvItemOnboarding3.visibility = View.VISIBLE
                        cvItemOnboarding4.visibility = View.VISIBLE
                        cvItemOnboarding5.visibility = View.GONE
                        tvAnswerItemOnboarding1.text = item.answers[0]
                        tvAnswerItemOnboarding2.text = item.answers[1]
                        tvAnswerItemOnboarding3.text = item.answers[2]
                        tvAnswerItemOnboarding4.text = item.answers[3]
                        cvItemOnboarding1.setOnClickListener {
                            onAnswerClick.selectItem(item.answers[0], isLastAnswer)
                        }
                        cvItemOnboarding2.setOnClickListener {
                            onAnswerClick.selectItem(item.answers[1], isLastAnswer)
                        }
                        cvItemOnboarding3.setOnClickListener {
                            onAnswerClick.selectItem(item.answers[2], isLastAnswer)
                        }
                        cvItemOnboarding4.setOnClickListener {
                            onAnswerClick.selectItem(item.answers[2], isLastAnswer)
                        }
                        etAnswerItemOnboarding.visibility = View.GONE
                        tvAnswerItemOnboarding5.visibility = View.GONE
                    }
                }
            }
        }
    }
}


object OnBoardingDiff : DiffUtil.ItemCallback<OnBoardingDTO>() {
    override fun areItemsTheSame(oldItem: OnBoardingDTO, newItem: OnBoardingDTO): Boolean {
        return oldItem.question == newItem.question
    }

    override fun areContentsTheSame(oldItem: OnBoardingDTO, newItem: OnBoardingDTO): Boolean {
        return oldItem == newItem
    }
}