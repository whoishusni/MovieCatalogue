package id.husni.moviecatalogue.ui.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import id.husni.moviecatalogue.data.DummiesData
import id.husni.moviecatalogue.data.source.CatalogueRepository
import id.husni.moviecatalogue.data.source.local.entity.MoviesEntity
import id.husni.moviecatalogue.data.source.local.entity.SeriesEntity
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DetailCatalogueViewModelTest {
    private lateinit var viewModel: DetailCatalogueViewModel
    private val dummyMovie = DummiesData.getMoviesData()[0]
    private val dummySeries = DummiesData.getSeriesData()[0]
    private val movieId = dummyMovie.id
    private val seriesId = dummySeries.id

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var repository: CatalogueRepository

    @Mock
    private lateinit var observerMovie: Observer<MoviesEntity>

    @Mock
    private lateinit var observerSeries: Observer<SeriesEntity>

    @Before
    fun setUp() {
        viewModel = DetailCatalogueViewModel(repository)
        viewModel.setMovieId(movieId.toString())
        viewModel.setSeriesId(seriesId.toString())
    }

    @Test
    fun getMovies() {
        val movie = MutableLiveData<MoviesEntity>()
        movie.value = dummyMovie
        Mockito.`when`(repository.loadMoviesById(movieId.toString())).thenReturn(movie)
        viewModel.getMovies().observeForever(observerMovie)
        Mockito.verify(observerMovie).onChanged(dummyMovie)
    }

    @Test
    fun getSeries() {
        val series = MutableLiveData<SeriesEntity>()
        series.value = dummySeries
        Mockito.`when`(repository.loadSeriesById(seriesId.toString())).thenReturn(series)
        viewModel.getSeries().observeForever(observerSeries)
        Mockito.verify(observerSeries).onChanged(dummySeries)
    }
}