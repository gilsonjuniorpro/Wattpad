package com.wattpad.ca.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wattpad.ca.controller.WattpadController
import com.wattpad.ca.core.WattpadApplication
import com.wattpad.ca.model.Story
import com.wattpad.ca.model.WattpadHttp
import com.wattpad.ca.model.WattpadResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class WattpadListViewModel() : ViewModel() {
    private val _state = MutableLiveData<State>()
    val state: LiveData<State>
        get() = _state

    fun loadStories() {
        if (_state.value != null) return

        //search()
        searchRetrofit()
    }

    //if you prefer to use OkHttp
    fun search() {
        viewModelScope.launch {
            _state.value = State.Loading
            //delay(5000)

            val result = withContext(Dispatchers.IO) {
                WattpadHttp.searchStories()
            }
            if (result?.stories == null) {
                _state.value = State.Error(Exception("Error loading stories"), false)
            } else {
                _state.value = State.Loaded(result.stories)
            }
        }
    }

    //if you prefer to use retrofit
    fun searchRetrofit() {
        val fields = "stories(id,title,cover,user)"
        val call = WattpadController.getStoriesCall(0, 10, fields, "new")

        call?.enqueue(object : Callback<WattpadResponse> {
            override fun onFailure(call: Call<WattpadResponse>?, t: Throwable?) {
                WattpadApplication.showError(t.toString())
            }

            override fun onResponse(
                call: Call<WattpadResponse>?,
                response: Response<WattpadResponse>?
            ) {
                val result = response?.body()

                if (result?.stories == null) {
                    _state.value = State.Error(Exception("Error loading stories"), false)
                } else {
                    _state.value = State.Loaded(result.stories)
                }
            }
        })
    }

    sealed class State {
        object Loading : State()
        data class Loaded(val items: List<Story>) : State()
        data class Error(val e: Throwable, var hasConsumed: Boolean) : State()
    }
}