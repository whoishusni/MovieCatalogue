package id.husni.moviecatalogue.ui.favourite.series

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import id.husni.moviecatalogue.data.source.CatalogueRepository
import id.husni.moviecatalogue.data.source.local.entity.SeriesEntity
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito

class FavSeriesViewModelTest {
    @Rule
    @JvmField
    val instantTaskExecutorRule: InstantTaskExecutorRule = InstantTaskExecutorRule()
    private lateinit var viewModel: FavSeriesViewModel
    private var catalogueRepository = Mockito.mock(CatalogueRepository::class.java)

    @Before
    fun setUp() {
        viewModel = FavSeriesViewModel(catalogueRepository)
    }

    @Test
    fun getAllFavSeries() {
        val dummyMovies = MutableLiveData<PagedList<SeriesEntity>>()
        val pagedList: PagedList<SeriesEntity> = Mockito.mock(PagedList::class.java) as PagedList<SeriesEntity>
        dummyMovies.value = pagedList

        Mockito.`when`(catalogueRepository.getAllFavSeries()).thenReturn(dummyMovies)
        val observer = Mockito.mock(Observer::class.java) as Observer<PagedList<SeriesEntity>>

        viewModel.getAllFavSeries().observeForever(observer)

        Mockito.verify(observer).onChanged(pagedList)
    }
}