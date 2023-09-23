package com.example.talktou.message

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.talktou.R
import com.example.talktou.UserAdapter
import com.google.firebase.auth.FirebaseAuth

class MessageAdapter(val context: Context, val messageList: ArrayList<Message>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val ITEM_RECEIVE = 1
    private val ITEM_SENT = 2
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType==1){
            val view: View = LayoutInflater.from(context).inflate(R.layout.recieved,parent,false)
            RecieveViewHolder(view)
        }else{
            val view: View = LayoutInflater.from(context).inflate(R.layout.sent,parent,false)
            SentViewHolder(view)
        }
    }

    override fun getItemViewType(position: Int): Int {
        val currentMessage = messageList[position]
        return if(FirebaseAuth.getInstance().currentUser?.uid.equals(currentMessage.senderId)){
            ITEM_SENT
        }else{
            ITEM_RECEIVE
        }
    }
    override fun getItemCount(): Int {
        return messageList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val currentMessage = messageList[position]
        if (holder.javaClass == SentViewHolder::class.java) {
            val viewHolder = holder as SentViewHolder
            holder.sentMessage.text = currentMessage.message

        } else {
            val viewHolder = holder as RecieveViewHolder
            holder.recieveMessage.text = currentMessage.message
        }
    }

    class SentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val sentMessage: TextView = itemView.findViewById(R.id.sent_massage)
    }

    class RecieveViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val recieveMessage: TextView = itemView.findViewById(R.id.recieved_message)
    }
}