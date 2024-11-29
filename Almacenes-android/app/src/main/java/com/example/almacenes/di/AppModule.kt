package com.example.almacenes.di

import com.example.almacenes.data.network.ApiService
import com.example.almacenes.data.repository.AlmacenRepository
import com.example.almacenes.data.repository.AlmacenRepositoryImpl
import com.example.almacenes.data.repository.ExistenciaRepository
import com.example.almacenes.data.repository.ExistenciaRepositoryImpl
import com.example.almacenes.data.repository.ProductoRepository
import com.example.almacenes.data.repository.ProductoRepositoryImpl
import com.example.almacenes.data.repository.TransaccionRepository
import com.example.almacenes.data.repository.TransaccionRepositoryImpl
import com.example.almacenes.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideApiService(): ApiService {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }

    @Provides
    fun provideExistenciaRepository(apiService: ApiService): ExistenciaRepository {
        return ExistenciaRepositoryImpl(apiService)
    }

    @Provides
    fun provideTransaccionRepository(apiService: ApiService): TransaccionRepository {
        return TransaccionRepositoryImpl(apiService)
    }

    @Provides
    fun provideProductoRepository(apiService: ApiService): ProductoRepository {
        return ProductoRepositoryImpl(apiService)
    }

    @Provides
    fun provideAlmacenRepository(apiService: ApiService): AlmacenRepository {
        return AlmacenRepositoryImpl(apiService)
    }
}
