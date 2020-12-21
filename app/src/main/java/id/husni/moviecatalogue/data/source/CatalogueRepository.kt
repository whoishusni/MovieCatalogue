package id.husni.moviecatalogue.data.source

import androidx.lifecycle.LiveData
import id.husni.moviecatalogue.data.source.local.LocalDataSource
import id.husni.moviecatalogue.data.source.local.entity.MoviesEntity
import id.husni.moviecatalogue.data.source.remote.RemoteDataSource
import id.husni.moviecatalogue.data.source.remote.response.ResultsSeries

class CatalogueRepository private constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) : CatalogueDataSource {
    companion object {
        @Volatile
        private var instance: CatalogueRepository? = null
        fun getInstance(
            remoteDataSource: RemoteDataSource,
            localDataSource: LocalDataSource
        ): CatalogueRepository =
            instance ?: synchronized(this) {
                instance ?: CatalogueRepository(remoteDataSource, localDataSource)
            }
    }

    override fun loadMovies(): LiveData<List<MoviesEntity>> = remoteDataSource.getAllMovies()

    override fun loadMoviesById(id: String): LiveData<MoviesEntity> = remoteDataSource.getMoviesById(id)

    override fun loadSeries(): LiveData<List<ResultsSeries>> = remoteDataSource.getAllSeries()

    override fun loadSeriesById(id: String): LiveData<ResultsSeries> = remoteDataSource.getSeriesById(id)

    override fun isMovieBookmarked(moviesEntity: MoviesEntity): Boolean = localDataSource.isMovieBookmarked(moviesEntity)

    //bookmark
    override fun getAllFavMovies(): LiveData<List<MoviesEntity>> = localDataSource.getAllMovies()
    override fun addMovieFav(moviesEntity: MoviesEntity) {
        localDataSource.addMovieFav(moviesEntity)
    }

    override fun deleteMovieFav(moviesEntity: MoviesEntity) {
        localDataSource.deleteMoviesFav(moviesEntity)
    }


}