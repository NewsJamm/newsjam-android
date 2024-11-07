package com.example.data.model

sealed class ChatMessage {
    data class AIMessage(val message: String) : ChatMessage()
    data class HumanMessage(val message: String) : ChatMessage()
    data class AppMessage(val message: String, val topMessage : Boolean = false) : ChatMessage()
}