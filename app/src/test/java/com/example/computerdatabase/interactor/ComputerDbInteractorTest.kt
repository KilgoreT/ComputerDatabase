package com.example.computerdatabase.interactor

import com.example.computerdatabase.AbstractUnitTest
import com.example.computerdatabase.entity.ComputerList
import com.example.computerdatabase.repository.NetworkRepository
import com.example.computerdatabase.repository.NetworkRepositoryInterface
import io.reactivex.Single
import org.junit.Test
import org.mockito.ArgumentMatchers
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
        whenever(repository.getComputers())
            .thenReturn(Single.just(ComputerList(items, 0, 0, 1)))

        interactor.getComputers()
        verify(repository, times(1))
            .getComputers()
    }

    @Test
    fun `verify that request getComputerDetail() is called`() {
        whenever(repository.getComputerDetail(ArgumentMatchers.anyInt()))
            .thenReturn(Single.just(computerDetail1))

        interactor.getComputerDetail(ArgumentMatchers.anyInt())
        verify(repository, times(1)).getComputerDetail(ArgumentMatchers.anyInt())
    }

//    @Test
//    fun `verify that request getSimilar() is called`() {
//        whenever(repository.getSimilar(ArgumentMatchers.anyInt()))
//            .thenReturn(Single.just(items))
//
//        interactor.getSimilar(ArgumentMatchers.anyInt())
//        Mockito.verify(repository, Mockito.times(1)).getSimilar(ArgumentMatchers.anyInt())
//    }

}