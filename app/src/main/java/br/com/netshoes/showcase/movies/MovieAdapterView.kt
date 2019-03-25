package br.com.netshoes.showcase.movies

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.netshoes.showcase.R
import br.com.netshoes.showcase.getImagePath
import br.com.netshoes.showcase.movies.viewmodel.MovieViewModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.adapter_movies.view.*

class MovieAdapterView(private val click: (Int) -> Any): RecyclerView.Adapter<MovieAdapterView.MovieViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.adapter_movies, parent, false)
        return MovieViewHolder(view, click)
    }

    override fun getItemCount(): Int = data.count()

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(data[position])
    }

    private val data = mutableListOf<MovieViewModel>()

    fun setData(item: List<MovieViewModel>) {
        data.clear()
        data.addAll(item)
        notifyDataSetChanged()
    }

    class MovieViewHolder(
        itemView: View,
        val click: (Int) -> Any
    ): RecyclerView.ViewHolder(itemView) {

        fun bind(item: MovieViewModel) {
            with(itemView) {
                movie_title.text = item.title
                Picasso.get()
                    .load(getImagePath(item))
                    .fit()
                    .centerCrop()
                    .into(movie_image)
                setOnClickListener{ click(adapterPosition) }
            }
        }
    }

}