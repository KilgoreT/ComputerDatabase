package com.example.computerdatabase.datasource.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.computerdatabase.entity.ComputerDetail
import io.reactivex.Single

@Dao
interface ComputerDetailDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertComputerDetail(computerDetail: ComputerDetail)

    @Query("SELECT * from ComputerDetail")
    fun getComputerDetailList(): Single<List<ComputerDetail>>

    @Query("SELECT * FROM ComputerDetail WHERE id == :id")
    fun getComputerDetail(id: Int): Single<ComputerDetail>

}