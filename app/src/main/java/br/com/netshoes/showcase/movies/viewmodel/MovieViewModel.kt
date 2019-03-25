package br.com.netshoes.showcase.movies.viewmodel

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class MovieViewModel (
    val title: String,
    val description: String,
    val pathImage: String
): Serializable