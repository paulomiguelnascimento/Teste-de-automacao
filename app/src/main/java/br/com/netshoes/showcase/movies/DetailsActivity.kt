package br.com.netshoes.showcase.movies

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.netshoes.showcase.MOVIE_EXTRA
import br.com.netshoes.showcase.R
import br.com.netshoes.showcase.getImagePath
import br.com.netshoes.showcase.movies.viewmodel.MovieViewModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_details.*

class DetailsActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

       val movie =  intent.getSerializableExtra(MOVIE_EXTRA) as MovieViewModel

        detail_title.text = movie.title
        detail_description.text = movie.description

        Picasso.get().load(getImagePath(movie))
            .fit()
            .centerCrop()
            .into(detail_image)
    }

}