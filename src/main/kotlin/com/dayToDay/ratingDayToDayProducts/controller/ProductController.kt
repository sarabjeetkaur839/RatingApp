package com.dayToDay.ratingDayToDayProducts.controller

import com.dayToDay.ratingDayToDayProducts.Product
import com.dayToDay.ratingDayToDayProducts.Ratings
import com.dayToDay.ratingDayToDayProducts.service.ProductService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/home/products")
class ProductController (private val productService : ProductService){

    @ExceptionHandler(NoSuchElementException :: class)
    fun notFound(e: NoSuchElementException) :  ResponseEntity<String> {
        return ResponseEntity(e.message, HttpStatus.NOT_FOUND)
    }

    @GetMapping
    fun getProduct() : Map<Int, Product> {
        return productService.getProduct()
    }

    @PostMapping("/{productId}")
    @ResponseStatus(HttpStatus.CREATED)
    fun addRating(@PathVariable productId: Int, @RequestBody ratings: Ratings): Product? {
        return productService.addRating(productId, ratings)
    }
    @PatchMapping("/{productId}/{customerId}/{rating}")
    fun  updateRating(@PathVariable productId: Int,@PathVariable customerId: Int, @PathVariable rating: Double) : Product?{
        return productService.updateRating(productId, customerId, rating)
    }

    @GetMapping("/{productId}")
    fun fetchRating(@PathVariable productId: Int) : Pair<Double, Map<Double,Int>> {
        return productService.fetchRating(productId)
    }

}