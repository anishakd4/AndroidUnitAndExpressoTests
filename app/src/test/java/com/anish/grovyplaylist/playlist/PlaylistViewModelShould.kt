package com.anish.grovyplaylist.playlist

import com.anish.grovyplaylist.utils.BaseUnitTest
import com.anish.grovyplaylist.utils.captureValues
import com.anish.grovyplaylist.utils.getValueForTest
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test

//class PlaylistViewModelShould {
//
//    @get:Rule
//    var coroutinesTestRule = MainCoroutineScopeRule()
//
//    @get:Rule
//    var instantTaskExecutorRule = InstantTaskExecutorRule()
//
//    private val viewModel: PlaylistViewModel
//    private val repository: PlaylistRepository = mock()
//    private val playlists = mock<List<Playlist>>()
//    private val expected = Result.success(playlists)
//
//    init {
//        whenever(repository.getPlaylists()).thenReturn(
//            flow {
//                emit(expected)
//            }
//        )
//        viewModel = PlaylistViewModel(repository)
//    }
//
//    @Test
//    fun getPlaylistsFromRepository() {
//        viewModel.playlists.getValueForTest()
//
//        verify(repository, times(1)).getPlaylists()
//    }
//}


class PlaylistViewModelShould : BaseUnitTest() {

    private val repository: PlaylistRepository = mock()
    private val playlists = mock<List<Playlist>>()
    private val expected = Result.success(playlists)
    private val exception = RuntimeException("Something went wrong")

    init {

    }

    @Test
    fun getPlaylistsFromRepository() = runBlockingTest {

        val viewModel = mockSucessfulCase()

        viewModel.playlists.getValueForTest()

        verify(repository, times(1)).getPlaylists()
    }

    @Test
    fun emitsPlaylistFromRepository() = runBlockingTest {

        val viewModel = mockSucessfulCase()

        assertEquals(expected, viewModel.playlists.getValueForTest())
    }

    @Test
    fun emitErrorWhenReceiveError() {
        val viewModel = mockErrorCase()

        assertEquals(exception, viewModel.playlists.getValueForTest()!!.exceptionOrNull())
    }

    @Test
    fun showSpinnerWhileLoading() = runBlockingTest {
        val viewModel = mockSucessfulCase()

        viewModel.loader.captureValues {
            viewModel.playlists.getValueForTest()
            assertEquals(true, values[0])
        }
    }

    @Test
    fun closeLoaderAfterPlaylistLoads() = runBlockingTest {
        val viewModel = mockSucessfulCase()

        viewModel.loader.captureValues {
            viewModel.playlists.getValueForTest()
            assertEquals(false, values.last())
        }
    }

    @Test
    fun closeLoaderAfterError() = runBlockingTest {
        val viewModel = mockErrorCase()

        viewModel.loader.captureValues {
            viewModel.playlists.getValueForTest()
            assertEquals(false, values.last())
        }
    }

    private fun mockSucessfulCase(): PlaylistViewModel {
        runBlocking {
            whenever(repository.getPlaylists()).thenReturn(
                flow {
                    emit(expected)
                }
            )
        }
        return PlaylistViewModel(repository)
    }

    private fun mockErrorCase(): PlaylistViewModel {
        runBlocking {
            whenever(repository.getPlaylists()).thenReturn(
                flow {
                    emit(Result.failure<List<Playlist>>(exception))
                }
            )
        }
        return PlaylistViewModel(repository)
    }
}