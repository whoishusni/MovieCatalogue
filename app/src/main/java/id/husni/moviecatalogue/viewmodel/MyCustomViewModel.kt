package id.husni.moviecatalogue.viewmodel

import android.app.Application
import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import id.husni.moviecatalogue.data.source.CatalogueRepository
import id.husni.moviecatalogue.di.Injection
import id.husni.moviecatalogue.ui.detail.DetailCatalogueViewModel
import id.husni.moviecatalogue.ui.favourite.movies.FavMoviesViewModel
import id.husni.moviecatalogue.ui.movies.MoviesViewModel
import id.husni.moviecatalogue.ui.series.SeriesViewModel

class MyCustomViewModel private constructor(private val catalogueRepository: CatalogueRepository) :
    ViewModelProvider.NewInstanceFactory() {
    companion object {
        @Volatile
        private var instance: MyCustomViewModel? = null
        fun getInstance(context: Context): MyCustomViewModel =
            instance ?: synchronized(this) {
                instance ?: MyCustomViewModel(Injection.provideRepo(context))
            }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(MoviesViewModel::class.java) -> {
                MoviesViewModel(catalogueRepository) as T
            }
            modelClass.isAssignableFrom(SeriesViewModel::class.java) -> {
                SeriesViewModel(catalogueRepository) as T
            }
            modelClass.isAssignableFrom(DetailCatalogueViewModel::class.java) -> {
                DetailCatalogueViewModel(catalogueRepository) as T
            }
            modelClass.isAssignableFrom(FavMoviesViewModel::class.java)->{
                FavMoviesViewModel(catalogueRepository) as T
            }
            else -> {
                throw Throwable("Unknown ViewModel Class " + modelClass.name)
            }
        }
    }
}