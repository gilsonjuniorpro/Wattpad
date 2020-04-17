package com.wattpad.ca.repository

import android.content.Context
import com.wattpad.ca.model.Story
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class WattpadRepository(context: Context) {

    private val dao: StoryDao = AppDatabase.getDatabase(context).getStoryDao()

    suspend fun save(story: Story) {
        dao.save(WattpadStoryMapper.storyToEntity(story))
    }

    suspend fun delete(story: Story) {
        dao.delete(WattpadStoryMapper.storyToEntity(story))
    }

    suspend fun isFavorite(id: String): Boolean {
        return dao.isFavorite(id) > 0
    }

    fun allFavorites(): Flow<List<Story>> {
        return dao.allFavorites()
            .map { storyList ->
                storyList.map { storyEntity ->
                    WattpadStoryMapper.entityToStory(storyEntity)
                }
            }
    }
}