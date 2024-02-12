package com.puroblast.tintest.domain

import com.puroblast.tintest.domain.model.Film
import com.puroblast.tintest.domain.model.Films
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

const val apiKeyHeader = "X-API-KEY: e30ffed0-76ab-4dd6-b41f-4c9da2b2735b"
interface FilmsApi {
    @Headers(apiKeyHeader)
    @GET("api/v2.2/films/top?type=TOP_100_POPULAR_FILMS")
    suspend fun getTopFilms() : Films

    @Headers(apiKeyHeader)
    @GET("api/v2.2/films/{id}")
    suspend fun getFilm(@Path("id") id : String) : Film
}
