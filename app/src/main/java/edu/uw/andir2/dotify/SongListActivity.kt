package edu.uw.andir2.dotify

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.ericchee.songdataprovider.Song
import com.ericchee.songdataprovider.SongDataProvider
import edu.uw.andir2.dotify.databinding.ActivityPlayerBinding
import edu.uw.andir2.dotify.databinding.ActivitySongListBinding

class SongListActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySongListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_song_list)
        supportActionBar?.title = "All Songs"
        binding = ActivitySongListBinding.inflate(layoutInflater).apply { setContentView(root) }

        var songs: List<Song> = SongDataProvider.getAllSongs()

        with(binding) {
            val adapter = SongListAdapter(songs)
            rvSongs.adapter = adapter

            adapter.onSongClickListener = { _, song ->
                //Toast.makeText(this@SongListActivity, song.toString(), Toast.LENGTH_SHORT).show()
                tvSongSummary.text = root.context.getString(R.string.song_summary, song.title, song.artist)
            }

            adapter.onSongLongClickListener = {song ->
                adapter.deleteSongs(song)
                val songName: String = song.title
                Toast.makeText(this@SongListActivity, root.context.getString(R.string.delete_song_message, song.title), Toast.LENGTH_SHORT).show()
            }

            btnShuffleSongs.setOnClickListener {
                adapter.shuffleSongs(songs.toMutableList().shuffled())
            }

        }
    }
}