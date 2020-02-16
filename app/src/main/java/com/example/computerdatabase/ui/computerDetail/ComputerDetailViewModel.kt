package com.example.computerdatabase.ui.computerDetail

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import com.example.computerdatabase.App
import com.example.computerdatabase.entity.Computer
import com.example.computerdatabase.entity.ComputerDetail
import com.example.computerdatabase.interactor.ComputerDbInteractorInterface
import com.example.computerdatabase.ui.base.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@SuppressLint("CheckResult")
class ComputerDetailViewModel: BaseViewModel() {

    val detailLiveData = MutableLiveData<ComputerDetail>()
    val similarLiveData = MutableLiveData<List<Computer>>()

    @Inject
    lateinit var interactor: ComputerDbInteractorInterface

    init {
        App.instance
            .getAppComponent()
            .inject(this)
    }

    fun loadData(id: Int) {
        interactor
            .getComputerDetail(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                setupDetail(it)
            }, {

            })
    }

    fun loadSimilar(id: Int) {
        interactor.getSimilar(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                setupSimilar(it)
            }, {

            })
    }

    private fun setupSimilar(data: List<Computer>) {
        similarLiveData.postValue(data)
    }

    private fun setupDetail(data: ComputerDetail) {
        detailLiveData.postValue(data)
    }
}