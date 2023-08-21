package com.soenmez.mealzapp.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


/**
 *
 * @author olcay.soenmez
 * @since 19.08.2023
 */

internal var moshi: Moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory()).build()

internal val client: OkHttpClient = OkHttpClient().newBuilder()
    .addInterceptor(HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }
    ).build()

internal var retrofit = Retrofit.Builder()
    .baseUrl("https://www.themealdb.com/api/json/v1/1/")
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .client(client)
    .build()

internal fun provideMealCategoryApi(): MealCategoryApi =
    retrofit.create(MealCategoryApi::class.java)