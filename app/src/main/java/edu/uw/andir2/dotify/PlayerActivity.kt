package edu.uw.andir2.dotify

import android.content.Context
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import android.widget.*
import kotlin.random.Random
import com.ericchee.songdataprovider.Song
import edu.uw.andir2.dotify.databinding.ActivityPlayerBinding
import kotlinx.android.parcel.Parcelize

private const val SONG_KEY = "song"
const val PLAYS_KEY = "PLAYS_KEY"

fun navigateToPlayerActivity(context: Context, song: Song) {
    val intent = Intent(context, PlayerActivity::class.java)
    val bundle = Bundle(). apply {
        putParcelable(SONG_KEY, song)
    }

    intent.putExtras(bundle)

    context.startActivity(intent)
}
@Parcelize
data class SongCount(
    val song: Song,
    val count: Int
): Parcelable

class PlayerActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPlayerBinding
    private var randomNumber = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player)
        val launchIntent = intent
        val song: Song? = launchIntent.extras?.getParcelable(SONG_KEY)

        binding = ActivityPlayerBinding.inflate(layoutInflater).apply { setContentView(root) }

        with(binding) {

            if (song != null) {
                tvSongName.text = song.title
                tvArtistName.text = song.artist
                ivSongCover.setImageResource(song.largeImageID)
            }

            randomNumber = savedInstanceState?.getInt(PLAYS_KEY, 0) ?: Random.nextInt(100000, 10000000)

            tvPlayCount.text = root.context.getString(R.string.play_count, randomNumber)

            ivSongCover.setOnLongClickListener {
                tvPlayCount.setTextColor(Color.argb(255, Random.nextInt(256), Random.nextInt(256), Random.nextInt(256)))
                true
            }

            btnPlayButton.setOnClickListener {
                randomNumber += 1
                tvPlayCount.text = root.context.getString(R.string.play_count, randomNumber)
            }

            btnNextButton.setOnClickListener {
                Toast.makeText(this@PlayerActivity, "Skipping to next track", Toast.LENGTH_SHORT).show()
            }

            btnPrevButton.setOnClickListener {
                Toast.makeText(this@PlayerActivity, "Skipping to previous track", Toast.LENGTH_SHORT).show()
            }

            btnSettings.setOnClickListener {

                val currentSongCount = SongCount(
                    song = song!!,
                    count = randomNumber
                )

                if (song != null) {
                    navigateToSettingsActivity(this@PlayerActivity, currentSongCount)
                }
            }

            btnBack.setOnClickListener {
                if (song != null) {
                    navigateToSongListActivity(this@PlayerActivity, song)
                }
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt(PLAYS_KEY, randomNumber)
        super.onSaveInstanceState(outState)
    }
}

