package id.husni.moviecatalogue.data.source.local
import android.content.Context
import androidx.paging.DataSource
import id.husni.moviecatalogue.data.source.local.entity.MoviesEntity
import id.husni.moviecatalogue.data.source.local.entity.SeriesEntity
import id.husni.moviecatalogue.data.source.local.room.CatalogueDao
import id.husni.moviecatalogue.data.source.local.room.CatalogueDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class LocalDataSource(context: Context) {

    private val catalogueDao: CatalogueDao

    init {
        val db = CatalogueDatabase.getInstance(context)
        catalogueDao = db.catalogueDao()
    }

    companion object {
        @Volatile
        var INSTANCE: LocalDataSource? = null

        fun getInstance(context: Context): LocalDataSource{
            if (INSTANCE == null){
                synchronized(this){
                    if (INSTANCE == null){
                        INSTANCE = LocalDataSource(context)
                    }
                }
            }
            return INSTANCE as LocalDataSource
        }
    }
    //movies
    fun getAllMovies(): DataSource.Factory<Int, MoviesEntity> = catalogueDao.getAllMoviesFav()

    fun isMovieBookmarked(moviesEntity: MoviesEntity): Boolean {
        return catalogueDao.getMoviesById(moviesEntity.id) != null
    }

    fun addMovieFav(moviesEntity: MoviesEntity){
        GlobalScope.launch(Dispatchers.IO) { catalogueDao.addMovieFav(moviesEntity) }
    }

    fun deleteMoviesFav(moviesEntity: MoviesEntity){
        GlobalScope.launch(Dispatchers.IO) { catalogueDao.deleteMoviesFav(moviesEntity) }
    }

    //series
    fun getAllSeries(): DataSource.Factory<Int,SeriesEntity> = catalogueDao.getAllSeriesFav()

    fun isSeriesBookmarked(seriesEntity: SeriesEntity): Boolean{
        return catalogueDao.getSeriesById(seriesEntity.id) !=null
    }

    fun addSeriesFav(seriesEntity: SeriesEntity){
        GlobalScope.launch(Dispatchers.IO) { catalogueDao.addSeriesFav(seriesEntity) }
    }

    fun deleteSeriesFav(seriesEntity: SeriesEntity){
        GlobalScope.launch(Dispatchers.IO) { catalogueDao.deleteSeriesFav(seriesEntity) }
    }

}