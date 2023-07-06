package com.sunul.sunul.data.repository

import com.sunul.sunul.data.model.request.RequestTest
import com.sunul.sunul.data.model.response.ResponseTest
import com.sunul.sunul.data.source.remote.TestDataSource
import com.sunul.sunul.domain.TestRepository

class TestRepositoryImpl(private val testDataSource: TestDataSource):TestRepository {
    override suspend fun getTest(requestTest: RequestTest): ResponseTest {
        return testDataSource.getTest(requestTest)
    }

}