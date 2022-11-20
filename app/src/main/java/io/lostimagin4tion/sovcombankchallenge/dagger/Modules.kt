package io.lostimagin4tion.sovcombankchallenge.dagger

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Binds
import dagger.Module
import dagger.Provides
import io.lostimagin4tion.sovcombankchallenge.data.api.SovcombankApi
import io.lostimagin4tion.sovcombankchallenge.data.repositories.AuthorizationRepository
import io.lostimagin4tion.sovcombankchallenge.domain.repositories.IAuthorizationRepository
import io.lostimagin4tion.sovcombankchallenge.state.Session
import io.lostimagin4tion.sovcombankchallenge.state.Settings
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber
import javax.inject.Singleton

@Module
class DataModule {

    @Singleton
    @Provides
    fun provideSovcombankApi(session: Session, gson: Gson): SovcombankApi {
        return Retrofit.Builder()
            .baseUrl(SovcombankApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(OkHttpClient.Builder().build())
            .build()
            .create(SovcombankApi::class.java)
    }

    @Provides
    @Singleton
    fun provideGson() = GsonBuilder()
        .serializeNulls()
        .create()

    @Provides
    @Singleton
    fun provideSession(context: Context) = Session(context)

    @Provides
    @Singleton
    fun provideSettings(context: Context) = Settings(context)
}

@Module
abstract class RepositoriesModule {
    @Singleton
    @Binds
    abstract fun bindIAuthorizationRepository(authRepository: AuthorizationRepository): IAuthorizationRepository
}