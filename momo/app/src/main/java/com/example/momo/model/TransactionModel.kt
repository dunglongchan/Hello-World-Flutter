package com.example.momo.model

data class TransactionModel(
    val transactionID: String,
    val sender: String,
    val receiver: String,
    val date: String,
    val value: String
) {
}