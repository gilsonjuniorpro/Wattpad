package com.wattpad.ca.repository

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface StoryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(story: StoryEntity): Long

    @Delete
    suspend fun delete(vararg story: StoryEntity): Int

    @Query("SELECT * FROM StoryEntity")
    fun allFavorites(): Flow<List<StoryEntity>>

    @Query("SELECT COUNT(id) FROM StoryEntity WHERE id = :id")
    suspend fun isFavorite(id: String) : Int
}