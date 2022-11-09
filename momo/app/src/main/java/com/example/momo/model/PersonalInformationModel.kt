package com.example.momo.model

data class PersonalInformationModel(
    val address: String,
    val dob: String,
    val email: String,
    val gender: Int,
    val job: String,
    val relationShip: String,
) {
    constructor() : this("","","",0,"","")
}