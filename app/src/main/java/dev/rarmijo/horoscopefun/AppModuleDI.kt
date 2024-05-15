package dev.rarmijo.horoscopefun

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.rarmijo.horoscopefun.data.local.providers.HoroscopeProvider
import dev.rarmijo.horoscopefun.data.repository.HoroscopeInfoRepoImpl
import dev.rarmijo.horoscopefun.domain.repository.HoroscopeInfoRepo

@Module
@InstallIn(SingletonComponent::class)
class AppModuleDI {

    @Provides
    fun provideHoroscopeProvider(): HoroscopeProvider = HoroscopeProvider()

    @Provides
    fun provideHoroscopeRepo(horoscopeProvider: HoroscopeProvider): HoroscopeInfoRepo =
        HoroscopeInfoRepoImpl(horoscopeProvider)
}