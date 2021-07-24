package com.dayToDay.ratingDayToDayProducts.mock

import com.dayToDay.ratingDayToDayProducts.Product
import com.dayToDay.ratingDayToDayProducts.Ratings
import org.springframework.stereotype.Repository

@Repository
class MockProductDataSource : ProductDataSource {
        val products = mutableMapOf<Int,Product> (
            1 to Product("Lab Test Order", mutableListOf(Ratings(1, "Rahul", 3.4),
            Ratings(12, "Anjali", 3.4),
            Ratings(13, "Sam", 4.0),
            Ratings(19, "Tiya", 4.0),
            Ratings(15, "Tonny", 2.0))),
            2 to (Product("Mindfulness", mutableListOf(Ratings(12, "Anjali", 3.4)))),
            3 to Product("CarePlan", mutableListOf(Ratings(13, "Sam", 3.4))),
            4 to Product("WellBeing", mutableListOf(Ratings(14, "John", 3.4))),
            5 to Product(" COVID-19", mutableListOf(Ratings(15, "Tonny", 3.4))),
            )

    override fun retrieveProducts(): Map<Int,Product> {
        return products
    }

    override fun addRating(productId: Int, rating: Ratings): Product? {
       if(!products.containsKey(productId)){
           throw NoSuchElementException("The product with given $productId does not exists")
       }
        products[productId]?.rating?.add(rating)
        return products[productId]

    }

    override fun updateRating(productId: Int, customerId :Int, newRating: Double):  Product? {
        val ratings = products[productId]?.rating
       // var isCustomerPresent = false
        if (ratings != null) {
            for( r  in  ratings) {
                if(r.customerId == customerId){
                    r.rating = newRating
                   // isCustomerPresent = true
                    return products[productId]
                }
            }
          /*  if(!isCustomerPresent) {
                throw NoSuchElementException("The customer with  $customerId is not present")
            }*/
        }
        throw NoSuchElementException("The product has been rated")
    }

    override fun fetchRating(productId: Int): Pair<Double, Map<Double,Int>> {
        if(!products.containsKey(productId)){
            throw NoSuchElementException("The product with given $productId does not exists")
        }
        var countOfRating : MutableMap<Double,Int> = mutableMapOf()

        var ratings = products[productId]?.rating
        var sum = 0.0
        var count  = 0
        ratings?.forEach { it->
            if(countOfRating.containsKey(it.rating)) {
                countOfRating[it.rating] = countOfRating.getValue(it.rating)+1;
            }
            else{
                countOfRating[it.rating] = 1
            }

            sum += it.rating
            count++
        }
        return Pair(sum/count, countOfRating)

    }
}