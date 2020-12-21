package id.husni.moviecatalogue.data.source.local.room

import androidx.lifecycle.LiveData
import androidx.room.*
import id.husni.moviecatalogue.data.source.local.entity.MoviesEntity

@Dao
interface CatalogueDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addMovieFav(moviesEntity: MoviesEntity)

    @Delete
    fun deleteMovies(moviesEntity: MoviesEntity)

    @Query("SELECT * FROM movies_favourite WHERE id =:id")
    fun getMoviesById(id: Int): MoviesEntity

    @Query("SELECT * FROM movies_favourite")
    fun getAllMovies(): LiveData<List<MoviesEntity>>
}