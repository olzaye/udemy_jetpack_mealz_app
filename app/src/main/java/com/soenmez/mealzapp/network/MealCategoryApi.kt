package com.soenmez.mealzapp.network

import com.soenmez.mealzapp.model.response.MealsCategoriesResponse
import retrofit2.Response
import retrofit2.http.GET

/**
 *
 * @author olcay.soenmez
 * @since 19.08.2023
 */
internal interface MealCategoryApi {

    @GET("categories.php")
    suspend fun getMealCategories(): Response<MealsCategoriesResponse>
}