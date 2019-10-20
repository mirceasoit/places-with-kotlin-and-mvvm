package com.mirceasoit.places

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.mirceasoit.places.adapters.RecyclerAdapter
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.mirceasoit.places.models.Place
import com.mirceasoit.places.viewmodels.MainActivityViewModel



class MainActivity : AppCompatActivity() {

    private lateinit var fab: FloatingActionButton
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: RecyclerAdapter
    private lateinit var progressBar: ProgressBar

    private lateinit var mainActivityViewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fab = findViewById(R.id.fab)
        recyclerView = findViewById(R.id.recycler_view)
        progressBar = findViewById(R.id.progress_bar)

        mainActivityViewModel = ViewModelProviders.of(this).get(MainActivityViewModel::class.java)
        mainActivityViewModel.init()
        mainActivityViewModel.getPlaces().observe(this,
            Observer<List<Place>> {
                adapter.notifyDataSetChanged()
            })
        mainActivityViewModel.getIsUpdating().observe(this, Observer<Boolean>{
            if (it) {
                showProgressBar()
            } else {
                hideProgressBar()
                recyclerView.smoothScrollToPosition(mainActivityViewModel.getPlaces().value!!.size - 1)
            }
        })

        fab.setOnClickListener {
            mainActivityViewModel.addPlace(Place("Timisoara", "https://upload.wikimedia.org/wikipedia/commons/6/66/Timisoara_collage.jpg"))
        }

        initRecyclerView()
    }

    private fun initRecyclerView() {
        adapter = RecyclerAdapter(this, mainActivityViewModel.getPlaces())
        val linearLayoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = linearLayoutManager
        recyclerView.adapter = adapter
    }

    private fun showProgressBar() {
        progressBar.visibility = View.VISIBLE
    }

    private fun hideProgressBar() {
        progressBar.visibility = View.GONE
    }
}
