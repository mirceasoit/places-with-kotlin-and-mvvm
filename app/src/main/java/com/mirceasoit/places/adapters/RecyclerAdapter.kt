package com.mirceasoit.places.adapters

import android.content.Context
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mirceasoit.places.models.Place
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mirceasoit.places.R
import de.hdodenhof.circleimageview.CircleImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class RecyclerAdapter(private var context: Context, private var places: LiveData<List<Place>>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_listitem, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return places.value!!.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        // Set the name
        (holder as ViewHolder).title.text = places.value!![position].title

        // Set the image
        val defaultOptions = RequestOptions()
            .error(R.drawable.ic_launcher_background)
        Glide.with(context)
            .setDefaultRequestOptions(defaultOptions)
            .load(places.value!![position].imageUrl)
            .into(holder.image)
    }

    private inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val image: CircleImageView = itemView.findViewById(R.id.image)
        val title: TextView = itemView.findViewById(R.id.title)

    }
}