package com.wattpad.ca.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wattpad.ca.model.Story
import com.wattpad.ca.repository.WattpadRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DetailStoryViewModel(
    private val repository: WattpadRepository
) : ViewModel() {
    private val _isFavorite = MutableLiveData<Boolean>()

    val isFavorite: LiveData<Boolean> = _isFavorite

    fun onCreate(story: Story) {
        viewModelScope.launch {
            _isFavorite.value = repository.isFavorite(story.id)
        }
    }

    fun saveToFavorites(story: Story) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                repository.save(story)
            }
            _isFavorite.value = repository.isFavorite(story.id)
        }
    }

    fun removeFromFavorites(story: Story) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                repository.delete(story)
            }
            _isFavorite.value = repository.isFavorite(story.id)
        }
    }
}