package `in`.androidcodes.lastfmclone

import `in`.androidcodes.lastfmclone.adapter.GenreAdapter2
import `in`.androidcodes.lastfmclone.model.topgenre.Tag
import `in`.androidcodes.lastfmclone.repo.Repository
import `in`.androidcodes.lastfmclone.utils.Constants.Companion.API_KEY
import `in`.androidcodes.lastfmclone.utils.Resource
import `in`.androidcodes.lastfmclone.views.TagViewModel
import `in`.androidcodes.lastfmclone.views.TagsModelFactory
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.NonCancellable.start
import okhttp3.logging.HttpLoggingInterceptor

class MainActivity : AppCompatActivity(), GenreAdapter2.OnItemClickListener {

    private lateinit var viewModel: TagViewModel
    private lateinit var genreAdapter: GenreAdapter2
    private lateinit var genreAdapterTop: GenreAdapter2
    private val responseList = ArrayList<`in`.androidcodes.lastfmclone.model.top.Tag>()
    private val responseList10 = ArrayList<`in`.androidcodes.lastfmclone.model.top.Tag>()
    private lateinit var genreRecyclerTop: RecyclerView
    private lateinit var genreRecycler: RecyclerView
    lateinit var btnExpend: ImageView
    private var flag=false
    private lateinit var genreName: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnExpend=btn_expend
        genreRecyclerTop = recycle_view_top


        genreRecycler = recycle_view
        genreAdapter = GenreAdapter2(responseList, this)
        val layoutManager = GridLayoutManager(this, 3)
        genreRecyclerTop.layoutManager = layoutManager
        genreRecyclerTop.adapter = genreAdapter


        genreAdapterTop = GenreAdapter2(responseList, this)
        val layoutManagerTop = GridLayoutManager(this, 3)
        genreRecyclerTop.layoutManager = layoutManagerTop
        genreRecyclerTop.adapter = genreAdapterTop

        genreName = "Rock"



        val repository = Repository()
        val viewModelFactory = TagsModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(TagViewModel::class.java)

        viewModel.getNewTopGenres()
        viewModel.topGenres2.observe(this, Observer {
            if (it.isSuccessful) {
                Log.d("RESPONSE 2", "Response: ${it.body()!!.toptags.attr.total}")
                val sizeOfItem = it.body()!!.toptags.tag.size
                print(sizeOfItem)
                Log.d("SIZE", "Size $sizeOfItem")
                for (element in it.body()!!.toptags.tag) {
                    Log.d("RESPONSE", "Response: ${listOf(element.name)}")

                    val item = `in`.androidcodes.lastfmclone.model.top.Tag(
                        element.count,
                        element.name,
                        element.reach
                    )
                    responseList.add(item)
                    Log.d("TAG NAME", item.name)
                }
                var i = 0
                for (it in responseList) {
                    responseList10.add(it)
                    i++
                    if (i == 12)
                        break
                }
                genreAdapter.notifyDataSetChanged()
                genreAdapterTop.notifyDataSetChanged()
            } else {
                Log.d("RESPONSE 2", "Response: ${it.errorBody()}")
            }
        })

        btnExpend.setOnClickListener {
            if(!flag){
                genreRecycler.visibility= View.VISIBLE
                genreRecyclerTop.visibility=View.GONE
                flag=true
                btnExpend.setImageResource(R.drawable.ic_down)
            }else{
                genreRecycler.visibility=View.GONE
                genreRecyclerTop.visibility=View.VISIBLE
                btnExpend.setImageResource(R.drawable.ic_start)
                flag=false
            }
        }

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




    }

    override fun onItemClick(position: Int) {
        val intent = Intent(this, GenreDetailActivity::class.java)
        intent.putExtra("genre", genreName)
        startActivity(intent)
    }
}