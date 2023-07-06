package com.sunul.sunul.data.source.remote

import com.sunul.sunul.data.api.TestService
import com.sunul.sunul.data.model.request.RequestTest

class TestDataSource(private val testService: TestService) {
    suspend fun getTest(requestTest: RequestTest) = testService.getTest(requestTest)
}