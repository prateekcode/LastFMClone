package `in`.androidcodes.lastfmclone.views

import `in`.androidcodes.lastfmclone.model.top.Toptags
import `in`.androidcodes.lastfmclone.model.topgenre.TopGenresResponse
import `in`.androidcodes.lastfmclone.repo.Repository
import `in`.androidcodes.lastfmclone.utils.Constants.Companion.API_KEY
import `in`.androidcodes.lastfmclone.utils.Resource
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import retrofit2.Response

class TagViewModel(private val repo: Repository): ViewModel(){
    val tagsResponse: MutableLiveData<Response<Toptags>> = MutableLiveData()

    private val _newTagResponse: MutableLiveData<Resource<Toptags>> = MutableLiveData()
    val tagResponseNew: LiveData<Resource<Toptags>> = _newTagResponse

    private val _topGenres: MutableLiveData<Resource<TopGenresResponse>> = MutableLiveData()
    val topGenres: LiveData<Resource<TopGenresResponse>> = _topGenres

//    private val _topGenres2: MutableLiveData<Resource<TopGenresResponse>> = MutableLiveData()
//    val topGenres2: LiveData<Resource<TopGenresResponse>> = _topGenres2

    //Use this
    val topGenres2: MutableLiveData<Response<TopGenresResponse>> = MutableLiveData()

    //Ignore
    fun getTags(api_key: String){
        viewModelScope.launch {
            val response = repo.getTags(api_key)
            tagsResponse.value = response
        }
    }

    //Use this
    fun getNewTopGenres(){
        viewModelScope.launch {
            //_topGenres2.postValue(Resource.Loading())
            val response = repo.getAllGenres(API_KEY)
            topGenres2.value = response
        }
    }


    //Ignore
    init {
        getNewTags(api_key = API_KEY)
        getTopGenres()
    }

    //Ignore
    fun getNewTags(api_key: String){
        viewModelScope.launch {
            _newTagResponse.postValue(Resource.Loading())
            val response = repo.getTags(api_key)
            _newTagResponse.postValue(handleTopGenresResponse(response))
        }
    }

    //Ignore
    fun getTopGenres(){
        viewModelScope.launch {
            _topGenres.postValue(Resource.Loading())
            val response = repo.getAllGenres(API_KEY)
            _topGenres.postValue(handleTopGenresResponse2(response))
        }
    }

//    fun getNewTopGeneres(){
//        viewModelScope.launch {
//            //_topGenres2.postValue(Resource.Loading())
//            val response = repo.getAllGenres(API_KEY)
//            topGenres2.value = response
//        }
//    }

    //Ignore
    private fun handleTopGenresResponse(response: Response<Toptags>): Resource<Toptags>{
        if (response.isSuccessful){
            response.body()?.let {
                resultResponse ->
                return Resource.Success(resultResponse)
            }
        }
        return Resource.Error(response.message())
    }

    //Ignore
    private fun handleTopGenresResponse2(response: Response<TopGenresResponse>): Resource<TopGenresResponse>{
        if (response.isSuccessful){
            response.body()?.let {
                    resultResponse ->
                return Resource.Success(resultResponse)
            }
        }
        return Resource.Error(response.message())
    }
}
