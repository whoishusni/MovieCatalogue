package id.husni.moviecatalogue.ui.favourite.series

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import id.husni.moviecatalogue.data.source.CatalogueRepository
import id.husni.moviecatalogue.data.source.local.entity.SeriesEntity

class FavSeriesViewModel(private val catalogueRepository: CatalogueRepository): ViewModel() {
    fun getAllFavSeries(): LiveData<PagedList<SeriesEntity>> = LivePagedListBuilder(catalogueRepository.getAllFavSeries(),4).build()
}