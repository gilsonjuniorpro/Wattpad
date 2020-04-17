package com.wattpad.ca.repository

import com.wattpad.ca.model.Story
import com.wattpad.ca.model.User

object WattpadStoryMapper {

    fun storyToEntity(story: Story) =
        StoryEntity(
            story.id,
            story.cover,
            story.title,
            story.user?.avatar,
            story.user?.fullname,
            story.user?.name
        )

    fun entityToStory(entity: StoryEntity) =
        entity.run {
            Story(
                id,
                cover,
                title,
                User(
                    avatar,
                    fullname,
                    name
                )
            )
        }
}