package com.yurich.mapsapp.di.data

import com.yurich.mapsapp.data.network.service.VehicleDataSource
import com.yurich.mapsapp.data.network.service.NetworkVehicleService
import com.yurich.mapsapp.data.network.service.NetworkVehicleServiceWrapper
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class NetworkModule {

    @Provides
    @Singleton
    fun retrofit(): NetworkVehicleService =
        Retrofit.Builder()
            .baseUrl("http://data.m-tribes.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create()

    @Binds
    @Singleton
    abstract fun service(wrapper: NetworkVehicleServiceWrapper): VehicleDataSource

}