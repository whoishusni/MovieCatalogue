package id.husni.moviecatalogue.di

import android.app.Application
import id.husni.moviecatalogue.data.source.CatalogueRepository
import id.husni.moviecatalogue.data.source.local.LocalDataSource
import id.husni.moviecatalogue.data.source.remote.RemoteDataSource
import id.husni.moviecatalogue.utils.ApiHelper

object Injection {
    fun provideRepo(application: Application): CatalogueRepository {

        val remoteRepo = RemoteDataSource.getInstance(ApiHelper())
        val localRepo = LocalDataSource.getInstance(application)
        return CatalogueRepository.getInstance(remoteRepo,localRepo)
    }
}