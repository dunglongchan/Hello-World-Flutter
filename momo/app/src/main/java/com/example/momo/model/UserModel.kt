package com.example.momo.model

import com.example.momo.common.Constant

data class UserModel(
    val user_id: String,
    val avatar: String,
    val name: String,
    val information: Map<String, Any>,
    val security: Map<String, Any>
) {
    constructor(user_id: String) : this(
        user_id,
        "",
        "",
        mapOf(
            Constant.ADDRESS to "",
            Constant.DOB to "",
            Constant.EMAIL to "",
            Constant.GENDER to -1,
            Constant.JOB to "",
            Constant.RELATIONSHIP to ""
        ),
        mapOf(
            Constant.PASSWORD to mapOf(Constant.PASSWORD to "", Constant.TYPE to 0),
            Constant.IDENTIFY_CARD to mapOf(Constant.CARD_NUMBER to "", Constant.VERIFIED to false),
            Constant.BALANCE to ""
        )
    )
}