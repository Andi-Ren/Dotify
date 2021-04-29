package edu.uw.andir2.dotify

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import edu.uw.andir2.dotify.databinding.FragmentSettingsBinding

class SettingsFragment : Fragment() {

    private val navController by lazy { findNavController()}

    private val safeArgs: SettingsFragmentArgs by navArgs()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {
        val binding = FragmentSettingsBinding.inflate(inflater)

        val songCount = safeArgs.count

        with(binding) {
            btnProfile.setOnClickListener {
                navController.navigate(R.id.profileFragment)
            }

            btnAbout.setOnClickListener {
                navController.navigate(edu.uw.andir2.dotify.R.id.aboutFragment)
            }

            btnProfileStat.setOnClickListener {
                //navController.navigate(edu.uw.andir2.dotify.R.id.statisticsFragment)
                navController.navigate(NavGraphDirections.actionGlobalStatisticsFragment2(songCount))
            }
        }

        return binding.root
    }
}