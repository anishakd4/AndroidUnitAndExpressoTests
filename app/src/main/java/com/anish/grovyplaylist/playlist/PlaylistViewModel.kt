package com.anish.grovyplaylist.playlist

import androidx.lifecycle.*
import kotlinx.coroutines.flow.onEach
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

    val loader = MutableLiveData<Boolean>()

    val playlists = liveData {
        loader.postValue(true)
        emitSource(repository.getPlaylists().onEach { loader.postValue(false) }.asLiveData())
    }

}
