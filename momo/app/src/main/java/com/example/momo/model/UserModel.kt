package com.example.momo.model

data class UserModel(
    val user_id: String,
    val avatar: String,
    val name: String,
    val personal: PersonalModel,
) {
    constructor() : this(
        "", "", "", PersonalModel(
            PersonalInformationModel(),
            PersonalSecurityModel()
        )
    )
}