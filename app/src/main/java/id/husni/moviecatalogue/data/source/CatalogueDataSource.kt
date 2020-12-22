package id.husni.moviecatalogue.data.source

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import id.husni.moviecatalogue.data.source.local.entity.MoviesEntity
import id.husni.moviecatalogue.data.source.local.entity.SeriesEntity

interface CatalogueDataSource {
    fun loadMovies(): LiveData<List<MoviesEntity>>
    fun loadSeries(): LiveData<List<SeriesEntity>>
    fun loadMoviesById(id: String): LiveData<MoviesEntity>
    fun loadSeriesById(id: String): LiveData<SeriesEntity>

    //bookmark movie
    fun isMovieBookmarked(moviesEntity: MoviesEntity): Boolean
    fun getAllFavMovies(): DataSource.Factory<Int, MoviesEntity>
    fun addMovieFav(moviesEntity: MoviesEntity)
    fun deleteMovieFav(moviesEntity: MoviesEntity)

    //bookmark series
    fun isSeriesBookmarked(seriesEntity: SeriesEntity): Boolean
    fun getAllFavSeries(): DataSource.Factory<Int, SeriesEntity>
    fun addSeriesFav(seriesEntity: SeriesEntity)
    fun deleteSeriesFav(seriesEntity: SeriesEntity)

}