package com.anish.grovyplaylist.playlist

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import com.anish.grovyplaylist.R
import dagger.hilt.android.AndroidEntryPoint
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

@AndroidEntryPoint
class PlaylistFragment : Fragment() {

    private var columnCount = 1

    lateinit var viewModel: PlaylistViewModel

    @Inject
    lateinit var viewModelFactory: PlaylistViewModelFactory

//    private val service = PlaylistService(api)
//
//    //    private val service = PlaylistService(object : PlaylistAPI {})
//    private val repository = PlaylistRepository(service)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_playlist, container, false)

        setupViewModel()

        viewModel.loader.observe(this as LifecycleOwner, { loading ->

            when (loading) {
                true -> view.findViewById<ProgressBar>(R.id.loader).visibility = View.VISIBLE
                false -> view.findViewById<ProgressBar>(R.id.loader).visibility = View.GONE
            }

        })

        viewModel.playlists.observe(this as LifecycleOwner, { playlists ->

            if (playlists.getOrNull() != null) {
                setupList(view.findViewById(R.id.playlists_list), playlists.getOrNull()!!)
            } else {

            }

        })

        return view
    }

    private fun setupList(
        view: View?,
        playlists: List<Playlist>
    ) {
        if (view is RecyclerView) {
            with(view) {
                layoutManager = when {
                    columnCount <= 1 -> LinearLayoutManager(context)
                    else -> GridLayoutManager(context, columnCount)
                }
                adapter = MyPlaylistRecyclerViewAdapter(playlists)
            }
        }
    }

    private fun setupViewModel() {
        viewModel = ViewModelProvider(this, viewModelFactory).get(PlaylistViewModel::class.java)
    }

    companion object {
        @JvmStatic
        fun newInstance() = PlaylistFragment().apply {}
    }
}