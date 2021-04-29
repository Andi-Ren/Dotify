package edu.uw.andir2.dotify

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import edu.uw.andir2.dotify.databinding.FragmentAboutBinding

class AboutFragment : Fragment() {

    private val navController by lazy { findNavController()}

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {
        val binding = FragmentAboutBinding.inflate(inflater)
        val versionNum = BuildConfig.VERSION_NAME
        with(binding) {
            btnBack.setOnClickListener {
                navController.navigate(R.id.settingsFragment)
            }

            tvVersion.text = "Version: $versionNum"
        }

        return binding.root
    }
}