package id.husni.moviecatalogue.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import id.husni.moviecatalogue.data.source.CatalogueRepository
import id.husni.moviecatalogue.data.source.local.entity.MoviesEntity
import id.husni.moviecatalogue.data.source.remote.response.ResultsSeries

class DetailCatalogueViewModel(private val catalogueRepository: CatalogueRepository) : ViewModel() {
    private lateinit var moviesId: String
    private lateinit var seriesId: String

    fun setMovieId(moviesId: String) {
        this.moviesId = moviesId
    }

    fun setSeriesId(seriesId: String) {
        this.seriesId = seriesId
    }

    fun getMovies(): LiveData<MoviesEntity> = catalogueRepository.loadMoviesById(moviesId)

    fun getSeries(): LiveData<ResultsSeries> = catalogueRepository.loadSeriesById(seriesId)

    fun addMoviesFave(moviesEntity: MoviesEntity) = catalogueRepository.addMovieFav(moviesEntity)
}