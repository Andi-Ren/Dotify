package edu.uw.andir2.dotify

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import edu.uw.andir2.dotify.databinding.ItemSongBinding

class SongListAdapter(private var listOfSongs: List<String>): RecyclerView.Adapter<SongListAdapter.SongViewHolder>() {

    public var onSongClickListener: (position: Int, name: String) -> Unit = {_, _ -> }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SongViewHolder {
        val binding = ItemSongBinding.inflate(LayoutInflater.from(parent.context))

        return SongViewHolder(binding)
    }

    override fun getItemCount(): Int = listOfSongs.size

    override fun onBindViewHolder(holder: SongViewHolder, position: Int) {
        var song = listOfSongs[position]
        with(holder.binding) {
            tvSongNameList.text = song

            itemRoot.setOnClickListener {
                onSongClickListener(position, song)
            }
        }
    }

    fun updateSongs(newListOfSongs: List<String>) {
        this.listOfSongs = newListOfSongs

        notifyDataSetChanged()
    }

    class SongViewHolder(val binding: ItemSongBinding): RecyclerView.ViewHolder(binding.root)
}