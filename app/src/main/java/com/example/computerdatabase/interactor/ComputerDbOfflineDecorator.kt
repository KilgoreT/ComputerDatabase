package com.example.computerdatabase.interactor

import android.util.Log
import com.example.computerdatabase.entity.ComputerDetail
import com.example.computerdatabase.entity.ComputerList
import io.reactivex.Single

class ComputerDbOfflineDecorator(
    private val subject: ComputerDbInteractorInterface
): ComputerDbInteractorInterface {

    override fun getComputers(): Single<ComputerList> {
        Log.d("###", javaClass.simpleName + ":" + "getComputers()")
        return subject.getComputers()
    }

    override fun getComputerDetail(id: Int): Single<ComputerDetail> {
        Log.d("###", javaClass.simpleName + ":" + "getComputerDetail")
        return subject.getComputerDetail(id)
    }
}