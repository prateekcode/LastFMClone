package `in`.androidcodes.lastfmclone.repo

import `in`.androidcodes.lastfmclone.api.RetrofitInstance
import `in`.androidcodes.lastfmclone.model.top.Toptags
import retrofit2.Response

class Repository {

    //Ignore
    suspend fun getTags(api_key:String): Response<Toptags>{
        return RetrofitInstance.api.getTopTags(api_key)
    }

    //Use this
    suspend fun getAllGenres(api_key: String) = RetrofitInstance.api.getTopGenres(api_key)

}