package com.example.bicyclecrud.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bicyclecrud.data.BicycleRepository
import com.example.bicyclecrud.model.Bicycle
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: BicycleRepository
) : ViewModel() {

    val bicycles: StateFlow<List<Bicycle>> = repository
        .bicycles
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    fun insert(bicycle: Bicycle) = viewModelScope.launch {
        repository.insert(bicycle)
    }

    fun update(bicycle: Bicycle) = viewModelScope.launch {
        repository.update(bicycle)
    }

    fun deleteById(id: Int) = viewModelScope.launch {
        repository.deleteById(id)
    }
}