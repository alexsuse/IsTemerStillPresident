package com.example.susemihl.myapplication

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.runBlocking
import java.net.URL

suspend fun fetch_url(url: String): String {
    return URL(url).readText()
}

fun fetch_async(url: String, view: TextView) = runBlocking {
    val result = async(CommonPool) { fetch_url(url) }
    view.setText(result.await())
}

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // mainTextView.setText("Hello there.")
        mainButton.setOnClickListener {
            mainButton.setText("Check again.")
            fetch_async("https://bookshelf-168523.appspot.com/", mainTextView)
        }

    }
}
