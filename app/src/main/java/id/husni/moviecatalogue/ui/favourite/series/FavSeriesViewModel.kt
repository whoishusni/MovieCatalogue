package id.husni.moviecatalogue.ui.favourite.series

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import id.husni.moviecatalogue.data.source.CatalogueRepository
import id.husni.moviecatalogue.data.source.local.entity.SeriesEntity

class FavSeriesViewModel(private val catalogueRepository: CatalogueRepository): ViewModel() {
    fun getAllFavSeries(): LiveData<List<SeriesEntity>> = catalogueRepository.getAllFavSeries()
}