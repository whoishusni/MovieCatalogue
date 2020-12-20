package id.husni.moviecatalogue.ui.favourite.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import id.husni.moviecatalogue.data.source.CatalogueRepository
import id.husni.moviecatalogue.data.source.local.entity.MoviesEntity

class FavMoviesViewModel(private val catalogueRepository: CatalogueRepository) :ViewModel(){
    fun getAllFavMovies(): LiveData<List<MoviesEntity>> = catalogueRepository.getAllFavMovies()

    fun addMovie(moviesEntity: MoviesEntity) = catalogueRepository.addMovieFav(moviesEntity)
}