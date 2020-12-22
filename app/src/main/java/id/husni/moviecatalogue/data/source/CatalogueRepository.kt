package id.husni.moviecatalogue.data.source

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import id.husni.moviecatalogue.data.source.local.LocalDataSource
import id.husni.moviecatalogue.data.source.local.entity.MoviesEntity
import id.husni.moviecatalogue.data.source.local.entity.SeriesEntity
import id.husni.moviecatalogue.data.source.remote.RemoteDataSource

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
    //remote data
    override fun loadMovies(): LiveData<List<MoviesEntity>> = remoteDataSource.getAllMovies()

    override fun loadMoviesById(id: String): LiveData<MoviesEntity> = remoteDataSource.getMoviesById(id)

    override fun loadSeries(): LiveData<List<SeriesEntity>> = remoteDataSource.getAllSeries()

    override fun loadSeriesById(id: String): LiveData<SeriesEntity> = remoteDataSource.getSeriesById(id)

    //bookmark / local data
    override fun isMovieBookmarked(moviesEntity: MoviesEntity): Boolean = localDataSource.isMovieBookmarked(moviesEntity)

    override fun getAllFavMovies(): DataSource.Factory<Int,MoviesEntity> = localDataSource.getAllMovies()

    override fun addMovieFav(moviesEntity: MoviesEntity) = localDataSource.addMovieFav(moviesEntity)

    override fun deleteMovieFav(moviesEntity: MoviesEntity) = localDataSource.deleteMoviesFav(moviesEntity)

    override fun isSeriesBookmarked(seriesEntity: SeriesEntity): Boolean = localDataSource.isSeriesBookmarked(seriesEntity)

    override fun getAllFavSeries(): DataSource.Factory<Int,SeriesEntity> = localDataSource.getAllSeries()

    override fun addSeriesFav(seriesEntity: SeriesEntity) = localDataSource.addSeriesFav(seriesEntity)

    override fun deleteSeriesFav(seriesEntity: SeriesEntity) = localDataSource.deleteSeriesFav(seriesEntity)
}