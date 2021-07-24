package com.dayToDay.ratingDayToDayProducts.mock

import com.dayToDay.ratingDayToDayProducts.Product
import com.dayToDay.ratingDayToDayProducts.Ratings

interface ProductDataSource {
    fun retrieveProducts():Map<Int,Product>
    fun addRating(productId: Int, rating: Ratings): Product?
    fun updateRating(productId: Int, customerId :Int, newRating: Double):  Product?
    fun fetchRating(productId: Int): Pair<Double, Map<Double,Int>>
}