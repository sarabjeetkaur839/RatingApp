package com.dayToDay.ratingDayToDayProducts.service

import com.dayToDay.ratingDayToDayProducts.Product
import com.dayToDay.ratingDayToDayProducts.Ratings
import com.dayToDay.ratingDayToDayProducts.mock.ProductDataSource
import org.springframework.stereotype.Service


@Service
class ProductService(private val productDataSource: ProductDataSource) {
    fun getProduct(): Map<Int, Product> {
       return productDataSource.retrieveProducts()
    }

    fun addRating(productId: Int, ratings: Ratings): Product? {
        return productDataSource.addRating(productId, ratings)
    }

    fun updateRating(productId: Int, customerId: Int, rating: Double): Product? {
        return productDataSource.updateRating(productId,customerId,rating)
    }

    fun fetchRating(productId: Int): Pair<Double, Map<Double, Int>> {
        return productDataSource.fetchRating(productId)

    }
}