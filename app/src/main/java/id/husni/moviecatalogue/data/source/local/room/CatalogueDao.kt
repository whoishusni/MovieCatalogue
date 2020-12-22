package id.husni.moviecatalogue.data.source.local.room

import androidx.paging.DataSource
import androidx.room.*
import id.husni.moviecatalogue.data.source.local.entity.MoviesEntity
import id.husni.moviecatalogue.data.source.local.entity.SeriesEntity

@Dao
interface CatalogueDao {
    //Movies Fave
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addMovieFav(moviesEntity: MoviesEntity)

    @Delete
    fun deleteMoviesFav(moviesEntity: MoviesEntity)

    @Query("SELECT * FROM movies_favourite WHERE id = :id")
    fun getMoviesById(id: Int?): MoviesEntity?

    @Query("SELECT * FROM movies_favourite")
    fun getAllMoviesFav(): DataSource.Factory<Int,MoviesEntity>

    //Series Fave
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addSeriesFav(seriesEntity: SeriesEntity)

    @Delete
    fun deleteSeriesFav(seriesEntity: SeriesEntity)

    @Query("SELECT * FROM series_favourite WHERE id = :id")
    fun getSeriesById(id: Int?): SeriesEntity?

    @Query("SELECT * FROM series_favourite")
    fun getAllSeriesFav(): DataSource.Factory<Int, SeriesEntity>

}