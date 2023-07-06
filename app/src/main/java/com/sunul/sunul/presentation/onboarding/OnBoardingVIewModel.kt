package com.sunul.sunul.presentation.onboarding

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.runnect.runnect.presentation.state.UiState
import com.sunul.sunul.domain.OnBoardingRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnBoardingVIewModel @Inject constructor(private val onBoardingRepository: OnBoardingRepository) :
    ViewModel() {

    var curIndex = MutableLiveData<Int>(1)
    val maxSize = MutableLiveData<Int>(8)
    val uiState = MutableLiveData<UiState>()
    var question:String =""
    var answers = listOf<String>()
    var answersToPost = mutableListOf<String>()

    fun getQnA(qid:String) {
        viewModelScope.launch {
            runCatching {
                uiState.value = UiState.Loading
                onBoardingRepository.getQnA(qid)
            }.onSuccess {
                question = it.que
                answers = it.ans
                uiState.value = UiState.Success


            }.onFailure {

                uiState.value = UiState.Failure
            }

        }
    }

    fun increasingIndex() {
        if (curIndex.value!! < ((maxSize.value as Int) - 1)) {
            curIndex.value = curIndex.value?.plus(1)
        }
    }

    fun decreasingIndex() {
        if (curIndex.value!! > 0) {
            curIndex.value = curIndex.value?.minus(1)
        }
    }
}