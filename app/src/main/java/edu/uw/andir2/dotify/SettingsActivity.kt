package edu.uw.andir2.dotify

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ericchee.songdataprovider.Song

private const val SONG_KEY = "song"

fun navigateToSettingsActivity(context: Context, song: Song) {
    val intent = Intent(context, SettingsActivity::class.java)
    val bundle = Bundle(). apply {
        putParcelable(SONG_KEY, song)
    }

    intent.putExtras(bundle)

    context.startActivity(intent)
}

class SettingsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
    }
}