package com.example.computerdatabase.interactor

import com.example.computerdatabase.AbstractUnitTest
import com.example.computerdatabase.entity.ComputerList
import com.example.computerdatabase.repository.NetworkRepositoryInterface
import io.reactivex.Single
import org.junit.Test
import org.mockito.ArgumentMatchers.anyInt
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mockito.times
import org.mockito.Mockito.verify

class ComputerDbInteractorTest : AbstractUnitTest() {

    private lateinit var interactor: ComputerDbInteractor
    private val repository = mock<NetworkRepositoryInterface>()

    override fun prepareTest() {
        interactor = ComputerDbInteractor(repository)
    }

    @Test
    fun `verify that request getComputerList() is called`() {
        whenever(repository.getComputers(anyInt(), anyString()))
            .thenReturn(Single.just(ComputerList(items, 0, 0, 1)))

        interactor.getComputers(anyInt(), anyString())
        verify(repository, times(1))
            .getComputers(anyInt(), anyString())
    }

    @Test
    fun `verify that request getComputerDetail() is called`() {
        whenever(repository.getComputerDetail(anyInt()))
            .thenReturn(Single.just(computerDetail1))

        interactor.getComputerDetail(anyInt())
        verify(repository, times(1)).getComputerDetail(anyInt())
    }

}