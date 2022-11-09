package com.example.momo.model

import java.util.*

data class PersonalSecurityModel(
    val balance: String,
    val identify_card: HashMap<String, Objects>,
    val password: HashMap<String, Objects>,
) {
    constructor() : this("", hashMapOf(), hashMapOf())
}