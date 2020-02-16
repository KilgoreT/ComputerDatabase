package com.example.computerdatabase.ui.base

import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.example.computerdatabase.entity.Computer

abstract class BasePagedKeyDataSource: PageKeyedDataSource<Int, Computer>() {

    var searchText: String = ""
    var state: MutableLiveData<InteractorResult> = MutableLiveData()


    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, Computer>
    ) {
        onLoadInitial(params, callback)
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Computer>) {
        onLoadAfter(params, callback)
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Computer>) {
        onLoadBefore(params, callback)
    }

    abstract fun onLoadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, Computer>
    )
    abstract fun onLoadAfter(
        params: LoadParams<Int>,
        callback: LoadCallback<Int, Computer>
    )
    abstract fun onLoadBefore(
        params: LoadParams<Int>,
        callback: LoadCallback<Int, Computer>
    )

    companion object {
        const val INIT_PAGE = 0
    }
}

sealed class InteractorResult
object LoadingResult : InteractorResult()
object SuccessEmptyResult : InteractorResult()
object SuccessNonEmptyResult : InteractorResult()
data class ErrorResult(val message: String) : InteractorResult()