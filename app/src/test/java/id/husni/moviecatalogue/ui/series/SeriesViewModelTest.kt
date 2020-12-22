package id.husni.moviecatalogue.ui.series

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import id.husni.moviecatalogue.data.DummiesData
import id.husni.moviecatalogue.data.source.CatalogueRepository
import id.husni.moviecatalogue.data.source.local.entity.SeriesEntity
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class SeriesViewModelTest {
    private lateinit var viewModel: SeriesViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var repository: CatalogueRepository

    @Mock
    private lateinit var observer: Observer<List<SeriesEntity>>

    @Before
    fun setUp() {
        viewModel = SeriesViewModel(repository)
    }

    @Test
    fun getSeries() {
        val dummy = DummiesData.getSeriesData()
        val series = MutableLiveData<List<SeriesEntity>>()
        series.value = dummy
        Mockito.`when`(repository.loadSeries()).thenReturn(series)
        viewModel.getSeries().observeForever(observer)
        Mockito.verify(observer).onChanged(dummy)
    }
}