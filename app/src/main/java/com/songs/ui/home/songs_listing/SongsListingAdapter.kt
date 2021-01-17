package com.songs.ui.home.songs_listing

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.songs.R
import com.songs.data.model.songs_list.SongDetailItem
import com.songs.databinding.LayoutSongItemBinding
import com.songs.utils.ViewUtils


class SongsListingAdapter(private val listener: SongsListingAdapterListener) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var songsList = ArrayList<SongDetailItem>()

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): RecyclerView.ViewHolder {
        return ViewHolderSong(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.layout_song_item, parent, false
            )
        )
    }

    override fun getItemCount(): Int {
        return songsList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ViewHolderSong -> {
                holder.bind(songsList[position])
            }
        }
    }

    fun clearAllData() {
        songsList.clear()
        notifyDataSetChanged()
    }

    fun loadData(list: ArrayList<SongDetailItem>) {
        songsList.clear()
        songsList.addAll(list)
        notifyDataSetChanged()
    }

    inner class ViewHolderSong(val binding: LayoutSongItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(songDetailItem: SongDetailItem) {
            ViewUtils.loadImage(
                binding.ivImage,
                binding.pbLoading,
                R.drawable.bg_grey_side_rounded_rectangle,
                songDetailItem.artworkUrl100 ?: "",
                8,
                true
            )
            binding.root.setOnClickListener {
                listener.onSongItemClick(songDetailItem)
            }
            binding.btnPlay.setOnClickListener {
                songDetailItem.previewUrl?.let { listener.onPlayClick(it) }
            }
        }
    }

    interface SongsListingAdapterListener {
        fun onSongItemClick(songDetailItem: SongDetailItem)
        fun onPlayClick(url: String)
    }
}