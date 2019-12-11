package fr.appsolute.tp.data.repository

import fr.appsolute.tp.data.database.DatabaseManager
import fr.appsolute.tp.data.database.dao.EpisodeDao
import fr.appsolute.tp.data.model.Character
import fr.appsolute.tp.data.model.Episode
import fr.appsolute.tp.data.networking.HttpClientManager
import fr.appsolute.tp.data.networking.api.EpisodeApi
import fr.appsolute.tp.data.networking.createApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception

private class EpisodeRepositoryImpl(
    private val api: EpisodeApi,
    private val dao: EpisodeDao
) : EpisodeRepository {


    private suspend fun getCountDao(scope: CoroutineScope): Int? {

        return withContext(Dispatchers.IO){
            try {
                dao.getCount()
            } catch (e: Exception) {
                e.printStackTrace()
                null
            }
        }
    }

    private suspend fun getListDao(): List<Episode>? {

        return withContext(Dispatchers.IO){
            try {
                dao.selectAll()
            } catch (e: Exception) {
                e.printStackTrace()
                null
            }
        }
    }

    private suspend fun getListApi(): List<Episode>? {

        return withContext(Dispatchers.IO){

            var listEpisode: MutableList<Episode> = ArrayList<Episode>()
            var startPage = 1
            var continueWhile: Boolean = true

            try {
                var response = api.getEpisodes(startPage)

                while (response.isSuccessful && response.body() != null && continueWhile) {
                    response.body()!!.results.forEach{ item -> listEpisode.add(item)}
                    if (response.body()?.information?.next != "") {
                        response = api.getEpisodes(startPage++)
                    }
                    else{
                        continueWhile = false
                    }
                }
                dao.insertAll(listEpisode)
                return@withContext listEpisode
            } catch (e: Exception) {
                e.printStackTrace()
                null
            }
        }
    }

    private suspend fun getCountApi(): Int? {

        return withContext(Dispatchers.IO){
            try {
                val response = api.getEpisodes(1)
                check(response.isSuccessful) { "Response is not a success : code = ${response.code()}"}
                val data = response.body()?.information?.count ?: throw  IllegalStateException("Body is null")
                data
            } catch (e: Exception) {
                e.printStackTrace()
                null
            }
        }
    }

    override suspend fun getAllEpisodes(): List<Episode>? {

        return withContext(Dispatchers.IO){
            if (getCountApi() != dao.getCount()) {
                return@withContext getListApi()
            } else {
                return@withContext getListDao()
            }
        }

    }
}

/**
 * Repository of model [Character]
 */
interface EpisodeRepository {

    suspend fun getAllEpisodes(): List<Episode>?

    companion object {

        /**
         * Singleton for the interface [EpisodeRepository]
         */
        fun instance(): EpisodeRepository {
            // Lazy means "When I need it" so here this block will be launch
            // the first time you need the instance,
            // then, the reference will be stored in the value `instance`
            return EpisodeRepositoryImpl(
                HttpClientManager.instance.createApi(),
                DatabaseManager.getInstance().database.episodeDao
            )
        }
    }
}