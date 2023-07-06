package com.sunul.sunul.domain

import com.sunul.sunul.data.model.request.RequestTest
import com.sunul.sunul.data.model.response.ResponseTest

interface TestRepository {
    suspend fun getTest(requestTest: RequestTest):ResponseTest
}