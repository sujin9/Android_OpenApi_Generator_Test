package com.example.test

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.network.api.PetApi
import com.example.network.infrastructure.ApiClient
import com.example.network.model.Pet
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val pet = Pet(
            "jiny",
            listOf("https://get.pxhere.com/photo/nature-grass-flower-wildlife-kitten-cat-mammal-fauna-close-up-whiskers-vertebrate-domestic-cat-macro-photography-young-cat-red-mackerel-tabby-red-cat-cat-baby-wild-cat-small-to-medium-sized-cats-cat-like-mammal-634099.jpg"),
        )
        ApiClient().createService(PetApi::class.java).addPet(pet).enqueue(object : Callback<Pet> {
            override fun onResponse(call: Call<Pet>, response: Response<Pet>) {
                Log.d("ssuu", "onResponse: ${response.body()}")
            }

            override fun onFailure(call: Call<Pet>, t: Throwable) {
                Log.d("ssuu", "onFailure: ${t.message}")
            }
        })
    }
}
