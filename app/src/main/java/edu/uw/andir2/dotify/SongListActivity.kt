package edu.uw.andir2.dotify

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import edu.uw.andir2.dotify.databinding.ActivityMainBinding
import edu.uw.andir2.dotify.databinding.ActivitySongListBinding

class SongListActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySongListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_song_list)
        supportActionBar?.title = "All Songs"
        binding = ActivitySongListBinding.inflate(layoutInflater).apply { setContentView(root) }

        val songs = listOf("AAA", "BBB", "CCC", "DDDD", "EEEE", "FFFF", "XXXXXX", "PPPPPPPPP", "asdadasdasda", "asdasdadasdasdasdasdasdasdasdas")

        with(binding) {
            val adapter = SongListAdapter(songs)
            rvSongs.adapter = adapter

            adapter.onSongClickListener = { position, name ->
                Toast.makeText(this@SongListActivity, "You clicked on $name at pos: $position", Toast.LENGTH_SHORT).show()
            }

            btnShuffleSongs.setOnClickListener {
                adapter.updateSongs(songs.toMutableList().shuffled())
            }
        }
    }
}