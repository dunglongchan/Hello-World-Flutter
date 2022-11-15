package com.example.momo.model

import java.util.*

data class ConversationModel(
    val conversationID: String,
    val sender: String,
    val receiver: String,
    val content: HashMap<String, Objects>
) {
}