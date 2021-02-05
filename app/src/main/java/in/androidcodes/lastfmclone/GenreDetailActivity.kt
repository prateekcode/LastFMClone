package `in`.androidcodes.lastfmclone

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_genre_detail.*

class GenreDetailActivity : AppCompatActivity() {

    private lateinit var textViewString: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_genre_detail)

        textViewString = textView

        val genreString = intent.getStringExtra("genre")
        textViewString.text = genreString.toString()
    }
}