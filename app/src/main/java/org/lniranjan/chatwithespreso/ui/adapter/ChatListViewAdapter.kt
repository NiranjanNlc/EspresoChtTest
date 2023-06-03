package org.lniranjan.chatwithespreso.ui.adapter

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import org.lniranjan.chatwithespreso.databinding.ConversationItemBinding
import org.lniranjan.chatwithespreso.modal.Conversation

/**
 * [RecyclerView.Adapter] that can display a [PlaceholderItem].
 * TODO: Replace the implementation with code for your data type.
 */


class ChatListAdapter constructor(val clickListener: OnChatClickListener ):
    ListAdapter<Conversation, ChatListAdapter.ChatListViewHolder>(COMPARATOR)
{

    interface OnChatClickListener
    {
        fun onClick(chats: Conversation)
    }

    class ChatListViewHolder(var items: ConversationItemBinding,private val clickListener:OnChatClickListener): RecyclerView.ViewHolder(items.root) {

        fun bind(chatItems: Conversation) {
            items.conversation = chatItems
            items.profile.setImageURI(chatItems.profilePic)

        }
        companion object {
            fun from(parent: ViewGroup, clickListener: OnChatClickListener): ChatListViewHolder {
                val inflater = LayoutInflater.from(parent.context)
                val binding = ConversationItemBinding.inflate(inflater)
                return ChatListViewHolder(binding, clickListener)
            }
        }
    }

    companion object COMPARATOR : DiffUtil.ItemCallback<Conversation>()
    {
        override fun areItemsTheSame(oldItem: Conversation, newItem: Conversation): Boolean
        {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Conversation, newItem: Conversation): Boolean {
            return oldItem.equals(newItem)
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatListViewHolder
    {
        println("On view create ")
//        val inflater = LayoutInflater.from(parent.context)
//        val binding = ChatItemsBinding.inflate(inflater)
        return ChatListViewHolder.from(parent,clickListener)

    }
    override fun onBindViewHolder(holder: ChatListViewHolder, position: Int)
    {
        val ChatItems = getItem(position)
        // println( " see thid " + ChatItems.profilePic.toString())
        holder.bind(ChatItems)
        holder.items.executePendingBindings()
    }
}