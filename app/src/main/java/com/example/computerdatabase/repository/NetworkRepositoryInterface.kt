package com.example.computerdatabase.repository

import com.example.computerdatabase.entity.Computer
import com.example.computerdatabase.entity.ComputerDetail
import com.example.computerdatabase.entity.ComputerList
import io.reactivex.Single

interface NetworkRepositoryInterface {

    fun getComputers(page: Int, filter: String): Single<ComputerList>
    fun getComputerDetail(id: Int): Single<ComputerDetail>
    fun getSimilar(id: Int): Single<List<Computer>>

}