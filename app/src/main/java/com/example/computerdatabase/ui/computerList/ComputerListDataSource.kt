package com.example.computerdatabase.ui.computerList

import android.annotation.SuppressLint
import com.example.computerdatabase.entity.Computer
import com.example.computerdatabase.interactor.ComputerDbInteractorInterface
import com.example.computerdatabase.ui.base.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@SuppressLint("CheckResult")
class ComputerListDataSource @Inject constructor(
    private val interactor: ComputerDbInteractorInterface
) : BasePagedKeyDataSource() {

    override fun onLoadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, Computer>
    ) {
        state.postValue(LoadingResult)
        interactor.getComputers(INIT_PAGE, searchText)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    when (it.items.size > 0) {
                        true -> {
                            callback.onResult(it.items, null, INIT_PAGE + 1)
                            state.postValue(SuccessNonEmptyResult)
                        }
                        false -> {
                            state.postValue(SuccessEmptyResult)
                        }
                    }
                }, {
                    state.postValue(ErrorResult(it.message ?: "Unknown error"))
                }
            )
    }

    override fun onLoadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Computer>) {
        interactor.getComputers(params.key, searchText)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                callback.onResult(it.items, params.key + 1)
            }, {
                state.postValue(ErrorResult(it.message ?: "Unknown error"))
            })
    }

    override fun onLoadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Computer>) {
    }
}