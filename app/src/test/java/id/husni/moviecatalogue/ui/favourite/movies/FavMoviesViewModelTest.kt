package id.husni.moviecatalogue.ui.favourite.movies

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import id.husni.moviecatalogue.data.source.CatalogueRepository
import id.husni.moviecatalogue.data.source.local.entity.MoviesEntity
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito

class FavMoviesViewModelTest {
    @Rule
    @JvmField
    val instantTaskExecutorRule: InstantTaskExecutorRule = InstantTaskExecutorRule()
    private lateinit var viewModel: FavMoviesViewModel
    private var catalogueRepository = Mockito.mock(CatalogueRepository::class.java)

    @Before
    fun setUp() {
        viewModel = FavMoviesViewModel(catalogueRepository)
    }

    @Test
    fun getAllFavMovies() {
        val dummyMovies = MutableLiveData<PagedList<MoviesEntity>>()
        val pagedList: PagedList<MoviesEntity> = Mockito.mock(PagedList::class.java) as PagedList<MoviesEntity>
        dummyMovies.value = pagedList

        Mockito.`when`(catalogueRepository.getAllFavMovies()).thenReturn(dummyMovies)
        val observer = Mockito.mock(Observer::class.java) as Observer<PagedList<MoviesEntity>>

        viewModel.getAllFavMovies().observeForever(observer)

        Mockito.verify(observer).onChanged(pagedList)
    }
}