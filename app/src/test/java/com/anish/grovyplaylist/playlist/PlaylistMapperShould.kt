package com.anish.grovyplaylist.playlist

import com.anish.grovyplaylist.R
import com.anish.grovyplaylist.utils.BaseUnitTest
import junit.framework.TestCase.assertEquals
import org.junit.Test

class PlaylistMapperShould : BaseUnitTest() {

    private val playlistRaw = PlaylistRaw("1", "a name", "a category")
    private val playlistRawRock = PlaylistRaw("1", "a name", "rock")
    private val mapper = PlaylistMapper()

    private val playlists = mapper(listOf(playlistRaw))
    private val playlist = playlists[0]

    private val playlistsRock = mapper(listOf(playlistRawRock))
    private val playlistRock = playlistsRock[0]

    @Test
    fun keepSameId() {
        assertEquals(playlistRaw.id, playlist.id)
    }

    @Test
    fun keepSameName() {
        assertEquals(playlistRaw.name, playlist.name)
    }

    @Test
    fun keepSameCategory() {
        assertEquals(playlistRaw.category, playlist.category)
    }

    @Test
    fun mapDefaultImageWhenNotRock() {
        assertEquals(R.mipmap.playlist, playlist.image)
    }

    @Test
    fun mapRockImageWhenRockCategory() {
        assertEquals(R.mipmap.rock, playlistRock.image)
    }
}