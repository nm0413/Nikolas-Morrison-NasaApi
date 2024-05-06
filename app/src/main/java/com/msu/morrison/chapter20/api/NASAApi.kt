package com.msu.morrison.chapter20.api

import retrofit2.http.GET
import retrofit2.http.Query

interface NASAApi {
    @GET("planetary/apod")
    suspend fun fetchPhotos(
        @Query("api_key") apiKey: String,
        @Query("date") date: String? = null,
        @Query("start_date") startDate: String? = null,
        @Query("end_date") endDate: String? = null,
        @Query("count") count: Int? = null,
        @Query("thumbs") thumbs: Boolean? = null
    ): List<GalleryItem>
}
