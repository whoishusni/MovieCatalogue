package id.husni.moviecatalogue.data.source.local
import androidx.lifecycle.LiveData
import id.husni.moviecatalogue.data.source.local.entity.MoviesEntity
import id.husni.moviecatalogue.data.source.local.room.CatalogueDao

class LocalDataSource private constructor(private val catalogueDao: CatalogueDao) {

    companion object {
        @Volatile
        var INSTANCE: LocalDataSource? = null

        fun getInstance(catalogueDao: CatalogueDao): LocalDataSource{
            if (INSTANCE == null){
                synchronized(this){
                    if (INSTANCE == null){
                        INSTANCE = LocalDataSource(catalogueDao)
                    }
                }
            }
            return INSTANCE as LocalDataSource
        }
    }

    fun getAllMovies(): LiveData<List<MoviesEntity>> = catalogueDao.getAllMovies()

    fun addMovieFav(moviesEntity: MoviesEntity) = catalogueDao.addMovieFav(moviesEntity)
}