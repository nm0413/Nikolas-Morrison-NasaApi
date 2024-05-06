package com.msu.morrison.chapter20

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.msu.morrison.chapter20.api.GalleryItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class PhotoGalleryViewModel(private val photoRepository: PhotoRepository) : ViewModel() {

    private val _galleryItems: MutableStateFlow<List<GalleryItem>> = MutableStateFlow(emptyList())
    val galleryItems: StateFlow<List<GalleryItem>>
        get() = _galleryItems.asStateFlow()

    fun loadPhotosForDate(apiKey: String, date: String) {
        viewModelScope.launch {
            try {
                val items = photoRepository.fetchPhotosForDate(apiKey, date)
                _galleryItems.value = items
            } catch (ex: Exception) {
                Log.e("PhotoGalleryViewModel", "Failed to fetch gallery items for date", ex)
            }
        }
    }

    fun loadPhotosForDateRange(apiKey: String, startDate: String, endDate: String) {
        viewModelScope.launch {
            try {
                val items = photoRepository.fetchPhotosForDateRange(apiKey, startDate, endDate)
                _galleryItems.value = items
            } catch (ex: Exception) {
                Log.e("PhotoGalleryViewModel", "Failed to fetch gallery items for date range", ex)
            }
        }
    }

    fun loadRandomPhotos(apiKey: String, count: Int) {
        viewModelScope.launch {
            try {
                val items = photoRepository.fetchRandomPhotos(apiKey, count)
                _galleryItems.value = items
            } catch (ex: Exception) {
                Log.e("PhotoGalleryViewModel", "Failed to fetch random gallery items", ex)
            }
        }
    }

    fun loadPhotosWithThumbnails(apiKey: String, date: String, thumbs: Boolean) {
        viewModelScope.launch {
            try {
                val items = photoRepository.fetchThumbnails(apiKey, date, thumbs)
                _galleryItems.value = items
            } catch (ex: Exception) {
                Log.e("PhotoGalleryViewModel", "Failed to fetch gallery items with thumbnails", ex)
            }
        }
    }
}
