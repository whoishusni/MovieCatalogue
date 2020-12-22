package id.husni.moviecatalogue.ui.favourite.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import id.husni.moviecatalogue.data.source.CatalogueRepository
import id.husni.moviecatalogue.data.source.local.entity.MoviesEntity

class FavMoviesViewModel(private val catalogueRepository: CatalogueRepository) :ViewModel(){
    fun getAllFavMovies(): LiveData<PagedList<MoviesEntity>> = LivePagedListBuilder(catalogueRepository.getAllFavMovies(),4).build()
}