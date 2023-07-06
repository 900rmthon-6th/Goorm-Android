package com.sunul.sunul.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.runnect.runnect.presentation.state.UiState
import com.sunul.sunul.R
import com.sunul.sunul.data.dto.MbtiInfoDTO
import com.sunul.sunul.data.dto.OnBoardingDTO
import com.sunul.sunul.data.dto.SpotDTO
import com.sunul.sunul.data.model.request.RequestPersonalResult
import com.sunul.sunul.domain.OnBoardingRepository
import com.sunul.sunul.domain.SpotRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val onBoardingRepository: OnBoardingRepository,
    private val spotRepository: SpotRepository,
) : ViewModel() {
    var curIndex = MutableLiveData<Int>(1)
    val maxSize = MutableLiveData<Int>(8)
    val uiState = MutableLiveData<UiState>()
    var answersToPost = mutableListOf<String>()
    var qnaList = mutableListOf<OnBoardingDTO>()
    var index = 0
    var personalMbti = MutableLiveData<String>("")
    var personalReason = MutableLiveData<String>("")
    val mbtiHashMap = HashMap<String, MbtiInfoDTO>()
    var spots = mutableListOf<SpotDTO>()
    val spotImages = listOf<Int>(
        R.drawable.image1,
        R.drawable.image2,
        R.drawable.image3,
        R.drawable.image4,
        R.drawable.image5,
        R.drawable.image6,
        R.drawable.image7,
        R.drawable.image8,
        R.drawable.image9,
        R.drawable.image10,
        R.drawable.image11
    )
    var imageIndex = 0
    private val qnaIcons = mutableListOf<Int>(
        R.drawable.intp,
        R.drawable.infj,
        R.drawable.isfp,
        R.drawable.estj,
        R.drawable.entp,
        R.drawable.entj,
        R.drawable.esfj,
        R.drawable.estp
    )

    fun getQnAs() {
        Timber.d("getQnAs 호출")
        viewModelScope.launch {
            runCatching {
                uiState.value = UiState.Loading
                onBoardingRepository.getQnA()
            }.onSuccess { it ->
                it.data.forEach {
                    qnaList.add(OnBoardingDTO(it.que, it.ans, qnaIcons[index]))
                    index += 1
                }
                maxSize.value = it.data.size
                index = 0
                uiState.value = UiState.Success
            }.onFailure {
                Timber.d("${it.cause} ${it.message} ${it.localizedMessage} 실패!")
                uiState.value = UiState.Failure
            }

        }
    }

    fun postQnAs() {
        viewModelScope.launch {
            runCatching {
                uiState.value = UiState.Loading
                onBoardingRepository.postQnA(RequestPersonalResult(answersToPost, "2532"))

            }.onSuccess {

                Timber.d(it.mbti)
                Timber.d(it.reason)
                personalMbti.value = it.mbti
                personalReason.value = it.reason
                uiState.value = UiState.Success
            }.onFailure {
                Timber.d(it.message)
                Timber.d(it.localizedMessage)
                Timber.d(it.cause)
                uiState.value = UiState.Failure
            }
        }

    }

    fun getSpots() {
        viewModelScope.launch {
            runCatching {
                Timber.d("getSpots 호출")
                uiState.value = UiState.Loading
                spotRepository.getSpots(personalMbti.value.toString())
            }.onSuccess {
                it.data.forEach { spotData ->
                    spots.add(SpotDTO(spotImages[imageIndex], spotData))
                    imageIndex += 1
                }
                Timber.d("getSpots $spots")
                uiState.value = UiState.Success
            }.onFailure {
                Timber.d("${it.cause}")
                Timber.d("${it.message}")
                uiState.value = UiState.Failure
            }
        }
    }

    fun divideMbti() {
        mbtiHashMap["ENFJ"] = MbtiInfoDTO(R.drawable.enfj, R.string.enfj_title, R.string.enfj_desc)
        mbtiHashMap["ENFP"] = MbtiInfoDTO(R.drawable.enfp, R.string.enfp_title, R.string.enfp_desc)
        mbtiHashMap["ENTJ"] = MbtiInfoDTO(R.drawable.entj, R.string.entj_title, R.string.entj_desc)
        mbtiHashMap["ENTP"] = MbtiInfoDTO(R.drawable.entp, R.string.entp_title, R.string.entp_desc)
        mbtiHashMap["ESFJ"] = MbtiInfoDTO(R.drawable.esfj, R.string.esfj_title, R.string.esfj_desc)
        mbtiHashMap["ESFP"] = MbtiInfoDTO(R.drawable.esfp, R.string.esfp_title, R.string.esfp_desc)
        mbtiHashMap["ESTJ"] = MbtiInfoDTO(R.drawable.estj, R.string.estj_title, R.string.estj_desc)
        mbtiHashMap["ESTP"] = MbtiInfoDTO(R.drawable.estp, R.string.estp_title, R.string.estp_desc)
        mbtiHashMap["INFJ"] = MbtiInfoDTO(R.drawable.infj, R.string.infj_title, R.string.infj_desc)
        mbtiHashMap["INFP"] = MbtiInfoDTO(R.drawable.infp, R.string.infp_title, R.string.infp_desc)
        mbtiHashMap["INTJ"] = MbtiInfoDTO(R.drawable.intj, R.string.intj_title, R.string.infj_desc)
        mbtiHashMap["INTP"] = MbtiInfoDTO(R.drawable.intp, R.string.intp_title, R.string.intp_desc)
        mbtiHashMap["ISFJ"] = MbtiInfoDTO(R.drawable.isfj, R.string.isfj_title, R.string.isfj_desc)
        mbtiHashMap["ISFP"] = MbtiInfoDTO(R.drawable.isfp, R.string.isfp_title, R.string.isfp_desc)
        mbtiHashMap["ISTJ"] = MbtiInfoDTO(R.drawable.istj, R.string.istj_title, R.string.istj_desc)
        mbtiHashMap["ISTP"] = MbtiInfoDTO(R.drawable.istp, R.string.istp_title, R.string.istp_desc)
    }

    fun increaseIndex() {
        if (curIndex.value!! < ((maxSize.value as Int))) {
            curIndex.value = curIndex.value?.plus(1)
        }
    }

    //post호출 후, result 프래그먼트에서 로딩 뷰
}