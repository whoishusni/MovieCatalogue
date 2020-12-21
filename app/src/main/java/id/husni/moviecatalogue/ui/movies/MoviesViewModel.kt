package id.husni.moviecatalogue.ui.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import id.husni.moviecatalogue.data.source.CatalogueRepository
import id.husni.moviecatalogue.data.source.local.entity.MoviesEntity

class MoviesViewModel(private val repository: CatalogueRepository) : ViewModel() {
    fun getMovies() : LiveData<List<MoviesEntity>> = repository.loadMovies()
}