package com.wattpad.ca.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.wattpad.ca.repository.WattpadRepository
import java.lang.IllegalArgumentException

class WattpadViewModelFactory(val repository: WattpadRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(WattpadFavoritesViewModel::class.java)){
            return WattpadFavoritesViewModel(repository) as T
        } else if(modelClass.isAssignableFrom(DetailStoryViewModel::class.java)){
            return DetailStoryViewModel(repository) as T
        }
        throw IllegalArgumentException("Unkown ViewModel Class")
    }
}