package com.example.gadproject.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.gadproject.models.Skills


@Dao
interface SkillsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(skills: List<Skills>)

    @Query("SELECT * FROM skills")
    fun fetchSkills(): LiveData<List<Skills>>
}