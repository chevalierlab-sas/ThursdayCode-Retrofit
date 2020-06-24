package lab.chevalier.simplemovieapp.api

import lab.chevalier.simplemovieapp.api.response.Movie
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface Service {

    @GET("movie/popular")
    fun getPopular(@Query("api_key") apiKey: String) : Call<Movie>

}