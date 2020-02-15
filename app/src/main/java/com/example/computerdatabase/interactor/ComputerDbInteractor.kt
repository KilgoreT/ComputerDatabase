package com.example.computerdatabase.interactor

import android.util.Log
import com.example.computerdatabase.entity.ComputerDetail
import com.example.computerdatabase.entity.ComputerList
import com.example.computerdatabase.repository.NetworkRepositoryInterface
import io.reactivex.Single
import javax.inject.Inject

class ComputerDbInteractor @Inject constructor(
    private val repository: NetworkRepositoryInterface
): ComputerDbInteractorInterface {

    override fun getComputers(): Single<ComputerList> {
//        Log.d("###", javaClass.simpleName + ":" + "getComputers()")
        return repository.getComputers()
    }

    override fun getComputerDetail(id: Int): Single<ComputerDetail> {
//        Log.d("###", javaClass.simpleName + ":" + "getComputerDetail()")
        return repository.getComputerDetail(id)
    }
}