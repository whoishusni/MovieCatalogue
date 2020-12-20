package id.husni.moviecatalogue.data.source.local.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import id.husni.moviecatalogue.data.source.local.entity.MoviesEntity

@Dao
interface CatalogueDao {
    @Insert
    fun addMovieFav(moviesEntity: List<MoviesEntity>)

    @Delete
    fun deleteMovies(moviesEntity: MoviesEntity)

    @Query("SELECT * FROM movies_favourite")
    fun getAllMovies(): LiveData<List<MoviesEntity>>
}