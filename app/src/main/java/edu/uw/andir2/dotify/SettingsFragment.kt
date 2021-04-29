package edu.uw.andir2.dotify

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import edu.uw.andir2.dotify.databinding.FragmentSettingsBinding

class SettingsFragment : Fragment() {

    private val navController by lazy { findNavController()}

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {
        val binding = FragmentSettingsBinding.inflate(inflater)

        with(binding) {
            btnProfile.setOnClickListener {
                navController.navigate(R.id.profileFragment)
            }

            btnAbout.setOnClickListener {
                navController.navigate(edu.uw.andir2.dotify.R.id.aboutFragment)
            }

            btnProfileStat.setOnClickListener {
                navController.navigate(edu.uw.andir2.dotify.R.id.statisticsFragment)
            }
        }

        return binding.root
    }
}