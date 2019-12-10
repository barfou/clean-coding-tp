package fr.appsolute.tp.data.repository

/*import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import fr.appsolute.tp.data.model.Character
import fr.appsolute.tp.data.model.Episode
import fr.appsolute.tp.data.networking.HttpClientManager
import fr.appsolute.tp.data.networking.api.CharacterApi
import fr.appsolute.tp.data.networking.createApi
import fr.appsolute.tp.data.networking.datasource.CharacterDataSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext*/

/*private class EpisodeRepositoryImpl(
    private val api: CharacterApi
) : EpisodeRepository {
    /**
     * Config for pagination
     */
    private val paginationConfig = PagedList.Config
        .Builder()
        // If you set true you will have to catch
        // the place holder case in the adapter
        .setEnablePlaceholders(false)
        .setPageSize(20)
        .build()

    override fun getPaginatedList(scope: CoroutineScope): PagedList<Character> {
        return LivePagedListBuilder(
            CharacterDataSource.Factory(api, scope),
            paginationConfig
        ).build()
    }
}*/

/**
 * Repository of model [Character]
 */
/*interface EpisodeRepository {

    /**
     * Return a LiveData (Observable Design Pattern) of a Paged List of Character
     */
    fun getPaginatedList(scope: CoroutineScope): PagedList<Character>

    //suspend fun getAllEpisodes(id :Int):

    companion object {
        /**
         * Singleton for the interface [EpisodeRepository]
         */
        val instance: EpisodeRepository by lazy {
            // Lazy means "When I need it" so here this block will be launch
            // the first time you need the instance,
            // then, the reference will be stored in the value `instance`
            EpisodeRepositoryImpl(HttpClientManager.instance.createApi())
        }
    }
}*/