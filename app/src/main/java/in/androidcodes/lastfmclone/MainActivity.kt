package `in`.androidcodes.lastfmclone

import `in`.androidcodes.lastfmclone.repo.Repository
import `in`.androidcodes.lastfmclone.utils.Constants.Companion.API_KEY
import `in`.androidcodes.lastfmclone.utils.Resource
import `in`.androidcodes.lastfmclone.views.TagViewModel
import `in`.androidcodes.lastfmclone.views.TagsModelFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import okhttp3.logging.HttpLoggingInterceptor

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: TagViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val repository = Repository()
        val viewModelFactory = TagsModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(TagViewModel::class.java)
//        viewModel.getTags(API_KEY)
//        viewModel.tagsResponse.observe(this, Observer {
//            if (it.isSuccessful) {
//                Log.d("RESPONSE", "Response ${it.body()}")
//                //Log.d("RESPONSE", "Response ${it.body()!!.tag.size}")
//            } else {
//                Log.d("RESPONSE", "Response ${it.errorBody()}")
//            }
//        })

//        viewModel.getNewTags(API_KEY)
//        viewModel.tagResponseNew.observe(this, Observer { response ->
//            when (response) {
//                is Resource.Success -> {
//                    Log.d("RESPONSE", "Response: ${response.data!!}")
//                }
//                is Resource.Loading -> {
//                    Log.d("RESPONSE", "Loading...")
//                }
//                is Resource.Error -> {
//                    Log.d("RESPONSE", "Response Error: ${response.message} ")
//                }
//            }
//        })


//        viewModel.getTopGenres()
//        viewModel.topGenres.observe(this, Observer {
//            response ->
//            when (response) {
//                is Resource.Success -> {
//                    Log.d("RESPONSE", "Response: ${response.data!!}")
//                }
//                is Resource.Loading -> {
//                    Log.d("RESPONSE", "Loading...")
//                }
//                is Resource.Error -> {
//                    Log.d("RESPONSE", "Response Error: ${response.message} ")
//                }
//            }
//        })


        viewModel.getNewTopGenres()
        viewModel.topGenres2.observe(this, Observer {
            if (it.isSuccessful) {
                Log.d("RESPONSE 2", "Response: ${it.body()}")
            }
            else{
                Log.d("RESPONSE 2", "Response: ${it.errorBody()}")
            }
        })





    }
}