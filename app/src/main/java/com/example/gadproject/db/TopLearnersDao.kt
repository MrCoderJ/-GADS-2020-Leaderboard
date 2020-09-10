package com.example.gadproject.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.gadproject.models.TopLearner

@Dao
interface TopLearnersDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(hours: List<TopLearner>)

    @Query("SELECT * FROM hours")
    fun fetchHours(): LiveData<List<TopLearner>>
}