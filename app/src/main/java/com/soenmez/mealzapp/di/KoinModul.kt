package com.soenmez.mealzapp.di

import com.soenmez.mealzapp.network.provideMealCategoryApi
import com.soenmez.mealzapp.repository.MealRepository
import com.soenmez.mealzapp.repository.MealRepositoryImpl
import com.soenmez.mealzapp.ui.details.MealDetailsViewModel
import com.soenmez.mealzapp.ui.meals.MealsCategoriesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 *
 * @author olcay.soenmez
 * @since 19.08.2023
 */
internal val appModule = module {

    factory { provideMealCategoryApi() }
    single<MealRepository> { MealRepositoryImpl(mealCategoryApi = get()) }
    viewModel { MealsCategoriesViewModel(repo = get()) }
    viewModel { MealDetailsViewModel(repo = get(), saveStateHandle = get()) }

}