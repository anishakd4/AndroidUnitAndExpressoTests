package com.anish.grovyplaylist.playlist

import androidx.lifecycle.*
import kotlinx.coroutines.launch
import javax.inject.Inject

class PlaylistViewModel(private val repository: PlaylistRepository) :
    ViewModel() {

    //    val playlists = MutableLiveData<Result<List<Playlist>>>()
//
//    init {
//        viewModelScope.launch {
//            repository.getPlaylists().collect {
//                playlists.value = it
//            }
//        }
//    }
    val playlists = liveData {
        emitSource(repository.getPlaylists().asLiveData())
    }

}
