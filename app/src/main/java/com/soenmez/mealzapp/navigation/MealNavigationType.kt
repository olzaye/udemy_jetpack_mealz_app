package com.soenmez.mealzapp.navigation

internal sealed class MealNavigationType {
    internal data class AnimateMealDetailByClick(val mealId: String) : MealNavigationType()
    internal data class AnimateMealDetailByScrolling(val mealId: String) : MealNavigationType()
}