package fr.appsolute.tp.data.networking.api

import fr.appsolute.tp.data.model.Episode
import fr.appsolute.tp.data.model.PaginatedResult
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface EpisodeApi {

    @GET(EpisodeApi.GET_ALL_EPISODE)
    suspend fun getEpisodes(
        @Query("page") id:Int
    ): Response<PaginatedResult<Episode>>



    companion object {
        const val GET_ALL_EPISODE = "episode/"
    }
}