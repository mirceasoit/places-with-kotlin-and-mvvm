package com.mirceasoit.places.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mirceasoit.places.models.Place
import com.mirceasoit.places.repositories.PlaceRepository
import android.os.AsyncTask


class MainActivityViewModel : ViewModel() {

    var places = MutableLiveData<List<Place>>()
    private val repository = PlaceRepository
    var isUpdating = MutableLiveData<Boolean>()


    fun init() {
        places = repository.getPlaces()
    }

    fun getPlaces(): LiveData<List<Place>> {
        return places
    }

    fun getIsUpdating(): LiveData<Boolean> {
        return isUpdating
    }

    fun addPlace(place: Place) {
        isUpdating.value = true

        object : AsyncTask<Void, Void, Void>() {
            override fun onPostExecute(aVoid: Void?) {
                super.onPostExecute(aVoid)
                var currentPlaces: MutableList<Place>? = places.value?.toMutableList()
                currentPlaces?.add(place)
                places.postValue(currentPlaces)
                isUpdating.postValue(false)
            }

            override fun doInBackground(vararg voids: Void): Void? {

                try {
                    Thread.sleep(2000)
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }

                return null
            }
        }.execute()
    }
}