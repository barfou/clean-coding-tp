package fr.appsolute.tp.data.repository

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import fr.appsolute.tp.RickAndMortyApplication
import fr.appsolute.tp.data.database.DatabaseManager
import fr.appsolute.tp.data.database.EpisodeDao
import fr.appsolute.tp.data.model.Character
import fr.appsolute.tp.data.model.Episode
import fr.appsolute.tp.data.networking.HttpClientManager
import fr.appsolute.tp.data.networking.api.CharacterApi
import fr.appsolute.tp.data.networking.api.EpisodeApi
import fr.appsolute.tp.data.networking.createApi
import fr.appsolute.tp.data.networking.datasource.CharacterDataSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

private class EpisodeRepositoryImpl(
    private val api: EpisodeApi,
    private val dao: EpisodeDao
) : EpisodeRepository {

    override fun getPaginatedList(scope: CoroutineScope): PagedList<Episode> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}

/**
 * Repository of model [Character]
 */
interface EpisodeRepository {

    fun getPaginatedList(scope: CoroutineScope): PagedList<Episode>

    companion object {
        /**
         * Singleton for the interface [EpisodeRepository]
         */
        fun newInstance (application: RickAndMortyApplication ): EpisodeRepository{
            // Lazy means "When I need it" so here this block will be launch
            // the first time you need the instance,
            // then, the reference will be stored in the value `instance`
            return EpisodeRepositoryImpl(HttpClientManager.instance.createApi(), DatabaseManager.newInstance(application).database.episodeDao)
        }
    }
}