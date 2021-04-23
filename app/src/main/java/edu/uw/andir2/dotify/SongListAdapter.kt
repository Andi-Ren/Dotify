package edu.uw.andir2.dotify

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.ericchee.songdataprovider.Song
import edu.uw.andir2.dotify.databinding.ItemSongBinding

class SongListAdapter(private var listOfSongs: List<Song>): RecyclerView.Adapter<SongListAdapter.SongViewHolder>() {

    var onSongClickListener: (position: Int, song: Song) -> Unit = {_, _ -> }
    var onSongLongClickListener: (song: Song) -> Unit = {_ -> }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SongViewHolder {
        val binding = ItemSongBinding.inflate(LayoutInflater.from(parent.context))

        return SongViewHolder(binding)
    }

    override fun getItemCount(): Int = listOfSongs.size

    override fun onBindViewHolder(holder: SongViewHolder, position: Int) {
        val song: Song = listOfSongs[position]
        with(holder.binding) {
            tvSongNameList.text = song.title
            tvArtistNameList.text = song.artist
            ivSongCoverList.setImageResource(song.smallImageID)
            root.setOnClickListener {
                onSongClickListener(position, song)
            }
            root.setOnLongClickListener {
                onSongLongClickListener(song)
                true
            }
        }
    }

    fun shuffleSongs(newListOfSongs: List<Song>) {
        val callback = SongsDiffCallback(newListOfSongs, listOfSongs)
        val result = DiffUtil.calculateDiff(callback)
        result.dispatchUpdatesTo(this)

        this.listOfSongs = newListOfSongs
    }

    fun deleteSongs(song: Song): List<Song> {
        val deletedListOfSongs = listOfSongs.toMutableList().apply { removeAt(listOfSongs.indexOf(song)) }

        val callback = SongsDiffCallback(deletedListOfSongs, listOfSongs)
        val result = DiffUtil.calculateDiff(callback)
        result.dispatchUpdatesTo(this)

        this.listOfSongs = deletedListOfSongs
        return deletedListOfSongs
    }

    class SongViewHolder(val binding: ItemSongBinding): RecyclerView.ViewHolder(binding.root)
}