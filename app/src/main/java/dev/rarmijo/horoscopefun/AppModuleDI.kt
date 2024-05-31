package dev.rarmijo.horoscopefun

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.rarmijo.horoscopefun.data.Constants.PREDICTIONS_API_BASE_URL
import dev.rarmijo.horoscopefun.data.local.providers.HoroscopeProvider
import dev.rarmijo.horoscopefun.data.remote.NoConnectionInterceptor
import dev.rarmijo.horoscopefun.data.remote.PredictionApi
import dev.rarmijo.horoscopefun.data.repository.HoroscopeInfoRepoImpl
import dev.rarmijo.horoscopefun.data.repository.PredictionRepoImpl
import dev.rarmijo.horoscopefun.domain.repository.HoroscopeInfoRepo
import dev.rarmijo.horoscopefun.domain.repository.PredictionRepo
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

@Module
@InstallIn(SingletonComponent::class)
class AppModuleDI {

    @Provides
    fun provideHoroscopeProvider(): HoroscopeProvider = HoroscopeProvider()

    @Provides
    fun provideHoroscopeRepo(horoscopeProvider: HoroscopeProvider): HoroscopeInfoRepo =
        HoroscopeInfoRepoImpl(horoscopeProvider)


    ////////////////////////////////////////////////////////////////////////////////////////////////


    @Provides
     fun provideOkHttpClient():OkHttpClient{
         val interceptor = HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.HEADERS }
         return OkHttpClient.Builder()
             //.addInterceptor(NoConnectionInterceptor())
             .addInterceptor(interceptor)
             .build()
     }

    @Provides
    fun providePredictionApi(client: OkHttpClient): PredictionApi{
        return Retrofit.Builder()
            .baseUrl(PREDICTIONS_API_BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .client(client)
            .build()
            .create(PredictionApi::class.java)
    }


    @Provides
    fun providePredictionRepo(predictionApi: PredictionApi): PredictionRepo = PredictionRepoImpl(predictionApi)
}