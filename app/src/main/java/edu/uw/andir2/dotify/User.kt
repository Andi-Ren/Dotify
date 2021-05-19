package edu.uw.andir2.dotify

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class User (
    val username: String,
    val firstName: String,
    val lastName: String,
    val hasNose: Boolean,
    val platform: Double,
    val profilePicURL: String
): Parcelable