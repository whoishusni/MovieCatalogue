package id.husni.moviecatalogue.ui.movies

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import id.husni.moviecatalogue.data.DummiesData
import id.husni.moviecatalogue.data.source.CatalogueRepository
import id.husni.moviecatalogue.data.source.local.entity.MoviesEntity
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MoviesViewModelTest {
    private lateinit var viewModel: MoviesViewModel

    @get:Rule
    var instantExecutor = InstantTaskExecutorRule()

    @Mock
    private lateinit var repository: CatalogueRepository

    @Mock
    private lateinit var observer: Observer<List<MoviesEntity>>

    @Before
    fun setUp() {
        viewModel = MoviesViewModel(repository)
    }

    @Test
    fun getMovies() {
        val dummy = DummiesData.getMoviesData()
        val movies = MutableLiveData<List<MoviesEntity>>()
        movies.value = dummy
        Mockito.`when`(repository.loadMovies()).thenReturn(movies)
        viewModel.getMovies().observeForever(observer)
        Mockito.verify(observer).onChanged(dummy)
    }

}