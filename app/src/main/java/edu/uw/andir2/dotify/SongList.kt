package edu.uw.andir2.dotify

data class SongList(
    val title: String,
    val numOfSongs: Int,
    val songs: List<Song>,
)
