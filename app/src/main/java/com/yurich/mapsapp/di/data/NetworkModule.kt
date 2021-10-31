package com.yurich.mapsapp.di.data

import com.yurich.mapsapp.data.network.service.NetworkVehicleService
import com.yurich.mapsapp.data.network.service.NetworkVehicleServiceWrapper
import com.yurich.mapsapp.data.network.service.VehicleDataSource
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
object NetworkModule {

    @Provides
    @Singleton
    fun retrofit(): NetworkVehicleService =
        Retrofit.Builder()
            .baseUrl("https://data.m-tribes.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create()

    @Provides
    @Singleton
    fun service(service: NetworkVehicleService): VehicleDataSource =
        NetworkVehicleServiceWrapper(service)

}