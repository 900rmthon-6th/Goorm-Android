package com.sunul.sunul.presentation.onboarding

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.runnect.runnect.presentation.state.UiState
import com.sunul.sunul.R
import com.sunul.sunul.data.dto.OnBoardingDTO
import com.sunul.sunul.domain.OnBoardingRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class OnBoardingVIewModel @Inject constructor(private val onBoardingRepository: OnBoardingRepository) :
    ViewModel() {
    var curIndex = MutableLiveData<Int>(1)
    val maxSize = MutableLiveData<Int>(8)
    val uiState = MutableLiveData<UiState>()
    var answersToPost = mutableListOf<String>()
    var qnaList = mutableListOf<OnBoardingDTO>()
    var index = 0
    private val qnaIcons
    = mutableListOf<Int>(R.drawable.intp,R.drawable.infj,R.drawable.isfp,R.drawable.estj
        ,R.drawable.entp,R.drawable.entj,R.drawable.esfj,R.drawable.estp)
    fun getQnAs() {
        Timber.d("getQnAs 호출")
        viewModelScope.launch {
            runCatching {
                uiState.value = UiState.Loading
                onBoardingRepository.getQnA()
            }.onSuccess { it ->
                Timber.d("${it} 성공!")
                it.data.forEach {
                    qnaList.add(OnBoardingDTO(it.que,it.ans,qnaIcons[index]))
                    index+=1
                }
                maxSize.value = it.data.size
                index=0
                uiState.value = UiState.Success
            }.onFailure {
                Timber.d("${it.cause} ${it.message} ${it.localizedMessage} 실패!")
                uiState.value = UiState.Failure
            }

        }
    }

    fun increaseIndex() {
        if (curIndex.value!! < ((maxSize.value as Int))) {
            curIndex.value = curIndex.value?.plus(1)
        }
    }
}