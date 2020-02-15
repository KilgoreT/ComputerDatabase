package com.example.computerdatabase

import com.example.computerdatabase.entity.Company
import com.example.computerdatabase.entity.Computer
import com.example.computerdatabase.entity.ComputerDetail
import org.junit.Before
import org.mockito.Mockito
import org.mockito.stubbing.OngoingStubbing

abstract class AbstractUnitTest {

    @Before
    fun setUp() {
        prepareTest()
    }

    abstract fun prepareTest()

    inline fun <reified T> mock() = Mockito.mock(T::class.java)
    inline fun <T> whenever(methodCall: T): OngoingStubbing<T> =
        Mockito.`when`(methodCall)

    companion object {
        private val company1 = Company(1, "companyName1")
        private val company2 = Company(2, "companyName2")
        private val computer1 = Computer(1, "testComp1", company1)
        private val computer2 = Computer(2, "testComp2", company2)
        val computerDetail1 = ComputerDetail(1, "testComp1", null, null, null, company1, "desc1")
        val items = mutableListOf(computer1, computer2)
    }

}