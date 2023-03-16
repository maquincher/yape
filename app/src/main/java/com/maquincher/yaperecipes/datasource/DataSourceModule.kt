package com.maquincher.yaperecipes.datasource

import com.maquincher.yaperecipes.recipes.RecipesApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Named
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class DataSourceModule {
    @Singleton
    @Provides
    @Named("BaseUrl")
    fun providerBaseUrl() = "https://eo4ih3caxevac8z.m.pipedream.net"

    @Singleton
    @Provides
    fun providerRetrofit(@Named("BaseUrl") baseUrl: String): Retrofit =
        Retrofit.Builder().baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            // we need to add converter factory to
            // convert JSON object to Java object
            .build()

    @Singleton
    @Provides
    fun getRestDataSource(retrofit: Retrofit): RecipesApi =
        retrofit.create<RecipesApi>()
}


