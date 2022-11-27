package com.example.momo.model

import com.example.momo.common.Constant

data class UserModel(
    var user_id: String,
    var avatar: String,
    var phoneNumber: String,
    var name: String,
    var information: MutableMap<String, Any>,
    var security: MutableMap<String, Any>,
    var policy: MutableMap<String, Int>,
    var bankAccount: ArrayList<String>
) {
    constructor(user_id: String, phone: String) : this(
        user_id,
        "",
        phone,
        "",
        mutableMapOf(
            Constant.ADDRESS to "",
            Constant.DOB to "",
            Constant.EMAIL to "",
            Constant.GENDER to -1,
            Constant.JOB to "",
            Constant.RELATIONSHIP to "",
            Constant.NATIONAL to "",
            Constant.EDUCATION to "",
            Constant.HABIT to "",
            Constant.NICKNAME to "",

            ),
        mutableMapOf(
            Constant.BALANCE to 0,
            Constant.PASSWORD to "",
            Constant.TYPE to 0,
            Constant.CARD_NUMBER to "",
            Constant.VERIFIED to false,
            Constant.FRIENDS to listOf<String>(

            ),
        ),
        mutableMapOf(
            Constant.ADDRESS to 2,
            Constant.DOB to 2,
            Constant.EMAIL to 2,
            Constant.GENDER to 2,
            Constant.JOB to 2,
            Constant.RELATIONSHIP to 2,
            Constant.NATIONAL to 2,
            Constant.EDUCATION to 2,
            Constant.HABIT to 2,
            Constant.NICKNAME to 2
        ),
        arrayListOf("")
    )
}