package edu.uw.andir2.dotify

import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.converter.gson.GsonConverterFactory

class DataRepository {
    private val dataService = Retrofit.Builder()
        .baseUrl("https://raw.githubusercontent.com/echeeUW/codesnippets/master/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(DataService::class.java)

    suspend fun getUser(): User = dataService.getUser()
    suspend fun getSongs(): SongList = dataService.getSongs()
}

interface DataService {
    @GET("user_info.json")
    suspend fun getUser(): User

    @GET("musiclibrary.json")
    suspend fun getSongs() : SongList
}