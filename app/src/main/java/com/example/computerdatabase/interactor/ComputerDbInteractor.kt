package com.example.computerdatabase.interactor

import com.example.computerdatabase.entity.ComputerDetail
import com.example.computerdatabase.entity.ComputerList
import com.example.computerdatabase.repository.NetworkRepositoryInterface
import io.reactivex.Single
import javax.inject.Inject

class ComputerDbInteractor @Inject constructor(
    private val repository: NetworkRepositoryInterface
): ComputerDbInteractorInterface {

    override fun getComputers(page: Int, filter: String): Single<ComputerList> {
        return repository.getComputers(page, filter)
    }

    override fun getComputerDetail(id: Int): Single<ComputerDetail> {
        return repository.getComputerDetail(id)
    }
}