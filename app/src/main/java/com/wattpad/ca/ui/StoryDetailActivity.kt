package com.wattpad.ca.ui

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import coil.api.load
import com.wattpad.ca.R
import com.wattpad.ca.databinding.ActivityStoryDetailBinding
import com.wattpad.ca.model.Story
import com.wattpad.ca.repository.WattpadRepository
import com.wattpad.ca.viewmodel.DetailStoryViewModel
import com.wattpad.ca.viewmodel.WattpadViewModelFactory

class StoryDetailActivity : AppCompatActivity() {

    lateinit var binding: ActivityStoryDetailBinding

    private val viewModel: DetailStoryViewModel by lazy {
        ViewModelProvider(
            this,
            WattpadViewModelFactory(
                WattpadRepository(this)
            )
        ).get(DetailStoryViewModel::class.java)
    }

    lateinit var story: Story

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_story_detail)

        val story = intent.getParcelableExtra<Story>(EXTRA_STORY)

        if (story != null) {
            this.story = story
            binding.tvTitle.text = story.title
            binding.tvName.text = story.user?.name
            binding.tvFullname.text = story.user?.fullname

            if (story.cover != null) {
                binding.ivCover.load(story.cover) {
                    crossfade(true)
                    crossfade(500)
                    placeholder(R.drawable.image_not_found)
                }
            } else {
                binding.ivCover.setImageResource(R.drawable.image_not_found)
            }

            if (story.user?.avatar != null) {
                binding.ivAvatar.load(story.user.avatar) {
                    crossfade(true)
                    crossfade(500)
                    placeholder(R.drawable.image_not_found)
                }
            } else {
                binding.ivAvatar.setImageResource(R.drawable.image_not_found)
            }

            viewModel.isFavorite.observe(
                this,
                Observer { isFavorite ->
                    if (isFavorite) {
                        binding.fab.setImageResource(R.drawable.ic_remove)
                        binding.fab.setOnClickListener {
                            viewModel.removeFromFavorites(story)
                        }
                    } else {
                        binding.fab.setImageResource(R.drawable.ic_add)
                        binding.fab.setOnClickListener {
                            viewModel.saveToFavorites(story)
                        }
                    }
                }
            )
            viewModel.onCreate(story)
        } else {
            finish()
        }
    }

    companion object {
        private const val EXTRA_STORY = "story"

        fun open(context: Context, story: Story) {
            val intent = Intent(context, StoryDetailActivity::class.java)
            intent.putExtra(EXTRA_STORY, story)
            context.startActivity(intent)
        }
    }
}
