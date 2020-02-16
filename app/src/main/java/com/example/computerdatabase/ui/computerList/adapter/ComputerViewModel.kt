package com.example.computerdatabase.ui.computerList.adapter

import androidx.lifecycle.MutableLiveData
import com.example.computerdatabase.entity.Computer
import com.example.computerdatabase.ui.base.BaseViewModel

class ComputerViewModel: BaseViewModel() {

    private val name = MutableLiveData<String>()
    private val company = MutableLiveData<String>()
    private val isCompanyVisibility = MutableLiveData<Boolean>()

    fun bind(computer: Computer) {
        name.value = computer.name
        company.value = computer.company?.name ?: ""
        isCompanyVisibility.value = computer.company != null
    }

    fun getName(): MutableLiveData<String> {
        return name
    }

    fun getCompany(): MutableLiveData<String> {
        return company
    }

    fun getCompanyVisibility(): MutableLiveData<Boolean> {
        return isCompanyVisibility
    }
}