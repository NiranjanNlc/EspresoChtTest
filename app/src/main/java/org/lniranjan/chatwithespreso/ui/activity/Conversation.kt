package org.lniranjan.chatwithespreso.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import org.lniranjan.chatwithespreso.R
import org.lniranjan.chatwithespreso.databinding.ActivityConversationBinding
import org.lniranjan.chatwithespreso.ui.adapter.ConversationAdapter
import org.lniranjan.chatwithespreso.ui.viewmodal.ConversationViewModal

class Conversation : AppCompatActivity() {

     lateinit var binding: ActivityConversationBinding
     lateinit var viewModal: ConversationViewModal
     lateinit var adapter: ConversationAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_conversation)
        binding.lifecycleOwner = this
        viewModal = ConversationViewModal() // Initialize your view model here

        adapter = ConversationAdapter() // Initialize your adapter here
        binding.messagesView.adapter = adapter
        binding.messagesView.layoutManager = LinearLayoutManager(this)

        binding.buttonSend.setOnClickListener {
            val message = binding.textInput.text.toString()
            if (message.isNotBlank()) {
                viewModal.sendMessage(message)
                binding.textInput.text?.clear()
                binding.textInput.clearFocus()
            }
        }

        // Observe the message list in the view model
        viewModal.messageList.observe(this) { it ->
            adapter.submitList(it)
            if (it.size!=0) binding.messagesView.smoothScrollToPosition(it.size - 1)
        }
    }
}