package edu.uw.andir2.dotify

import android.content.Context
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.core.content.ContextCompat.startActivity
import kotlin.random.Random
import com.ericchee.songdataprovider.Song
import edu.uw.andir2.dotify.databinding.ActivityPlayerBinding
import edu.uw.andir2.dotify.databinding.ActivitySongListBinding

private const val SONG_KEY = "song"

fun navigateToPlayerActivity(context: Context, song: Song) {
    val intent = Intent(context, PlayerActivity::class.java)
    val bundle = Bundle(). apply {
        putParcelable(SONG_KEY, song)
    }

    intent.putExtras(bundle)

    context.startActivity(intent)
}

class PlayerActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPlayerBinding

    private var randomNumber = Random.nextInt(100000, 10000000)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player)

        binding = ActivityPlayerBinding.inflate(layoutInflater).apply { setContentView(root) }

        with(binding) {
            val launchIntent = intent
            val song: Song? = launchIntent.extras?.getParcelable(SONG_KEY)
            if (song != null) {
                tvSongName.text = song.title
                tvArtistName.text = song.artist
                ivSongCover.setImageResource(song.largeImageID)
            }

            tvPlayCount.text = root.context.getString(R.string.play_count, randomNumber)

            ivSongCover.setOnLongClickListener {
                tvPlayCount.setTextColor(Color.argb(255, Random.nextInt(256), Random.nextInt(256), Random.nextInt(256)))
                true
            }

            btnPlayButton.setOnClickListener {
                randomNumber++
                tvPlayCount.text = root.context.getString(R.string.play_count, randomNumber)
            }

            btnNextButton.setOnClickListener {
                Toast.makeText(this@PlayerActivity, "Skipping to next track", Toast.LENGTH_SHORT).show()
            }

            btnPrevButton.setOnClickListener {
                Toast.makeText(this@PlayerActivity, "Skipping to previous track", Toast.LENGTH_SHORT).show()
            }
        }
    }

    /* class PlayerActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPlayerBinding

    private var randomNumber = Random.nextInt(100000, 10000000)

    private lateinit var tvPlayNumber: TextView
    private lateinit var tvUsername: TextView
    private lateinit var tvSongName: TextView
    private lateinit var tvArtistName: TextView

    private lateinit var ptUserNameEdit: EditText

    private lateinit var ivSongCover: ImageView

    private lateinit var btnChangeUser: Button
    private lateinit var btnPlay: ImageButton
    private lateinit var btnNext: ImageButton
    private lateinit var btnPrev: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_player)

        val songName: String? = intent.extras?.getString("song_name")
        val artistName: String? = intent.extras?.getString("artist_name")

        ivSongCover = findViewById(R.id.ivSongCover)

        tvUsername = findViewById(R.id.tvUserName)
        tvSongName = findViewById(R.id.tvSongName)
        tvArtistName = findViewById(R.id.tvArtistName)
        tvPlayNumber = findViewById(R.id.tvPlayCount)

        ptUserNameEdit = findViewById(R.id.ptUserNameEdit)

        btnChangeUser = findViewById(R.id.btnChangeUser)
        btnPlay = findViewById(R.id.btnPlayButton)
        btnNext = findViewById(R.id.btnNextButton)
        btnPrev = findViewById(R.id.btnPrevButton)

        tvUsername.text = "Guest User"
        tvSongName.text = songName
        btnChangeUser.text = "Change User"
        tvArtistName.text = artistName
        tvPlayNumber.text = "1"
        ptUserNameEdit.setVisibility(View.GONE)

        ivSongCover.setOnLongClickListener {
            tvPlayNumber.setTextColor(Color.argb(255, Random.nextInt(256), Random.nextInt(256), Random.nextInt(256)))
            true
        }

        btnPlay.setOnClickListener {
            randomNumber++
            tvPlayNumber.text = "1"
        }

        btnNext.setOnClickListener {
            Toast.makeText(this, "Skipping to next track", Toast.LENGTH_SHORT).show()
        }

        btnPrev.setOnClickListener {
            Toast.makeText(this, "Skipping to previous track", Toast.LENGTH_SHORT).show()
        }

        btnChangeUser.setOnClickListener{
            if (btnChangeUser.text == "Change User") {
                btnChangeUser.text = "Apply"
                toggleView(tvUsername)
                toggleView(ptUserNameEdit)
            } else if (btnChangeUser.text == "Apply") {
                if (ptUserNameEdit.text.toString() == "") {
                    Toast.makeText(this, "Username cannot be empty", Toast.LENGTH_SHORT).show()
                } else {
                    tvUsername.text = ptUserNameEdit.text
                    btnChangeUser.text = "Change User"
                    toggleView(tvUsername)
                    toggleView(ptUserNameEdit)
                }

            }

        }

    }

    fun toggleView (view: View) {
        if(view.getVisibility()==View.GONE)
            view.setVisibility(View.VISIBLE)
        else if(view.getVisibility()==View.VISIBLE)
            view.setVisibility(View.GONE)
    }

    */

}

