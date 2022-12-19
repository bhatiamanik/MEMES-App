package com.example.memes

import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    var currentImageUrl: String?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        loadMeme()// to load Memes
    }

    private fun loadMeme() {
        progressbar.visibility = View.VISIBLE
        val queue = Volley.newRequestQueue(this)          //volley library used..
        val url ="https://meme-api.com/gimme"
        val jsonRequest = JsonObjectRequest(
            Request.Method.GET, url, null,
            Response.Listener { response ->
                currentImageUrl =response.getString("url")
                Glide.with(this).load(currentImageUrl).listener(object: RequestListener<Drawable>{
                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: Target<Drawable>?,
                        isFirstResource: Boolean
                    ): Boolean {
                        progressbar.visibility=View.GONE
                        return false

                    }

                    override fun onResourceReady(
                        resource: Drawable?,
                        model: Any?,
                        target: Target<Drawable>?,
                        dataSource: DataSource?,
                        isFirstResource: Boolean
                    ): Boolean {
                        progressbar.visibility=View.GONE
                        return false

                    }


                }).into(memeimageView)


            },
            Response.ErrorListener { Toast.makeText(this, "ERROR ", Toast.LENGTH_LONG).show() })

        queue.add(jsonRequest)


    }

    fun nextMeme(view: View)         //function for next meme
    {
        loadMeme()


    }

    fun shareMeme(view: View)        // function for share meme
    {



    }
}