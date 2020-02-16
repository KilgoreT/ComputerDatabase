package com.example.computerdatabase.ui.computerList

import android.text.Editable
import android.text.TextWatcher
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.computerdatabase.App
import com.example.computerdatabase.entity.Computer
import com.example.computerdatabase.ui.base.BasePagedKeyDataSource
import com.example.computerdatabase.ui.base.BaseViewModel
import com.example.computerdatabase.ui.base.InteractorResult
import io.reactivex.subjects.PublishSubject
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class ComputerListViewModel: BaseViewModel() {

    @Inject
    lateinit var dataSourceFactory: ComputerListDataSourceFactory

    @Inject
    lateinit var config: PagedList.Config

    val publishSubject = PublishSubject.create<String>()
    var pagedListLiveData: LiveData<PagedList<Computer>>

    private val executor = Executors.newSingleThreadExecutor()
    private var searchText = ""

    val watcher = object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
            if (searchText != s.toString()) {
                searchText = s.toString()
                publishSubject.onNext(s.toString())
            }
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        }

    }

    init {
        App.instance.getAppComponent()
            .inject(this)

        pagedListLiveData = LivePagedListBuilder<Int, Computer>(dataSourceFactory, config)
            .setFetchExecutor(executor)
            .build()

        disposable.add(
            publishSubject
                .debounce(600, TimeUnit.MILLISECONDS)
                .subscribe { searchText -> loadData(searchText) }
        )
    }

    fun loadData(searchText: String) {
        dataSourceFactory.searchText = searchText
        dataSourceFactory.sourceLiveData.value?.invalidate()
    }

    fun getState(): LiveData<InteractorResult> = Transformations
        .switchMap<BasePagedKeyDataSource, InteractorResult>(
            dataSourceFactory.sourceLiveData,
            BasePagedKeyDataSource::state
        )
}

