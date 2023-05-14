package org.lniranjan.chatwithespreso.ui.adapter

import android.view.Gravity
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.lniranjan.chatwithespreso.databinding.MessageItemBinding
import org.lniranjan.chatwithespreso.modal.Message


class MessageAdapter(private val messageList: List<Message>, private val onClickListener: (Message) -> Unit) :
    RecyclerView.Adapter<MessageAdapter.MessageViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = MessageItemBinding.inflate(inflater, parent, false)
        return MessageViewHolder(binding)
    }
    override fun onBindViewHolder(holder: MessageViewHolder, position: Int) {
        val message = messageList[position]
        holder.bind(message)
    }
    override fun getItemCount(): Int = messageList.size
    inner class MessageViewHolder(private val binding: MessageItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(message: Message) {
            binding.message = message

            // Set different bubble settings for incoming and outgoing messages
            val layoutParams = binding.root.layoutParams as ViewGroup.MarginLayoutParams
            val gravity = if (message.sender.equals("ram")) Gravity.START else Gravity.END
            layoutParams.marginEnd = gravity
            binding.root.layoutParams = layoutParams

            binding.root.setOnClickListener {
                onClickListener.invoke(message)
            }
            binding.executePendingBindings()
        }
    }
}
