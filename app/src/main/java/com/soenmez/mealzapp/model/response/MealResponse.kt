package com.soenmez.mealzapp.model.response

import com.squareup.moshi.Json

/**
 *
 * @author olcay.soenmez
 * @since 19.08.2023
 */
internal data class MealResponse(
    @Json(name = "idCategory") val id: String,
    @Json(name = "strCategory") val categoryName: String,
    @Json(name = "strCategoryThumb") val categoryThumb: String,
    @Json(name = "strCategoryDescription") val categoryDescription: String
)
