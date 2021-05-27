package edu.uw.andir2.dotify

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import edu.uw.andir2.dotify.databinding.ActivitySettingsBinding

private const val SONG_COUNT_KEY = "count"

fun navigateToSettingsActivity(context: Context, songCount: SongCount) = with(context) {
    startActivity(Intent(this, SettingsActivity::class.java).apply {
        putExtra(SONG_COUNT_KEY, songCount)
    })
}

class SettingsActivity : AppCompatActivity() {

    //private val safeArgs: StatisticsFragmentArgs by navArgs()



    private lateinit var binding: ActivitySettingsBinding

    private val navController by lazy { findNavController(R.id.navHost) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_settings)
        binding = ActivitySettingsBinding.inflate(layoutInflater).apply { setContentView(root) }

        with(binding) {

            //navController.setGraph(R.navigation.nav_graph, intent.extras)
            val songCount = intent.extras?.getParcelable<SongCount>(SONG_COUNT_KEY)
            /*if (songCount != null) {
                Toast.makeText(this@SettingsActivity, "$songCount", Toast.LENGTH_SHORT).show()
            }*/
            navController.setGraph(
                R.navigation.nav_graph, intent.extras
            )
        }
    }
}