package com.example.verdevo.model

import java.io.Serializable

data class Material(
    val typeId : String = "",
    var name : String = "",
    var description : String = "",
    var picUrl : ArrayList<String> = ArrayList(),
    var showTrends : Boolean = true,
    var heatConductor : Boolean = true,
    var eletricityConductor : Boolean = true,
    var applications : ArrayList<String> = ArrayList()
) : Serializable
