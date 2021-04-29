package edu.uw.andir2.dotify

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import edu.uw.andir2.dotify.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {

    private val navController by lazy { findNavController()}
    
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {
        val binding = FragmentProfileBinding.inflate(inflater)

        with(binding) {
            btnBack.setOnClickListener {
                navController.navigate(R.id.settingsFragment)
            }
        }

        return binding.root
    }
}