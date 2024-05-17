/**
 * Developed by Malcolm Woods
 */

package co.kidcasa.reporadar.di

import co.kidcasa.reporadar.BuildConfig
import co.kidcasa.reporadar.api.GitHubApi
import co.kidcasa.reporadar.api.GitHubRepository
import co.kidcasa.reporadar.api.GitHubRepositoryImpl
import co.kidcasa.reporadar.topstarredrepos.viewmodel.ReposViewModel
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import java.util.concurrent.TimeUnit
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

val appModule = module {

    single {
        Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }

    single {
        val logging = HttpLoggingInterceptor()
        logging.level =
            if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE

        val client = OkHttpClient.Builder()
            .addInterceptor(logging)
            .addInterceptor { chain ->
                val original = chain.request()
                val requestBuilder = original.newBuilder()
                    .addHeader(CONTENT_TYPE, APPLICATION_JSON)
                    .addHeader(ACCEPT, APPLICATION_JSON)
                    // Rate limit problem starts to happen when we try to get contributors
                    .addHeader(AUTHORIZATION, "token ${BuildConfig.GITHUB_TOKEN}")
                val request = requestBuilder.build()
                chain.proceed(request)
            }
            .connectTimeout(DEFAULT_CONNECTION_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(DEFAULT_CONNECTION_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(DEFAULT_CONNECTION_TIMEOUT, TimeUnit.SECONDS).build()

        Retrofit.Builder()
            .baseUrl(BuildConfig.GITHUB_BASE_URL)
            .client(client)
             .addConverterFactory(MoshiConverterFactory.create(get()))
            // .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(GitHubApi::class.java)
    }

    single<GitHubRepository> { GitHubRepositoryImpl(get()) }
    /**
     * ViewModel providers
     */
    viewModel { ReposViewModel(get(), get()) }
}

const val DEFAULT_CONNECTION_TIMEOUT: Long = 60
const val APPLICATION_JSON = "application/json"
const val CONTENT_TYPE = "Content-Type"
const val ACCEPT = "Accept"
const val AUTHORIZATION = "Authorization"
