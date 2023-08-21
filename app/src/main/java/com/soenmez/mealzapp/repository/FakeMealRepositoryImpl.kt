package com.soenmez.mealzapp.repository

import com.soenmez.mealzapp.model.response.MealResponse
import com.soenmez.mealzapp.model.response.MealsCategoriesResponse
import com.soenmez.mealzapp.network.NetworkResource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 *
 * @author olcay.soenmez
 * @since 19.08.2023
 */
internal class FakeMealRepositoryImpl : MealRepository {
    override suspend fun getMealCategory(): Flow<NetworkResource<MealsCategoriesResponse>> = flow {
        emit(
            NetworkResource.Success(
                MealsCategoriesResponse(
                    listOf(
                        MealResponse(
                            id = "",
                            categoryName = "Sample",
                            categoryThumb = "https://www.themealdb.com/images/category/beef.png",
                            categoryDescription = "Sample descriotion"
                        )
                    )
                )
            )
        )
    }

    override fun getMeal(id: String): MealResponse {
        return MealResponse(
            id = "",
            categoryName = "Sample",
            categoryThumb = "https://www.themealdb.com/images/category/beef.png",
            categoryDescription = "Sample descriotion"
        )
    }
}