package com.soenmez.mealzapp.ui.details

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.soenmez.mealzapp.model.response.MealResponse
import com.soenmez.mealzapp.repository.MealRepository

/**
 *
 * @author olcay.soenmez
 * @since 19.08.2023
 */
internal class MealDetailsViewModel(
    saveStateHandle: SavedStateHandle,
    repo: MealRepository
) : ViewModel() {

    val mealState = mutableStateOf<MealResponse?>(null)

    init {
        val id = saveStateHandle.get<String>("meal_category_id").orEmpty()
        repo.getMeal(id)?.let {
            mealState.value = it
        }
    }
}