package id.husni.moviecatalogue.utils

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import id.husni.moviecatalogue.BuildConfig
import id.husni.moviecatalogue.data.source.local.entity.MoviesEntity
import id.husni.moviecatalogue.data.source.remote.response.MoviesResponse
import id.husni.moviecatalogue.data.source.remote.response.MoviesResult
import id.husni.moviecatalogue.data.source.remote.response.ResultsSeries
import id.husni.moviecatalogue.data.source.remote.response.SeriesResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ApiHelper {
    companion object {
        const val TAG = "TAG"
        const val IMAGE_POSTER_URL = "https://image.tmdb.org/t/p/w500/"
    }

    fun loadMovies(): LiveData<List<MoviesResult>> {
        val _list = MutableLiveData<List<MoviesResult>>()
        EspressoIdlingResource.increment()
        val list: LiveData<List<MoviesResult>> = _list
        val client = ApiConfig.getApiService().getMovies(BuildConfig.TMDB_API, "en-US")
        client.enqueue(object : Callback<MoviesResponse> {
            override fun onResponse(call: Call<MoviesResponse>, response: Response<MoviesResponse>
            ) {
                if (response.isSuccessful) {
                    _list.value = response.body()?.results
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

    fun loadMoviesById(id: String): LiveData<MoviesResult> {
        val _movies = MutableLiveData<MoviesResult>()
        EspressoIdlingResource.increment()
        val moviesEntity: LiveData<MoviesResult> = _movies
        val call = ApiConfig.getApiService().getMoviesId(id, BuildConfig.TMDB_API, "en-US")
        call.enqueue(object : Callback<MoviesResult> {
            override fun onResponse(call: Call<MoviesResult>, response: Response<MoviesResult>) {
                if (response.isSuccessful) {
                    _movies.value = response.body()
                    EspressoIdlingResource.decrement()
                } else {
                    Log.e(TAG, "onFailure : fetch movies by id")
                }
            }

            override fun onFailure(call: Call<MoviesResult>, t: Throwable) {
                Log.e(TAG, t.message.toString())
            }
        })
        return moviesEntity
    }

    fun loadSeries(): LiveData<List<ResultsSeries>> {
        val _list = MutableLiveData<List<ResultsSeries>>()
        EspressoIdlingResource.increment()
        val list: LiveData<List<ResultsSeries>> = _list
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

    fun loadSeriesById(id: String): LiveData<ResultsSeries> {
        val _series = MutableLiveData<ResultsSeries>()
        EspressoIdlingResource.increment()
        val series: LiveData<ResultsSeries> = _series
        val call = ApiConfig.getApiService().getSeriesById(id, BuildConfig.TMDB_API, "en-US")
        call.enqueue(object : Callback<ResultsSeries> {
            override fun onResponse(call: Call<ResultsSeries>, response: Response<ResultsSeries>) {
                if (response.isSuccessful) {
                    _series.value = response.body()
                    EspressoIdlingResource.decrement()
                } else {
                    Log.e(TAG, "onFailure : fetch series by id")
                }
            }

            override fun onFailure(call: Call<ResultsSeries>, t: Throwable) {
                Log.e(TAG, t.message.toString())
            }
        })
        return series
    }
}