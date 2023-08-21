package com.soenmez.mealzapp.network

/**
 *
 * @author olcay.soenmez
 * @since 19.08.2023
 */

sealed class NetworkResource<T> {
    class Success<T>(val data: T) : NetworkResource<T>()
    class Error<T>(val message: String) : NetworkResource<T>()
    class Loading<T> : NetworkResource<T>()
}