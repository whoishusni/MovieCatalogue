package id.husni.moviecatalogue.data.source.remote

import androidx.lifecycle.LiveData
import id.husni.moviecatalogue.data.source.local.entity.MoviesEntity
import id.husni.moviecatalogue.data.source.remote.response.ResultsSeries
import id.husni.moviecatalogue.utils.ApiHelper

class RemoteDataSource private constructor(private val apiHelper: ApiHelper) {

    companion object {

        @Volatile
        private var instance: RemoteDataSource? = null
        fun getInstance(apiHelper: ApiHelper): RemoteDataSource =
            instance ?: synchronized(this) {
                instance ?: RemoteDataSource(apiHelper)
            }
    }

    fun getAllMovies(): LiveData<List<MoviesEntity>> = apiHelper.loadMovies()

    fun getMoviesById(id: String): LiveData<MoviesEntity> = apiHelper.loadMoviesById(id)

    fun getAllSeries(): LiveData<List<ResultsSeries>> = apiHelper.loadSeries()

    fun getSeriesById(id: String): LiveData<ResultsSeries> = apiHelper.loadSeriesById(id)
}