package com.soenmez.mealzapp.repository

import com.soenmez.mealzapp.model.response.MealResponse
import com.soenmez.mealzapp.model.response.MealsCategoriesResponse
import com.soenmez.mealzapp.network.MealCategoryApi
import com.soenmez.mealzapp.network.NetworkResource
import com.soenmez.mealzapp.network.provideMealCategoryApi
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

/**
 *
 * @author olcay.soenmez
 * @since 18.08.2023
 */
internal class MealRepositoryImpl(
    private val mealCategoryApi: MealCategoryApi,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) :
    MealRepository {

    private var cacheResponse: MealsCategoriesResponse? = null

    override suspend fun getMealCategory(): Flow<NetworkResource<MealsCategoriesResponse>> = flow {
        if (cacheResponse != null) {
            emit((NetworkResource.Success(cacheResponse!!)))
            return@flow
        }

        emit(NetworkResource.Loading())

        val response = mealCategoryApi.getMealCategories()
        val data = response.body()

        if (response.isSuccessful && data != null) {
            cacheResponse = data
            emit(NetworkResource.Success(data))
        } else {
            emit(NetworkResource.Error(response.errorBody()?.string().orEmpty()))
        }
    }.flowOn(dispatcher)

    override fun getMeal(id: String): MealResponse? {
        return cacheResponse?.categories?.find { it.id == id }
    }
}