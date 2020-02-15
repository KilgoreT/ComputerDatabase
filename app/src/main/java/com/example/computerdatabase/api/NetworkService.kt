package com.example.computerdatabase.api

import com.example.computerdatabase.entity.Computer
import com.example.computerdatabase.entity.ComputerDetail
import com.example.computerdatabase.entity.ComputerList
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface NetworkService {

    @GET("rest/computers")
    fun getComputerList(
        @Query("p") page: Int,
        @Query("f") filter: String
    ): Single<ComputerList>

    @GET("rest/computers/{id}")
    fun getComputerDetail(
        @Path("id") id: Int
    ): Single<ComputerDetail>

    @GET("rest/computers/{id}/similar")
    fun getSimilar(
        @Path("id") id: Int
    ): Single<List<Computer>>

}