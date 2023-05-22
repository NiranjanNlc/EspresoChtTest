package org.lniranjan.chatwithespreso.ui.viewmodal

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import org.lniranjan.chatwithespreso.modal.Message

class ConversationViewModal {

    val _messageList = MutableLiveData<List<Message>>()
    val messageList: LiveData<List<Message>>
        get() = _messageList

    fun sendMessage(message: String) {
        val newMessage = Message(message = message)
        val currentMessages = _messageList.value.orEmpty().toMutableList()
        currentMessages.add(newMessage)
        _messageList.value = currentMessages
    }

    fun deleteAllMessages() {
    _messageList.postValue(emptyList())
    }
}
