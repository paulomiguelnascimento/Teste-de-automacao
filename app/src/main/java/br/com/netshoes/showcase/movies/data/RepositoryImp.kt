package br.com.netshoes.showcase.movies.data

import br.com.netshoes.showcase.TEXT_EMPTY
import br.com.netshoes.showcase.getMockMovies
import br.com.netshoes.showcase.movies.viewmodel.MovieViewModel
import com.google.gson.Gson
import io.reactivex.Single
import java.util.concurrent.TimeUnit

interface Repository {
    fun getMovies(): Single<List<MovieViewModel>>
}


class RepositoryImp : Repository {

    override fun getMovies(): Single<List<MovieViewModel>> {
        val data = Gson().fromJson<Data>(getMockMovies(), Data::class.java)
            .results?.map {
            MovieViewModel(
                it?.title ?: it?.name ?: TEXT_EMPTY,
                it?.overview ?: TEXT_EMPTY,
                it?.posterPath ?: TEXT_EMPTY
            )
        } ?: listOf()

        return Single.just(data).delay(500, TimeUnit.MILLISECONDS)
    }

}