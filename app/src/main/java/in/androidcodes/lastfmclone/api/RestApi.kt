package `in`.androidcodes.lastfmclone.api

import `in`.androidcodes.lastfmclone.model.top.Toptags
import `in`.androidcodes.lastfmclone.model.topgenre.TopGenresResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RestApi {

    @GET("?method=tag.getTopTags&format=json")
    suspend fun getTopTags(@Query("api_key") api_key:String): Response<Toptags>

    @GET("?method=tag.getTopTags&format=json")
    suspend fun getTopGenres(@Query("api_key") api_key:String): Response<TopGenresResponse>



}