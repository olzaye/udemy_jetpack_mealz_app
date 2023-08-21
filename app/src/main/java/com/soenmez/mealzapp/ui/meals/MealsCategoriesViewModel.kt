package com.soenmez.mealzapp.ui.meals

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.soenmez.mealzapp.model.response.MealResponse
import com.soenmez.mealzapp.network.NetworkResource
import com.soenmez.mealzapp.repository.MealRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import timber.log.Timber

/**
 *
 * @author olcay.soenmez
 * @since 18.08.2023
 */
internal class MealsCategoriesViewModel(private val repo: MealRepository) : ViewModel() {

    private val _mealsCategoriesFlow = MutableStateFlow(emptyList<MealResponse>())
    val mealsCategoriesFlow = _mealsCategoriesFlow.asStateFlow()

    init {
        viewModelScope.launch {
            repo.getMealCategory().collect {
                when (it) {
                    is NetworkResource.Error -> Timber.d("response error")
                    is NetworkResource.Loading -> Timber.d("response loading")
                    is NetworkResource.Success -> {
                        _mealsCategoriesFlow.emit(it.data.categories)
                        Timber.d("response success ${it.data.categories[0]}")
                    }
                }
            }
        }
    }
}