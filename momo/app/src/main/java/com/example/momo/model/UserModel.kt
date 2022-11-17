package com.example.momo.model

import com.example.momo.common.Constant

data class UserModel(
    var user_id: String,
    var avatar: String,
    var name: String,
    var information: MutableMap<String, Any>,
    var security: MutableMap<String, Any>
) {
    constructor(user_id: String) : this(
        user_id,
        "",
        "",
        mutableMapOf(
            Constant.ADDRESS to "",
            Constant.DOB to "",
            Constant.EMAIL to "",
            Constant.GENDER to -1,
            Constant.JOB to "",
            Constant.RELATIONSHIP to ""
        ),
        mutableMapOf(
            Constant.BALANCE to "",
            Constant.PASSWORD to "",
            Constant.TYPE to 0,
            Constant.IDENTIFY_CARD to "",
            Constant.VERIFIED to false
        )
    )
}