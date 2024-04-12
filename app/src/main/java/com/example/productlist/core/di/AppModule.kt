package com.example.productlist.core.di

import android.content.Context
import com.example.productlist.cart.repository.CartsRepositoryImpl
import com.example.productlist.database.ProductsDatabase
import com.example.productlist.repository.CartsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext app: Context) = ProductsDatabase(context = app)

    @Provides
    @Singleton
    fun providesCartRepository(
        db: ProductsDatabase,
    ): CartsRepository = CartsRepositoryImpl(db = db)
}