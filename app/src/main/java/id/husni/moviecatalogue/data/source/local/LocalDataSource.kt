package id.husni.moviecatalogue.data.source.local
import android.content.Context
import androidx.lifecycle.LiveData
import id.husni.moviecatalogue.data.source.local.entity.MoviesEntity
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

    fun getAllMovies(): LiveData<List<MoviesEntity>> = catalogueDao.getAllMovies()

    fun isMovieBookmarked(moviesEntity: MoviesEntity): Boolean {
        return true
    //return catalogueDao.getMoviesById(moviesEntity.id) != null
    }

    fun addMovieFav(moviesEntity: MoviesEntity){
        GlobalScope.launch(Dispatchers.IO) { catalogueDao.addMovieFav(moviesEntity) }
    }

    fun deleteMoviesFav(moviesEntity: MoviesEntity){
        GlobalScope.launch(Dispatchers.IO) { catalogueDao.deleteMovies(moviesEntity) }
    }
}