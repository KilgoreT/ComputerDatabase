package com.example.computerdatabase.repository

import com.example.computerdatabase.entity.ComputerDetail

interface DatabaseRepositoryInterface: NetworkRepositoryInterface {
    fun saveComputerDetail(computerDetail: ComputerDetail)
}