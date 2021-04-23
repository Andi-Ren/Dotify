package edu.uw.andir2.dotify

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import kotlin.random.Random
import com.ericchee.songdataprovider.Song

fun navigateToPlayerActivity(song: Song) {
}

class PlayerActivity : AppCompatActivity() {
    private var randomNumber = Random.nextInt(100000, 10000000)
    private var playCountEnd = " plays"

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
        tvSongName.text = "Star Boy"
        btnChangeUser.text = "Change User"
        tvArtistName.text = "The Weeknd Ft. Daft Punk"
        tvPlayNumber.text = randomNumber.toString() + playCountEnd
        ptUserNameEdit.setVisibility(View.GONE)

        ivSongCover.setOnLongClickListener {
            tvPlayNumber.setTextColor(Color.argb(255, Random.nextInt(256), Random.nextInt(256), Random.nextInt(256)))
            true
        }

        btnPlay.setOnClickListener {
            randomNumber++
            tvPlayNumber.text = randomNumber.toString() + playCountEnd
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

}

