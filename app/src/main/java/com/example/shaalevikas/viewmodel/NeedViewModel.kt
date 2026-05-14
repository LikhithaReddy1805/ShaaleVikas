package com.example.shaalevikas.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.shaalevikas.model.Need
import com.example.shaalevikas.repository.NeedRepository

class NeedViewModel : ViewModel() {

    private val repository = NeedRepository()

    var needs = mutableStateListOf<Need>()
        private set

    init {

        repository.getNeeds { result ->

            needs.clear()

            needs.addAll(result)
        }
    }
}