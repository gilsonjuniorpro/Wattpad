package com.wattpad.ca.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.wattpad.ca.R
import com.wattpad.ca.model.Story
import kotlinx.android.synthetic.main.item_story.view.*

class WattpadListAdapter(
    private val items: List<Story>,
    private val onItemClick: (Story) -> Unit
) : RecyclerView.Adapter<WattpadListAdapter.StoryHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoryHolder {
        val layout = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_story, parent, false)
        return StoryHolder(layout)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: StoryHolder, position: Int) {
        val story = items[position]
        holder.tvTitle.text = story.title
        holder.tvAuthors.text = story.user?.name
        if (story.cover != null) {
            holder.ivCover.load(story.cover) {
                crossfade(true)
                crossfade(500)
                placeholder(R.drawable.image_not_found)
            }
        } else {
            holder.ivCover.setImageResource(R.drawable.image_not_found)
        }
        holder.itemView.setOnClickListener { onItemClick(story) }
    }

    class StoryHolder(rootView: View) : RecyclerView.ViewHolder(rootView) {
        val ivCover: ImageView = rootView.ivCover
        val tvTitle: TextView = rootView.tvTitle
        val tvAuthors: TextView = rootView.tvName
    }
}