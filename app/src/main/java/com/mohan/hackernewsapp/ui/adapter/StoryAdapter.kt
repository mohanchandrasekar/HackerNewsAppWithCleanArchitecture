package com.mohan.hackernewsapp.ui.adapter

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.mohan.hackernews.data.Story
import kotlinx.android.synthetic.main.top_stories_list.view.*

class StoryAdapter(private val onItemClick: (Story, Int) -> Unit = { track: Story, i: Int -> }) :
    RecyclerView.Adapter<StoryAdapter.StoryViewHolder>() {

    var storyList = arrayListOf<Story>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoryViewHolder {
        LayoutInflater.from(parent.context)
            .inflate(R.layout.top_stories_list, parent, false)
            .let { return StoryViewHolder(it) }
    }

    override fun getItemCount(): Int {
        return storyList.size
    }

    override fun onBindViewHolder(holder: StoryViewHolder, position: Int) {
        storyList[position].let { track ->
            holder.setData(track)
            holder.itemView.setOnClickListener {
                Log.e("Mohan", "Position : $position")
                onItemClick(track, position) }
        }
    }

    fun setData(list: ArrayList<Story>) {
        storyList = list
    }

    class StoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun setData(data: Story) {
            itemView.story_title.text = data.title
            val uri = data.url
            val url: Array<String> = uri.split("/").toTypedArray()
            itemView.story_uri.text = url[2]

            val hours = convertSecondsToHours(data.time.toLong())
            val postinfo = " points " + data.score + " by " + data.by + " " + hours + " hours ago "
            itemView.story_post_info.text = postinfo
            itemView.story_comments.text = data.descendants.toString()
        }

        /**
         * Convert from long to hours
         * @param seconds time in long value
         * @return which return the hour string
         */
        private fun convertSecondsToHours(seconds: Long): String? {
            val h = seconds / (60 * 60) % 24
            return String.format("%d", h)
        }
    }

}