package id.husni.moviecatalogue.di

import android.content.Context
import id.husni.moviecatalogue.data.source.CatalogueRepository
import id.husni.moviecatalogue.data.source.local.LocalDataSource
import id.husni.moviecatalogue.data.source.local.room.CatalogueDatabase
import id.husni.moviecatalogue.data.source.remote.RemoteDataSource
import id.husni.moviecatalogue.utils.ApiHelper
import id.husni.moviecatalogue.utils.AppExecutors

object Injection {
    fun provideRepo(context: Context): CatalogueRepository {
        val database = CatalogueDatabase.getInstance(context)

        val remoteRepo = RemoteDataSource.getInstance(ApiHelper())
        val localRepo = LocalDataSource.getInstance(database.catalogueDao())
        val appExecutors= AppExecutors()
        return CatalogueRepository.getInstance(remoteRepo,localRepo,appExecutors)
    }
}