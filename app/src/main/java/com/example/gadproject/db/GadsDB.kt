package com.example.gadproject.db

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.gadproject.models.TopLearner
import com.example.gadproject.models.Skills


@Database(entities = [Skills::class, TopLearner::class], version = 1, exportSchema = false)
abstract class GadsDB: RoomDatabase() {

    abstract fun skillDao(): SkillsDao
    abstract fun hoursDao(): TopLearnersDao

    companion object{
        fun getDb(app:Application): GadsDB{
            return Room.databaseBuilder(app, GadsDB::class.java, "gads_app.db").fallbackToDestructiveMigration().build()
        }
    }
}