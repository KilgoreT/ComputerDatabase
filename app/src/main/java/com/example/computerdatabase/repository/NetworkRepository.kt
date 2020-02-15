package com.example.computerdatabase.repository

import com.example.computerdatabase.api.NetworkService
import com.example.computerdatabase.entity.Computer
import com.example.computerdatabase.entity.ComputerDetail
import com.example.computerdatabase.entity.ComputerList
import io.reactivex.Single
import javax.inject.Inject

class NetworkRepository @Inject constructor(
    private val api: NetworkService
): NetworkRepositoryInterface  {
    override fun getComputers(): Single<ComputerList> {
        return api.getComputerList(0, "")
    }

    override fun getComputerDetail(id: Int): Single<ComputerDetail> {
        return api.getComputerDetail(id)
    }

    override fun getSimilar(id: Int): Single<List<Computer>> {
        return api.getSimilar(id)
    }
}