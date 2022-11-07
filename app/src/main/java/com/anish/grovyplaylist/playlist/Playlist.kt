package com.anish.grovyplaylist.playlist

import com.anish.grovyplaylist.R

data class Playlist(
    val id: String, val name: String, val category: String, val image: Int = R.mipmap.playlist
)