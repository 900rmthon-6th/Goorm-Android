package com.sunul.sunul.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sunul.sunul.data.model.request.RequestTest
import com.sunul.sunul.domain.TestRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val testRepository: TestRepository) : ViewModel() {
    val testData = MutableLiveData<String>("")


    fun getTest() {
        viewModelScope.launch {
            runCatching {
                testRepository.getTest(RequestTest("성산일출봉"))
            }.onSuccess {
                Timber.d(it.message)
                testData.value = it.message
            }.onFailure {
                Timber.d("실패")
                Timber.d("${it.message}")
                Timber.d("${it.cause}")
                Timber.d(it.localizedMessage)
            }
        }
    }
}