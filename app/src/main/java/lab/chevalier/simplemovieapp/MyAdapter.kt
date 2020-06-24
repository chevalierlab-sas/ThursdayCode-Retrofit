package lab.chevalier.simplemovieapp

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import lab.chevalier.simplemovieapp.api.response.Movie
import lab.chevalier.simplemovieapp.api.response.Result
import lab.chevalier.simplemovieapp.databinding.ItemMovieBinding

class MyAdapter(val context: Context, val clickListener : (Result) -> Unit) : RecyclerView.Adapter<MyAdapter.MovieViewHolder>() {

    var listData : List<Result> = mutableListOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    inner class MovieViewHolder(val binding: ItemMovieBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(data : Result){
            binding.root.setOnClickListener { clickListener(data) }
            binding.tvTitle.text = data.original_title
            binding.tvOverview.text = data.overview
            Glide.with(context)
                .load("https://image.tmdb.org/t/p/w500/${data.poster_path}")
                .override(150, 150)
                .into(binding.imgPoster)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder = MovieViewHolder(
        DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_movie, parent, false)
    )

    override fun getItemCount(): Int = listData.size

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(listData[position])
    }

}