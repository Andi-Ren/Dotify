package edu.uw.andir2.dotify


import kotlinx.parcelize.Parcelize
import android.os.Parcelable

@Parcelize
data class Song (
    val id: String,
    val title: String,
    val artist: String,
    val durationMillis: Int,
    val smallImageURL: String,
    val largeImageURL: String,
): Parcelable
