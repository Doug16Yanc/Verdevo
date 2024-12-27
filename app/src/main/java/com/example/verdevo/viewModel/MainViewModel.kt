package com.example.verdevo.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.verdevo.model.Material
import com.example.verdevo.model.Slider
import com.example.verdevo.model.Type
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.Query
import com.google.firebase.database.ValueEventListener

class MainViewModel() : ViewModel() {

    private val firebaseDatabase = FirebaseDatabase.getInstance()

    private val _banner = MutableLiveData<List<Slider>>()
    private val _type = MutableLiveData<MutableList<Type>>()
    private val _trends = MutableLiveData<MutableList<Material>>()

    val banners : LiveData<List<Slider>> = _banner
    val types : LiveData<MutableList<Type>> = _type
    val trends : LiveData<MutableList<Material>> = _trends

    fun loadBanners() {
        val Reference = firebaseDatabase.getReference("Banner")
        Reference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val lists = mutableListOf<Slider>()
                for (childSnapshot in snapshot.children) {
                    val list = childSnapshot.getValue(
                        Slider::class.java
                    )
                    if (list != null) {
                        lists.add(list)
                    }
                }
                _banner.value = lists
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("Firebase", "Erro na consulta : ${error.message}")
            }

        })
    }
    fun loadTypes() {
        val Reference = firebaseDatabase.getReference("Types")
        Reference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val lists = mutableListOf<Type>()
                for (childSnapshot in snapshot.children) {
                    val list = childSnapshot.getValue(
                        Type::class.java
                    )
                    if (list != null) {
                        lists.add(list)
                    }
                }
                _type.value = lists
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("Firebase", "Erro na consulta : ${error.message}")
            }

        })
    }
    fun loadTrends() {
        val Reference = firebaseDatabase.getReference("Material")
        val query : Query = Reference.orderByChild("showTrends").equalTo(true)
        query.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val lists = mutableListOf<Material>()
                for (childSnapshot in snapshot.children) {
                    val list = childSnapshot.getValue(Material::class.java)
                    if (list != null) {
                        lists.add(list)
                    }
                }
                _trends.value = lists
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("Firebase", "Erro na consulta : ${error.message}")
            }

        })
    }  
    
    fun loadFiltered(id : String) {

    }
}