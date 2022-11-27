package com.example.momo.common

import com.example.momo.model.ConversationModel
import com.example.momo.model.TransactionModel
import com.example.momo.model.UserModel
import java.util.*

class Constant {
    companion object {
        const val APP_DB_NAME = "Momo.db"
        const val NATIONAL = "national"
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
        const val NICKNAME = "nickname"
        const val EDUCATION = "education"
        const val POLYCY = "policy"
        const val HABIT = "habit"
        const val BANK_ACCOUNT = "bank_account"
        const val PUBLIC = "public"
        const val FRIENDS = "friends"
        const val PRIVATE = "private"
        const val MESSAGE = "message"
        const val USER_MODEL = 101
        const val PHONE_NUMBER = "phone_number"
        const val TRANSACTION_MODEL = 102
        const val CONVERSATION_MODEL = 103
        const val IS_USER_SIGNED = "is_user_signed"
        const val GET_MODEL = "get_model"
        var userModel: UserModel = UserModel("", "")

        fun castDataToUserModel(data: MutableMap<String, Any>): UserModel {
            var name = ""
            var user_id = ""
            var phoneNumber = ""
            var avatar = ""
            var information: MutableMap<String, Any> = HashMap()
            var security: MutableMap<String, Any> = HashMap()
            var policy: MutableMap<String, Int> = HashMap()
            var bankAccount: ArrayList<String> = ArrayList()
            for (i in data) {
                when (i.key) {
                    NAME -> {
                        name = i.value.toString()
                    }
                    AVATAR -> {
                        avatar = i.value.toString()
                    }
                    PHONE_NUMBER -> {
                        phoneNumber = i.value.toString()
                    }
                    USER_ID -> {
                        user_id = i.value.toString()
                    }
                    SECURITY -> {
                        security = i.value as MutableMap<String, Any>
                    }
                    INFORMATION -> {
                        information = i.value as MutableMap<String, Any>
                    }
                    POLYCY -> {
                        policy = i.value as MutableMap<String, Int>
                    }
                    BANK_ACCOUNT -> {
                        bankAccount = i.value as ArrayList<String>
                    }
                }
            }

            return UserModel(
                user_id,
                avatar,
                phoneNumber,
                name,
                information,
                security,
                policy,
                bankAccount
            )
        }

        fun getUserModelData(): HashMap<String, Any> {
            return hashMapOf(
                USER_ID to userModel.user_id,
                AVATAR to "",
                PHONE_NUMBER to userModel.phoneNumber,
                NAME to userModel.name,

                INFORMATION to hashMapOf(
                    ADDRESS to userModel.information[ADDRESS],
                    DOB to userModel.information[DOB],
                    GENDER to userModel.information[GENDER],
                    JOB to userModel.information[JOB],
                    RELATIONSHIP to userModel.information[RELATIONSHIP],
                    EMAIL to userModel.information[EMAIL],
                    NATIONAL to userModel.information[NATIONAL],
                    HABIT to userModel.information[HABIT],
                    EDUCATION to userModel.information[EDUCATION],
                    NICKNAME to userModel.information[NICKNAME],
                ),
                SECURITY to hashMapOf(
                    BALANCE to userModel.security[BALANCE],
                    CARD_NUMBER to userModel.security[CARD_NUMBER],
                    VERIFIED to userModel.security[VERIFIED],
                    PASSWORD to userModel.security[PASSWORD],
                    TYPE to userModel.security[TYPE],
                ),
                POLYCY to userModel.policy,
                BANK_ACCOUNT to userModel.bankAccount
            )
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