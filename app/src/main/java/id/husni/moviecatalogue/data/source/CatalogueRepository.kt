package id.husni.moviecatalogue.data.source

import androidx.lifecycle.LiveData
import id.husni.moviecatalogue.data.source.local.LocalDataSource
import id.husni.moviecatalogue.data.source.local.entity.MoviesEntity
import id.husni.moviecatalogue.data.source.remote.RemoteDataSource
import id.husni.moviecatalogue.data.source.remote.response.MoviesResult
import id.husni.moviecatalogue.data.source.remote.response.ResultsSeries
import id.husni.moviecatalogue.utils.AppExecutors

class CatalogueRepository private constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : CatalogueDataSource {
    companion object {
        @Volatile
        private var instance: CatalogueRepository? = null
        fun getInstance(
            remoteDataSource: RemoteDataSource,
            localDataSource: LocalDataSource,
            appExecutors: AppExecutors
        ): CatalogueRepository =
            instance ?: synchronized(this) {
                instance ?: CatalogueRepository(remoteDataSource, localDataSource,appExecutors)
            }
    }

    override fun loadMovies(): LiveData<List<MoviesResult>> = remoteDataSource.getAllMovies()

    override fun loadMoviesById(id: String): LiveData<MoviesResult> = remoteDataSource.getMoviesById(id)

    override fun loadSeries(): LiveData<List<ResultsSeries>> = remoteDataSource.getAllSeries()

    override fun loadSeriesById(id: String): LiveData<ResultsSeries> = remoteDataSource.getSeriesById(id)

    //bookmark
    override fun getAllFavMovies(): LiveData<List<MoviesEntity>> = localDataSource.getAllMovies()
    override fun addMovieFav(moviesEntity: MoviesEntity) {

    }


}