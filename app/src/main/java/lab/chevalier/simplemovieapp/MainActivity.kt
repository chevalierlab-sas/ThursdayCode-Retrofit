package lab.chevalier.simplemovieapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import lab.chevalier.simplemovieapp.api.BaseApi
import lab.chevalier.simplemovieapp.api.response.Movie
import lab.chevalier.simplemovieapp.api.response.Result
import lab.chevalier.simplemovieapp.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var listData : List<Result> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        BaseApi().services.getPopular("b63e4d8ae86a370699745b910ff01fad").enqueue(object : Callback<Movie>{
            override fun onFailure(call: Call<Movie>, t: Throwable) {
                Log.e("GetPopular Error", t.message.toString())
            }

            override fun onResponse(call: Call<Movie>, response: Response<Movie>) {
                if (response.isSuccessful){
                    listData = response.body()?.results!!
                    setupRvMovie(listData)
                }
            }
        })
    }

    private fun setupRvMovie(listData : List<Result>){
        binding.rvMovie.apply {
            this.adapter = MyAdapter(this@MainActivity){ result: Result -> onClick(result) }.apply { this.listData = listData }
            this.layoutManager = LinearLayoutManager(this@MainActivity)
        }
    }

    private fun onClick(data : Result){
        Toast.makeText(this, data.title, Toast.LENGTH_LONG).show()
    }
}