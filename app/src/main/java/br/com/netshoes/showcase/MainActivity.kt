package br.com.netshoes.showcase

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import br.com.netshoes.showcase.movies.MovieAdapterView
import br.com.netshoes.showcase.movies.data.Repository
import br.com.netshoes.showcase.movies.data.RepositoryImp
import br.com.netshoes.showcase.movies.viewmodel.MovieViewModel
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

    private val data = mutableListOf<MovieViewModel>()
    private val disposable = CompositeDisposable()

    private val movieViewAdapter: MovieAdapterView by lazy {
        MovieAdapterView { this.gotDetailsMovie(data[it]) }
    }

    private val repository: Repository by lazy {
        RepositoryImp()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setup()
        movie_loader.show()
        disposable.add(getMovies())
    }

    override fun onDestroy() {
        super.onDestroy()
        disposable.clear()
    }

    private fun setup() {
        movie_container.apply {
            adapter = movieViewAdapter
            layoutManager = GridLayoutManager(this@MainActivity, 2)
        }
    }

    private fun getMovies(): Disposable {
       return repository.getMovies()
           .delay(500, TimeUnit.MILLISECONDS)
           .subscribeOn(Schedulers.io())
           .observeOn(AndroidSchedulers.mainThread())
           .subscribe(
               {
                   movie_loader.hide()
                   data.clear()
                   data.addAll(it)
                   movieViewAdapter.setData(it)
               },
               {
                   movie_loader.hide()
               }
           )
    }
}


