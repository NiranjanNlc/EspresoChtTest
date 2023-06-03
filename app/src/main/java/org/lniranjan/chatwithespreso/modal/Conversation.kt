package org.lniranjan.chatwithespreso.modal

import android.net.Uri

data class Conversation(
    val id: String,          // Unique identifier for the chat
    val contactName: String, // Name of the contact
    val lastMessage: String, // Last message in the chat
    val timestamp: Long  ,    // Timestamp of the last message
    val profilePic: Uri?     // Profile picture of the contact
) {
}
