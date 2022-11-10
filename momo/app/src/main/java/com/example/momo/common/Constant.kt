package com.example.momo.common

import com.example.momo.model.ConversationModel
import com.example.momo.model.TransactionModel
import com.example.momo.model.UserModel
import java.util.*

class Constant {
    companion object {
        const val NAME = "name"
        const val AVATAR = "avatar"
        const val INFORMATION = "information"
        const val SECURITY = "security"
        const val TRANSACTION_ID = "transaction_id"
        const val CONVERSATION_ID = "conversation_id"
        const val ADDRESS = "address"
        const val DOB = "dob"
        const val EMAIL = "email"
        const val GENDER = "gender"
        const val JOB = "job"
        const val RELATIONSHIP = "relationship"
        const val BALANCE = "balance"
        const val IDENTIFY_CARD = "identify_card"
        const val CARD_NUMBER = "card_number"
        const val VERIFIED = "verified"
        const val PASSWORD = "password"
        const val TYPE = "type"
        const val USER_ID = "user_id"
        const val DATE = "date"
        const val RECEIVER = "receiver"
        const val SENDER = "sender"
        const val VALUE = "value"
        const val CONTENT = "content"
        const val MESSAGE = "message"
        const val USER_MODEL = 101
        const val TRANSACTION_MODEL = 102
        const val CONVERSATION_MODEL = 103
        const val IS_USER_SIGNED = "is_user_signed"
        const val GET_MODEL = "get_model"
        const val PASSWORD_TYPE = "password_type"

        lateinit var userModel: UserModel

        fun castDataToUserModel(data: MutableMap<String, Any>): UserModel {
            var name = ""
            var user_id = ""
            var avatar = ""
            var information: HashMap<String, Objects> = HashMap()
            var security: HashMap<String, Objects> = HashMap()

            for (i in data) {
                when (i.key) {
                    NAME -> {
                        name = i.value.toString()
                    }
                    AVATAR -> {
                        avatar = i.value.toString()
                    }
                    USER_ID -> {
                        user_id = i.value.toString()
                    }
                    SECURITY -> {
                        security = i.value as HashMap<String, Objects>
                    }
                    INFORMATION -> {
                        information = i.value as HashMap<String, Objects>
                    }
                }
            }

            return UserModel(user_id, avatar, name, information, security)
        }

        fun castDataToTransactionModel(data: MutableMap<String, Any>): TransactionModel {
            var date = ""
            var receiver = ""
            var sender = ""
            var value = ""
            var transactionID = ""

            for (i in data) {
                when (i.key) {
                    TRANSACTION_ID -> {
                        transactionID = i.value.toString()
                    }
                    DATE -> {
                        date = i.value.toString()
                    }
                    RECEIVER -> {
                        receiver = i.value.toString()
                    }
                    SENDER -> {
                        sender = i.value.toString()
                    }
                    VALUE -> {
                        value = i.value.toString()
                    }
                }
            }

            return TransactionModel(transactionID, sender, receiver, date, value)
        }

        fun castDataToCoversationModel(data: MutableMap<String, Any>): ConversationModel {
            var content: HashMap<String, Objects> = HashMap()
            var receiver = ""
            var sender = ""
            var conversationID = ""

            for (i in data) {
                when (i.key) {
                    CONVERSATION_ID -> {
                        conversationID = i.value.toString()
                    }

                    CONTENT -> {
                        content = i.value as HashMap<String, Objects>
                    }

                    RECEIVER -> {
                        receiver = i.value.toString()
                    }

                    SENDER -> {
                        sender = i.value.toString()
                    }
                }
            }

            return ConversationModel(conversationID, sender, receiver, content)
        }
    }
}