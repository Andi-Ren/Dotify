package edu.uw.andir2.dotify

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.ericchee.songdataprovider.Song
import com.ericchee.songdataprovider.SongDataProvider
import edu.uw.andir2.dotify.databinding.ActivitySongListBinding

private const val SONG_KEY = "song"

fun navigateToSongListActivity(context: Context, song: Song) {
    val intent = Intent(context, SongListActivity::class.java)
    val bundle = Bundle(). apply {
        putParcelable(SONG_KEY, song)
    }

    intent.putExtras(bundle)

    context.startActivity(intent)
}

class SongListActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySongListBinding
    private var currentSong: Song? = null
    private lateinit var songs: List<Song>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_song_list)

        val launchIntent = intent
        val playerSong: Song? = launchIntent.extras?.getParcelable(SONG_KEY)

        supportActionBar?.title = "All Songs"
        binding = ActivitySongListBinding.inflate(layoutInflater).apply { setContentView(root) }
        songs = SongDataProvider.getAllSongs()


        with(binding) {
            val adapter = SongListAdapter(songs)

            rvSongs.adapter = adapter

            currentSong = if (savedInstanceState != null) {
                savedInstanceState.getParcelable(SONG_KEY)
            } else {
                playerSong
            }

            if (currentSong == null) {
                tvSongSummary.visibility = View.GONE
            } else {
                tvSongSummary.text = root.context.getString(R.string.song_summary, currentSong?.title, currentSong?.artist)
                tvSongSummary.visibility = View.VISIBLE
            }

            adapter.onSongClickListener = { _, song ->
                tvSongSummary.text = root.context.getString(R.string.song_summary, song.title, song.artist)
                tvSongSummary.visibility = View.VISIBLE
                currentSong = song
            }

            adapter.onSongLongClickListener = {song ->
                val deletedSongs: List<Song> = adapter.deleteSongs(song)
                Toast.makeText(this@SongListActivity, root.context.getString(R.string.delete_song_message, song.title), Toast.LENGTH_SHORT).show()
                songs = deletedSongs
            }

            btnShuffleSongs.setOnClickListener {
                adapter.shuffleSongs(songs.toMutableList().shuffled())
            }


            tvSongSummary.setOnClickListener {
                navigateToPlayerActivity(this@SongListActivity, currentSong!!)
            }


        }


    }

    override fun onSaveInstanceState(outState: Bundle) {
        // Save data
        outState.putParcelable(SONG_KEY, currentSong)
        super.onSaveInstanceState(outState)
    }

}