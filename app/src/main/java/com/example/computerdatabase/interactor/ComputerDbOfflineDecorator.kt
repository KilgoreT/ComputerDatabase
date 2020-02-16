package com.example.computerdatabase.interactor

import com.example.computerdatabase.entity.Computer
import com.example.computerdatabase.entity.ComputerDetail
import com.example.computerdatabase.entity.ComputerList
import com.example.computerdatabase.repository.DatabaseRepositoryInterface
import io.reactivex.Single

class ComputerDbOfflineDecorator(
    private val repository: DatabaseRepositoryInterface,
    private val subject: ComputerDbInteractorInterface
): ComputerDbInteractorInterface {

    override fun getComputers(page: Int, filter: String): Single<ComputerList> {
        return subject.getComputers(page, filter)
    }

    override fun getComputerDetail(id: Int): Single<ComputerDetail> {
        return repository.getComputerDetail(id)
            .onErrorResumeNext {
                subject.getComputerDetail(id)
                    .doOnSuccess {
                        repository.saveComputerDetail(it)
                    }
            }
    }

    override fun getSimilar(id: Int): Single<List<Computer>> {
        return subject.getSimilar(id)
    }
}