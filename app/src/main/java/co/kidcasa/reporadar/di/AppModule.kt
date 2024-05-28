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

/**
 * Koin module for providing dependencies across the application.
 */
val appModule = module {

    /**
     * Provides a singleton instance of Moshi for JSON serialization and deserialization.
     */
    single {
        Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }

    /**
     * Provides a singleton instance of GitHubApi for accessing GitHub API endpoints.
     *
     * Configures Retrofit with Moshi for JSON conversion, OkHttpClient for HTTP requests,
     * and logging interceptor for debugging purposes.
     */
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
            .build()
            .create(GitHubApi::class.java)
    }

    /**
     * Provides a singleton instance of GitHubRepository.
     *
     * Uses GitHubRepositoryImpl as the implementation of GitHubRepository.
     */
    single<GitHubRepository> { GitHubRepositoryImpl(get()) }

    /**
     * Provides the ViewModel for repositories.
     *
     * Sets up the ReposViewModel with the necessary dependencies.
     */
    viewModel { ReposViewModel(get(), get()) }
}

/**
 * Default connection timeout duration in seconds.
 */
const val DEFAULT_CONNECTION_TIMEOUT: Long = 60

/**
 * MIME type for application/json.
 */
const val APPLICATION_JSON = "application/json"

/**
 * Header key for Content-Type.
 */
const val CONTENT_TYPE = "Content-Type"

/**
 * Header key for Accept.
 */
const val ACCEPT = "Accept"

/**
 * Header key for Authorization.
 */
const val AUTHORIZATION = "Authorization"
