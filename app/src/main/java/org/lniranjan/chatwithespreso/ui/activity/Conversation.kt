package org.lniranjan.chatwithespreso.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import org.lniranjan.chatwithespreso.R
import org.lniranjan.chatwithespreso.databinding.ActivityConversationBinding

class Conversation : AppCompatActivity() {

    private lateinit var binding: ActivityConversationBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_conversation)
        binding.lifecycleOwner = this
        binding.executePendingBindings()
        binding.buttonSend.setOnClickListener {
            binding.recyclerView.adapter?.notifyDataSetChanged()
        }
    }
}