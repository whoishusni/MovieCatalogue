package id.husni.moviecatalogue.utils

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import id.husni.moviecatalogue.BuildConfig
import id.husni.moviecatalogue.data.source.local.entity.MoviesEntity
import id.husni.moviecatalogue.data.source.local.entity.SeriesEntity
import id.husni.moviecatalogue.data.source.remote.response.MoviesResponse
import id.husni.moviecatalogue.data.source.remote.response.SeriesResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ApiHelper {
    companion object {
        const val TAG = "TAG"
        const val IMAGE_POSTER_URL = "https://image.tmdb.org/t/p/w500/"
    }

    fun loadMovies(): LiveData<List<MoviesEntity>> {
        val _list = MutableLiveData<List<MoviesEntity>>()
        EspressoIdlingResource.increment()
        val list: LiveData<List<MoviesEntity>> = _list
        val client = ApiConfig.getApiService().getMovies(BuildConfig.TMDB_API, "en-US")
        client.enqueue(object : Callback<MoviesResponse> {
            override fun onResponse(call: Call<MoviesResponse>, response: Response<MoviesResponse>
            ) {
                if (response.isSuccessful) {
                    _list.value = response.body()?.resultMovies
                    EspressoIdlingResource.decrement()
                } else {
                    Log.e(TAG, "onFailure : Fetch Data ")
                }
            }

            override fun onFailure(call: Call<MoviesResponse>, t: Throwable) {
                Log.e(TAG, t.message.toString())
            }
        })
        return list
    }

    fun loadMoviesById(id: String): LiveData<MoviesEntity> {
        val _movies = MutableLiveData<MoviesEntity>()
        EspressoIdlingResource.increment()
        val entityMovies: LiveData<MoviesEntity> = _movies
        val call = ApiConfig.getApiService().getMoviesId(id, BuildConfig.TMDB_API, "en-US")
        call.enqueue(object : Callback<MoviesEntity> {
            override fun onResponse(call: Call<MoviesEntity>, response: Response<MoviesEntity>) {
                if (response.isSuccessful) {
                    _movies.value = response.body()
                    EspressoIdlingResource.decrement()
                } else {
                    Log.e(TAG, "onFailure : fetch movies by id")
                }
            }

            override fun onFailure(call: Call<MoviesEntity>, t: Throwable) {
                Log.e(TAG, t.message.toString())
            }
        })
        return entityMovies
    }

    fun loadSeries(): LiveData<List<SeriesEntity>> {
        val _list = MutableLiveData<List<SeriesEntity>>()
        EspressoIdlingResource.increment()
        val list: LiveData<List<SeriesEntity>> = _list
        val call = ApiConfig.getApiService().getSeries(BuildConfig.TMDB_API, "en-US")
        call.enqueue(object : Callback<SeriesResponse> {
            override fun onResponse(call: Call<SeriesResponse>, response: Response<SeriesResponse>
            ) {
                if (response.isSuccessful) {
                    _list.value = response.body()?.results
                    EspressoIdlingResource.decrement()
                } else {
                    Log.e(TAG, "onFailure : Fetch Data ")
                }
            }

            override fun onFailure(call: Call<SeriesResponse>, t: Throwable) {
                Log.e(TAG, t.message.toString())
            }
        })
        return list
    }

    fun loadSeriesById(id: String): LiveData<SeriesEntity> {
        val _series = MutableLiveData<SeriesEntity>()
        EspressoIdlingResource.increment()
        val series: LiveData<SeriesEntity> = _series
        val call = ApiConfig.getApiService().getSeriesById(id, BuildConfig.TMDB_API, "en-US")
        call.enqueue(object : Callback<SeriesEntity> {
            override fun onResponse(call: Call<SeriesEntity>, response: Response<SeriesEntity>) {
                if (response.isSuccessful) {
                    _series.value = response.body()
                    EspressoIdlingResource.decrement()
                } else {
                    Log.e(TAG, "onFailure : fetch series by id")
                }
            }

            override fun onFailure(call: Call<SeriesEntity>, t: Throwable) {
                Log.e(TAG, t.message.toString())
            }
        })
        return series
    }
}