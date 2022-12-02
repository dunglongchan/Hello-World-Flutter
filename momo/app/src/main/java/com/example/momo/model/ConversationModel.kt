package com.example.momo.model

data class ConversationModel(
    val conversationID: String,
    val sender: String,
    val receiver: String,
    val content: HashMap<String, Any>
) {
}