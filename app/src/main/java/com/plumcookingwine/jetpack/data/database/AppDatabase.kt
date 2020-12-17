package com.plumcookingwine.jetpack.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.plumcookingwine.jetpack.data.entity.HomeArticleData

/**
 * Created by kangf on 2020/12/17.
 */
// @Database(entities = [HomeArticleData::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun articleDao(): ArticleDataDao
}