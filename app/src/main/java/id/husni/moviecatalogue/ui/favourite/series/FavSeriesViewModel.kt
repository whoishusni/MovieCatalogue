package id.husni.moviecatalogue.ui.favourite.series

import androidx.lifecycle.ViewModel
import id.husni.moviecatalogue.data.source.CatalogueRepository

class FavSeriesViewModel(private val catalogueRepository: CatalogueRepository): ViewModel() {
    fun getAllFavSeries() = catalogueRepository.getAllFavSeries()
}