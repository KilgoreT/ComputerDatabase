package com.example.computerdatabase.ui.computerList

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.example.computerdatabase.entity.Computer
import com.example.computerdatabase.interactor.ComputerDbInteractorInterface
import com.example.computerdatabase.ui.base.BasePagedKeyDataSource
import javax.inject.Inject

class ComputerListDataSourceFactory @Inject constructor(
    val interactorInterface: ComputerDbInteractorInterface
) : DataSource.Factory<Int, Computer>() {

    var searchText = ""
    val sourceLiveData = MutableLiveData<BasePagedKeyDataSource>()

    override fun create(): DataSource<Int, Computer> {
        val dataSource = ComputerListDataSource(interactorInterface)
        dataSource.searchText = searchText
        sourceLiveData.postValue(dataSource)
        return dataSource
    }

}