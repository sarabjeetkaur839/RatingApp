package com.dayToDay.ratingDayToDayProducts.service

import com.dayToDay.ratingDayToDayProducts.mock.MockProductDataSource
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class ProductServiceTest {
    private val mockProductDataSource = MockProductDataSource()
    @Test
    fun `provide a map of Product`(){
        val products = mockProductDataSource.retrieveProducts()
        assertThat(products).isNotEmpty
    }
}