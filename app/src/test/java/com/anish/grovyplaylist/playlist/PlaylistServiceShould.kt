package com.anish.grovyplaylist.playlist

import com.anish.grovyplaylist.utils.BaseUnitTest
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test

class PlaylistServiceShould : BaseUnitTest() {

    private lateinit var service: PlaylistService
    private val api: PlaylistAPI = mock()
    private val playlists: List<Playlist> = mock()

    @Test
    fun fetchPlaylistFromApi() = runBlockingTest {
        service = PlaylistService(api)
        service.fetchPlaylists().first()

        verify(api, times(1)).fetchAllPlaylists()
    }

    @Test
    fun convertValuesToFlowAndEmitThem() = runBlockingTest {
        mockSucessfulCase()

        assertEquals(Result.success(playlists), service.fetchPlaylists().first())
    }

    @Test
    fun emitsErrorResultWhenNetworkFails() = runBlockingTest {
        mockFailureCase()

        assertEquals(
            "Something went wrong",
            service.fetchPlaylists().first().exceptionOrNull()?.message
        )
    }

    private suspend fun mockSucessfulCase() {
        whenever(api.fetchAllPlaylists()).thenReturn(playlists)
        service = PlaylistService(api)
    }

    private suspend fun mockFailureCase() {
        whenever(api.fetchAllPlaylists()).thenThrow(RuntimeException("Damn Backend developers"))
        service = PlaylistService(api)
    }
}