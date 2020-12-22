package id.husni.moviecatalogue.ui.series

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import id.husni.moviecatalogue.data.source.CatalogueRepository
import id.husni.moviecatalogue.data.source.local.entity.SeriesEntity

class SeriesViewModel(private val repository: CatalogueRepository) : ViewModel() {
    fun getSeries() : LiveData<List<SeriesEntity>> = repository.loadSeries()
}