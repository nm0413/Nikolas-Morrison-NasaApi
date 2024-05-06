package com.msu.morrison.chapter20

import com.msu.morrison.chapter20.api.GalleryItem
import com.msu.morrison.chapter20.api.NASAApi
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class PhotoRepository {
    private val nasaApi: NASAApi
    private val apiKey = "TDLcDwO277Se3tc9ZRJFIFCWyTvK8sdwH9fsGBW7"  // Replace YOUR_ACTUAL_API_KEY with your NASA API key

    init {
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://api.nasa.gov/")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()

        nasaApi = retrofit.create(NASAApi::class.java)
    }

    suspend fun fetchPhotosForDate(date: String): List<GalleryItem> =
        nasaApi.fetchPhotos(apiKey, date = date)

    suspend fun fetchPhotosForDateRange(startDate: String, endDate: String): List<GalleryItem> =
        nasaApi.fetchPhotos(apiKey, startDate = startDate, endDate = endDate)

    suspend fun fetchRandomPhotos(count: Int): List<GalleryItem> =
        nasaApi.fetchPhotos(apiKey, count = count)

    suspend fun fetchThumbnails(date: String, thumbs: Boolean): List<GalleryItem> =
        nasaApi.fetchPhotos(apiKey, date = date, thumbs = thumbs)
}
