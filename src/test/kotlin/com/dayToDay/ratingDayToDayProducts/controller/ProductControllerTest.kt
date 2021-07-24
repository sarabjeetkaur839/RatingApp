package com.dayToDay.ratingDayToDayProducts.controller

import com.dayToDay.ratingDayToDayProducts.Product
import com.dayToDay.ratingDayToDayProducts.Ratings
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.client.match.MockRestRequestMatchers.content
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.patch
import org.springframework.test.web.servlet.post

@SpringBootTest
@AutoConfigureMockMvc
internal class ProductControllerTest @Autowired constructor(val mockMvc: MockMvc, val objectMapper: ObjectMapper){

    private val baseurl = "/home/products"

    @Test
    fun `getAllProducts`() {
        mockMvc.get(baseurl)
                .andExpect {
                    status { isOk() }
                    content { contentType(MediaType.APPLICATION_JSON) }
                }
    }

    @Test
    fun `add Rating of Product`(){
        val rating = Ratings(20,"Jimmy", 4.5)
        val response = mockMvc.post("$baseurl/2") {
            contentType = MediaType.APPLICATION_JSON
            content = objectMapper.writeValueAsString(rating)
        }
        response.andDo { print() }
        .andExpect { status { isCreated() } }

    }

    @Test
    fun `update Rating of Product`(){
        val updateProduct =  Product("Lab Test Order", mutableListOf(Ratings(1, "Rahul", 3.4),
                Ratings(12, "Anjali", 3.4),
                Ratings(13, "Sam", 2.2),
                Ratings(19, "Tiya", 4.0),
                Ratings(15, "Tonny", 2.0)))
        val response = mockMvc.patch("$baseurl/1/13/2.2") {
            contentType = MediaType.APPLICATION_JSON
            content = objectMapper.writeValueAsString(updateProduct)
        }
        response.andDo { print() }
                .andExpect { status { isOk() } }
    }

    @Test
    fun `Fetch Rating of Product`(){
        mockMvc.get("$baseurl/1") {}
        .andExpect { status { isOk() } }
    }
}