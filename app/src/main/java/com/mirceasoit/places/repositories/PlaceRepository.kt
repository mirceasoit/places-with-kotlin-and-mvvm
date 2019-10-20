package com.mirceasoit.places.repositories

import com.mirceasoit.places.models.Place
import androidx.lifecycle.MutableLiveData



object PlaceRepository {

    private val dataSet = ArrayList<Place>()

    fun getPlaces(): MutableLiveData<List<Place>> {
        setPlaces()
        val data = MutableLiveData<List<Place>>()
        data.value = dataSet
        return data
    }

    private fun setPlaces() {
        dataSet.add(
            Place(
                "Cluj-Napoca",
                "https://upload.wikimedia.org/wikipedia/commons/thumb/7/78/CJROCluj-Napoca_19.jpg/290px-CJROCluj-Napoca_19.jpg"
            )
        )
        dataSet.add(
            Place(
                "Sibiu",
                "https://upload.wikimedia.org/wikipedia/commons/6/68/Sibiu_200811_800px.jpg"
            )
        )
    }

}