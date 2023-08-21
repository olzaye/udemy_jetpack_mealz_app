package com.soenmez.mealzapp.repository

import com.soenmez.mealzapp.model.response.MealResponse
import com.soenmez.mealzapp.model.response.MealsCategoriesResponse
import com.soenmez.mealzapp.network.NetworkResource
import kotlinx.coroutines.flow.Flow

/**
 *
 * @author olcay.soenmez
 * @since 18.08.2023
 */
internal interface MealRepository {

    suspend fun getMealCategory(): Flow<NetworkResource<MealsCategoriesResponse>>

    fun getMeal(id: String): MealResponse?
}