package com.anish.grovyplaylist.playlist

import androidx.test.espresso.IdlingResource
import com.jakewharton.espresso.OkHttp3IdlingResource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val client = OkHttpClient()
////val idlingResource = OkHttp3IdlingResource.create("okhttp", client)
//val idlingResource = UriIdlingResource

@Module
@InstallIn(FragmentComponent::class)
class PlaylistModule {

    @Provides
    fun playlistAPI(retrofit: Retrofit): PlaylistAPI = retrofit.create(PlaylistAPI::class.java)

//    @Provides
//    fun retrofit(): Retrofit = Retrofit.Builder()
//        .baseUrl("http://192.168.29.116:3000/")
//        .client(OkHttpClient())
//        .addConverterFactory(GsonConverterFactory.create())
//        .build()

    @Provides
    fun retrofit(): Retrofit = Retrofit.Builder()
        .baseUrl("http://192.168.29.116:3000/")
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}