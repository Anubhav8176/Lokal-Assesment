package com.anucodes.lokal_assessment.networking.core

import com.anucodes.lokal_assessment.networking.model.JobData
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    @GET("jobs")
    suspend fun getJobs(
        @Query("page") page: Int
    ):JobData
}