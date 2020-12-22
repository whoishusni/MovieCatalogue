package id.husni.moviecatalogue.data.source.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import id.husni.moviecatalogue.data.source.local.entity.MoviesEntity
import id.husni.moviecatalogue.data.source.local.entity.SeriesEntity

@Database(entities = [MoviesEntity::class, SeriesEntity::class], version = 1,exportSchema = false)
abstract class CatalogueDatabase: RoomDatabase() {
    abstract fun catalogueDao(): CatalogueDao
    companion object{
        @Volatile
        var INSTANCE : CatalogueDatabase? = null

        @JvmStatic
        fun getInstance(context: Context): CatalogueDatabase {
            if (INSTANCE == null) {
                synchronized(this){
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(context.applicationContext,CatalogueDatabase::class.java,"favourite_list")
                            .allowMainThreadQueries()
                            .build()
                    }
                }
            }
            return INSTANCE as CatalogueDatabase
        }
    }
}