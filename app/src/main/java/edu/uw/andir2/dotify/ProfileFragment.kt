package edu.uw.andir2.dotify

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import edu.uw.andir2.dotify.databinding.FragmentProfileBinding
import coil.load
import kotlinx.coroutines.launch

class ProfileFragment : Fragment() {

    private val dotifyApp: DotifyApplication by lazy { requireActivity().application as DotifyApplication }
    private val dataRepo by lazy {dotifyApp.dataRepository}
    
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {
        val binding = FragmentProfileBinding.inflate(inflater)
        with(binding) {
            pullDownContainer.setOnRefreshListener {
                lifecycleScope.launch {
                    kotlin.runCatching {
                        val user = dataRepo.getUser()

                        tvErrorMessage.visibility = View.GONE

                        tvUserName.text = user.username
                        tvFirstName.text = "First Name: " + user.firstName
                        tvLastName.text = "Last Name: " + user.lastName
                        tvHasNose.text = "Has Nose: " + user.hasNose.toString()
                        tvPlatform.text = "Platform: " + user.platform.toString()
                        ivProfilePic.load(user.profilePicURL)

                        tvUserName.visibility = View.VISIBLE
                        tvFirstName.visibility = View.VISIBLE
                        tvLastName.visibility = View.VISIBLE
                        tvHasNose.visibility = View.VISIBLE
                        tvPlatform.visibility = View.VISIBLE
                        ivProfilePic.visibility = View.VISIBLE
                    }.onFailure {
                        tvErrorMessage.visibility = View.VISIBLE
                    }
                }
                pullDownContainer.isRefreshing = false
            }
        }
        return binding.root
    }
}