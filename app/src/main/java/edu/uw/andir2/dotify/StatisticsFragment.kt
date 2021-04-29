package edu.uw.andir2.dotify

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import edu.uw.andir2.dotify.databinding.FragmentStatisticsBinding

class StatisticsFragment : Fragment() {

    private val navController by lazy { findNavController()}

    private val safeArgs: StatisticsFragmentArgs by navArgs()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {
        val binding = FragmentStatisticsBinding.inflate(inflater)
        val songCount = safeArgs.count
        with(binding) {
            tvSongName.text = songCount.song.title
            tvSongCount.text = "played ${songCount.count} times"
            ivSongCover.setImageResource((songCount.song.largeImageID))
        }

        return binding.root
    }
}