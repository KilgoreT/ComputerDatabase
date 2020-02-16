package com.example.computerdatabase.repository

import com.example.computerdatabase.AbstractUnitTest
import com.example.computerdatabase.datasource.api.NetworkService
import com.example.computerdatabase.entity.ComputerList
import io.reactivex.Single
import org.junit.Test
import org.mockito.ArgumentMatchers.anyInt
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mockito.times
import org.mockito.Mockito.verify


class NetworkRepositoryTest: AbstractUnitTest() {

    private lateinit var repository: NetworkRepository
    private val api = mock<NetworkService>()

    override fun prepareTest() {
        repository = NetworkRepository(api)
    }

    @Test
    fun `verify that request getComputerList() is called`() {
        whenever(api.getComputerList(anyInt(), anyString()))
            .thenReturn(Single.just(ComputerList(items, 0, 0, 1)))

        repository.getComputers(anyInt(), anyString())
        verify(api, times(1)).getComputerList(anyInt(), anyString())
    }

    @Test
    fun `verify that request getComputerDetail() is called`() {
        whenever(api.getComputerDetail(anyInt()))
            .thenReturn(Single.just(computerDetail1))

        repository.getComputerDetail(anyInt())
        verify(api, times(1)).getComputerDetail(anyInt())
    }

    @Test
    fun `verify that request getSimilar() is called`() {
        whenever(api.getSimilar(anyInt()))
            .thenReturn(Single.just(items))

        repository.getSimilar(anyInt())
        verify(api, times(1)).getSimilar(anyInt())
    }
}