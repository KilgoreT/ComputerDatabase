package com.example.computerdatabase.repository

import com.example.computerdatabase.datasource.room.dao.ComputerDetailDao
import com.example.computerdatabase.entity.Computer
import com.example.computerdatabase.entity.ComputerDetail
import com.example.computerdatabase.entity.ComputerList
import io.reactivex.Single
import javax.inject.Inject

class DatabaseRepository @Inject constructor(
    private val computerDetailDao: ComputerDetailDao
) : DatabaseRepositoryInterface {

    override fun getComputers(page: Int, filter: String): Single<ComputerList> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getComputerDetail(id: Int): Single<ComputerDetail> {
        return computerDetailDao.getComputerDetail(id)
    }

    override fun saveComputerDetail(computerDetail: ComputerDetail) {
        computerDetailDao.insertComputerDetail(computerDetail)
    }

    override fun getSimilar(id: Int): Single<List<Computer>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}