package com.soenmez.mealzapp.model.response

import com.squareup.moshi.Json

/**
 *
 * @author olcay.soenmez
 * @since 19.08.2023
 */
internal data class MealsCategoriesResponse(
    @Json(name = "categories") val categories: List<MealResponse>
)
