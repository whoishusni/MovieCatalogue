package id.husni.moviecatalogue.ui.favourite.movies

import androidx.lifecycle.ViewModel
import id.husni.moviecatalogue.data.source.CatalogueRepository

class FavMoviesViewModel(private val catalogueRepository: CatalogueRepository) :ViewModel(){
    fun getAllFavMovies() = catalogueRepository.getAllFavMovies()
}