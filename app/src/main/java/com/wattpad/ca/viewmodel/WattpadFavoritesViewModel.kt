package com.wattpad.ca.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.wattpad.ca.repository.WattpadRepository

class WattpadFavoritesViewModel(
    repository: WattpadRepository
) : ViewModel() {
    val favoriteStories = repository.allFavorites().asLiveData()
}