package id.husni.moviecatalogue.data.source

import androidx.lifecycle.LiveData
import id.husni.moviecatalogue.data.source.local.entity.MoviesEntity
import id.husni.moviecatalogue.data.source.remote.response.MoviesResult
import id.husni.moviecatalogue.data.source.remote.response.ResultsSeries

interface CatalogueDataSource {
    fun loadMovies(): LiveData<List<MoviesResult>>
    fun loadSeries(): LiveData<List<ResultsSeries>>
    fun loadMoviesById(id: String): LiveData<MoviesResult>
    fun loadSeriesById(id: String): LiveData<ResultsSeries>

    //bookmark
    fun getAllFavMovies(): LiveData<List<MoviesEntity>>
    fun addMovieFav(moviesEntity: MoviesEntity)
}